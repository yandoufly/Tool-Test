package com.lucene.crud;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

import com.lucene.entity.Article;
import com.lucene.utils.LuceneUtil;

/**
 * 增删改查索引库
 */
public class ArticleDao {
	@Test
	public void add() throws Exception {
		Article article = new Article(1, "培训", "传智是一家IT培训机构");
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document); //核心
		indexWriter.close();
	}
	
	@Test
	public void addAll() throws Exception {
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());

		Article article1 = new Article(1, "培训", "传智是一家java培训机构");
		Document document1 = LuceneUtil.javaBean2Document(article1);
		indexWriter.addDocument(document1);
		
		Article article2 = new Article(2, "培训", "传智是一家PHO培训机构");
		Document document2 = LuceneUtil.javaBean2Document(article2);
		indexWriter.addDocument(document2);
		
		Article article3 = new Article(3, "培训", "传智是一家C++培训机构");
		Document document3 = LuceneUtil.javaBean2Document(article3);
		indexWriter.addDocument(document3);
		
		Article article4 = new Article(4, "培训", "传智是一家C语言培训机构");
		Document document4 = LuceneUtil.javaBean2Document(article4);
		indexWriter.addDocument(document4);
		
		Article article5 = new Article(5, "培训", "传智是一家Ruby培训机构");
		Document document5 = LuceneUtil.javaBean2Document(article5);
		indexWriter.addDocument(document5);
		
		Article article6 = new Article(6, "培训", "传智是一家ios培训机构");
		Document document6 = LuceneUtil.javaBean2Document(article6);
		indexWriter.addDocument(document6);
		
		Article article7 = new Article(7, "培训", "传智是一家CSS+HTML培训机构");
		Document document7 = LuceneUtil.javaBean2Document(article7);
		indexWriter.addDocument(document7);
		
		indexWriter.close();
	}
	
	@Test
	public void update() throws Exception {
		Article newAarticle = new Article(2, "培训", "传智是一家PHP培训机构");
		Document document = LuceneUtil.javaBean2Document(newAarticle);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		/*
		 * 参数一：term表示需要更新的document对象,id表示document中的id属性，2表示id属性值
		 * 参数二:新的document对象
		 */
		indexWriter.updateDocument(new Term("id", "2"), document); //核心
		indexWriter.close();
	}
	
	@Test
	public void delete() throws Exception {
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.deleteDocuments(new Term("id", "2")); //核心
		indexWriter.close();
	}
	
	@Test
	public void deleteAll() throws Exception {
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.deleteAll(); //核心
		indexWriter.close();
	}

	@Test
	public void findIndexDB() throws Exception {
		String keywords = "培训";
		List<Article> articleList = new ArrayList<Article>();
		QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"content",LuceneUtil.getAnalyzer());
		Query query = queryParser.parse(keywords);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
		TopDocs topDocs = indexSearcher.search(query,100);  //核心
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
