/**
 * OSGi/JEE Sample.
 * 
 * Copyright (C) 2014 Goulwen Le Fur
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
package osgi.jee.samples.jpa.util.services;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public final class OSGIHelper {
	
	
	public static <T> T getService(Class<?> context, Class<T> requiredService) {
		Bundle bundle = FrameworkUtil.getBundle(context);
		if (bundle != null) {
			BundleContext bundleContext = bundle.getBundleContext();
			ServiceReference<T> serviceReference = bundleContext.getServiceReference(requiredService);
			if (serviceReference != null) {
				return bundleContext.getService(serviceReference); 
			} else {
				throw new IllegalStateException("No service of type " + requiredService.getName() + " seems to be provided");				
			}
		} else {
			throw new IllegalStateException("This application seems not to be launched in a proper OSGi-context");
		}
		
	}

}
