/**
 * 
 */
package com.zillians.dev.tools.flex.platform.projectwizard.newProject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

/**
 * Creates a new project containing folders and files for use with an
 * Example.com enterprise web site.
 * 
 * @author Nathan A. Good &lt;mail@nathanagood.com&gt;
 * 
 */
public class ZilliansProjectWizard extends Wizard implements INewWizard,
		IExecutableExtension {

	/*
	 * Use the WizardNewProjectCreationPage, which is provided by the Eclipse
	 * framework.
	 */
	private WizardNewProjectCreationPage wizardPage;

	private IConfigurationElement config;

	private IWorkbench workbench;

	private IProject project;

	/**
	 * Constructor
	 */
	public ZilliansProjectWizard() {
		super();
	}

	public void addPages() {
		/*
		 * Unlike the custom new wizard, we just add the pre-defined one and
		 * don't necessarily define our own.
		 */
		wizardPage = new WizardNewProjectCreationPage(
				"NewExampleComSiteProject");
		wizardPage.setDescription("Create a new Example.com Site Project.");
		wizardPage.setTitle("New Example.com Site Project");
		addPage(wizardPage);
	}

	@Override
	public boolean performFinish() {

		if (project != null) {
			return true;
		}

		final IProject projectHandle = wizardPage.getProjectHandle();

		URI projectURI = (!wizardPage.useDefaults()) ? wizardPage
				.getLocationURI() : null;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();

		final IProjectDescription desc = workspace
				.newProjectDescription(projectHandle.getName());

		desc.setLocationURI(projectURI);

		/*
		 * Just like the NewFileWizard, but this time with an operation object
		 * that modifies workspaces.
		 */
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			protected void execute(IProgressMonitor monitor)
					throws CoreException {
				createProject(desc, projectHandle, monitor);
			}
		};

		/*
		 * This isn't as robust as the code in the BasicNewProjectResourceWizard
		 * class. Consider beefing this up to improve error handling.
		 */
		try {
			getContainer().run(true, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();

			System.out.println("===抛出异常:" + realException.getMessage());
		}

		project = projectHandle;

		if (project == null) {
			return false;
		}

		BasicNewProjectResourceWizard.updatePerspective(config);
		BasicNewProjectResourceWizard.selectAndReveal(project,
				workbench.getActiveWorkbenchWindow());

		return true;
	}

	/**
	 * This creates the project in the workspace.
	 * 
	 * @param description
	 * @param projectHandle
	 * @param monitor
	 * @throws CoreException
	 * @throws OperationCanceledException
	 */
	void createProject(IProjectDescription description, IProject proj,
			IProgressMonitor monitor) throws CoreException,
			OperationCanceledException {
		try {

			monitor.beginTask("createProject", 2000);

			proj.create(description, new SubProgressMonitor(monitor, 1000));

			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}

			proj.open(IResource.BACKGROUND_REFRESH, new SubProgressMonitor(
					monitor, 1000));

			createZsrcFolder(proj, monitor);

			/*
			 * Okay, now we have the project and we can do more things with it
			 * before updating the perspective.
			 */
			IContainer container = (IContainer) proj;

			/* Add an XHTML file */
			// addFileToProject(container, new Path("index.html"),
			// NewXHTMLFileWizard.openContentStream("Welcome to "
			// + proj.getName()), monitor);

			/* Add the style folder and the site.css file to it */

			/*
			 * Add the images folder, which is an official Exmample.com standard
			 * for static web projects.
			 */
			IFolder imageFolder = container.getFolder(new Path("src"));
			imageFolder.create(true, true, monitor);
		} catch (IOException ioe) {
			IStatus status = new Status(IStatus.ERROR, "NewFileWizard",
					IStatus.OK, ioe.getLocalizedMessage(), null);
			throw new CoreException(status);
		} finally {
			monitor.done();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
	}

	/**
	 * Sets the initialization data for the wizard.
	 */
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		this.config = config;
	}

	private void createZsrcFolder(IProject proj, IProgressMonitor monitor)
			throws CoreException, IOException {
		IContainer container = (IContainer) proj;

		final IFolder styleFolder = container.getFolder(new Path("zsrc"));
		styleFolder.create(true, true, monitor);
		

		InputStream zxmlStream = this.getClass().getResourceAsStream(
				"template/zsrc/avatar_zxml.resource");
		addFileToProject(container, new Path(styleFolder.getName()
				+ Path.SEPARATOR + "zillians.zxml"), zxmlStream, monitor);
		zxmlStream.close();
		InputStream localPlayerStream = this.getClass().getResourceAsStream(
		"template/zsrc/local_player.resource");
		addFileToProject(container, new Path(styleFolder.getName()
				+ Path.SEPARATOR + "local_player.zs"), localPlayerStream, monitor);
		localPlayerStream.close();
		
		final IFolder subFolder = container.getFolder(new Path("zsrc/remote"));
		subFolder.create(true, true, monitor);
		
		InputStream remotePlayerStream = this.getClass().getResourceAsStream(
		"template/zsrc/remote/player.resource");
		addFileToProject(container, new Path(styleFolder.getName()
				+ Path.SEPARATOR + "remote/player.zs"), remotePlayerStream, monitor);
		remotePlayerStream.close();
	}

	/**
	 * Adds a new file to the project.
	 * 
	 * @param container
	 * @param path
	 * @param contentStream
	 * @param monitor
	 * @throws CoreException
	 */
	private void addFileToProject(IContainer container, Path path,
			InputStream contentStream, IProgressMonitor monitor)
			throws CoreException {
		final IFile file = container.getFile(path);

		if (file.exists()) {
			file.setContents(contentStream, true, true, monitor);
		} else {
			file.create(contentStream, true, monitor);
		}
	}

}
