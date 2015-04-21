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
package osgi.jee.samples.jpa.tests;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import osgi.jee.samples.jpa.dao.connection.DataConnectionFactoryRegistry;
import osgi.jee.samples.jpa.dao.db.DataBaseHandler;
import osgi.jee.samples.jpa.model.EmploymentFactory;
import osgi.jee.samples.model.dao.AddressDAO;

/**
 * @author <a href="mailto:goulwen.lefur@gmail.com">Goulwen Le Fur</a>.
 *
 */
public class TestsActivator implements BundleActivator {

    private static final String TEST_RESOURCES_PATH = "src/test/resources/";
	private static TestsActivator instance;
	private Bundle bundle;
        
    /**
     * @return the instance
     */
    public static TestsActivator getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        instance = this;
        bundle = context.getBundle();
    }

    /**
     * {@inheritDoc}
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext arg0) throws Exception {
    }
        
    public EntityManagerFactory getEntityManagerFactory() {
    	ServiceReference<EntityManagerFactory> serviceReference = bundle.getBundleContext().getServiceReference(EntityManagerFactory.class);
    	return bundle.getBundleContext().getService(serviceReference);    	
    }
    
    public DataConnectionFactoryRegistry getDataConnectionFactoryRegistry() {
    	ServiceReference<DataConnectionFactoryRegistry> reference = bundle.getBundleContext().getServiceReference(DataConnectionFactoryRegistry.class);
    	return bundle.getBundleContext().getService(reference);    	
    }
    
    public DataBaseHandler getDataBaseHandler() {
    	ServiceReference<DataBaseHandler> reference = bundle.getBundleContext().getServiceReference(DataBaseHandler.class);
    	return bundle.getBundleContext().getService(reference);
    }
    
    public EmploymentFactory getEmploymentFactory() {
    	ServiceReference<EmploymentFactory> reference = bundle.getBundleContext().getServiceReference(EmploymentFactory.class);
    	return bundle.getBundleContext().getService(reference);
    }
    
    public AddressDAO getAddressDAO() {
    	ServiceReference<AddressDAO> reference = bundle.getBundleContext().getServiceReference(AddressDAO.class);
    	return bundle.getBundleContext().getService(reference);    	
    }
    
    public InputStream getResource(String path) throws IOException {
    	URL entry = bundle.getEntry(path);
    	if (entry != null) {
    		return entry.openStream();
    	}
    	return null;
    }
    
    public InputStream getTestResource(String name) throws IOException {
    	URL entry = bundle.getEntry(TEST_RESOURCES_PATH + name);
    	if (entry != null) {
    		return entry.openStream();
    	}
    	return null;
    }
    
}
