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

import osgi.jee.samples.jpa.model.IAddress;
import osgi.jee.samples.jpa.model.IBigProject;
import osgi.jee.samples.jpa.model.IEmployee;
import osgi.jee.samples.jpa.model.IEmploymentPeriod;
import osgi.jee.samples.jpa.model.IPhone;
import osgi.jee.samples.jpa.model.IProject;
import osgi.jee.samples.jpa.model.ISmallProject;

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
	 *            the {@link IAddress} to persist.
	 */
	void persistAddress(DataConnection connection, IAddress address);

	/**
	 * Persists a big project.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param bigProject
	 *            the {@link IBigProject} to persist.
	 */
	void persistBigProject(DataConnection connection, IBigProject bigProject);

	/**
	 * Persists an employee.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param employee
	 *            the {@link IEmployee} to persist.
	 */
	void persistEmployee(DataConnection connection, IEmployee employee);

	/**
	 * Persists an employment period.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param period
	 *            the {@link IEmploymentPeriod} to persist.
	 */
	void persistEmploymentPeriod(DataConnection connection, IEmploymentPeriod period);

	/**
	 * Persists a phone.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param phone
	 *            the {@link IPhone} to persist.
	 */
	void persistPhone(DataConnection connection, IPhone phone);

	/**
	 * Persists a project.
	 * 
	 * @param project
	 *            the {@link IProject} to persist.
	 */
	void persistProject(DataConnection connection, IProject project);

	/**
	 * Persists a small project.
	 * 
	 * @param connection
	 *            the {@link DataConnection} to use.
	 * @param smallProject
	 *            the {@link ISmallProject} to persist.
	 */
	void persistSmallProject(DataConnection connection, ISmallProject smallProject);

}
