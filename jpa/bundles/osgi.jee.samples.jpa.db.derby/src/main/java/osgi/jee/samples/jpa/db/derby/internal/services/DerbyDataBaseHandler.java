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
package osgi.jee.samples.jpa.db.derby.internal.services;

import java.io.InputStream;
import java.sql.Connection;

import org.apache.derby.tools.ij;

import osgi.jee.samples.jpa.dao.db.DataBaseHandler;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class DerbyDataBaseHandler implements DataBaseHandler {

	private static final String UTF_8_ENCODING = "utf-8";
	
	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.dao.db.DataBaseHandler#initSchema(java.sql.Connection, java.io.InputStream)
	 */
	@Override
	public void initSchema(Connection connection, InputStream schemaResource) throws Exception {
		ij.runScript(connection,schemaResource, UTF_8_ENCODING, System.out, UTF_8_ENCODING);
	}

}
