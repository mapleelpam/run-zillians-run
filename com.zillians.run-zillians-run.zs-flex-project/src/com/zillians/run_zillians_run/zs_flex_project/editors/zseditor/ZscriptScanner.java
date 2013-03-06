package com.zillians.run_zillians_run.zs_flex_project.editors.zseditor;

import java.util.ArrayList;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

public class ZscriptScanner extends RuleBasedScanner {
	private TextAttribute keywords ;			//关键字的文本属性
	private TextAttribute string ;				//字符串的文本属性
	private TextAttribute object ;				//内置对象的文本属性
	private TextAttribute comment ;				//注释部分的文本属性
	private TextAttribute annotation ;			//注解部分的文本属性
	private TextAttribute mainObject ;			//重要对象的文本属性
	
	public ZscriptScanner( ColorManager colorManager ){

		keywords = new TextAttribute (colorManager.getColor(ZscriptColorConstants.KEYWORD));
		string = new TextAttribute (colorManager.getColor(ZscriptColorConstants.STRING));
		object = new TextAttribute (colorManager.getColor(ZscriptColorConstants.OBJECT));
		comment = new TextAttribute (colorManager.getColor(ZscriptColorConstants.COMMENT));
		annotation = new TextAttribute (colorManager.getColor(ZscriptColorConstants.ANNOTATION));
		mainObject = new TextAttribute (colorManager.getColor(ZscriptColorConstants.MAIN_OBJECT));
		//设置代码的规则
		setupRules();
	}
	private void setupRules() {

	    ArrayList<IRule> rules = new ArrayList<IRule>();
	    
	    //字符串规则
	    rules.add(new SingleLineRule("\"", "\"",new Token( string), '\\'));
	    //注释规则
	    rules.add(new MultiLineRule("/*", "*/", new Token( comment), '\\'));
	    rules.add(new EndOfLineRule("//", new Token( comment),'\\'));
	    
	    
	    //关键字规则
	    String[] list_keyword = ZscriptConstants.ListOfKeyword;
	    WordRule keywordRule = new WordRule(new KeywordDetector( ZscriptConstants.ListOfKeyword ));
	    IToken keywordsToken = new Token( keywords );
	    for (int i = 0;i < list_keyword.length; i++)
	    	keywordRule.addWord(list_keyword[i], keywordsToken);
	    rules.add(keywordRule);
	    
	    //ZILLIANS对象规则
//	    String[] list_object = ZscriptConstants.ListOfPrimitiveType;
//	    WordRule objectRule = new WordRule(new KeywordDetector( ZscriptConstants.WORD_DETECTOR ));
//	    
//	    for (int i = 0; i < list_object.length; i++)
//	    	objectRule.addWord(list_object[i], new Token( object ));
//	    rules.add(objectRule);
//	    
//	    //ZILLIANS重要对象规则
//	    String[] special_list = ZscriptConstants.ListOfSpecial;
//	    WordRule mainObjectRule = new WordRule(new KeywordDetector( ZscriptConstants.WORD_DETECTOR ));
//	    
//	    for (int i = 0; i < special_list.length; i++)
//	    	mainObjectRule.addWord(special_list[i], new Token( mainObject ));
//	    rules.add(mainObjectRule);
//	    
//	    //ZILLIANS注解规则
//	    String[] anno_list = ZscriptConstants.ListOfAnnotations;
//	    WordRule annotationRule = new WordRule(new KeywordDetector( ZscriptConstants.WORD_DETECTOR + "@" ));
//	    
//	    for (int i = 0; i < anno_list.length; i++)
//	    	annotationRule.addWord(anno_list[i], new Token( annotation ));
//	    rules.add(annotationRule);
	    
	    //空格规则
	    rules.add(new WhitespaceRule(new ZscriptWhitespaceDetector()));
	    
	    IRule[] result = new IRule[rules.size()];
	    rules.toArray(result);

	    setRules(result);
	}
	
}
