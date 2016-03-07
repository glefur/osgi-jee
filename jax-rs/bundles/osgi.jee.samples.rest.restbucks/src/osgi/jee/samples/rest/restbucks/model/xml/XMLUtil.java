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
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class XMLUtil {
	
	public String toXML(Order order) {
		StringBuilder builder = new StringBuilder("<order>\n");
		if (order.getLocation() != Location.Unknown) {
			builder.append("\t<location>")
				.append(order.getLocation().toString())
				.append("</location>\n");
		}
		if (order.getProducts().size() > 0) {
			builder.append("\t<items>\n");
			for (Product product : order.getProducts()) {
				builder.append("\t\t<item>\n");
				builder.append("\t\t\t<name>").append(product.getName()).append("</name>\n");
				builder.append("\t\t\t<quantity>1</quantity>\n");
				if (product instanceof Beverage) {
					builder.append("\t\t\t<milk>").append(((Beverage) product).getMilk().toString()).append("</milk>\n");
					builder.append("\t\t\t<size>").append(((Beverage) product).getSize().toString()).append("</size>\n");
					if (product instanceof Coffee) {
						builder.append("\t\t\t<shots>").append(((Coffee) product).getShots().toString()).append("</shots>\n");
					} else if (product instanceof HotChocolate) {
						builder.append("\t\t\t<whippedCream>");
						if (((HotChocolate) product).isWhippedCream()) {
							builder.append("yes");
						} else {
							builder.append("no");
						}
						builder.append("</whippedCream>\n");
					}
					
				} else if (product instanceof Cookie) {
					builder.append("\t\t\t<kind>").append(((Cookie) product).getKind().toString()).append("</kind>\n");					
				}
				builder.append("\t\t</item>\n");				
			}
			builder.append("\t</items>\n");
		}
		
		return builder.append("</order>").toString();
	}
	
	public Order fromXML(String input) throws Exception {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(input.getBytes()));
		OrderSaxHandler handler = new OrderSaxHandler();
		parser.parse(inputStream, handler);
		return handler.getOrder();
	}
	
	
}
