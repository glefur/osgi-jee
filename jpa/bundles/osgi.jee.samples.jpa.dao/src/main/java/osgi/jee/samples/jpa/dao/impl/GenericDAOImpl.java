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

import javax.persistence.EntityManager;

import osgi.jee.samples.jpa.dao.GenericDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class GenericDAOImpl<T> implements GenericDAO<T> {

	private EntityManager entityManager;
	private Class<T> type;

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.GenericDAO#create(java.lang.Object)
	 */
	@Override
	public final T create(final T t) {
		this.entityManager.persist(t);
		return t;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.GenericDAO#delete(java.lang.Object)
	 */
	@Override
	public final void delete(final T entity) {
		this.entityManager.remove(entity);
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.GenericDAO#find(java.lang.Object)
	 */
	@Override
	public final T find(final Object id) {
		return (T) this.entityManager.find(type, id);
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.GenericDAO#update(java.lang.Object)
	 */
	@Override
	public final T update(final T t) {
		return this.entityManager.merge(t);    
	}
}
