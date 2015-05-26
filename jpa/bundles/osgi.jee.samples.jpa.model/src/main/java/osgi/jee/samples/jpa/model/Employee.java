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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Access(AccessType.FIELD)
public class Employee {
	
	public static final String FAKE_NAME = "Doe";
	

	@Id
	@GeneratedValue
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
	private Set<Employee> managedEmployees;
	@ManyToMany
	private Set<Project> projects;
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
	@Access(AccessType.PROPERTY)
	public String getLastName() {
		// returns a fake value
		return FAKE_NAME;
	}
	
	public String getRealName() {
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
	 * Adds a new phone to the phones list.
	 * @param phone the phone to add.
	 */
	public void addPhone(Phone phone) {
		if (this.phones == null) {
			phones = new ArrayList<Phone>();
		}
		phones.add(phone);
	}
	
	/**
	 * Removes a phone from the phones list.
	 * @param phone the phone to remove.
	 */
	public void deletePhone(Phone phone) {
		if (phones != null) {
			phones.remove(phone);
		}
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
		if (manager.managedEmployees == null || !manager.managedEmployees.contains(this)) {
			manager.addManagedEmployee(this);
		}
	}

	/**
	 * @return the managedEmployees
	 */
	public Set<Employee> getManagedEmployees() {
		return managedEmployees;
	}

	/**
	 * @param managedEmployees
	 *            the managedEmployees to set
	 */
	public void setManagedEmployees(Set<Employee> managedEmployees) {
		this.managedEmployees = managedEmployees;
	}
	
	/**
	 * Adds a new employee in the list of managed employees.
	 * @param employee the {@link Employee} to add.
	 */
	public void addManagedEmployee(Employee employee) {
		if (managedEmployees == null) {
			managedEmployees = new HashSet<Employee>();
		}
		managedEmployees.add(employee);
		if (employee.manager != this) {
			employee.manager = this;
		}
	}

	/**
	 * Removes a new employee from the list of managed employees.
	 * @param employee the {@link Employee} to remove.
	 */
	public void removeManagedEmployee(Employee employee) {
		if (managedEmployees != null) {
			managedEmployees.remove(employee);
			employee.manager = null;
		}
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
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	/**
	 * Adds a project to the employee's list of projects.
	 * @param project the project to add.
	 */
	public void addProject(Project project) {
		if (projects == null) {
			projects = new HashSet<Project>();
		}
		projects.add(project);
	}
	
	/**
	 * Removes a project from the employee's list of projects.
	 * @param project the project to remove.
	 */
	public void removeProject(Project project) {
		if (projects != null) {
			projects.remove(project);
		}
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
