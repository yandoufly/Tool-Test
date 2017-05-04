package com.lucene.highlighter;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.junit.Test;

import com.lucene.entities.Article;
import com.lucene.utils.LuceneUtil;
/**
 * ��������йؼ��ָ���
 */
public class ArticleDao {
	@Test
	public void add() throws Exception {
		Article article = new Article(1, "��ѵ", "������һ��IT��ѵ����", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		indexWriter.close();
	}
	
	@Test
	public void findAll() throws Exception {
		String keywords = "��";
		List<Article> articleList = new ArrayList<Article>();
		
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
		QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(), "content", LuceneUtil.getAnalyzer());
		Query query = queryParser.parse(keywords);
		TopDocs topDocs = indexSearcher.search(query , 10);
		
		//��ʽ����
		Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
		//�ؼ��ֶ���
		Scorer fragmentScorer = new QueryScorer(query);
		//���´���������к��йؼ��ֵ��ַ���������ʾ
		Highlighter highlighter = new Highlighter(formatter, fragmentScorer);
		
		for(int i = 0; i < topDocs.scoreDocs.length; i++){
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			int no = scoreDoc.doc;
			
			//�ؼ���û�и���
			Document document = indexSearcher.doc(no);
			//�ؼ��ָ���
			String bestFragment = highlighter.getBestFragment(LuceneUtil.getAnalyzer(), "content", document.get("content"));
	//			System.out.println(bestFragment);
			
			//�����������ݷ�װ��document������ȥ
			document.getField("title").setValue(bestFragment);
			document.getField("content").setValue(bestFragment);
			
			
			Article article = (Article) LuceneUtil.document2JavaBean(document, Article.class);
			articleList.add(article);
		}
		
		for(Article a : articleList){
			System.out.println(a);
		}
	}
}
