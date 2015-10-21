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
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import org.junit.Test;

import osgi.jee.samples.jpa.model.BigProject;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.model.Project;
import osgi.jee.samples.jpa.tests.util.AbstractTest;
import osgi.jee.samples.jpa.tests.util.Sampler;
import osgi.jee.samples.model.dao.ProjectDAO;

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
		
		dataConnection.beginTransaction();
		BigProject helios = Sampler.createHeliosProject(employmentFactory);
		helios.addTopic("Software");
		helios.addTopic("Finance");
		Sampler.persistProject(dataConnection, helios);
		dataConnection.commit();

		assertNotNull("Schema not correctly defined", dataConnection.getSchema().getTable("TOPICS"));
		
		ProjectDAO projectDAO = TestsActivator.getInstance().getService(ProjectDAO.class);
		Collection<Project> allProjects = projectDAO.findAll(dataConnection);
		Project persistedHelios = allProjects.iterator().next();
		
		assertEquals("Bad fax serialization", 2, persistedHelios.getTopics().size());
		assertEquals("Bad fax serialization", "Software", persistedHelios.getTopics().get(0));
		
		
	}
	
}