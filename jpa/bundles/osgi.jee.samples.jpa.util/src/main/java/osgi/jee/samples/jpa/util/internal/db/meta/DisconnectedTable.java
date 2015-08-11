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

import osgi.jee.samples.jpa.util.db.meta.Column;
import osgi.jee.samples.jpa.util.db.meta.ForeignKey;
import osgi.jee.samples.jpa.util.db.meta.Table;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class DisconnectedTable implements Table {

	private String name;
	private Collection<Column> columns;
	private Collection<Column> primaryKeys;
	private Collection<ForeignKey> foreignKeys;

	public DisconnectedTable(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Table#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Table#getColumns()
	 */
	@Override
	public Collection<Column> getColumns() throws SQLException {
		return columns;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Table#getColumn(java.lang.String)
	 */
	@Override
	public Column getColumn(String name) throws SQLException {
		if (columns != null) {
			for (Column column : columns) {
				if (name.equals(column.getName())) {
					return column;
				}
			}
		}
		return null;
	}

	public void addColumn(Column column) {
		if (columns == null) {
			columns = new ArrayList<Column>();
		}
		columns.add(column);
	}

	public void removeColumn(Column column) {
		if (columns != null) {
			columns.remove(column);
		}
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Table#getPrimaryKeys()
	 */
	@Override
	public Collection<Column> getPrimaryKeys() throws SQLException {
		return primaryKeys;
	}

	public void addPrimaryKey(Column column) {
		if (primaryKeys == null) {
			primaryKeys = new ArrayList<Column>();
		}
		primaryKeys.add(column);
	}

	public void removePrimaryKey(Column column) {
		if (primaryKeys != null) {
			primaryKeys.remove(column);
		}
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Table#getForeignKeys()
	 */
	@Override
	public Collection<ForeignKey> getForeignKeys() throws SQLException {
		if (foreignKeys == null) {
			foreignKeys = new ArrayList<ForeignKey>();
		}
		return foreignKeys;
	}

	public void addForeignKey(ForeignKey foreignKey) {
		if (foreignKeys == null) {
			foreignKeys = new ArrayList<ForeignKey>();
		}
		foreignKeys.add(foreignKey);
	}

	public void removeForeignKey(ForeignKey foreignKey) {
		if (foreignKeys != null) {
			foreignKeys.remove(foreignKey);
		}
	}
	
	@Override
	public String toString() {
		return "Table " + name;
	}

}
