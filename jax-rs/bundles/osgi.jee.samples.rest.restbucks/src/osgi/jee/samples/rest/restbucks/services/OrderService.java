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
import osgi.jee.samples.rest.restbucks.model.Status;

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
	 * Returns an {@link Order} with the given id.
	 * @param id Id of the expected order. 
	 * @return the expected {@link Order} if found <code>null</code> otherwise.
	 */
	public Order getOrder(String id) {
		return orders.get(Integer.valueOf(id));
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
	 * Simulates the update of an {@link Order}.
	 * 
	 * It checks if the order exists and if it can be updated before performing a replace of the order value.
	 * @param id ID of the order to update.
	 * @param order new value of the order.
	 * @return <code>true</code> if everything if ok, <code>false</code> if something went wrong.
	 */
	public boolean updateOrder(String id, Order order) {
		if (orderExists(id) && canUpdate(id)) {
			orders.put(Integer.valueOf(id), order);
			return true;
		}
		return false;
	}
	
	/**
	 * Returns whether the order with the specified id exists or not.
	 * @param id Id of the order to check.
	 * @return <code>true</code> if the order exists, <code>false</code> otherwise.
	 */
	public boolean orderExists(String id) {
		return orders.get(Integer.valueOf(id)) != null;
	}
	
	/**
	 * Returns whether an order can be updated or not.
	 * @param id ID of the order to check.
	 * @return <code>true</code> if the order can be updated.
	 */
	private boolean canUpdate(String id) {
		return true;
	}

	/**
	 * Archives the order with the given ID
	 * @param orderId ID of the order to archive.
	 */
	public void archive(String orderId) {
		orders.remove(orderId);
	}

	/**
	 * 
	 */
	public void initOrders() {
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
					.quantity(1)
				.addCookie(CookieKind.ChocolateChip)
					.quantity(2)
				.build());
		Order order3 = Order.Builder.newInstance()
				.addHotChocolate()
					.milk(Milk.Skim)
					.size(Size.Large)
					.whippedCream()
					.quantity(2)
				.build();
		order3.setStatus(Status.SERVED);
		orders.put(3, order3);
		index = 3;
	}

}
