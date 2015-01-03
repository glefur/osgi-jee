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
package osgi.jee.samples.jpa.api.services.persistence;

import java.io.InputStream;
import java.sql.Connection;

import javax.persistence.EntityManager;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public interface PersistenceService {
	
	/**
	 * Extracts the database connection from the given entity manager.
	 * @param entityManager the {@link EntityManager} to process.
	 * @return the database {@link Connection}.
	 */
	Connection extractConnection(EntityManager entityManager);
	
	/**
	 * Initializes the database schema described in the given resource into the database connection.
	 * @param connection database {@link Connection} to initialize.
	 * @param schemaResource {@link InputStream} describing the database schema to initialize.
	 */
	void initSchema(Connection connection, InputStream schemaResource) throws Exception;
	
}
