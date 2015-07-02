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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.model.Phone;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.model.dao.EmployeeDAO;
import osgi.jee.samples.model.dao.PhoneDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	@Test
	public void test() throws SQLException {

		// Creating and persisting example company
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		Employee henriMenard = employmentFactory.createEmployee();
		henriMenard.setFirstName(Sampler.HENRI_MENARD_FIRSTNAME);
		henriMenard.setLastName(Sampler.HENRI_MENARD_LASTNAME);
		
		dataConnection.beginTransaction();
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		employeeDAO.create(dataConnection, henriMenard);
		dataConnection.commit();
		
		Phone hmPhone = employmentFactory.createPhone();
		hmPhone.setType("PRO");
		hmPhone.setNumber("000");
		hmPhone.setOwner(henriMenard);
		
		dataConnection.beginTransaction();
		PhoneDAO phoneDAO = TestsActivator.getInstance().getService(PhoneDAO.class);
		phoneDAO.create(dataConnection, hmPhone);
		dataConnection.commit();
		
		PreparedStatement stmt = dataConnection.getSQLConnection().prepareStatement("SELECT EMPLOYEEID FROM EMPLOYEE");
		stmt.execute();
		ResultSet resultSet = stmt.getResultSet();
		assertEquals("", 1, resultSet.getFetchSize());
		resultSet.next();
		long generatedEmployeeID = resultSet.getLong("EMPLOYEEID");
		
		PreparedStatement stmt2 = dataConnection.getSQLConnection().prepareStatement("SELECT OWNER_ID FROM PHONE");
		stmt2.execute();
		ResultSet resultSet2 = stmt2.getResultSet();
		assertEquals("", 1, resultSet2.getFetchSize());
		resultSet2.next();
		long linkedEmployeeID = resultSet2.getLong("OWNER_ID");
		assertEquals("Bad foreign key serialization for phones", generatedEmployeeID, linkedEmployeeID);
	}
}
