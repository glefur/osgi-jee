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
package osgi.jee.samples.ui.fwks;

import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class FwksRegisterer {

	public static final ResourceDescriptor BOOTSTRAP = new ResourceDescriptor("fwks/bootstrap-3.3.6-dist", "/bootstrap");
	public static final ResourceDescriptor ANGULAR = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular", "/angular");
	public static final ResourceDescriptor ANGULAR_ANIMATE = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular-animate", "/angular/animate");
	public static final ResourceDescriptor ANGULAR_ARIA = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular-aria", "/angular/aria");
	public static final ResourceDescriptor ANGULAR_COOKIES = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular-cookies", "/angular/cookies");
	public static final ResourceDescriptor ANGULAR_LOADER = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular-loader", "/angular/loader");
	public static final ResourceDescriptor ANGULAR_MESSAGES = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular-messages", "/angular/messages");
	public static final ResourceDescriptor ANGULAR_MESSAGE_FORMAT = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular-message-format", "/angular/message-format");
	public static final ResourceDescriptor ANGULAR_PARSE_EXT = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular-parse-ext", "/angular/parse-ext");
	public static final ResourceDescriptor ANGULAR_RESOURCE = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular-resource", "/angular/resource");
	public static final ResourceDescriptor ANGULAR_ROUTE = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular-route", "/angular/route");
	public static final ResourceDescriptor ANGULAR_SANITIZE = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular-sanitize", "/angular/sanitize");
	public static final ResourceDescriptor ANGULAR_TOUCH = new ResourceDescriptor("fwks/angular-1.5.6-dist/angular-touch", "/angular/touch");

	private HttpService httpService;
	
	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}

	public void registerFramework(ResourceDescriptor... fwks) throws NamespaceException {
		for (ResourceDescriptor fwk : fwks) {
			httpService.registerResources(fwk.alias, fwk.path, null);
		}
	}

}
