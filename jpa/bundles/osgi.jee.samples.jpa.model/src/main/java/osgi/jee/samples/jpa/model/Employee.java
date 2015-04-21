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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
@Entity
public class Employee {

	@Id
	private long id;
	private String firstName;
	private String lastName;
	private BigDecimal salary;
	@OneToMany
	private List<Phone> phones;
	@OneToOne
	private Address address;
	@ManyToOne
	private Employee manager;
	@OneToMany
	private Collection<Employee> managedEmployees;
	@ManyToMany
	private Collection<Project> projects;
	private EmploymentPeriod employmentPeriod;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the salary
	 */
	public BigDecimal getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	/**
	 * @return the phones
	 */
	public List<Phone> getPhones() {
		return phones;
	}

	/**
	 * @param phones
	 *            the phones to set
	 */
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
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
	 * @return the managedEmployees
	 */
	public Collection<Employee> getManagedEmployees() {
		return managedEmployees;
	}

	/**
	 * @param managedEmployees
	 *            the managedEmployees to set
	 */
	public void setManagedEmployees(Collection<Employee> managedEmployees) {
		this.managedEmployees = managedEmployees;
	}

	/**
	 * @return the projects
	 */
	public Collection<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
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
