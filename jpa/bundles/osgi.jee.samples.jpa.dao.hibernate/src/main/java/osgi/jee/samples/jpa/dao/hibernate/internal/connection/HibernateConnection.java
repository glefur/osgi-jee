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
package osgi.jee.samples.jpa.dao.hibernate.internal.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

import osgi.jee.samples.jpa.dao.impl.connection.JPADataConnection;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class HibernateConnection extends JPADataConnection {

	public HibernateConnection(EntityManager entityManager) {
		super(entityManager);
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.connection.DataConnection#getSQLConnection()
	 */
	@Override
	public Connection getSQLConnection() {
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
