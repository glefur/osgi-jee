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
package osgi.jee.samples.jpa.util.internal.db.meta;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import osgi.jee.samples.jpa.util.db.meta.Schema;
import osgi.jee.samples.jpa.util.db.meta.Table;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class ConnectedSchema implements Schema {

	private DatabaseMetaData metaData;
	private Map<String, Table> tables = null;

	public ConnectedSchema(DatabaseMetaData metaData) {
		this.metaData = metaData;
	}

	@Override
	public Collection<Table> getTables() throws SQLException {
		if (tables == null) {
			tables = new HashMap<String, Table>();
			ResultSet tables2 = metaData.getTables(null, null, null, null);
			while (tables2.next()) {
				String name = tables2.getString(3);
				if (!name.startsWith("SYS")) {
					ConnectedTable table = new ConnectedTable(metaData, name);
					table.setSchema(this);
					tables.put(name, table);
				}
			}
		}
		return tables.values();
	}
	
	@Override
	public Table getTable(String name) throws SQLException {
		if (tables == null) {
			getTables();
		}
		return tables.get(name);
	}
}
