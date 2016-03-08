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
package osgi.jee.samples.rest.restbucks.crud;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.ComponentContext;

/**
 * Abstract infrastructure for Restbucks servlets. 
 * 
 * It manages the path of the servlet and provides to general services to underlying servlets.
 * 
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
@SuppressWarnings("serial")
public abstract class RestbuckServlet extends HttpServlet {
	
	private String path;
	
	public void activate(ComponentContext context) {
		path = (String) context.getProperties().get("servlet.path");
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.rest.restbucks.crud.RestbuckServlet#getPath()
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param req
	 * @return
	 * @throws IOException
	 */
	protected String getContentsAsString(HttpServletRequest req) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		String string = builder.toString();
		return string;
	}

}
