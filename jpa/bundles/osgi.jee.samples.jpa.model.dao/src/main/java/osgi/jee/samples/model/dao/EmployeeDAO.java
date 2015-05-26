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
package osgi.jee.samples.model.dao;

import osgi.jee.samples.jpa.dao.GenericDAO;
import osgi.jee.samples.jpa.dao.connection.DataConnection;
import osgi.jee.samples.jpa.model.Employee;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public interface EmployeeDAO extends GenericDAO<Employee> {
	
	/**
	 * Finds an employee by his last name
	 * @param lastname the last name of the searched employee.
	 * @param dataConnection the data connection to use.
	 * @return the {@link Employee} with the corresponding last name <code>null</code> otherwise.
	 */
	Employee findByName(DataConnection dataConnection, String lastname);

}
