package com.zillians.dev.tools.flex.platform.core;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.zillians.dev.tools.flex.platform.core.ZilliansActivator;

/**
 * The activator class controls the plug-in life cycle
 */
public class ZilliansActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.zillians.dev.tools.flex.platform.core"; //$NON-NLS-1$

	// The shared instance
	private static ZilliansActivator plugin;
	
	/**
	 * The constructor
	 */
	public ZilliansActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
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
	public static ZilliansActivator getDefault() {
		return plugin;
	}

}
