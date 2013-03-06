package com.zillians.run_zillians_run.zs_flex_project.editors.zseditor;

import org.eclipse.jface.text.rules.IWordDetector;
public class KeywordDetector implements IWordDetector {

	private String[] strings = null;
	
	public KeywordDetector (String[] strings) {
		this.strings = new String[]{"new"};
	}
	
	// 接口中的方法,字符是否是单词的开始
	public boolean isWordStart(char c) {
//		for (String s:strings) {
//			if (s.charAt(0) == c) {
//				return true;
//
//			}
//		}
//		return false;
		return Character.isJavaIdentifierStart(c);
	}

	// 接口中的方法,字符是否是单词中的一部分
	public boolean isWordPart(char c) {
//		for (String s:strings) {
//			if (s.indexOf(c) != -1) {
//				return true;
//			}
//		}
//		return false;
		return Character.isJavaIdentifierPart(c);
	}

}
