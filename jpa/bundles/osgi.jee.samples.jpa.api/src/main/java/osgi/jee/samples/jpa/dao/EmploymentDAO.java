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
	 * Initializes a connection to the data persistence system and returns a
	 * object contains all required data to use it.
	 * 
	 * @return a {@link DataConnection}.
	 */
	DataConnection getDataConnection();

	/**
	 * Initializes the format of the persisted data.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param dataFormat
	 *            an {@link InputStream} described the expected format.
	 * @throws Exception
	 *             an error occurred during data initiailization.
	 */
	void initDataFormat(DataConnection connection, InputStream dataFormat) throws Exception;

	/**
	 * Persists an adress.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param address
	 *            the {@link Address} to persist.
	 */
	void persistAddress(DataConnection connection, Address address);

	/**
	 * Persists a big project.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param bigProject
	 *            the {@link BigProject} to persist.
	 */
	void persistBigProject(DataConnection connection, BigProject bigProject);

	/**
	 * Persists an employee.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param employee
	 *            the {@link Employee} to persist.
	 */
	void persistEmployee(DataConnection connection, Employee employee);

	/**
	 * Persists an employment period.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param period
	 *            the {@link EmploymentPeriod} to persist.
	 */
	void persistEmploymentPeriod(DataConnection connection, EmploymentPeriod period);

	/**
	 * Persists a phone.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param phone
	 *            the {@link Phone} to persist.
	 */
	void persistPhone(DataConnection connection, Phone phone);

	/**
	 * Persists a project.
	 * 
	 * @param project
	 *            the {@link Project} to persist.
	 */
	void persistProject(DataConnection connection, Project project);

	/**
	 * Persists a small project.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param smallProject
	 *            the {@link SmallProject} to persist.
	 */
	void persistSmallProject(DataConnection connection, SmallProject smallProject);

}
