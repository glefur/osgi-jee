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
 * This class is a builder that eases the production of XML contents describing an order.
 * 
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public final class RestbuckXMLBuilder {

	/**
	 * Tag name for an order.
	 */
	public static String ORDER = "order";
	
	/**
	 * Tag name for the location of an order.
	 */
	public static String LOCATION = "location";
	
	/**
	 * Tag name for the item list of an order. 
	 */
	public static String ITEMS = "items";
	
	/**
	 * Tag name for one item.
	 */
	public static String ITEM = "item";
	
	/**
	 * Tag name for the name of an item. 
	 */
	public static String NAME = "name";
	
	/**
	 * Tag name for the quantity of an item. 
	 */
	public static String QUANTITY = "quality";
	
	/**
	 * Tag name for the milk settings of a beverage. 
	 */
	public static String MILK = "milk";
	
	/**
	 * Tag name for the size settings of a beverage. 
	 */
	public static String SIZE = "size";

	/**
	 * Tag name for the shots settings of a coffee. 
	 */
	public static String SHOTS = "shots";

	/**
	 * Tag name for the cream settings of a hot chocolate. 
	 */
	public static String WHIPPEDCREAM = "whippedCream";
	
	/**
	 * Tag name for the kind settings of a cookie. 
	 */
	public static String KIND = "kind";
	
	/**
	 * Yes to the cream! 
	 */
	public static String YES = "yes";

	/**
	 * No thanks, without cream. 
	 */
	public static String NO = "no";
	
	private StringBuilder builder;
	
	private String internalIndent = "";
	
	public RestbuckXMLBuilder() { 
		builder = new StringBuilder();		
	}

	/**
	 * Append a start tag with the given name to the contents.
	 * @param name tag name.
	 * @return this builder.
	 */
	public RestbuckXMLBuilder startTag(String name) {
		builder.append(internalIndent).append("<").append(name).append(">\n");
		internalIndent += '\t';
		return this;
	}

	/**
	 * Append a end tag with the given name to the contents.
	 * @param name tag name.
	 * @return this builder.
	 */
	public RestbuckXMLBuilder endTag(String name) {
		internalIndent = internalIndent.substring(1);
		builder.append(internalIndent).append("</").append(name).append(">\n");
		return this;
	}

	/**
	 * Append a single tag with the given name to the contents.
	 * @param name tag name.
	 * @return this builder.
	 */
	public RestbuckXMLBuilder tag(String name) {
		builder.append(internalIndent).append("<").append(name).append("/>\n");
		return this;
	}
	
	/**
	 * Append a start tag with the given name, the given value and a end tag to the contents.
	 * @param name tag name.
	 * @param value the tag value.
	 * @return this builder.
	 */
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
