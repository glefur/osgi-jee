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
package osgi.jee.samples.jpa.util.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import osgi.jee.samples.jpa.util.services.ServiceProvider;
import osgi.jee.samples.jpa.util.services.ServiceProviderRegistry;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class ServiceProviderRegistryImpl<S, K> implements ServiceProviderRegistry<S, K> {

	private Collection<ServiceProvider<S, K>> providers;
	
	public ServiceProviderRegistryImpl() {
		providers = new ArrayList<ServiceProvider<S,K>>();
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.services.ServiceProviderRegistry#register(osgi.jee.samples.jpa.util.services.ServiceProvider)
	 */
	@Override
	public <SERVICE_PROVIDER extends ServiceProvider<S, K>> void register(SERVICE_PROVIDER serviceProvider) {
		providers.add(serviceProvider);
		
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.services.ServiceProviderRegistry#unregister(osgi.jee.samples.jpa.util.services.ServiceProvider)
	 */
	@Override
	public <ANY_SUBTYPE_OF_SERVICE_PROVIDER extends ServiceProvider<S, K>> void unregister(ANY_SUBTYPE_OF_SERVICE_PROVIDER serviceProvider) {
		providers.remove(serviceProvider);
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.services.ServiceProviderRegistry#getService(java.lang.Object)
	 */
	@Override
	public S getService(K key) {
		for (ServiceProvider<S, K> provider : providers) {
			if (provider.provides(key)) {
				return provider.getService(key);
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see osgi.jee.samples.jpa.util.services.ServiceProviderRegistry#getAllServices(java.lang.Object)
	 */
	@Override
	public Collection<S> getAllServices(K key) {
		Collection<S> allServices = new ArrayList<S>();
		for (ServiceProvider<S, K> provider : providers) {
			if (provider.provides(key)) {
				allServices.add(provider.getService(key));
			}
		}
		return allServices;
	}

}
