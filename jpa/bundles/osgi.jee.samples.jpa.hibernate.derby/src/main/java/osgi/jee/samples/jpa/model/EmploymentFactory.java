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
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.EmploymentFactory#createAddress()
	 */
	public Address createAddress() {
		return new Address();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.EmploymentFactory#createBigProject()
	 */
	public BigProject createBigProject() {
		return new BigProject();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.EmploymentFactory#createEmployee()
	 */
	public Employee createEmployee() {
		return new Employee();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.EmploymentFactory#createEmploymentPeriod()
	 */
	public EmploymentPeriod createEmploymentPeriod() {
		return new EmploymentPeriod();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.EmploymentFactory#createPhone()
	 */
	public Phone createPhone() {
		return new Phone();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.EmploymentFactory#createProject()
	 */
	public Project createProject() {
		return new Project();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.model.EmploymentFactory#createSmallProject()
	 */
	public SmallProject createSmallProject() {
		return new SmallProject();
	}

}
