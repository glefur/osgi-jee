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
package osgi.jee.samples.jpa.dao.impl.connection;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import osgi.jee.samples.jpa.dao.connection.DataConnection;
import osgi.jee.samples.jpa.util.db.meta.Schema;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public abstract class JPADataConnection implements DataConnection {

	protected final EntityManager entityManager;

	public JPADataConnection(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	

	/**
	 * {@inheritDoc}
	 * @throws SQLException 
	 * @see osgi.jee.samples.jpa.dao.connection.DataConnection#getSchema()
	 */
	@Override
	public Schema getSchema() throws SQLException {
		DatabaseMetaData metaData = getSQLConnection().getMetaData();
		if (metaData != null) {
			return new Schema(metaData);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.connection.DataConnection#beginTransaction()
	 */
	@Override
	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.connection.DataConnection#commit()
	 */
	@Override
	public void commit() {
		entityManager.getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.connection.DataConnection#close()
	 */
	@Override
	public void close() {
		entityManager.close();
	}

}
