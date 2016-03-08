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
package osgi.jee.samples.rest.restbucks.internal.crud;

import javax.servlet.ServletException;

import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

import osgi.jee.samples.rest.restbucks.crud.RestbuckServlet;

/**
 * Service automatically registering Restbucks servlet into the current servlet engine.
 * 
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class ServletRegister {

	private HttpService httpService;
	

	/**
	 * @param httpService the httpService to set
	 */
	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}
	
	/**
	 * Registers a new servlet into the current servlet engine.
	 * @param servlet the servlet to register.
	 * @throws ServletException an error occurred during registration.
	 * @throws NamespaceException an error occurred during registration.
	 */
	public void addServlet(RestbuckServlet servlet) throws ServletException, NamespaceException {
		httpService.registerServlet("/" + servlet.getPath(), servlet, null, null);
	}
	
}
