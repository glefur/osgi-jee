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


/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class ForeignKey {
	
	private Table sourceTable;
	private Column sourceColumn;
	private Table targetTable;
	private Column targetColumn;
	
	/**
	 * @param sourceTable
	 * @param sourceColumn
	 * @param targetTable
	 * @param targetColumn
	 */
	public ForeignKey(Table sourceTable, Column sourceColumn, Table targetTable, Column targetColumn) {
		this.sourceTable = sourceTable;
		this.sourceColumn = sourceColumn;
		this.targetTable = targetTable;
		this.targetColumn = targetColumn;
	}

	/**
	 * @return the sourceTable
	 */
	public Table getSourceTable() {
		return sourceTable;
	}

	/**
	 * @return the sourceColumn
	 */
	public Column getSourceColumn() {
		return sourceColumn;
	}

	/**
	 * @return the targetTable
	 */
	public Table getTargetTable() {
		return targetTable;
	}

	/**
	 * @return the targetColumn
	 */
	public Column getTargetColumn() {
		return targetColumn;
	}

}
