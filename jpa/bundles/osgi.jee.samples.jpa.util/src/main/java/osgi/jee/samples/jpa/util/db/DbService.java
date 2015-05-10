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
package osgi.jee.samples.jpa.util.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;

import osgi.jee.samples.jpa.util.db.meta.Schema;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public interface DbService {

	/**
	 * Extracts a "meta"-schema from the given sql Connection.
	 * @param connection the {@link Connection} to process.
	 * @return the extracted {@link Schema}.
	 * @throws SQLException an error occurred during metadata extraction. 
	 */
	Schema getSchema(Connection connection) throws SQLException;
	
	/**	
	 * Generates a string describing a DDL for the given {@link Schema}.
	 * @param schema the {@link Schema} to process.
	 * @return a DDL generating the {@link Schema}.
	 */
	String toDDL(Schema schema);
	
	/**
	 * @param schema
	 * @return
	 * @throws DatabaseUnitException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	String toDataSet(Connection connection, Schema schema) throws DatabaseUnitException, SQLException, IOException;
	
}
