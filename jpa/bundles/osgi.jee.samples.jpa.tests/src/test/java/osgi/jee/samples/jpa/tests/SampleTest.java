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
import java.text.ParseException;

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
	public void testConflictHandling() throws SQLException, ParseException {
		EmploymentFactory employmentFactory = TestsActivator.getInstance().getService(EmploymentFactory.class);
		Employee henriMenard = Sampler.createHenriMenard(employmentFactory);
		Employee corinneParizeau = Sampler.createCorinneParizeau(employmentFactory, henriMenard);
		dataConnection.beginTransaction();
		Sampler.persistEmployee(dataConnection, henriMenard);
		Sampler.persistEmployee(dataConnection, corinneParizeau);
		dataConnection.commit();
		Table table = dataConnection.getSchema().getTable("EMPLOYEE");
		assertNotNull("Unable to retrieve Employee table", table);
		assertNotNull("Column directive for Firstname hasn't be applied", table.getColumn("F_NAME"));
		assertEquals("Column directive for Firstname hasn't be applied", 100, table.getColumn("F_NAME").getLength());
		assertNotNull("Column directive for Lastname hasn't be applied", table.getColumn("L_NAME"));
		assertEquals("Column directive for Lastname hasn't be applied", 200, table.getColumn("L_NAME").getLength());
		assertEquals("Column directive for Gender hasn't be applied", "VARCHAR", table.getColumn("GENDER").getType());
		ResultSet result = dataConnection.getSQLConnection().prepareStatement("SELECT GENDER FROM EMPLOYEE").executeQuery();
		assertTrue("Bad Gender request", result.next());
		assertEquals("Column directive for Gender hasn't be applied", "MALE", result.getString(1));
		assertTrue("Bad Gender request", result.next());
		assertEquals("Column directive for Gender hasn't be applied", "FEMALE", result.getString(1));
		
		assertEquals("Column directive for Birthdate hasn't be applied", "DATE", table.getColumn("BIRTHDATE").getType());
		
		
	}
	
}
