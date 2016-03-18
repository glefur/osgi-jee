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
package osgi.jee.samples.rest.restbucks.tests.integration.crud;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import osgi.jee.samples.rest.restbucks.model.CookieKind;
import osgi.jee.samples.rest.restbucks.model.Location;
import osgi.jee.samples.rest.restbucks.model.Milk;
import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.Shots;
import osgi.jee.samples.rest.restbucks.model.Size;
import osgi.jee.samples.rest.restbucks.services.OrderService;
import osgi.jee.samples.rest.restbucks.tests.AbstractIntegrationTest;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class PostOrderServiceAsCRUDTest extends AbstractIntegrationTest {

	/**
	 * Here we test the order creation via a post method. We send a request and check that we have a Created return code,
	 * that we have a location in the response and that the good order has been placed in the dedicated service.
	 * @throws Exception
	 */
	@Test
	public void testPostOrder() throws Exception {
		String path = "/servlet/crud/order";
		Order order = Order.Builder.newInstance()
				.addCappuccino()
					.milk(Milk.Semi)
					.size(Size.Large)
					.shots(Shots.Single)
					.quantity(3)
				.addLatte()
					.quantity(1)
					.milk(Milk.Whole)
					.size(Size.Medium)
					.shots(Shots.Double)
				.setLocation(Location.TakeAway)
				.addCookie(CookieKind.Ginger)
					.quantity(4)
				.addHotChocolate()
					.milk(Milk.Skim)
					.size(Size.Large)
					.whippedCream()
					.quantity(2)
						.build();

		HttpResponse response = postPOX(path, order);
		assertEquals("Invalid response status", Response.Status.CREATED.getStatusCode(), response.getStatusLine().getStatusCode());
		Header[] headers = response.getHeaders("Location");
		assertNotNull("Not location header returned.", headers);
		assertEquals("Invalid count of location header returned.", 1, headers.length);
		Header locationHeader = headers[0];
		String location = locationHeader.getValue();
		String orderId = extractId(location);
		BundleContext context = FrameworkUtil.getBundle(Order.class).getBundleContext();
		ServiceReference<OrderService> ref = context.getServiceReference(OrderService.class);
		OrderService service = context.getService(ref);
		if (service != null) {
			Order storedOrder = service.getOrder(orderId);
			assertEquals("Order badly stored.", order, storedOrder);
		} else {
			fail("Unable to retrieve OrderService");
		}
		
	}

	@Test
	public void testUpdatedOrder() throws Exception {
		String basePath = "/services/crud/order";
		Order order = Order.Builder.newInstance()
				.addCappuccino()
					.milk(Milk.Semi)
					.size(Size.Large)
					.shots(Shots.Single)
					.quantity(3)
				.addLatte()
					.quantity(1)
					.milk(Milk.Whole)
					.size(Size.Medium)
					.shots(Shots.Double)
				.setLocation(Location.TakeAway)
				.addCookie(CookieKind.Ginger)
					.quantity(4)
				.addHotChocolate()
					.milk(Milk.Skim)
					.size(Size.Large)
					.whippedCream()
					.quantity(2)
						.build();

		HttpResponse response = putPOX(basePath + "/1", order);
		assertEquals("Invalid response status", 200, response.getStatusLine().getStatusCode());
	}
}
