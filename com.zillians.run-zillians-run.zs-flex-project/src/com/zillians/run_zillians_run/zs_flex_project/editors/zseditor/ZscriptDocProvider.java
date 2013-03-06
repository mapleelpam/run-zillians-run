package com.zillians.run_zillians_run.zs_flex_project.editors.zseditor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class ZscriptDocProvider extends FileDocumentProvider {

	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if (document != null) {
			IDocumentPartitioner partitioner =
				new FastPartitioner(
					new RuleBasedPartitionScanner(),
					new String[] {
						IDocument.DEFAULT_CONTENT_TYPE,IDocument.DEFAULT_CATEGORY});
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}
}