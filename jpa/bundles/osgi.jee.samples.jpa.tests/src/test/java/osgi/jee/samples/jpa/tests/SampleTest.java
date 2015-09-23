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

import osgi.jee.samples.jpa.dao.impl.connection.JPADataConnection;
import osgi.jee.samples.jpa.model.Address;
import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.model.dao.EmployeeDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	/**
	 * Here we test lazyness of the "Employee -> Address" and "Employee -> Phone relations". After creating an employee,
	 * we get via the entity manager and then we close the entity manager to ensure it can't do any additional db query. When we
	 * try to get data from the address of the employee or one of his phone, a lazyness exception is throw.
	 */
	@Test
	public void testLaziness() throws ParseException {
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		dataConnection.beginTransaction();
		Sampler.persistEmployee(dataConnection, Sampler.createHenriMenard(employmentFactory));
		dataConnection.commit();
		((JPADataConnection)dataConnection).getEntityManager().clear();
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		Employee hmenard = employeeDAO.find(dataConnection, 1l);
		((JPADataConnection)dataConnection).getEntityManager().close();
		boolean lazinessExceptionEncountered = false;
		try {
			hmenard.getAddress().getCity();
		} catch (Exception e) {
			if (e.getClass().getName().contains("Lazy")) {
				lazinessExceptionEncountered = true;
			}
		}
		assertTrue("The Employee -> Address relationship is Eager",lazinessExceptionEncountered);

		lazinessExceptionEncountered = false;
		try {
			hmenard.getPhones().size();
		} catch (Exception e) {
			if (e.getClass().getName().contains("Lazy")) {
				lazinessExceptionEncountered = true;
			}
		}
		assertTrue("The Employee -> Phones relationship is Eager",lazinessExceptionEncountered);

		dataConnection = createDataConnection();
	}


	/**
	 * Here we test cascading of a relation. When creates an employee with an Address, persisting the employee
	 * will automatically persist his address (without any usage of Address DAO). The same behavior is expected 
	 * for the delete operation. 
	 */
	@Test
	public void testCascading() throws SQLException {
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		Employee doe = employmentFactory.createEmployee();
		doe.setFirstName("John");
		doe.setLastName("Doe");
		Address address = employmentFactory.createAddress();
		address.setCity("X");
		doe.setAddress(address );
		dataConnection.beginTransaction();
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		employeeDAO.create(dataConnection, doe);
		dataConnection.commit();
		PreparedStatement stmt = dataConnection.getSQLConnection().prepareStatement("Select City from ADDRESS");
		ResultSet executeQuery = stmt.executeQuery();
		executeQuery.next();
		assertEquals("ADDRESS entities not seem to be correctly persisted", "X", executeQuery.getString(1));
		
		dataConnection.beginTransaction();
		employeeDAO.delete(dataConnection, doe);
		dataConnection.commit();
		stmt = createDataConnection().getSQLConnection().prepareStatement("Select City from ADDRESS");
		executeQuery = stmt.executeQuery();
		assertFalse("ADDRESS entities not seem to be persisted", executeQuery.next());
		
	}
	
	/**
	 * Here we test the orphan entities removing. We create an employee with a phone and then, remove the phone from
	 * his owner list. When updating the employee, the phone is removed from the database.
	 */
	@Test
	public void testOrphanRemoval() throws SQLException, ParseException {
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		dataConnection.beginTransaction();
		Sampler.persistEmployee(dataConnection, Sampler.createHenriMenard(employmentFactory));
		dataConnection.commit();
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		Employee hmenard = employeeDAO.findByName(dataConnection, Sampler.HENRI_MENARD_LASTNAME);
		hmenard.getPhones().clear();
		dataConnection.beginTransaction();
		employeeDAO.update(dataConnection, hmenard);
		dataConnection.commit();
		PreparedStatement stmt = createDataConnection().getSQLConnection().prepareStatement("Select * from PHONE");
		ResultSet executeQuery = stmt.executeQuery();
		assertFalse("ADDRESS entities not seem to be persisted", executeQuery.next());		
	}
	
}