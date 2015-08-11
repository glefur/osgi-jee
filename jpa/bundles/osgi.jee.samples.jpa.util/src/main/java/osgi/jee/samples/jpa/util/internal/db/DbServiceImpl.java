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
package osgi.jee.samples.jpa.util.internal.db;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import osgi.jee.samples.jpa.util.db.DbService;
import osgi.jee.samples.jpa.util.db.meta.Column;
import osgi.jee.samples.jpa.util.db.meta.ForeignKey;
import osgi.jee.samples.jpa.util.db.meta.Schema;
import osgi.jee.samples.jpa.util.db.meta.Table;
import osgi.jee.samples.jpa.util.internal.db.meta.ConnectedSchema;
import osgi.jee.samples.jpa.util.internal.db.meta.MetaDBUtil;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class DbServiceImpl implements DbService {

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.DbService#getSchema(java.sql.Connection)
	 */
	@Override
	public Schema getSchema(Connection connection) throws SQLException {
		DatabaseMetaData metaData = connection.getMetaData();
		if (metaData != null) {
			return new ConnectedSchema(metaData);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.DbService#copy(osgi.jee.samples.jpa.util.db.meta.Schema)
	 */
	@Override
	public Schema copy(Schema src) throws SQLException {
		return MetaDBUtil.clone(src);
	}
	
	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.DbService#buildReverseMap(osgi.jee.samples.jpa.util.db.meta.Schema)
	 */
	public Map<Table, Collection<ForeignKey>> buildReverseMap(Schema schema) throws SQLException {
		Map<Table, Collection<ForeignKey>> result = new HashMap<Table, Collection<ForeignKey>>();
		for (Table table : schema.getTables()) {
			for (ForeignKey foreignKey : table.getForeignKeys()) {
				Table targetTable = foreignKey.getTargetTable();
				Collection<ForeignKey> foreignKeys = result.get(targetTable);
				if (foreignKeys == null) {
					foreignKeys = new ArrayList<ForeignKey>();
					result.put(targetTable, foreignKeys);
				}
				foreignKeys.add(foreignKey);
			}
		}
		return result ;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.DbService#removeFromReverseMap(java.util.Map, osgi.jee.samples.jpa.util.db.meta.Table)
	 */
	@Override
	public void removeFromReverseMap(Map<Table, Collection<ForeignKey>> reverseMap, Table table) throws SQLException {
		for (ForeignKey foreignKey : table.getForeignKeys()) {
			Table targetTable = foreignKey.getTargetTable();
			Collection<ForeignKey> collection = reverseMap.get(targetTable);
			if (collection != null) {
				if (collection.contains(foreignKey)) {
					collection.remove(foreignKey);
					if (collection.isEmpty()) {
						reverseMap.remove(targetTable);
					}
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.DbService#toDDL(osgi.jee.samples.jpa.util.db.meta.Schema)
	 */
	@Override
	public String toDDL(Schema schema) {
		StringBuilder builder = new StringBuilder();
		try {
			Collection<Table> tables = schema.getTables();
			for (Table table : tables) {
				builder.append(toDDL(table));
			}
			for (Table table : tables) {
				for (ForeignKey fk : table.getForeignKeys()) {
					builder.append(toDDL(fk));
				}
			}
		} catch (SQLException e) {
			builder.append("Error build DDL schema: Unable to retrieve schema tables from metadata");
		}
		return builder.toString();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.DbService#toDataSet(java.sql.Connection, osgi.jee.samples.jpa.util.db.meta.Schema)
	 */
	@Override
	public String toDataSet(Connection connection, Schema schema) throws DatabaseUnitException, SQLException, IOException {
		QueryDataSet dataSet = new QueryDataSet(new DatabaseConnection(connection));
		for (Table table : schema.getTables()) {
			dataSet.addTable(table.getName());
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		FlatXmlDataSet.write(dataSet, byteArrayOutputStream);
		return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
	}

	private String toDDL(Table table) {
		StringBuilder builder = new StringBuilder();
		builder.append("DROP TABLE " + table.getName() + ";\n");
		builder.append("CREATE TABLE " + table.getName() + " (\n");
		try {
			Collection<Column> primaryKeys = table.getPrimaryKeys();
			for (Iterator<Column> iterColumns = table.getColumns().iterator(); iterColumns.hasNext(); ) {
				Column column = iterColumns.next();
				builder.append(toDDL(column));
				if (iterColumns.hasNext() || primaryKeys.size() > 0) {
					builder.append(',');
				} 
				builder.append('\n');
			}
			if (primaryKeys.size() > 0) {
				builder.append("PRIMARY KEY (");
				for (Iterator<Column> iterColumns = primaryKeys.iterator(); iterColumns.hasNext(); ) {
					Column column = iterColumns.next();
					builder.append(column.getName());
					if (iterColumns.hasNext()) {
						builder.append(',');
					} 
				}
				builder.append(")\n");
			}
			
			
		} catch (SQLException e) {
			builder.append("Error on build table schema: Unable to retrieve table's columns");
		}
		builder.append(");\n");
		return builder.toString();
	}
	
	private String toDDL(Column column) {
		StringBuilder builder = new StringBuilder();
		builder.append(column.getName()).append(' ').append(column.getType());
		if ("VARCHAR".equals(column.getType())) {
			builder
				.append('(')
				.append(column.getLength())
				.append(')');
		}
		return builder.toString();
	}
	
	private String toDDL(ForeignKey fk) {
		StringBuilder builder = 
				new StringBuilder("ALTER TABLE ")
						.append(fk.getSourceTable().getName())
						.append(" ADD CONSTRAINT ")
						.append(buildFKName(fk))
						.append(" FOREIGN KEY (")
						.append(fk.getSourceColumn().getName())
						.append(") REFERENCES ")
						.append(fk.getTargetTable().getName())
						.append(" (")
						.append(fk.getTargetColumn().getName())
						.append(");\n");
		return builder.toString();
	}

	private String buildFKName(ForeignKey fk) {
		return "FK_" + fk.getSourceTable().getName() + "_" + fk.getTargetTable().getName();
	}
}
