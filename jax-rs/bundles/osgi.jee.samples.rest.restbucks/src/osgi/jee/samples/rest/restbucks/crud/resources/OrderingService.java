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
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.xml.XMLUtil;
import osgi.jee.samples.rest.restbucks.services.OrderService;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
@Path("/crud/order")
public class OrderingService {

	private OrderService orderService;
	
	private @Context UriInfo uriInfo;
	
	/**
	 * @param orderService the orderService to set
	 */
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@GET
	@Path("/{orderId}")
	@Produces(MediaType.APPLICATION_XML)
	public String getOrder(@PathParam("orderId") String orderId) {
		Order order = orderService.getOrder(orderId);
		if (order != null) {
			XMLUtil xmlUtil = new XMLUtil();
			return xmlUtil.toXML(order);
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
	}
	
	@PUT
	@Path("/{orderId}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response updateOrder(@PathParam("orderId") String orderId, String orderRepresentation) {
		if (orderService.orderExists(orderId)) {
			try {
				orderService.updateOrder(orderId, new XMLUtil().fromXML(orderRepresentation));
				return Response.ok().entity(orderRepresentation).build();
			} catch (Exception e) {
				throw new WebApplicationException(Response.Status.BAD_REQUEST);
			}
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
}
