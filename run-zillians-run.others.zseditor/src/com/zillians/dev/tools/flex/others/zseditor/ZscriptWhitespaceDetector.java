package com.zillians.dev.tools.flex.others.zseditor;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class ZscriptWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r' || c ==';');
	}
}
