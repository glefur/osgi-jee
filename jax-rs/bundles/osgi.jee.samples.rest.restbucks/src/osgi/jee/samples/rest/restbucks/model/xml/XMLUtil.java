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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import osgi.jee.samples.rest.restbucks.internal.model.xml.OrderSaxHandler;
import osgi.jee.samples.rest.restbucks.model.Beverage;
import osgi.jee.samples.rest.restbucks.model.Coffee;
import osgi.jee.samples.rest.restbucks.model.Cookie;
import osgi.jee.samples.rest.restbucks.model.HotChocolate;
import osgi.jee.samples.rest.restbucks.model.Location;
import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.Product;

/**
 * This class provides "xml oriented" services for order.
 * 
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class XMLUtil {

	/**
	 * Converts an {@link Order} to an XML string.
	 * @param order the {@link Order} to convert.
	 * @return an XML string describing the input order.
	 */
	public String toXML(Object object) {
		if (object instanceof Order) {
			Order order = (Order) object;
			RestbuckXMLBuilder xmlBuilder = new RestbuckXMLBuilder();
			xmlBuilder.startTag(RestbuckXMLBuilder.ORDER);
			if (order.getLocation() != Location.Unknown) {
				xmlBuilder.tag(RestbuckXMLBuilder.LOCATION, order.getLocation().toString());
			}
			if (order.getProducts().size() > 0) {
				xmlBuilder.startTag(RestbuckXMLBuilder.ITEMS);
				for (Product product : order.getProducts()) {
					xmlBuilder.startTag(RestbuckXMLBuilder.ITEM);
					xmlBuilder.tag(RestbuckXMLBuilder.NAME, product.getName());
					xmlBuilder.tag(RestbuckXMLBuilder.QUANTITY, String.valueOf(product.getQuantity()));
					if (product instanceof Beverage) {
						xmlBuilder.tag(RestbuckXMLBuilder.MILK, ((Beverage) product).getMilk().toString());
						xmlBuilder.tag(RestbuckXMLBuilder.SIZE, ((Beverage) product).getSize().toString());
						if (product instanceof Coffee) {
							xmlBuilder.tag(RestbuckXMLBuilder.SHOTS, ((Coffee) product).getShots().toString());
						} else if (product instanceof HotChocolate) {
							xmlBuilder.tag(RestbuckXMLBuilder.WHIPPEDCREAM, ((HotChocolate) product).isWhippedCream()?RestbuckXMLBuilder.YES:RestbuckXMLBuilder.NO);
						}

					} else if (product instanceof Cookie) {
						xmlBuilder.tag(RestbuckXMLBuilder.KIND, ((Cookie) product).getKind().toString());
					}
					xmlBuilder.endTag(RestbuckXMLBuilder.ITEM);
				}
				xmlBuilder.endTag(RestbuckXMLBuilder.ITEMS);
			}
			xmlBuilder.endTag(RestbuckXMLBuilder.ORDER);
			return xmlBuilder.toString();
		}
		return null;
	}

	/**
	 * Parses an XML string and convert it in {@link Order}.
	 * @param input the input string.
	 * @return the resulting {@link Order}.
	 * @throws Exception an error occurred during parsing.
	 */
	public Order fromXML(String input) throws Exception {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(input.getBytes()));
		OrderSaxHandler handler = new OrderSaxHandler();
		parser.parse(inputStream, handler);
		return handler.getOrder();
	}


}
