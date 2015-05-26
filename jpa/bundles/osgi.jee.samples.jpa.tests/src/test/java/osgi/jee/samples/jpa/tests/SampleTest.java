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

import org.junit.Test;

import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.TestConstants;
import osgi.jee.samples.model.dao.EmployeeDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class SampleTest extends AbstractTest {

	/**
	 * 
	 */
	private static final String NEW_NAME = "Michon";

	@Test
	public void test() {
		EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
		Employee parizeau = employeeDAO.findByName(dataConnection, TestConstants.PARIZEAU_LASTNAME);
		assertNotNull(parizeau);
		parizeau.setLastName(NEW_NAME);
		assertEquals(parizeau.getRealName(), NEW_NAME);
		assertEquals(parizeau.getLastName(), Employee.FAKE_NAME);
		dataConnection.beginTransaction();
		employeeDAO.update(dataConnection, parizeau);
		assertNull(employeeDAO.findByName(dataConnection, NEW_NAME));
		assertNotNull(employeeDAO.findByName(dataConnection, Employee.FAKE_NAME));
	}
}
