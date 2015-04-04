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
package osgi.jee.samples.jpa.hibernate.derby.internal.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import osgi.jee.samples.jpa.model.IAddress;
import osgi.jee.samples.jpa.model.IEmployee;
import osgi.jee.samples.jpa.model.IEmploymentPeriod;
import osgi.jee.samples.jpa.model.IPhone;
import osgi.jee.samples.jpa.model.IProject;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
@Entity
public class Employee implements IEmployee {

	@Id
	private long id;
	private String firstName;
	private String lastName;
	private BigDecimal salary;
	private List<IPhone> phones;
	private IAddress address;
	private IEmployee manager;
	private Collection<IEmployee> managedEmployees;
	private Collection<IProject> projects;
	private IEmploymentPeriod employmentPeriod;

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#getId()
	 */
	public long getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#setId(long)
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#getLastName()
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#getSalary()
	 */
	public BigDecimal getSalary() {
		return salary;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#setSalary(java.math.BigDecimal)
	 */
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#getPhones()
	 */
	public List<IPhone> getPhones() {
		return phones;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#setPhones(java.util.List)
	 */
	public void setPhones(List<IPhone> phones) {
		this.phones = phones;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#getAddress()
	 */
	public IAddress getAddress() {
		return address;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#setAddress(osgi.jee.samples.jpa.hibernate.derby.internal.model.IAddress)
	 */
	public void setAddress(IAddress address) {
		this.address = address;
	}

	/**
	 * @return the manager
	 */
	public osgi.jee.samples.jpa.model.IEmployee getManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(osgi.jee.samples.jpa.model.IEmployee manager) {
		this.manager = manager;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#getManagedEmployees()
	 */
	public Collection<osgi.jee.samples.jpa.model.IEmployee> getManagedEmployees() {
		return managedEmployees;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#setManagedEmployees(java.util.Collection)
	 */
	public void setManagedEmployees(Collection<osgi.jee.samples.jpa.model.IEmployee> managedEmployees) {
		this.managedEmployees = managedEmployees;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#getProjects()
	 */
	public Collection<IProject> getProjects() {
		return projects;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee#setProjects(java.util.Collection)
	 */
	public void setProjects(Collection<IProject> projects) {
		this.projects = projects;
	}

	/**
	 * @return the employmentPeriod
	 */
	public IEmploymentPeriod getEmploymentPeriod() {
		return employmentPeriod;
	}

	/**
	 * @param employmentPeriod
	 *            the employmentPeriod to set
	 */
	public void setEmploymentPeriod(IEmploymentPeriod employmentPeriod) {
		this.employmentPeriod = employmentPeriod;
	}

}
