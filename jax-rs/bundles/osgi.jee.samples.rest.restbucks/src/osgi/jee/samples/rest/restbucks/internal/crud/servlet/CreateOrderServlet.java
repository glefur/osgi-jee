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
package osgi.jee.samples.rest.restbucks.internal.crud.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import osgi.jee.samples.rest.restbucks.crud.servlet.RestbucksServlet;
import osgi.jee.samples.rest.restbucks.model.Order;
import osgi.jee.samples.rest.restbucks.model.xml.XMLUtil;
import osgi.jee.samples.rest.restbucks.services.OrderManager;

/**
 * This servlet process order creation.
 * 
 * It parses the POX in the post request and creates the order described in this one.
 * 
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class CreateOrderServlet extends RestbucksServlet {

	/**
	 * Serialization ID 
	 */
	private static final long serialVersionUID = 2515153789916645819L;
	
	private OrderManager service;

	/**
	 * @param service the service to set
	 */
	public void setOrderService(OrderManager service) {
		this.service = service;
	}

	/**
	 * {@inheritDoc}
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String contents = getContentsAsString(req);
			Order order = new XMLUtil().fromXML(contents);
			if (order == null) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			} else {
				String id = service.createOrder(order);
				resp.setHeader("Location", "localhost:9092/order/" + id);
				resp.setStatus(HttpServletResponse.SC_CREATED);
			}
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);				
		}
	}

}
