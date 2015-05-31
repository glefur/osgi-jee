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
package osgi.jee.samples.jpa.util.db.meta;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class Table {
	
	private DatabaseMetaData metaData;
	private Schema schema;
	private String name;
	
	
	private Map<String, Column> columns;
	private Collection<Column> primaryKeys;
	private Collection<ForeignKey> foreignKeys;

	public Table(DatabaseMetaData metaData, String name) {
		this.metaData = metaData;
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param schema the schema to set
	 */
	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	/**
	 * @return the columns
	 * @throws SQLException 
	 */
	public Collection<Column> getColumns() throws SQLException {
		if (columns == null) {
			columns = new LinkedHashMap<String, Column>();
			ResultSet columns2 = metaData.getColumns(null, null, name, null);
			while (columns2.next()) {
				String colName = columns2.getString(4);
				String type = columns2.getString(6);
				int length = columns2.getInt(7);
				boolean generated = "YES".equals(columns2.getString("IS_GENERATEDCOLUMN"));
				boolean autoIncrement = "YES".equals(columns2.getString("IS_AUTOINCREMENT"));
				int nullableInt = columns2.getInt(11);
				Column col = new Column(colName, type, length, generated, autoIncrement, nullableInt == DatabaseMetaData.columnNoNulls);
				columns.put(colName, col);
			}
		}
		
		return columns.values();
	}
	
	public Column getColumn(String name) throws SQLException {
		if (columns == null) {
			getColumns();
		}
		return columns.get(name);
	}

	/**
	 * @return the primaryKeys
	 * @throws SQLException 
	 */
	public Collection<Column> getPrimaryKeys() throws SQLException {
		if (primaryKeys == null) {
			if (columns == null) {
				getColumns();
			}
			primaryKeys = new ArrayList<Column>();
			ResultSet primary = metaData.getPrimaryKeys(null, null, name);
			while (primary.next()) {
				primaryKeys.add(columns.get(primary.getString(4)));
			}
		}
		return primaryKeys;
	}

	/**
	 * @return the foreignKeys
	 * @throws SQLException 
	 */
	public Collection<ForeignKey> getForeignKeys() throws SQLException {
		if (foreignKeys == null) {
			foreignKeys = new ArrayList<ForeignKey>();
			ResultSet exportedKeys = metaData.getImportedKeys(null, null, name);
			while (exportedKeys.next()) {
				String sourceColumnName = exportedKeys.getString("FKCOLUMN_NAME");
				Column sourceColumn = getColumn(sourceColumnName);
				String targetTableName = exportedKeys.getString("PKTABLE_NAME");
				Table targetTable = schema.getTable(targetTableName);
				String targetColumnName = exportedKeys.getString("PKCOLUMN_NAME");
				Column targetColumn = targetTable.getColumn(targetColumnName);
				ForeignKey fk = new ForeignKey(this, sourceColumn, targetTable, targetColumn);
				foreignKeys.add(fk);
			}
		}
		return foreignKeys;
	}

}
