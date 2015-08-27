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
import java.util.Collection;

import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

import org.junit.Test;

import osgi.jee.samples.jpa.dao.connection.DataConnection;
import osgi.jee.samples.jpa.model.Company;
import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.model.dao.CompanyDAO;
import osgi.jee.samples.model.dao.EmployeeDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	/**
	 * Here we simulate a conflict on a versionned entity. A optimistic lock exception is thrown.
	 */
	@Test
	public void testConflictHandling() throws SQLException, ParseException {
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		Employee henriMenard = Sampler.createHenriMenard(employmentFactory);
		dataConnection.beginTransaction();
		Sampler.persistEmployee(dataConnection, henriMenard);
		dataConnection.commit();
		
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		Employee hmenard = employeeDAO.findByName(dataConnection, Sampler.HENRI_MENARD_LASTNAME);
		DataConnection dataConnection2 = createDataConnection();
		Employee hmenard2 = employeeDAO.findByName(dataConnection2, Sampler.HENRI_MENARD_LASTNAME);
		
		dataConnection.beginTransaction();
		dataConnection2.beginTransaction();
		
		hmenard.setFirstName("Joel");
		hmenard2.setFirstName("Michel");
		employeeDAO.update(dataConnection2, hmenard2);
		employeeDAO.update(dataConnection, hmenard);
		
		boolean conflictHandle = false;
		
		try {
			dataConnection.commit();
			dataConnection2.commit();
		} catch (RollbackException e) {
			conflictHandle = e.getCause() instanceof OptimisticLockException;
		}
		
		assertTrue("A transaction conflict haven't been properly handle", conflictHandle);
	}
	
	/**
	 * Here we simulate a conflict on a non-versionned entity. We reach an inconsistent state : the two data connections have a different 
	 * version of the same entity.  
	 */
	@Test
	public void testConflictNonVersionned() {
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		Company company = employmentFactory.createCompany();
		company.setName("CompanyName");
		CompanyDAO companyDAO = TestsActivator.getInstance().getService(CompanyDAO.class);
		
		dataConnection.beginTransaction();
		companyDAO.create(dataConnection, company);
		dataConnection.commit();

		Collection<Company> allCompanies = companyDAO.findAll(dataConnection);
		Company foundCompany = allCompanies.iterator().next();
		DataConnection dataConnection2 = createDataConnection();
		Collection<Company> allCompanies2 = companyDAO.findAll(dataConnection2);
		Company foundCompany2 = allCompanies2.iterator().next();
		
		dataConnection.beginTransaction();
		dataConnection2.beginTransaction();
		foundCompany.setName("Name1");
		foundCompany2.setName("Name2");
		companyDAO.update(dataConnection, foundCompany);
		companyDAO.update(dataConnection2, foundCompany2);
		
		dataConnection.commit();
		dataConnection2.commit();
		
		allCompanies = companyDAO.findAll(dataConnection);
		allCompanies2 = companyDAO.findAll(dataConnection2);
		
		assertNotEquals("An excepted unstable state hasn't been reached", allCompanies.iterator().next().getName(), allCompanies2.iterator().next().getName());
		
	}

}
