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
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

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
	public void test() {

		try {
			EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
			Employee henriMenard = Sampler.createHenriMenard(employmentFactory);
			henriMenard.setYearOfService(11);
			Employee corinneParizeau = Sampler.createCorinneParizeau(employmentFactory, henriMenard);
			corinneParizeau.setYearOfService(8);

			dataConnection.beginTransaction();
			Sampler.persistEmployee(dataConnection, henriMenard);
			Sampler.persistEmployee(dataConnection, corinneParizeau);
			dataConnection.commit();
			Statement statement = dataConnection.getSQLConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM EMP_DATA");
			Map<Long, Object[]> results = new HashMap<Long, Object[]>(2);
			while (resultSet.next()) {
				Long emp_id = resultSet.getLong("EMP_ID");
				String mgr_id = resultSet.getString("MGR_ID");
				int yos = resultSet.getInt("YEAR_OF_SERV");
				results.put(emp_id, new Object[] {mgr_id, yos});
			}
			assertEquals("Bad result count", 2, results.size());
			EmployeeDAO employeeDAO = TestsActivator.getInstance().getService(EmployeeDAO.class);
			Employee foundHenriMenard = employeeDAO.findByName(dataConnection, Sampler.HENRI_MENARD_LASTNAME);
			Object[] henriMenardData = results.get(foundHenriMenard.getId());
			assertNotNull("Unable to found Henri Ménard data", henriMenardData);
			assertNull("Bad manager definition for Henri Ménard", henriMenardData[0]);
			assertEquals("Bad Year of Service definition for Henri Ménard", henriMenard.getYearOfService(), henriMenardData[1]);
			Employee foundCorinneParizeau = employeeDAO.findByName(dataConnection, Sampler.CORINNE_PARIZEAU_LASTNAME);
			Object[] corinneParizeauData = results.get(foundCorinneParizeau.getId());
			assertNotNull("Unable to found Corinne Parizeau data", corinneParizeauData);
			assertEquals("Bad manager definition for Corinne Parizeau", foundHenriMenard.getId(), Long.parseLong((String) corinneParizeauData[0])); 
			assertEquals("Bad Year of Service definition for Corinne Parizeau", corinneParizeau.getYearOfService(), corinneParizeauData[1]);
			
			
		} catch (ParseException e) {
			fail("Unable to create an employee");
		} catch (SQLException e) {
			fail("Unable to perform SQL query");
		}
	}
}
