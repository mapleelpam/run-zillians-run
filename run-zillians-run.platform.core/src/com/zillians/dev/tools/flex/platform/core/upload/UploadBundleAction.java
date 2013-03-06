/**ZILLIANS INC.
 * run-zillians-run.core
 * CREATOR: Merlyle [merlyle@gmail.com]
 * DATE:    2011-3-2
 */
package com.zillians.dev.tools.flex.platform.core.upload;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 *
 */
public class UploadBundleAction implements IWorkbenchWindowActionDelegate {
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		UploadDialog dialog = new UploadDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		dialog.open();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		System.out.println("===========================");
		if (selection instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection)selection).getFirstElement();

		    IProject project = null;
		    System.out.println("===IStructuredSelection");
		    if (element instanceof IResource) {
		        project= ((IResource)element).getProject();
		        System.out.println("===UploadZipTool.setProjectLocation:" + project.getLocation().toString());
		        UploadZipTool.getInit().setProjectLocation(project.getLocation().toString());
		    }
		}
		else if ( selection instanceof ITreeSelection ) {
			System.out.println("===ITreeSelection");
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	@Override
	public void dispose() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	@Override
	public void init(IWorkbenchWindow window) {
	}

}
