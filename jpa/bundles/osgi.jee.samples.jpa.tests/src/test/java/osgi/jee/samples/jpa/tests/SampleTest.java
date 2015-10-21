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
import java.util.List;

import org.junit.Test;

import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.model.FAX;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.model.dao.EmployeeDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	/**
	 * We test an ElementCollection for Embeddable elements.
	 */
	@Test
	public void test() throws ParseException, SQLException {
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		
		dataConnection.beginTransaction();
		Employee hmenard = Sampler.createHenriMenard(employmentFactory);
		FAX fax = employmentFactory.createFAX();
		fax.setAreaCode("+33");
		fax.setNumber("2-51-13-68-66");
		fax.setType("PRO");
		hmenard.addFax(fax);
		Sampler.persistEmployee(dataConnection, hmenard);
		dataConnection.commit();

		assertNotNull("Schema not correctly defined", dataConnection.getSchema().getTable("FAXES"));
		
		Employee henrimenard = employeeDAO.findByName(dataConnection, Sampler.HENRI_MENARD_LASTNAME);
		List<FAX> faxes = henrimenard.getFaxes();
		assertEquals("Bad fax serialization", 1, faxes.size());
		assertEquals("Bad fax serialization", "+33", faxes.get(0).getAreaCode());
		
		
	}
	
}