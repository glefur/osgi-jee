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


/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class EmploymentFactory {

	/**
	 * Creates a new address.
	 * @return the created {@link Address}.
	 */
	public Address createAddress() {
		return new Address();
	}

	/**
	 * Creates a new big project.
	 * @return the created {@link BigProject}.
	 */
	public BigProject createBigProject() {
		return new BigProject();
	}
	
	/**
	 * Creates a new company.
	 * @return the created {@link Company}.
	 */
	public Company createCompany() {
		return new Company();
	}
	
	/**
	 * Creates a new department.
	 * @return the created {@link Department}.
	 */
	public Department createDepartment() {
		return new Department();
	}

	/**
	 * Creates a new employee
	 * @return the created {@link Employee}.
	 */
	public Employee createEmployee() {
		return new Employee();
	}

	/**
	 * Creates a new employment period.
	 * @return the created {@link Period}.
	 */
	public Period createPeriod() {
		return new Period();
	}

	/**
	 * Creates a new phone.
	 * @return the created {@link Phone}.
	 */
	public Phone createPhone() {
		return new Phone();
	}
	
	/**
	 * Creates a new fax.
	 * @return the created {@link FAX}
	 */
	public FAX createFAX() {
		return new FAX();
	}

	/**
	 * Creates a new small project.
	 * @return the created {@link SmallProject}.
	 */
	public SmallProject createSmallProject() {
		return new SmallProject();
	}
	
	/**
	 * Creates a new User.
	 * @return the created {@link User}.
	 */
	public User createUser() {
		return new User();
	}

}
