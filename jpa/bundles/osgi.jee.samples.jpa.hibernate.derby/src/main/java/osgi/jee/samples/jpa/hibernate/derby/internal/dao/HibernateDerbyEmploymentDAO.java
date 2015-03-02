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
package osgi.jee.samples.jpa.hibernate.derby.internal.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

import osgi.jee.samples.jpa.dao.DataConnection;
import osgi.jee.samples.jpa.dao.EmploymentDAO;
import osgi.jee.samples.jpa.dao.impl.JPADataConnection;
import osgi.jee.samples.jpa.db.DataBaseHandler;
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
	private DataBaseHandler dbHandler;

	/**
	 * @param entityManagerFactory
	 *            the entityManagerFactory to set
	 */
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	/**
	 * @param handler the dbHandler to set
	 */
	public void setDataBaseHandler(DataBaseHandler handler) {
		this.dbHandler = handler;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.dao.EmploymentDAO#getDataConnection()
	 */
	public DataConnection getDataConnection() {
		return new JPADataConnection(entityManagerFactory.createEntityManager());
	}

	/**
	 * {@inheritDoc}
	 * @throws Exception 
	 * 
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.dao.EmploymentDAO#initDataFormat(osgi.jee.samples.jpa.hibernate.derby.internal.dao.DataConnection,
	 *      java.io.InputStream)
	 */
	public void initDataFormat(DataConnection connection, InputStream dataFormat) throws Exception {
		assert connection instanceof JPADataConnection : "Unable to perform persistence with this kind of connection";
		EntityManager entityManager = ((JPADataConnection) connection).getEntityManager();
		dbHandler.initSchema(extractConnection(entityManager), dataFormat);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.dao.EmploymentDAO#persistAddress(osgi.jee.samples.jpa.hibernate.derby.internal.dao.DataConnection,
	 *      osgi.jee.samples.jpa.hibernate.derby.internal.model.Address)
	 */
	public void persistAddress(DataConnection connection, Address address) {
		assert connection instanceof JPADataConnection : "Unable to perform persistence with this kind of connection";
		EntityManager entityManager = ((JPADataConnection) connection).getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(address);
		entityManager.getTransaction().commit();

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.dao.EmploymentDAO#persistBigProject(osgi.jee.samples.jpa.hibernate.derby.internal.dao.DataConnection,
	 *      osgi.jee.samples.jpa.hibernate.derby.internal.model.BigProject)
	 */
	public void persistBigProject(DataConnection connection, BigProject bigProject) {
		assert connection instanceof JPADataConnection : "Unable to perform persistence with this kind of connection";
		EntityManager entityManager = ((JPADataConnection) connection).getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(bigProject);
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.dao.EmploymentDAO#persistEmployee(osgi.jee.samples.jpa.hibernate.derby.internal.dao.DataConnection,
	 *      osgi.jee.samples.jpa.hibernate.derby.internal.model.Employee)
	 */
	public void persistEmployee(DataConnection connection, Employee employee) {
		assert connection instanceof JPADataConnection : "Unable to perform persistence with this kind of connection";
		EntityManager entityManager = ((JPADataConnection) connection).getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.dao.EmploymentDAO#persistEmploymentPeriod(osgi.jee.samples.jpa.hibernate.derby.internal.dao.DataConnection,
	 *      osgi.jee.samples.jpa.hibernate.derby.internal.model.EmploymentPeriod)
	 */
	public void persistEmploymentPeriod(DataConnection connection, EmploymentPeriod period) {
		assert connection instanceof JPADataConnection : "Unable to perform persistence with this kind of connection";
		EntityManager entityManager = ((JPADataConnection) connection).getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(period);
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.dao.EmploymentDAO#persistPhone(osgi.jee.samples.jpa.hibernate.derby.internal.dao.DataConnection,
	 *      osgi.jee.samples.jpa.hibernate.derby.internal.model.Phone)
	 */
	public void persistPhone(DataConnection connection, Phone phone) {
		assert connection instanceof JPADataConnection : "Unable to perform persistence with this kind of connection";
		EntityManager entityManager = ((JPADataConnection) connection).getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(phone);
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.dao.EmploymentDAO#persistProject(osgi.jee.samples.jpa.hibernate.derby.internal.dao.DataConnection,
	 *      osgi.jee.samples.jpa.hibernate.derby.internal.model.Project)
	 */
	public void persistProject(DataConnection connection, Project project) {
		assert connection instanceof JPADataConnection : "Unable to perform persistence with this kind of connection";
		EntityManager entityManager = ((JPADataConnection) connection).getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see osgi.jee.samples.jpa.hibernate.derby.internal.dao.EmploymentDAO#persistSmallProject(osgi.jee.samples.jpa.hibernate.derby.internal.dao.DataConnection,
	 *      osgi.jee.samples.jpa.hibernate.derby.internal.model.SmallProject)
	 */
	public void persistSmallProject(DataConnection connection, SmallProject smallProject) {
		assert connection instanceof JPADataConnection : "Unable to perform persistence with this kind of connection";
		EntityManager entityManager = ((JPADataConnection) connection).getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(smallProject);
		entityManager.getTransaction().commit();
	}

	
	private Connection extractConnection(EntityManager entityManager) {
		Session session = entityManager.unwrap(Session.class);
		SessionFactory sessionFactory = session.getSessionFactory();
		if (sessionFactory instanceof SessionFactoryImplementor) {
			@SuppressWarnings("deprecation")
			ConnectionProvider connectionProvider = ((SessionFactoryImplementor)sessionFactory).getConnectionProvider();
			try {
				return connectionProvider.getConnection();
			} catch (SQLException e) {
				return null;
			}
		}
		return null;
	}
}
