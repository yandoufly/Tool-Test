package com.lucene.analyzer;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.lucene.utils.LuceneUtil;

/**
 * 测试Lucene内置和第三方分词器的分词效果
 */
public class TestAnalyzer {
	private static void testAnalyzer(Analyzer analyzer, String text) throws Exception {
		System.out.println("当前使用的分词器：" + analyzer.getClass());
		TokenStream tokenStream = analyzer.tokenStream("content",new StringReader(text));
		tokenStream.addAttribute(TermAttribute.class);
		while (tokenStream.incrementToken()) {
			TermAttribute termAttribute = tokenStream.getAttribute(TermAttribute.class);
			System.out.println(termAttribute.term());
		}
	}
	
	public static void main(String[] args) throws Exception {
		//内置分词器
//		testAnalyzer(new StandardAnalyzer(LuceneUtil.getVersion()), "传智博客说我们的首都是北京啊 I am IT");
		//法国分词器
//		testAnalyzer(new FrenchAnalyzer(LuceneUtil.getVersion()), "传智博客说我们的首都是北京啊 I am IT");

//		testAnalyzer(new CJKAnalyzer(LuceneUtil.getVersion()), "传智博客说我们的首都是北京啊 I am IT");
		testAnalyzer(new IKAnalyzer(), "传智博客说我们的首都是北京啊 I am IT");
//		testAnalyzer(new IKAnalyzer(), "上海自来水来自海上");
	}
}
