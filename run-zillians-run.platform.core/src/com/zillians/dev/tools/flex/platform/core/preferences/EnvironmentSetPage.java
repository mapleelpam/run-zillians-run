/**ZILLIANS INC.
 * run-zillians-run.core
 * CREATOR: Merlyle [merlyle@gmail.com]
 * DATE:    2011-3-2
 */
package com.zillians.dev.tools.flex.platform.core.preferences;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.zillians.dev.tools.flex.platform.core.ZilliansActivator;

/**
 *
 */
public class EnvironmentSetPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private IPreferenceStore preStore = null;

	private DirectoryFieldEditor edt_zsdk_path = null;
//	private FileFieldEditor edt_zcc_path = null;
	private BooleanFieldEditor zUseEnv = null;
	private StringFieldEditor	serverUrlField = null;
	/**
	 * 
	 */
	public EnvironmentSetPage() {
		super(GRID);
		setPreferenceStore(ZilliansActivator.getDefault().getPreferenceStore());
		setDescription("Zillians Prefernece Set");

		preStore = this.getPreferenceStore();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors() {
		edt_zsdk_path = new DirectoryFieldEditor(
				PreferenceConstants.Z_LIB_PATH, "&Zillians API Library path:",
				getFieldEditorParent());
//		edt_zcc_path = new FileFieldEditor(
//				PreferenceConstants.Z_ZCC_PATH, "zcc-flex path:",
//				getFieldEditorParent());
		

		
		zUseEnv = new BooleanFieldEditor(PreferenceConstants.Z_UES_ENV,
				"&Read $ZILLIANS_HOME environment variable", getFieldEditorParent());
		
		serverUrlField	= new StringFieldEditor(
				PreferenceConstants.Z_UPLOAD_URL, "Upload Server URL",
				getFieldEditorParent());
		
		
		if (preStore.getBoolean(PreferenceConstants.Z_UES_ENV)) {
			edt_zsdk_path.setEnabled(false, getFieldEditorParent());
//			edt_zcc_path.setEnabled(false, getFieldEditorParent());
		}
		if ( preStore.getBoolean(PreferenceConstants.Z_UPLOAD_URL ) ) {
			serverUrlField.setEnabled(true, getFieldEditorParent());
			serverUrlField.setStringValue("http://192.168.1.1:8001/");
		}


		zUseEnv.getDescriptionControl(getFieldEditorParent()).addMouseListener(
				new MouseListener() {

					@Override
					public void mouseUp(MouseEvent e) {
						if (zUseEnv.getBooleanValue()) {
							String env = System.getenv("ZILLIANS_HOME").replace('\\', '/');
							if (env == null || env.equals("")) {
								System.out.println("###env null.");
								MessageDialog.openInformation(getShell(),
										"Run-zillians-run",
										"ZILLIANS_HOME null");
							} else {
								System.out.println("###path change to:" + env);
								edt_zsdk_path.setStringValue(env);
								edt_zsdk_path.setEnabled(false, getFieldEditorParent());
//								edt_zcc_path.setStringValue(env+"bin");
//								edt_zcc_path.setEnabled(false, getFieldEditorParent());
							}
						}
						else {
							edt_zsdk_path.setEnabled(true, getFieldEditorParent());
//							edt_zcc_path.setEnabled(true, getFieldEditorParent());
						}

					}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		addField(edt_zsdk_path);
//		addField(edt_zcc_path);
		addField(zUseEnv);
		addField(serverUrlField);
		
		
	}

	public static void main (String[] argc) {
		String s = System.getenv("ZILLIANS_HOME");
		s = s.replace('\\', '/');
		System.out.println("===" + s);
	}

}
