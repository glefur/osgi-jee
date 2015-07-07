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
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.model.Period;
import osgi.jee.samples.jpa.model.User;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.jpa.util.db.meta.Table;
import osgi.jee.samples.model.dao.EmployeeDAO;
import osgi.jee.samples.model.dao.UserDAO;

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
		Table userTable = dataConnection.getSchema().getTable("USERS");
		assertNotNull("USERS table should have a VALID_SDATE column", userTable.getColumn("VALID_SDATE"));
		assertNotNull("USERS table should have a VALID_EDATE column", userTable.getColumn("VALID_EDATE"));
		Employee employee = employmentFactory.createEmployee();
		employee.setFirstName(Sampler.HENRI_MENARD_FIRSTNAME);
		employee.setLastName(Sampler.HENRI_MENARD_LASTNAME);
		User hmenard = employmentFactory.createUser();
		hmenard.setUsername("hmenard");
		Period period = employmentFactory.createPeriod();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		Date hmenardStartDate = sdf.parse(Sampler.HENRI_MENARD_START_DATE);
		period.setStartDate(hmenardStartDate);
		employee.setEmploymentPeriod(period);
		hmenard.setValidityPeriod(period);
		dataConnection.beginTransaction();
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		employeeDAO.create(dataConnection, employee);
		UserDAO userDAO = TestsActivator.getInstance().getService(UserDAO.class);
		userDAO.create(dataConnection, hmenard);
		dataConnection.commit();
		
		PreparedStatement stmt = dataConnection.getSQLConnection().prepareStatement("SELECT VALID_SDATE FROM USERS");
		ResultSet result = stmt.executeQuery();
		assertEquals("Bad USERS table states", 1, result.getFetchSize());
		result.next();
		java.sql.Date validityStartDate = result.getDate("VALID_SDATE");
		assertEquals("Bad Validity date persistence", hmenardStartDate.getTime(), validityStartDate.getTime());
		
		Employee foundHMenard = employeeDAO.findByName(dataConnection, Sampler.HENRI_MENARD_LASTNAME);
		User foundUser = userDAO.findByUsername(dataConnection, "hmenard");
		// Despite what is described in the book, this JPA provider is able to share the object instance between object.
		assertTrue("The two periods should be the same object instance", foundHMenard.getEmploymentPeriod() == foundUser.getValidityPeriod());
		
	}
}
