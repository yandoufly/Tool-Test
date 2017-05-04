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
 * 搜索结果中关键字高亮
 */
public class ArticleDao {
	@Test
	public void add() throws Exception {
		Article article = new Article(1, "培训", "传智是一家IT培训机构", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		indexWriter.close();
	}
	
	@Test
	public void findAll() throws Exception {
		String keywords = "是";
		List<Article> articleList = new ArrayList<Article>();
		
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
		QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(), "content", LuceneUtil.getAnalyzer());
		Query query = queryParser.parse(keywords);
		TopDocs topDocs = indexSearcher.search(query , 10);
		
		//格式对象
		Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
		//关键字对象
		Scorer fragmentScorer = new QueryScorer(query);
		//以下代码对内容中含有关键字的字符串高亮显示
		Highlighter highlighter = new Highlighter(formatter, fragmentScorer);
		
		for(int i = 0; i < topDocs.scoreDocs.length; i++){
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			int no = scoreDoc.doc;
			
			//关键字没有高亮
			Document document = indexSearcher.doc(no);
			//关键字高亮
			String bestFragment = highlighter.getBestFragment(LuceneUtil.getAnalyzer(), "content", document.get("content"));
	//			System.out.println(bestFragment);
			
			//将高亮的内容封装到document对象中去
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
