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
 * ����Lucene���ú͵������ִ����ķִ�Ч��
 */
public class TestAnalyzer {
	private static void testAnalyzer(Analyzer analyzer, String text) throws Exception {
		System.out.println("��ǰʹ�õķִ�����" + analyzer.getClass());
		TokenStream tokenStream = analyzer.tokenStream("content",new StringReader(text));
		tokenStream.addAttribute(TermAttribute.class);
		while (tokenStream.incrementToken()) {
			TermAttribute termAttribute = tokenStream.getAttribute(TermAttribute.class);
			System.out.println(termAttribute.term());
		}
	}
	
	public static void main(String[] args) throws Exception {
		//���÷ִ���
//		testAnalyzer(new StandardAnalyzer(LuceneUtil.getVersion()), "���ǲ���˵���ǵ��׶��Ǳ����� I am IT");
		//�����ִ���
//		testAnalyzer(new FrenchAnalyzer(LuceneUtil.getVersion()), "���ǲ���˵���ǵ��׶��Ǳ����� I am IT");

//		testAnalyzer(new CJKAnalyzer(LuceneUtil.getVersion()), "���ǲ���˵���ǵ��׶��Ǳ����� I am IT");
		testAnalyzer(new IKAnalyzer(), "���ǲ���˵���ǵ��׶��Ǳ����� I am IT");
//		testAnalyzer(new IKAnalyzer(), "�Ϻ�����ˮ���Ժ���");
	}
}
