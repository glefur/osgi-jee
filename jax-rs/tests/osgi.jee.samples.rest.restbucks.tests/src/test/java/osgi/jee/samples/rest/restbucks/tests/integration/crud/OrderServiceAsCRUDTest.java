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

import static com.eclipsesource.restfuse.Assert.*;
import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.MediaType;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.google.common.collect.Lists;

import osgi.jee.samples.rest.restbucks.crud.resources.OrderingService;
import osgi.jee.samples.rest.restbucks.model.Milk;
import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.Shots;
import osgi.jee.samples.rest.restbucks.model.Size;
import osgi.jee.samples.rest.restbucks.services.OrderManager;
import osgi.jee.samples.rest.restbucks.tests.AbstractIntegrationTest;
import osgi.jee.samples.rest.restbucks.tests.ContentFileDescription;
import osgi.jee.samples.rest.restbucks.tests.TestRequestingContentFile;

/**
 * This is a "restfuse based" test class for {@link OrderingService}.
 * 
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
@RunWith(HttpJUnitRunner.class)
public class OrderServiceAsCRUDTest extends AbstractIntegrationTest implements TestRequestingContentFile {
	
	public static final String TEST_PUT_ORDER_CONTENT_FILE_PATH = "/osgi/jee/samples/rest/restbucks/tests/integration/crud/testPutOrderContentFile.xml";

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.rest.restbucks.tests.TestRequestingContentFile#getContentFileDescriptions()
	 */
	@Override
	public Collection<ContentFileDescription> getContentFileDescriptions() {
		return Lists.newArrayList(
					new ContentFileDescription(TEST_PUT_ORDER_CONTENT_FILE_PATH, getXMLUtil().toXML(
							Order.Builder.newInstance()
								.addCappuccino()
								.milk(Milk.Semi)
								.size(Size.Small)
								.shots(Shots.Double)
								.quantity(2)
							.build()))
				);
	}

	@Rule
	public Destination destination = new Destination(this, BASE_URL);
	
	@Context
	private Response response;
	
	
	/**
	 * Here we test the GET method of the service. We check that we get the expected order.
	 */
	@HttpTest(method = Method.GET, path="/services/crud/order/1")
	public void testGetOrder() throws Exception {
		assertOk(response);
		assertEquals(MediaType.APPLICATION_XML, response.getType());
		Order order = getXMLUtil().fromXML(response.getBody());
		assertNotNull("Bad returned order", order);
		OrderManager orderService = getOrderManager();
		assertEquals("Bad returned order", orderService.getOrder("1"), order);
		
	}
	
	/**
	 * Here we test the PUT method. We put a new version of a service, we analyze the response and we check
	 * that the updated order match with the update.
	 */
	@HttpTest(method = Method.PUT, file=TEST_PUT_ORDER_CONTENT_FILE_PATH, path = "/services/crud/order/2")
	public void testPutOrder() throws Exception {
		String body = response.getBody();
		assertOk(response);
		assertEquals(MediaType.APPLICATION_XML, response.getType());
		Order responseOrder = getXMLUtil().fromXML(body);
		assertNotNull("Bad response", responseOrder);
		assertEquals("Bad product count in the updated order", 1, responseOrder.getProducts().size());
		getOrderManager().initOrders();
	}
	
	/**
	 * Here we test the DELETE method. We try to delete an unexisting order and we check that we get a method
	 * not allowed response.
	 */
	@HttpTest(method = Method.DELETE, path = "/services/crud/order/3")
	public void testDeleteUndeletableOrder() {
		assertMethodNotAllowed(response);
	}

	/**
	 * Here we test the DELETE method. We try to delete an order and we check in the {@link OrderManager} that
	 * the order have been really deleted.
	 */
	@HttpTest(method = Method.DELETE, path = "/services/crud/order/2")
	public void testDeleteOrder() {
		assertNoContent(response);
		Order order = getOrderManager().getOrder("2");
		assertNull("Order not deleted", order);
		getOrderManager().initOrders();
	}

}
