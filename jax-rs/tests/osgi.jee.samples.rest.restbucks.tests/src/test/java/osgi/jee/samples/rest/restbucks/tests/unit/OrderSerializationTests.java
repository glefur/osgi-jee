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
package osgi.jee.samples.rest.restbucks.tests.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import osgi.jee.samples.rest.restbucks.model.Coffee;
import osgi.jee.samples.rest.restbucks.model.CookieKind;
import osgi.jee.samples.rest.restbucks.model.Location;
import osgi.jee.samples.rest.restbucks.model.Milk;
import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.Product;
import osgi.jee.samples.rest.restbucks.model.Shots;
import osgi.jee.samples.rest.restbucks.model.Size;
import osgi.jee.samples.rest.restbucks.model.xml.XMLUtil;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class OrderSerializationTests {

	private XMLUtil xmlUtil = new XMLUtil();

	@Test
	public void testXMLSerialization() throws Exception {
		Order originalOrder = Order.Builder.newInstance()
			.addCappuccino()
				.milk(Milk.Semi)
				.quantity(2)
				.size(Size.Large)
				.shots(Shots.Single)
			.addLatte()
				.quantity(3)
				.milk(Milk.Whole)
				.size(Size.Medium)
				.shots(Shots.Double)
			.setLocation(Location.TakeAway)
			.addCookie(CookieKind.Ginger)
				.quantity(6)
			.addHotChocolate()
				.quantity(1)
				.milk(Milk.Skim)
				.size(Size.Large)
				.whippedCream()
					.build();
		String serialization = xmlUtil.toXML(originalOrder);
		assertNotNull(serialization);
		assertNotEquals("Serialiation service returned empty string.", "", serialization);
		Order order = xmlUtil.fromXML(serialization);
		List<Coffee> cappucinos = getProducts(order, Coffee.class, Coffee.CAPPUCCINO);
		assertEquals("Bad count of cappucino types", 1, cappucinos.size());
		Coffee cappucino = cappucinos.get(0);
		assertEquals("Bad configuration of cappucino", Milk.Semi, cappucino.getMilk());
		assertEquals("Bad configuration of cappucino", Size.Large, cappucino.getSize());
		assertEquals("Bad configuration of cappucino", Shots.Single, cappucino.getShots());
		assertEquals("Bad count of cappucino", 2, cappucino.getQuantity());
		
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Product> List<T> getProducts(Order input, Class<? extends Product> type, String name) {
		List<T> result = Lists.newArrayList();
		for (Product prod : input.getProducts()) {
			if (type.isInstance(prod) && name.equals(prod.getName())) {
				result.add((T) prod);
			}
		}
		return result;
	}
	
}
