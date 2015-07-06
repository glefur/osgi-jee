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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.jpa.util.db.meta.Table;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	@Test
	public void test() throws SQLException, ParseException {
		
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		Table employeeTable = dataConnection.getSchema().getTable("EMPLOYEE");
		assertNotNull("EMPLOYEE table should have a START_DATE column", employeeTable.getColumn("START_DATE"));
		assertNotNull("EMPLOYEE table should have a END_DATE column", employeeTable.getColumn("END_DATE"));
		Employee henriMenard = Sampler.createHenriMenard(employmentFactory);
		dataConnection.beginTransaction();
		Sampler.persistEmployee(dataConnection, henriMenard);
		dataConnection.commit();
		PreparedStatement stmt = dataConnection.getSQLConnection().prepareStatement("SELECT START_DATE FROM EMPLOYEE WHERE LASTNAME='" + Sampler.HENRI_MENARD_LASTNAME + "'");
		ResultSet result = stmt.executeQuery();
		assertEquals("Too many results", 1, result.getFetchSize());
		result.next();
		Date persistedDate = new Date(result.getDate("START_DATE").getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date expectedDate = sdf.parse(Sampler.HENRI_MENARD_START_DATE);
		assertEquals("Dates are not the same", expectedDate, persistedDate);
		
	}
}
