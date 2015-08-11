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
package osgi.jee.samples.jpa.util.internal.db.meta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import osgi.jee.samples.jpa.util.db.meta.Schema;
import osgi.jee.samples.jpa.util.db.meta.Table;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class DisconnectedSchema implements Schema {

	private Collection<Table> tables;

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Schema#getTables()
	 */
	@Override
	public Collection<Table> getTables() throws SQLException {
		return tables;
	}
	
	public void addTable(Table table) {
		if (tables == null) {
			tables = new ArrayList<Table>();
		}
		tables.add(table);
	}
	
	public void removeTable(Table table) {
		if (tables != null) {
			tables.remove(table);
		}
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Schema#getTable(java.lang.String)
	 */
	@Override
	public Table getTable(String name) throws SQLException {
		if (tables != null) {
			for (Table table : tables) {
				if (name.equals(table.getName())) {
					return table;
				}
			}
		}
		return null;
	}
	
}
