/**
 * 
 */
package server;

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import server.tests.persistence.TestService;


/**
 * @author <a href="mailto:goulwen.lefur@smartcontext.fr">Goulwen Le Fur</a>
 * 
 */
public class SlideUBackend implements BundleActivator {

	private ServiceTracker<EntityManagerFactory, EntityManagerFactory> emfTracker;
    private ServiceRegistration<TestService> serviceRegistration;

	/**
	 * {@inheritDoc}
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext context) throws Exception {
		System.out.println("Start");
		emfTracker = new ServiceTracker<EntityManagerFactory, EntityManagerFactory>(context, EntityManagerFactory.class, new ServiceTrackerCustomizer<EntityManagerFactory, EntityManagerFactory>() {

            /**
             * {@inheritDoc}
             * @see org.osgi.util.tracker.ServiceTrackerCustomizer#addingService(org.osgi.framework.ServiceReference)
             */
            public EntityManagerFactory addingService(ServiceReference<EntityManagerFactory> ref) {
                EntityManagerFactory emf = context.getService(ref);
                TestService service = new TestService(emf);
                serviceRegistration = context.registerService(TestService.class, service, null);
                return emf;
            }

            /**
             * {@inheritDoc}
             * @see org.osgi.util.tracker.ServiceTrackerCustomizer#modifiedService(org.osgi.framework.ServiceReference, java.lang.Object)
             */
            public void modifiedService(ServiceReference<EntityManagerFactory> arg0, EntityManagerFactory arg1) {
                
            }

            /**
             * {@inheritDoc}
             * @see org.osgi.util.tracker.ServiceTrackerCustomizer#removedService(org.osgi.framework.ServiceReference, java.lang.Object)
             */
            public void removedService(ServiceReference<EntityManagerFactory> arg0, EntityManagerFactory arg1) {
                serviceRegistration.unregister();
            }
        });
		emfTracker.open();
	}

	/**
	 * {@inheritDoc}
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop");
		emfTracker.close();
	}

}
