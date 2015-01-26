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
package osgi.jee.samples.jpa.dao;

import java.io.InputStream;

import osgi.jee.samples.jpa.model.Address;
import osgi.jee.samples.jpa.model.BigProject;
import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentPeriod;
import osgi.jee.samples.jpa.model.Phone;
import osgi.jee.samples.jpa.model.Project;
import osgi.jee.samples.jpa.model.SmallProject;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public interface EmploymentDAO {
	
	/**
	 * Initializes the format of the persisted data.
	 * @param dataFormat an {@link InputStream} described the expected format.
	 */
	void initDataFormat(InputStream dataFormat);
	
	/**
	 * Persists an adress.
	 * @param address the {@link Address} to persist.
	 */
	void persistAddress(Address address);
	
	/**
	 * Persists a big project.
	 * @param bigProject the {@link BigProject} to persist.
	 */
	void persistBigProject(BigProject bigProject);
	
	/**
	 * Persists an employee.
	 * @param employee the {@link Employee} to persist.
	 */
	void persistEmployee(Employee employee);
	
	/**
	 * Persists an employment period.
	 * @param period the {@link EmploymentPeriod} to persist.
	 */
	void persistEmploymentPeriod(EmploymentPeriod period);
	
	/**
	 * Persists a phone.
	 * @param phone the {@link Phone} to persist.
	 */
	void persistPhone(Phone phone);
	
	/**
	 * Persists a project.
	 * @param project the {@link Project} to persist.
	 */
	void persistProject(Project project);
	
	/**
	 * Persists a small project.
	 * @param smallProject the {@link SmallProject} to persist.
	 */
	void persistSmallProject(SmallProject smallProject);

}
