package com.zillians.dev.tools.flex.others.zseditor;

public interface ZscriptConstants {
	
	/***************
	 * 
	 * 关键词字典，请保持按字母顺序排序！！！
	 * 
	 * 
	 * ***************/
	
	
	String[] ListOfPrimitiveType = {
			 "string",
			 "arrary",
			 "set",
			 "list",
			 "object",
			 
			"bool",			
			"float32", 
			"float64", 
			
			"int8",
			"int16",
			"int32",
			"int64",
			"uint8",
			"uint16",
			"uint32",
			"uint64",
			"void",
			"_ming"
			 };

	String[] ListOfKeyword = {
			"abstract", 
			"break", 
			"case", "catch", "class", "const", "continue", 
			"default", "delete", "do", 
			"else", "extends",
			"false", "final", "finally", "for", "function", 
			"if", "import", 
			"new", 
			"package",  "struct",
			"return", 
			"static", "switch", 
			"throws", "true", "try", "typeof", 
			"while", "var", "null"
			  };

	String[] ListOfAnnotations = {
			"@client", "@handler", "@server", "@target",  "@nosync", "@atomic" };

	String[] ListOfSpecial = {
			"self", "global", "this" };

	String WORD_DETECTOR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
}
