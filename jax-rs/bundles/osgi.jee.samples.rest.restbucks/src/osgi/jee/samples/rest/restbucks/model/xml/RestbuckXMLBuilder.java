/**
 * OSGi/JEE Sample.
 * 
 * Copyright (C) 2016 Goulwen Le Fur
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
package osgi.jee.samples.rest.restbucks.model.xml;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public final class RestbuckXMLBuilder {
	
	public static String ORDER = "order";
	public static String LOCATION = "location";
	public static String ITEMS = "items";
	public static String ITEM = "item";
	public static String NAME = "name";
	public static String QUANTITY = "quality";
	public static String MILK = "milk";
	public static String SIZE = "size";
	public static String SHOTS = "shots";
	public static String WHIPPEDCREAM = "whippedCream";
	public static String KIND = "kind";
	public static String YES = "yes";
	public static String NO = "no";
	
	private StringBuilder builder;
	
	private String internalIndent = "";
	
	public RestbuckXMLBuilder() { 
		builder = new StringBuilder();		
	}

	public RestbuckXMLBuilder startTag(String name) {
		builder.append(internalIndent).append("<").append(name).append(">\n");
		internalIndent += '\t';
		return this;
	}

	public RestbuckXMLBuilder endTag(String name) {
		internalIndent = internalIndent.substring(1);
		builder.append(internalIndent).append("</").append(name).append(">\n");
		return this;
	}

	public RestbuckXMLBuilder tag(String name) {
		builder.append(internalIndent).append("<").append(name).append("/>\n");
		return this;
	}
	

	public RestbuckXMLBuilder tag(String name, String value) {
		builder.append(internalIndent).append("<").append(name).append(">").append(value).append("</").append(name).append(">\n");
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return builder.toString();
	}
}
