package com.zillians.dev.tools.flex.others.zseditor;

import org.eclipse.ui.editors.text.TextEditor;






public class ZsEditor extends TextEditor {

	private ColorManager colorManager;

	public ZsEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new ZscriptConfig(colorManager));
		setDocumentProvider(new ZscriptDocProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
