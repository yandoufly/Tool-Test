package com.lucene.secondapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

import com.lucene.entity.Article;
import com.lucene.utils.LuceneUtil;

/**
 * 
 */
public class SecondApp {
	/**
	 * ����������
	 */
	@Test
	public void createIndexDB() throws Exception {
//		Article article = new Article(1, "��ѵ", "������һ��IT��ѵ����");
//		Article article = new Article(2, "��ѵ", "������һ��IT��ѵ����");
		Article article = new Article(3, "��ѵ", "�ƴ���һ��IT��ѵ����");
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		indexWriter.close();
				
	}
	
	/**
	 * ���ݹؼ��ִ��������в�ѯ��������������
	 */
	@Test
	public void findIndexDB() throws Exception {
		String keywords = "��ѵ";
		List<Article> articleList = new ArrayList<Article>();
		QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"content",LuceneUtil.getAnalyzer());
		Query query = queryParser.parse(keywords);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
		TopDocs topDocs = indexSearcher.search(query,100);
		for(int i=0;i<topDocs.scoreDocs.length;i++){
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			int no = scoreDoc.doc;
			Document document = indexSearcher.doc(no);
			Article article = (Article)LuceneUtil.document2JavaBean(document,Article.class);
			articleList.add(article);
		}
		for(Article a : articleList){
			System.out.println(a);
		}
		
	}
}
