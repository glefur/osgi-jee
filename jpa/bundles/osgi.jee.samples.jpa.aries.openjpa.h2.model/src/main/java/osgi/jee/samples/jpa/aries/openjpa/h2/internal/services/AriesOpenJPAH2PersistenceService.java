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
package osgi.jee.samples.jpa.aries.openjpa.h2.internal.services;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.h2.tools.RunScript;

import osgi.jee.samples.jpa.api.services.persistence.PersistenceService;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class AriesOpenJPAH2PersistenceService implements PersistenceService {
	
	private EntityManagerFactory entityManagerFactory;
	
	/**
	 * @param entityManagerFactory the entityManagerFactory to set
	 */
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.api.services.persistence.PersistenceService#createEntityManager()
	 */
	public EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.api.services.persistence.PersistenceService#extractConnection(javax.persistence.EntityManager)
	 */
	public Connection extractConnection(EntityManager entityManager) {
        OpenJPAEntityManager oem = OpenJPAPersistence.cast(entityManager);
        return (Connection) oem.getConnection();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.api.services.persistence.PersistenceService#initSchema(java.sql.Connection, java.io.InputStream)
	 */
	public void initSchema(Connection connection, InputStream schemaResource) throws Exception {
		RunScript.execute(connection, new InputStreamReader(schemaResource));
	}


}
