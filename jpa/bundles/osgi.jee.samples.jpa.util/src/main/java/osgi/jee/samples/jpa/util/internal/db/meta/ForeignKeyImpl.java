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

import osgi.jee.samples.jpa.util.db.meta.Column;
import osgi.jee.samples.jpa.util.db.meta.ForeignKey;
import osgi.jee.samples.jpa.util.db.meta.Table;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class ForeignKeyImpl implements ForeignKey {
	
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
	public ForeignKeyImpl(Table sourceTable, Column sourceColumn, Table targetTable, Column targetColumn) {
		this.sourceTable = sourceTable;
		this.sourceColumn = sourceColumn;
		this.targetTable = targetTable;
		this.targetColumn = targetColumn;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.ForeignKey#getSourceTable()
	 */
	@Override
	public Table getSourceTable() {
		return sourceTable;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.ForeignKey#getSourceColumn()
	 */
	@Override
	public Column getSourceColumn() {
		return sourceColumn;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.ForeignKey#getTargetTable()
	 */
	@Override
	public Table getTargetTable() {
		return targetTable;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.ForeignKey#getTargetColumn()
	 */
	@Override
	public Column getTargetColumn() {
		return targetColumn;
	}
	
	@Override
	public String toString() {
		return "FK " + sourceTable.getName() + "(" + sourceColumn.getName() + ") --> " + targetTable.getName() + "(" + targetColumn.getName() + ")"; 
	}

}
