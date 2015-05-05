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
package osgi.jee.samples.jpa.dao.connection.meta;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class Column {

	private String name;
	private String type;
	private int length;
	private boolean notNull;
	
	public Column(String name, String type, int length, boolean notNull) {
		this.name = name;
		this.type = type;
		this.length = length;
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
	 * @return the notNull
	 */
	public boolean isNotNull() {
		return notNull;
	}
	
	public String toDDL() {
		StringBuilder builder = new StringBuilder();
		builder.append(name).append(' ').append(type);
		if ("VARCHAR".equals(type)) {
			builder
				.append('(')
				.append(length)
				.append(')');
		}
		return builder.toString();
	}
	
}
