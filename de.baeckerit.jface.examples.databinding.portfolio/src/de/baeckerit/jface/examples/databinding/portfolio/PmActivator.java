package de.baeckerit.jface.examples.databinding.portfolio;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventAdmin;

/**
 * The activator class controls the plug-in life cycle
 */
public class PmActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.baeckerit.jface.examples.databinding.portfolio"; //$NON-NLS-1$

	// The shared instance
	private static PmActivator plugin;

	private EventAdmin eventAdmin;

	/**
	 * The constructor
	 */
	public PmActivator() {
	}

	public EventAdmin getEventAdmin() {
		return eventAdmin;
	}

	public BundleContext getBundleContext() {
		return getBundle().getBundleContext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		eventAdmin = (EventAdmin) context.getService(context.getServiceReference(EventAdmin.class.getName()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static PmActivator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
