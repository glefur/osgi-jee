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
package osgi.jee.samples.rest.restbucks.hypermedia.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
@Path("/order")
public class OrderResource {
	private @Context UriInfo uriInfo;
	@GET
	@Path("/{orderId}")
	@Produces("application/vnd.restbucks+xml")
	public Response getOrder() {
		try {
			OrderRepresentation responseRepresentation = new ReadOrderActivity()
					.retrieveByUri(new RestbucksUri(uriInfo.getRequestUri()));
			return Response.ok().entity(responseRepresentation).build();
		} catch(NoSuchOrderException nsoe) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (Exception ex) {
			return Response.serverError().build();
		}
	}
	@POST
	@Consumes("application/vnd.restbucks+xml")
	@Produces("application/vnd.restbucks+xml")
	public Response createOrder(String orderRepresentation) {
		try {
			OrderRepresentation responseRepresentation = new CreateOrderActivity().create(OrderRepresentation.fromXmlString(orderRepresentation).getOrder(),
					new RestbucksUri(uriInfo.getRequestUri()));
			return Response.created(
					responseRepresentation.getUpdateLink().getUri())
					.entity(responseRepresentation).build();
		} catch (InvalidOrderException ioe) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception ex) {
			return Response.serverError().build();
		}
	}
	@DELETE
	@Path("/{orderId}")
	@Produces("application/vnd.restbucks+xml")
	public Response removeOrder() {
		try {
			OrderRepresentation removedOrder = new RemoveOrderActivity()
					.delete(new RestbucksUri(uriInfo.getRequestUri()));
			return Response.ok().entity(removedOrder).build();
		} catch (NoSuchOrderException nsoe) {
			return Response.status(Status.NOT_FOUND).build();
		} catch(OrderDeletionException ode) {
			return Response.status(405).header("Allow", "GET").build();
		} catch (Exception ex) {
			return Response.serverError().build();
		}
	}
	@POST
	@Path("/{orderId}")
	@Consumes("application/vnd.restbucks+xml")
	@Produces("application/vnd.restbucks+xml")
	public Response updateOrder(String orderRepresentation) {
		try {
			OrderRepresentation responseRepresentation = new UpdateOrderActivity()
					.update(
							OrderRepresentation.fromXmlString(
									orderRepresentation)
							.getOrder(), new RestbucksUri(uriInfo.getRequestUri()));
			return Response.ok().entity(responseRepresentation).build();
		} catch (InvalidOrderException ioe) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (NoSuchOrderException nsoe) {
			return Response.status(Status.NOT_FOUND).build();
		} catch(UpdateException ue) {
			return Response.status(Status.CONFLICT).build();
		} catch (Exception ex) {
			return Response.serverError().build();
		}
	}
}