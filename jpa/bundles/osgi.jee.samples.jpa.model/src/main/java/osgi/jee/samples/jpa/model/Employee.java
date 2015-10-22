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

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long employeeId;
	@Version
	private long version;
	@Basic
	private Calendar lastUpdated;

	@Column(name="F_NAME", length=100)
	private String firstName;
	@Column(name="L_NAME", length=200)
	private String lastName;
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Temporal(TemporalType.DATE)
	private Calendar birthDate;

	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] picture;

	@Column(name="SALARY", scale=10)
	private BigDecimal salary;
	
	@OneToMany(mappedBy="owner", orphanRemoval=true, cascade={CascadeType.ALL})
	@MapKeyJoinColumn(name="PHONE_TYPE")
	private Map<String, Phone> phones;
	
	@ElementCollection
	@CollectionTable(
			name="FAXES",
			joinColumns=@JoinColumn(name="OWNER_ID")
	)
	private List<FAX> faxes;
	
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="ADDR_ID")
	private Address address;
	@OneToOne
	private Employee manager;
	@OneToMany
	private Set<Employee> managedEmployees;
	@ManyToMany
	private Set<Project> projects;
	@Embedded
	private Period employmentPeriod;

	/**
	 * @return the employeeId
	 */
	public long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @return the version
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * @return the lastUpdated
	 */
	public Calendar getLastUpdated() {
		return lastUpdated;
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
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthDate
	 */
	public Calendar getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the picture
	 * @throws IOException 
	 */
	public Image getPicture() throws IOException {
		InputStream in = new ByteArrayInputStream(picture);
		return ImageIO.read(in);
	}

	/**
	 * @param picture the picture to set
	 * @throws IOException 
	 */
	public void setPicture(Image picture) throws IOException {
		if (picture instanceof BufferedImage) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write((BufferedImage)picture, "JPG", out);
			this.picture = out.toByteArray();	
		}
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
	public Collection<Phone> getPhones() {
		return phones.values();
	}

	/**
	 * Adds a new phone to the phones list.
	 * @param type type of the added phone.
	 * @param phone the phone to add.
	 */
	public void addPhone(String type, Phone phone) {
		if (this.phones == null) {
			phones = new HashMap<String, Phone>();
		}
		phone.setOwner(this);
		phones.put(type, phone);
	}

	/**
	 * Removes a phone from the phones list.
	 * @param phone the phone to remove.
	 */
	public void deletePhone(Phone phone) {
		if (phones != null) {
			for (Entry<String, Phone> entry : phones.entrySet()) {
				if (phone.equals(entry.getValue())) {
					phones.remove(entry.getKey());					
				}
			}
		}
	}

	/**
	 * @return the faxes
	 */
	public List<FAX> getFaxes() {
		return faxes;
	}

	/**
	 * Adds a new fax
	 * @param fax the fax to add.
	 */
	public void addFax(FAX fax) {
		if (faxes == null) {
			faxes = new ArrayList<FAX>(); 
		}
		faxes.add(fax);
	}
	
	/**
	 * Removes a fax.
	 * @param fax the fax to remove.
	 */
	public void removeFax(FAX fax) {
		if (faxes != null) {
			faxes.remove(fax);
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
		address.setOwner(this);
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
	public Period getEmploymentPeriod() {
		return employmentPeriod;
	}

	/**
	 * @param employmentPeriod
	 *            the employmentPeriod to set
	 */
	public void setEmploymentPeriod(Period employmentPeriod) {
		this.employmentPeriod = employmentPeriod;
	}
	
	@PrePersist
	public void prePersist() {
		this.lastUpdated = Calendar.getInstance();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.lastUpdated = Calendar.getInstance();
	}

}
