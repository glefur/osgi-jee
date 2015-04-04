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
public interface EmploymentFactory {
	
	/**
	 * Creates a new address.
	 * @return the created {@link IAddress}.
	 */
	IAddress createAddress();

	/**
	 * Creates a new big project.
	 * @return the created {@link IBigProject}.
	 */
	IBigProject createBigProject();
	
	/**
	 * Creates a new employee.
	 * @return the created {@link IEmployee}.
	 */
	IEmployee createEmployee();
	
	/**
	 * Creates a new employment period.
	 * @return the created {@link IEmploymentPeriod}.
	 */
	IEmploymentPeriod createEmploymentPeriod();
	
	/**
	 * Creates a new phone.
	 * @return the created {@link IPhone}.
	 */
	IPhone createPhone();
	
	/**
	 * Creates a new project.
	 * @return the created {@link IProject}.
	 */
	IProject createProject();
	
	/**
	 * Creates a new small project.
	 * @return the created {@link ISmallProject}.
	 */
	ISmallProject createSmallProject();
	
}
