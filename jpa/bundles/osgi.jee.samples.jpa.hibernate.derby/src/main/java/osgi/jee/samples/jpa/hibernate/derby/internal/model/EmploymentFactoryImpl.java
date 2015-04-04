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

import osgi.jee.samples.jpa.model.IAddress;
import osgi.jee.samples.jpa.model.IBigProject;
import osgi.jee.samples.jpa.model.IEmployee;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.jpa.model.IEmploymentPeriod;
import osgi.jee.samples.jpa.model.IPhone;
import osgi.jee.samples.jpa.model.IProject;
import osgi.jee.samples.jpa.model.ISmallProject;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class EmploymentFactoryImpl implements EmploymentFactory {

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.EmploymentFactory#createAddress()
	 */
	public IAddress createAddress() {
		return new Address();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.EmploymentFactory#createBigProject()
	 */
	public IBigProject createBigProject() {
		return new BigProject();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.EmploymentFactory#createEmployee()
	 */
	public IEmployee createEmployee() {
		return new osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.EmploymentFactory#createEmploymentPeriod()
	 */
	public IEmploymentPeriod createEmploymentPeriod() {
		return new EmploymentPeriod();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.EmploymentFactory#createPhone()
	 */
	public IPhone createPhone() {
		return new Phone();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.EmploymentFactory#createProject()
	 */
	public IProject createProject() {
		return new Project();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.model.EmploymentFactory#createSmallProject()
	 */
	public ISmallProject createSmallProject() {
		return new SmallProject();
	}

}
