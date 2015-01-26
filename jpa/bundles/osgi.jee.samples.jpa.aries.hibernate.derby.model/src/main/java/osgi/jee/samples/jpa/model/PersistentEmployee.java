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
public class PersistentEmployee implements Employee {

	private long id;
	private String firstName;
	private String lastName;
	private BigDecimal salary;
	private List<Phone> phones;
	private Address address;
	private Employee manager;
	private Collection<Employee> managedEmployees;
	private Collection<Project> projects;
	private EmploymentPeriod employmentPeriod;

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#getId()
	 */
	public long getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#setId(long)
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#getLastName()
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#getSalary()
	 */
	public BigDecimal getSalary() {
		return salary;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#setSalary(java.math.BigDecimal)
	 */
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#getPhones()
	 */
	public List<Phone> getPhones() {
		return phones;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#setPhones(java.util.List)
	 */
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#getAddress()
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#setAddress(osgi.jee.samples.jpa.model.Address)
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the manager
	 */
	public Employee getManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(Employee manager) {
		this.manager = manager;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#getManagedEmployees()
	 */
	public Collection<Employee> getManagedEmployees() {
		return managedEmployees;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#setManagedEmployees(java.util.Collection)
	 */
	public void setManagedEmployees(Collection<Employee> managedEmployees) {
		this.managedEmployees = managedEmployees;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#getProjects()
	 */
	public Collection<Project> getProjects() {
		return projects;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.Employee#setProjects(java.util.Collection)
	 */
	public void setProjects(Collection<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @return the employmentPeriod
	 */
	public EmploymentPeriod getEmploymentPeriod() {
		return employmentPeriod;
	}

	/**
	 * @param employmentPeriod
	 *            the employmentPeriod to set
	 */
	public void setEmploymentPeriod(EmploymentPeriod employmentPeriod) {
		this.employmentPeriod = employmentPeriod;
	}

}
