package com.lucene.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import com.lucene.entities.Article;
import com.lucene.utils.LuceneUtil;

/**
 * 演示多字段搜索
 */
public class ArticleDao {
	/**
	 * 增加document对象索引库中
	 */
	@Test
	public void add() throws Exception {
//		Article article = new Article(1, "培训", "传智是一家IT培训机构", 10);
//		Article article = new Article(2, "培训", "北大是一家IT培训机构", 10);
//		Article article = new Article(3, "培训", "中大是一家IT培训机构", 10);
		Article article = new Article(4, "培训", "小大是一家IT培训机构", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		indexWriter.close();
	}
	
	
	@Test
	public void findAll() throws Exception {
		String keywords = "机构";
		List<Article> articleList = new ArrayList<Article>();
		
		//单字段搜索
		//QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(), "content", LuceneUtil.getAnalyzer());
		
		//多字段搜索
		String[] fields = {"title", "content"};
		QueryParser queryParser = new MultiFieldQueryParser(LuceneUtil.getVersion(), fields , LuceneUtil.getAnalyzer());
		
		Query query = queryParser.parse(keywords);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
		
		
		 TopDocs topDocs = indexSearcher.search(query , 10);
		for(int i = 0; i < topDocs.scoreDocs.length; i++){
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			int no = scoreDoc.doc;
			Document document = indexSearcher.doc(no);
			Article article = (Article) LuceneUtil.document2JavaBean(document, Article.class);
			articleList.add(article);
		}
		
		for(Article a : articleList){
			System.out.println(a);
		}
	}
}
