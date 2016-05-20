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
package osgi.jee.samples.ui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;

import osgi.jee.samples.ui.fwks.FwksRegisterer;
import osgi.jee.samples.ui.fwks.ResourceDescriptor;
import osgi.jee.samples.ui.fwks.ResourceRegisterer;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class UISampleActivator implements BundleActivator {

	private ServiceTracker<FwksRegisterer, FwksRegisterer> fwksRegistererTracker;
	private ResourceRegisterer resourceRegisterer;

	/**
	 * {@inheritDoc}
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		fwksRegistererTracker = new ServiceTracker<FwksRegisterer, FwksRegisterer>(context, FwksRegisterer.class.getName(), null) {
			
			@Override
			public FwksRegisterer addingService(ServiceReference<FwksRegisterer> reference) {
				FwksRegisterer service = super.addingService(reference);
				try {
					service.registerFramework(FwksRegisterer.BOOTSTRAP);
				} catch (NamespaceException e) {
					e.printStackTrace();
				}
				return service;
			}
		};
		fwksRegistererTracker.open();
		resourceRegisterer = new ResourceRegisterer(context, new ResourceDescriptor("/web", "/"));
		resourceRegisterer.open();
	}

	/**
	 * {@inheritDoc}
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		fwksRegistererTracker.close();
		fwksRegistererTracker = null;
		resourceRegisterer.close();
		resourceRegisterer = null;
	}

}
