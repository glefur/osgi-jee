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

import osgi.jee.samples.jpa.util.db.meta.Column;
import osgi.jee.samples.jpa.util.db.meta.ForeignKey;
import osgi.jee.samples.jpa.util.db.meta.Schema;
import osgi.jee.samples.jpa.util.db.meta.Table;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class MetaDBUtil {

	public static Schema clone(Schema src) throws SQLException {
		DisconnectedSchema result = new DisconnectedSchema();
		for (Table table : src.getTables()) {
			DisconnectedTable clonedTable = new DisconnectedTable(table.getName());
			for (Column column : table.getColumns()) {
				Column clonedColumn = new ColumnImpl(column.getName(), column.getType(), column.getLength(), column.isGenerated(), column.isAutoIncrement(), column.isNotNull());
				clonedTable.addColumn(clonedColumn);
			}
			for (Column column : table.getPrimaryKeys()) {
				Column clonedColumn = clonedTable.getColumn(column.getName());
				clonedTable.addPrimaryKey(clonedColumn);
			}
			result.addTable(clonedTable);
		}
		for (Table table : src.getTables()) {
			for (ForeignKey foreignKey : table.getForeignKeys()) {
				DisconnectedTable clonedSourceTable = (DisconnectedTable) result.getTable(foreignKey.getSourceTable().getName());
				Table clonedTargetTable = result.getTable(foreignKey.getTargetTable().getName());
				ForeignKeyImpl clonedFK = new ForeignKeyImpl(clonedSourceTable, clonedSourceTable.getColumn(foreignKey.getSourceColumn().getName()), clonedTargetTable, clonedTargetTable.getColumn(foreignKey.getTargetColumn().getName()));
				clonedSourceTable.addForeignKey(clonedFK);
			}
		}
		
		
		return result;
	}
	
}
