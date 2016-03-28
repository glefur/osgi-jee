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
package osgi.jee.samples.rest.restbucks.crud.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.xml.XMLUtil;
import osgi.jee.samples.rest.restbucks.services.OrderManager;

/**
 * Webservice for managing orders registered as JAX-RS Resource.
 * 
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
@Path("/crud/order")
public class OrderingService {

	private OrderManager orderManager;
	
	/**
	 * @param orderManager the orderManager to set
	 */
	public void setOrderService(OrderManager orderManager) {
		this.orderManager = orderManager;
	}
	
	/**
	 * Get method for returning a given order.
	 * @param orderId ID of the expected order.
	 * @return the {@link Order} with the given id.
	 */
	@GET
	@Path("/{orderId}")
	@Produces(MediaType.APPLICATION_XML)
	public String getOrder(@PathParam("orderId") String orderId) {
		Order order = orderManager.getOrder(orderId);
		if (order != null) {
			XMLUtil xmlUtil = new XMLUtil();
			return xmlUtil.toXML(order);
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
	}
	
	/**
	 * Put method for updating an existing order.
	 * @param orderId ID of the order to updated.
	 * @param orderRepresentation XML representation of the updated order.
	 * @return a {@link Response} describing the result of the operation.
	 */
	@PUT
	@Path("/{orderId}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response updateOrder(@PathParam("orderId") String orderId, String orderRepresentation) {
		if (orderManager.orderExists(orderId)) {
			try {
				orderManager.updateOrder(orderId, new XMLUtil().fromXML(orderRepresentation));
				return Response.ok().entity(orderRepresentation).build();
			} catch (Exception e) {
				throw new WebApplicationException(Response.Status.BAD_REQUEST);
			}
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
	/**
	 * Delete method for deleting an order.
	 * @param orderId ID of the order to delete.
	 * @return a {@link Response} describing the result of the operation.
	 */
	@DELETE
	@Path("/{orderId}")
	public Response deleteOrder(@PathParam("orderId") String orderId) {
		if (orderManager.orderExists(orderId)) {
			Order order = orderManager.getOrder(orderId);
			if (order.canDelete()) {
				orderManager.archive(orderId);
				return Response.noContent().build();
			} else {
				throw new WebApplicationException(Response.Status.METHOD_NOT_ALLOWED);				
			}
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
}
