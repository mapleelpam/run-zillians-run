/**ZILLIANS INC.
 * run-zillians-run.core
 * CREATOR: Merlyle [merlyle@gmail.com]
 * DATE:    2011-3-2
 */
package com.zillians.dev.tools.flex.platform.core.zsgen;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;


/**
 *
 */
public class ZscriptGenAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window = null;
	private IResource zxmlResource = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		MessageDialog.openInformation(window.getShell(), "zscript client generated",
				"try to generated code...");

		if (zxmlResource != null) {
			String zxml_fp = zxmlResource.getLocation().toString();
			// System.out.println(" ---------------------- " +
			// zxmlResource.getLocation().removeLastSegments(1) );
			ZccRunner.runZccCodeGeneratre(zxml_fp, zxmlResource.getLocation()
					.removeLastSegments(1).toString());

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action
	 * .IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		boolean _should_we_enabled = false;
        IResource resource = null;
        IStructuredSelection struct_selection = null;
        
        if (selection instanceof IStructuredSelection) {
                struct_selection = (IStructuredSelection) (selection);
                if (struct_selection.size() == 1) {
                        Object obj = struct_selection.getFirstElement();
                        if( obj instanceof IResource )
                        {
                                resource = (IResource) ( obj );
                                if ( resource.getName().endsWith(".zxml")) {
                                        System.out.println("2 - zscript - " + resource.getFullPath() );
                                        System.out.println("2 - proejct - " + resource.getProject().getFullPath() );
                                        System.out.println("2 - location - " + resource.getProject().getLocation() );
                                        zxmlResource = resource;
                                        _should_we_enabled = true;
                                }
                        }
                }
                System.out.println("yes selection change set true" + struct_selection.size());
        } else if (selection.isEmpty()) {
                _should_we_enabled = false;
                System.out.println("yes selection is empty\n");
        } else {
                _should_we_enabled = false;
                System.out.println("yes selection change set false\n");
        }
        action.setEnabled(_should_we_enabled);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.
	 * IWorkbenchWindow)
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;

	}

}
