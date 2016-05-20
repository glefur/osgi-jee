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

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class ResourceRegisterer extends ServiceTracker<HttpService, HttpService> {

	private ResourceDescriptor[] descriptors;

	public ResourceRegisterer(BundleContext context, ResourceDescriptor... descriptors) {
		super(context, HttpService.class.getName(), null);
		this.descriptors = descriptors;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
	 */
	@Override
	public HttpService addingService(ServiceReference<HttpService> reference) {
		HttpService service = super.addingService(reference);
		for (ResourceDescriptor resourceDescriptor : descriptors) {
			try {
				service.registerResources(resourceDescriptor.alias, resourceDescriptor.path, null);
			} catch (NamespaceException e) {
				throw new RuntimeException("Unable to register resource " + resourceDescriptor.path, e);
			}
		}
		return service;
	}

}
