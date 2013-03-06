package com.zillians.run_zillians_run.zs_flex_project.preferences;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.zillians.run_zillians_run.zs_flex_project.ZccActivator;

 
/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class ZilliansPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private IPreferenceStore preStore = null;

	private DirectoryFieldEditor edt_zlib_path = null;
	private FileFieldEditor edt_zcc_path = null;
	private BooleanFieldEditor zUseEnv = null;

	public ZilliansPreferencePage() {
		super(GRID);
		setPreferenceStore(ZccActivator.getDefault().getPreferenceStore());
		setDescription("Zillians Prefernece Set");

		preStore = this.getPreferenceStore();

//		preStore.addPropertyChangeListener(new IPropertyChangeListener() {
//			@Override
//			public void propertyChange(PropertyChangeEvent event) {
//				if (event != null && event.getProperty().equals(PreferenceConstants.Z_UES_ENV)) {
//					if (preStore.getBoolean(PreferenceConstants.Z_UES_ENV)) {
//						String env = System.getenv("ZILLIANS_HOME");
//						if (env == null || env.equals("") ) {
//							System.out.println("###env null.");
//							MessageDialog.openInformation( getShell(), "Run-zillians-run", "ZILLIANS_HOME null！");
//						} else {
//							preStore.setValue(
//									PreferenceConstants.Z_FOLDER_PATH, env);
//							System.out.println("###path change to:" + env);
//							zFolderPath.setStringValue(env);
//							zFolderPath.setEnabled(false, getFieldEditorParent());
//							//MessageDialog.openInformation( getShell(), "Run-zillians-run", "ZILLIANS_HOME：" + env);
//						}
//
//					}
//					else {
//						zFolderPath.setEnabled(true, getFieldEditorParent());
//					}
//				}
//			}
//		});
		
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		edt_zlib_path = new DirectoryFieldEditor(
				PreferenceConstants.Z_LIB_PATH, "&Zillians API Library path:",
				getFieldEditorParent());
		edt_zcc_path = new FileFieldEditor(
				PreferenceConstants.Z_ZCC_PATH, "zcc-flex path:",
				getFieldEditorParent());

		
		zUseEnv = new BooleanFieldEditor(PreferenceConstants.Z_UES_ENV,
				"&Read $ZILLIANS_HOME environment variable", getFieldEditorParent());
		
		if (preStore.getBoolean(PreferenceConstants.Z_UES_ENV)) {
			edt_zlib_path.setEnabled(false, getFieldEditorParent());
			edt_zcc_path.setEnabled(false, getFieldEditorParent());
		}


		zUseEnv.getDescriptionControl(getFieldEditorParent()).addMouseListener(
				new MouseListener() {

					@Override
					public void mouseUp(MouseEvent e) {
						if (zUseEnv.getBooleanValue()) {
							String env = System.getenv("ZILLIANS_HOME");
							if (env == null || env.equals("")) {
								System.out.println("###env null.");
								MessageDialog.openInformation(getShell(),
										"Run-zillians-run",
										"ZILLIANS_HOME null！");
							} else {
								System.out.println("###path change to:" + env);
								edt_zlib_path.setStringValue(env+"inc/");
								edt_zlib_path.setEnabled(false, getFieldEditorParent());
								edt_zcc_path.setStringValue(env+"bin/zcc-flex");
								edt_zcc_path.setEnabled(false, getFieldEditorParent());
							}
						}
						else {
							edt_zlib_path.setEnabled(true, getFieldEditorParent());
							edt_zcc_path.setEnabled(true, getFieldEditorParent());
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

		addField(edt_zlib_path);
		addField(edt_zcc_path);
		addField(zUseEnv);

		// addField(new RadioGroupFieldEditor(
		// PreferenceConstants.P_CHOICE,
		// "An example of a multiple-choice preference",
		// 1,
		// new String[][] { { "&Choice 1", "choice1" }, {
		// "C&hoice 2", "choice2" }
		// }, getFieldEditorParent()));
		// addField(
		// new StringFieldEditor(PreferenceConstants.P_STRING,
		// "A &text preference:", getFieldEditorParent()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}



}