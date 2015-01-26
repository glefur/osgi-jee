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
package osgi.jee.samples.jpa.dao;

import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import osgi.jee.samples.jpa.model.Address;
import osgi.jee.samples.jpa.model.BigProject;
import osgi.jee.samples.jpa.model.Employee;
import osgi.jee.samples.jpa.model.EmploymentPeriod;
import osgi.jee.samples.jpa.model.Phone;
import osgi.jee.samples.jpa.model.Project;
import osgi.jee.samples.jpa.model.SmallProject;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class HibernateDerbyEmploymentDAO implements EmploymentDAO {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	/**
	 * @param entityManagerFactory the entityManagerFactory to set
	 */
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public void activate() {
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.EmploymentDAO#initDataFormat(java.io.InputStream)
	 */
	public void initDataFormat(InputStream dataFormat) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.EmploymentDAO#persistAddress(osgi.jee.samples.jpa.model.Address)
	 */
	public void persistAddress(Address address) {
		entityManager.getTransaction().begin();
		entityManager.persist(address);
		entityManager.getTransaction().commit();

	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.EmploymentDAO#persistBigProject(osgi.jee.samples.jpa.model.BigProject)
	 */
	public void persistBigProject(BigProject bigProject) {
		entityManager.getTransaction().begin();
		entityManager.persist(bigProject);
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.EmploymentDAO#persistEmployee(osgi.jee.samples.jpa.model.Employee)
	 */
	public void persistEmployee(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.EmploymentDAO#persistEmploymentPeriod(osgi.jee.samples.jpa.model.EmploymentPeriod)
	 */
	public void persistEmploymentPeriod(EmploymentPeriod period) {
		entityManager.getTransaction().begin();
		entityManager.persist(period);
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.EmploymentDAO#persistPhone(osgi.jee.samples.jpa.model.Phone)
	 */
	public void persistPhone(Phone phone) {
		entityManager.getTransaction().begin();
		entityManager.persist(phone);
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.EmploymentDAO#persistProject(osgi.jee.samples.jpa.model.Project)
	 */
	public void persistProject(Project project) {
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.EmploymentDAO#persistSmallProject(osgi.jee.samples.jpa.model.SmallProject)
	 */
	public void persistSmallProject(SmallProject smallProject) {
		entityManager.getTransaction().begin();
		entityManager.persist(smallProject);
		entityManager.getTransaction().commit();
	}

}
