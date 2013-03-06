package com.zillians.dev.tools.flex.others.zseditor;

import org.eclipse.jface.text.rules.IWordDetector;
public class KeywordDetector implements IWordDetector {

	public KeywordDetector (String[] strings) {
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
