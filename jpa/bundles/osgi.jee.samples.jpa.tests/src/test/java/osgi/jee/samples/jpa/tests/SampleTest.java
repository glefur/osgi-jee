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

import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Test;

import osgi.jee.samples.jpa.dao.connection.DataConnection;
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

	@Test
	public void test() throws SQLException, ParseException {
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		Employee henriMenard = Sampler.createHenriMenard(employmentFactory);
		dataConnection.beginTransaction();
		Sampler.persistEmployee(dataConnection, henriMenard);
		dataConnection.commit();
		
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		Employee hmenard = employeeDAO.findByName(dataConnection, Sampler.HENRI_MENARD_LASTNAME);
		
		dataConnection.beginTransaction();
		hmenard.setFirstName(Sampler.HENRI_MENARD_FIRSTNAME);
		DataConnection dataConnection2 = createDataConnection();
		dataConnection2.beginTransaction();
		hmenard.setFirstName("Michel");
		dataConnection2.commit();
		dataConnection.commit();
		Employee hmenard2 = employeeDAO.findByName(dataConnection2, Sampler.HENRI_MENARD_LASTNAME);
		assertNotEquals("Locking seems to be naturally handle by the JPA provider", hmenard2.getFirstName(), Sampler.HENRI_MENARD_FIRSTNAME);
	}

	@Test
	public void test2() throws SQLException, ParseException {
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		Employee henriMenard = Sampler.createHenriMenard(employmentFactory);
		dataConnection.beginTransaction();
		Sampler.persistEmployee(dataConnection, henriMenard);
		dataConnection.commit();
		
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		Employee hmenard = employeeDAO.findByName(dataConnection, Sampler.HENRI_MENARD_LASTNAME);
		
		dataConnection.beginTransaction();
		hmenard.setFirstName(Sampler.HENRI_MENARD_FIRSTNAME);
		DataConnection dataConnection2 = createDataConnection();
		dataConnection2.beginTransaction();
		hmenard.setFirstName("Michel");
		dataConnection.commit();
		dataConnection2.commit();
		Employee hmenard2 = employeeDAO.findByName(dataConnection2, Sampler.HENRI_MENARD_LASTNAME);
		assertNotEquals("Locking seems to be naturally handle by the JPA provider", hmenard2.getFirstName(), Sampler.HENRI_MENARD_FIRSTNAME);
	}

}
