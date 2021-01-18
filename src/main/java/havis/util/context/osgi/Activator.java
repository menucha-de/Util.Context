package havis.util.context.osgi;

import java.util.logging.Logger;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	Logger log = Logger.getLogger(Activator.class.getName());

	private ServiceRegistration<?> registration;

	@Override
	public void start(BundleContext context) throws Exception {
		if (registration == null) {
			registration = context.registerService(ClassLoader.class.getName(), new ServiceFactory<ClassLoader>() {
				@Override
				public ClassLoader getService(Bundle bundle, ServiceRegistration<ClassLoader> registration) {
					return Activator.class.getClassLoader();
				}

				@Override
				public void ungetService(Bundle bundle, ServiceRegistration<ClassLoader> registration, ClassLoader service) {
				}
			}, null);
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (registration != null) {
			registration.unregister();
			registration = null;
		}
	}
}