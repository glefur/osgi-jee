/**
 * OSGi/JEE Sample.
 * 
 * Copyright (C) 2014 Goulwen Le Fur
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package osgi.jee.samples.jpa.tests;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.junit.Test;

import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.jpa.util.db.DbService;
import osgi.jee.samples.jpa.util.db.meta.Column;
import osgi.jee.samples.jpa.util.db.meta.Schema;
import osgi.jee.samples.jpa.util.db.meta.Table;
import osgi.jee.samples.model.dao.EmployeeDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	@Test
	public void test() {

		try {
			EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
			Employee henriMenard = Sampler.createHenriMenard(employmentFactory);
			ResultSet rs = dataConnection.getSQLConnection().prepareStatement("values ( next value for PHN_SEQ )").executeQuery();
			rs.next();
			long nextPhoneIdSeq = rs.getLong(1);
			long nextPhoneId = (nextPhoneIdSeq + 1) * 50; 
			dataConnection.beginTransaction();
			Sampler.persistEmployee(dataConnection, henriMenard);
			dataConnection.commit();
			DbService dbService = TestsActivator.getInstance().getService(DbService.class);
			Schema schema = dbService.getSchema(dataConnection.getSQLConnection());
			Statement stmt = dataConnection.getSQLConnection().createStatement();
			ResultSet query = stmt.executeQuery("SELECT * FROM SEQUENCE_TABLE");
			long employeeLastId = -1;
			while (query.next()) {
				employeeLastId = query.getLong("SEQ_COUNT");
			}
			EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
			Employee foundHenriMenard = employeeDAO.findByName(dataConnection, Sampler.HENRI_MENARD_LASTNAME);
			// We have generated 1 id, so the value column should be set at 1
			assertEquals("Bad object id", foundHenriMenard.getId(), employeeLastId);
			Table employeeTable = schema.getTable("EMPLOYEE");
			Column employeeIDColumn = employeeTable.getColumn("ID");
			// a id generated with sequence strategy should not be mapped on auto-incremented column
			assertFalse("ID column of Employee Table shouldn't be auto-incremented", employeeIDColumn.isAutoIncrement());
			
			long id = foundHenriMenard.getPhones().get(0).getId();
			assertEquals("Phone IDs don't seem to be generated using a sequence", id, nextPhoneId);
			
			Table addressTable = schema.getTable("ADDRESS");
			Column addressIdColumn = addressTable.getColumn("ID");
			assertTrue("ID column of Address Table should be auto-incremented", addressIdColumn.isAutoIncrement());
			
		} catch (ParseException e) {
			fail("Unable to create an employee");
		} catch (SQLException e) {
			fail("Unable to perform SQL query " + e.getMessage());
		} 
	}
}
