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
package osgi.jee.samples.rest.restbucks.services;

import java.util.Map;

import com.google.common.collect.Maps;

import osgi.jee.samples.rest.restbucks.model.CookieKind;
import osgi.jee.samples.rest.restbucks.model.Milk;
import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.Shots;
import osgi.jee.samples.rest.restbucks.model.Size;

/**
 * This class emulates service for {@link Order}.
 *  
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 */
public class OrderService {
	
	private int index = 0;
	
	private Map<Integer, Order> orders;

	/**
	 * 
	 */
	public OrderService() {
		orders = Maps.newHashMap();
		initOrders();
	}

	/**
	 * Simulates the serialization of an {@link Order}.
	 * @param order the {@link Order} to serialize.
	 * @return id associated to the order.
	 */
	public String createOrder(Order order) {
		index++;
		orders.put(index, order);
		return String.valueOf(index);
	}
	
	/**
	 * Returns an {@link Order} with the given id.
	 * @param id Id of the expected order. 
	 * @return the expected {@link Order} if found <code>null</code> otherwise.
	 */
	public Order getOrder(String id) {
		return orders.get(Integer.valueOf(id));
	}
	
	/**
	 * 
	 */
	private void initOrders() {
		orders.put(1, Order.Builder.newInstance()
				.addCappuccino()
					.quantity(2)
					.milk(Milk.Semi)
					.shots(Shots.Single)
					.size(Size.Small)
				.build());
		orders.put(2, Order.Builder.newInstance()
				.addCappuccino()
					.quantity(1)
					.milk(Milk.Semi)
					.shots(Shots.Double)
					.size(Size.Large)
				.addHotChocolate()
					.milk(Milk.Skim)
					.size(Size.Large)
				.addCookie(CookieKind.ChocolateChip)
					.quantity(2)
				.build());
		index = 2;
	}

}
