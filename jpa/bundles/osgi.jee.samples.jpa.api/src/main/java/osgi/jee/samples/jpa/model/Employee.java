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
package osgi.jee.samples.jpa.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public interface Employee {

	/**
	 * @return the id
	 */
	long getId();

	/**
	 * @param id
	 *            the id to set
	 */
	void setId(long id);

	/**
	 * @return the firstName
	 */
	String getFirstName();

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	void setFirstName(String firstName);

	/**
	 * @return the lastName
	 */
	String getLastName();

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	void setLastName(String lastName);

	/**
	 * @return the salary
	 */
	BigDecimal getSalary();

	/**
	 * @param salary
	 *            the salary to set
	 */
	void setSalary(BigDecimal salary);

	/**
	 * @return the phones
	 */
	List<Phone> getPhones();

	/**
	 * @param phones
	 *            the phones to set
	 */
	void setPhones(List<Phone> phones);

	/**
	 * @return the address
	 */
	Address getAddress();

	/**
	 * @param address
	 *            the address to set
	 */
	void setAddress(Address address);

	/**
	 * @return the manager
	 */
	Employee getManager();

	/**
	 * @param manager
	 *            the manager to set
	 */
	void setManager(Employee manager);

	/**
	 * @return the managedEmployees
	 */
	Collection<Employee> getManagedEmployees();

	/**
	 * @param managedEmployees
	 *            the managedEmployees to set
	 */
	void setManagedEmployees(Collection<Employee> managedEmployees);

	/**
	 * @return the projects
	 */
	Collection<Project> getProjects();

	/**
	 * @param projects
	 *            the projects to set
	 */
	void setProjects(Collection<Project> projects);

	/**
	 * @return the employmentPeriod
	 */
	EmploymentPeriod getEmploymentPeriod();

	/**
	 * @param employmentPeriod
	 *            the employmentPeriod to set
	 */
	void setEmploymentPeriod(EmploymentPeriod employmentPeriod);

}