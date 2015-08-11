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

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class ColumnImpl implements Column {

	private String name;
	private String type;
	private int length;
	private boolean generated;
	private boolean autoIncrement;
	private boolean notNull;
	
	public ColumnImpl(String name, String type, int length, boolean generated, boolean autoIncrement, boolean notNull) {
		this.name = name;
		this.type = type;
		this.length = length;
		this.generated = generated;
		this.autoIncrement = autoIncrement;
		this.notNull = notNull;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Column#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Column#getType()
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Column#getLength()
	 */
	@Override
	public int getLength() {
		return length;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Column#isGenerated()
	 */
	@Override
	public boolean isGenerated() {
		return generated;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Column#isAutoIncrement()
	 */
	@Override
	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.db.meta.Column#isNotNull()
	 */
	@Override
	public boolean isNotNull() {
		return notNull;
	}
	
	@Override
	public String toString() {
		return "Column " + name + ": " + type;
	}
	
}
