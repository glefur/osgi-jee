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
package osgi.jee.samples.jpa.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManagerFactory;

import osgi.jee.samples.jpa.dao.JPADAO;
import osgi.jee.samples.jpa.dao.connection.DataConnection;
import osgi.jee.samples.jpa.dao.connection.DataConnectionFactoryRegistry;
import osgi.jee.samples.jpa.dao.impl.connection.JPADataConnection;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class JPADAOImpl<T> implements JPADAO<T> {

	private EntityManagerFactory entityManagerFactory;
	private DataConnectionFactoryRegistry dataConnectionFactoryRegistry;
	private Class<T> type;

	/**
	 * @param entityManagerFactory the entityManagerFactory to set
	 */
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	/**
	 * @param dataConnectionFactoryRegistry the dataConnectionFactoryRegistry to set
	 */
	public void setDataConnectionFactoryRegistry(DataConnectionFactoryRegistry dataConnectionFactoryRegistry) {
		this.dataConnectionFactoryRegistry = dataConnectionFactoryRegistry;
	}

	@SuppressWarnings("unchecked")
	public JPADAOImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.JPADAO#createDataConnection()
	 */
	@Override
	public JPADataConnection createDataConnection() {
		return (JPADataConnection) dataConnectionFactoryRegistry.getService(entityManagerFactory.createEntityManager());
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.GenericDAO#create(osgi.jee.samples.jpa.dao.connection.DataConnection, java.lang.Object)
	 */
	@Override
	public final T create(DataConnection dataConnection, final T t) {
		assert dataConnection instanceof JPADataConnection:"Unable to perform this operation with this data connection";
		((JPADataConnection)dataConnection).getEntityManager().persist(t);
		return t;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.GenericDAO#delete(osgi.jee.samples.jpa.dao.connection.DataConnection, java.lang.Object)
	 */
	@Override
	public final void delete(DataConnection dataConnection, final T entity) {
		assert dataConnection instanceof JPADataConnection:"Unable to perform this operation with this data connection";
		((JPADataConnection)dataConnection).getEntityManager().remove(entity);
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.GenericDAO#find(osgi.jee.samples.jpa.dao.connection.DataConnection, java.lang.Object)
	 */
	@Override
	public final T find(DataConnection dataConnection, final Object id) {
		assert dataConnection instanceof JPADataConnection:"Unable to perform this operation with this data connection";
		return (T) ((JPADataConnection)dataConnection).getEntityManager().find(type, id);
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.GenericDAO#update(osgi.jee.samples.jpa.dao.connection.DataConnection, java.lang.Object)
	 */
	@Override
	public final T update(DataConnection dataConnection, final T t) {
		assert dataConnection instanceof JPADataConnection:"Unable to perform this operation with this data connection";
		return ((JPADataConnection)dataConnection).getEntityManager().merge(t);    
	}
}
