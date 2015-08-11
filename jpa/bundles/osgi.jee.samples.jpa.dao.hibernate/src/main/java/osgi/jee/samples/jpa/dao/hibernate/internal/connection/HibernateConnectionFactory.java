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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import osgi.jee.samples.jpa.dao.connection.DataConnectionFactory;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class HibernateConnectionFactory implements DataConnectionFactory {
	
	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.services.ServiceProvider#provides(java.lang.Object)
	 */
	@Override
	public boolean provides(EntityManager entityManager) {
		EntityManagerFactory factory = entityManager.getEntityManagerFactory();
		return factory.getClass().getName().indexOf("hibernate") >= 0;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.services.ServiceProvider#getService(java.lang.Object)
	 */
	@Override
	public HibernateConnection getService(EntityManager entityManager) {
		return new HibernateConnection(entityManager);
	}

}
