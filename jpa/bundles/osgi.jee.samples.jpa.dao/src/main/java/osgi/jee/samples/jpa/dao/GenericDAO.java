/**
 * OSGi/JEE Sample.
 * 
 * Copyright (C) 2015 Goulwen Le Fur
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

import java.util.Collection;

import osgi.jee.samples.jpa.dao.connection.DataConnection;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 * 
 */
public interface GenericDAO<T> {

	/**
	 * Persists a new entity.
	 * @param entity the entity to persist.
	 * @return the persisted entity.
	 */
	T create(DataConnection dataConnection, T entity);

	/**
	 * Removes an existing entity from the persisting system.
	 * @param entity the entity to remove. 
	 */
	void delete(DataConnection dataConnection, T entity);

	/**
	 * Finds a entity given its id.
	 * @param id the entity id.
	 * @return the found entity if exists <code>null</code> otherwise.
	 */
	T find(DataConnection dataConnection, Object id);

	/**
	 * Updates the given entity.
	 * @param t the entity to update.
	 * @return the processed entity.
	 */
	T update(DataConnection dataConnection, T t);
	
	/**
	 * @param dataConnection
	 * @return
	 */
	Collection<T> findAll(DataConnection dataConnection);
}
