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

import java.util.Collection;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public interface ServiceProviderRegistry<S, K> {
	
	<SERVICE_PROVIDER extends ServiceProvider<S, K>> void register(SERVICE_PROVIDER serviceProvider);
	
	<SERVICE_PROVIDER extends ServiceProvider<S, K>> void unregister(SERVICE_PROVIDER serviceProvider);
	
	S getService(K key);
	
	Collection<S> getAllServices(K key);

}
