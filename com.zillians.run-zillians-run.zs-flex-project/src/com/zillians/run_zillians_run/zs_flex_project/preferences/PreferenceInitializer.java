package com.zillians.run_zillians_run.zs_flex_project.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.core.runtime.Platform;
import com.zillians.run_zillians_run.zs_flex_project.ZccActivator;


/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = ZccActivator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.Z_UES_ENV, true);
		
		store.setDefault(PreferenceConstants.P_CHOICE, "choice2");
		
		store.setDefault(PreferenceConstants.P_STRING, "Default value");
		
        if (Platform.getOS().equals(Platform.OS_WIN32)) {
        	/* TODO: ? default where */
        } else if  (Platform.getOS().equals(Platform.OS_LINUX)) {
        	store.setDefault(PreferenceConstants.Z_ZCC_PATH, "/opt/zillians/bin/zcc-flex" );
    		store.setDefault(PreferenceConstants.Z_LIB_PATH, "/opt/zillians/inc/" );
    		store.setDefault(PreferenceConstants.Z_ZHOME_PATH, "/opt/zillians/" );
        } else {
        	/*TODO: other os !? error? or warning */
        }
	}

}
