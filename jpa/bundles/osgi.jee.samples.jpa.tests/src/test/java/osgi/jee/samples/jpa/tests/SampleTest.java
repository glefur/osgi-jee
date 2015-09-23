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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Test;

import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.jpa.util.db.meta.Table;
import osgi.jee.samples.model.dao.EmployeeDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	/**
	 * Here we only check the db schema and the good id serialization for a map attribute.
	 */
	@Test
	public void test() throws ParseException, SQLException {
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		dataConnection.beginTransaction();
		Employee menard = Sampler.createHenriMenard(employmentFactory);
		Sampler.persistEmployee(dataConnection, menard);
		Sampler.persistEmployee(dataConnection, Sampler.createCorinneParizeau(employmentFactory, menard));
		dataConnection.commit();
		
		Table table = dataConnection.getSchema().getTable("PHONE");
		assertNotNull("Bad schema generation", table.getColumn("OWNER_EMPLOYEEID"));
		
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		Employee hmenard = employeeDAO.findByName(dataConnection, Sampler.HENRI_MENARD_LASTNAME);
		String sql = "SELECT * FROM PHONE"
				+ " WHERE OWNER_EMPLOYEEID=" + hmenard.getEmployeeId();
		System.out.println(sql);
		PreparedStatement stmt = dataConnection.getSQLConnection().prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		assertTrue("Bad phone's owner registration", result.next());
		
		
	}
	
}