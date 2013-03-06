package com.zillians.run_zillians_run.zs_flex_project.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.zillians.run_zillians_run.zs_flex_project.ZccRunner;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class ZccFlexAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private IResource zxmlResource = null;
	/**
	 * The constructor.
	 */
	public ZccFlexAction() {
		zxmlResource = null;
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		MessageDialog.openInformation(
			window.getShell(),
			"Zs-flex-project",
			"Run, Zillians Run");
		
		try {
		    // Construct data
		    String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8");
		    data += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("value2", "UTF-8");

		    // Send data
		    URL url = new URL("http://www.google.com/ncr");
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(data);
		    wr.flush();

		    // Get the response
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line;
		    while ((line = rd.readLine()) != null) {
		        System.out.println("###:" + line);
		    }
		    wr.close();
		    rd.close();
		} catch (Exception e) {
		}

		
		
		if( zxmlResource != null ) {
			String zxml_fp = zxmlResource.getLocation().toString();
//			System.out.println(" ---------------------- " + zxmlResource.getLocation().removeLastSegments(1) );
			ZccRunner.runZccCodeGeneratre(zxml_fp, zxmlResource.getLocation().removeLastSegments(1).toString());
			
			
			
		}
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
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

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}