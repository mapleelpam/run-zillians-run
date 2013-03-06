package com.zillians.run_zillians_run.zs_flex_project.editors.zseditor;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class ZscriptWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r' || c ==';');
	}
}
