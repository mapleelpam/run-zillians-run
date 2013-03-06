/**ZILLIANS INC.
 * run-zillians-run.core
 * CREATOR: Merlyle [merlyle@gmail.com]
 * DATE:    2011-3-2
 */
package com.zillians.dev.tools.flex.platform.core.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 *
 */
public class MainPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	/**
	 * 
	 */
	public MainPreferencePage() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 */
	public MainPreferencePage(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param image
	 */
	public MainPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
