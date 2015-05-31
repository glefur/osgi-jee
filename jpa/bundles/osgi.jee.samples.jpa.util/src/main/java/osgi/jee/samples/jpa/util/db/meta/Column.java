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
public class Column {

	private String name;
	private String type;
	private int length;
	private boolean generated;
	private boolean autoIncrement;
	private boolean notNull;
	
	public Column(String name, String type, int length, boolean generated, boolean autoIncrement, boolean notNull) {
		this.name = name;
		this.type = type;
		this.length = length;
		this.generated = generated;
		this.autoIncrement = autoIncrement;
		this.notNull = notNull;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return the generated
	 */
	public boolean isGenerated() {
		return generated;
	}

	/**
	 * @return the autoIncrement
	 */
	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	/**
	 * @return the notNull
	 */
	public boolean isNotNull() {
		return notNull;
	}
	
}
