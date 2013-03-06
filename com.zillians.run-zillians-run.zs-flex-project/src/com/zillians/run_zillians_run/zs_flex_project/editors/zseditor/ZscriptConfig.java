package com.zillians.run_zillians_run.zs_flex_project.editors.zseditor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class ZscriptConfig extends SourceViewerConfiguration {
	private ColorManager colorManager;

	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			IDocument.DEFAULT_CATEGORY };
	}
	
	public ZscriptConfig(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = 
				new DefaultDamagerRepairer(
						new ZscriptScanner(colorManager));
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		

		return reconciler;
	}
	
}
