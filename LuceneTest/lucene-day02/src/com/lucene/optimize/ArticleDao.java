package com.lucene.optimize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
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
 * 索引库进行优化
 */
public class ArticleDao {
	/**
	 * 增加document对象索引库中
	 * 问题引入：
	 */
	@Test
	public void add() throws Exception {
		Article article = new Article(1, "培训", "传智是一家IT培训机构", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		indexWriter.close();
	}
	
	/**
	 * 合并cfs文件，合并后的cfs文件是二进制压缩字符，能解决文件大小和数量的问题
	 */
	@Test
	public void type1() throws Exception {
		Article article = new Article(1, "培训", "传智是一家IT培训机构", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		//合并cfs文件
		indexWriter.optimize();
		indexWriter.close();
		
	}

	/**
	 * 设定合并因子，自动合并cfs文件
	 */
	@Test
	public void type2() throws Exception {
		Article article = new Article(1, "培训", "传智是一家IT培训机构", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		
		//设置合并因子，即满足3个cfs文本一合并
		indexWriter.setMergeFactor(3);
		indexWriter.close();
		
	}
	
	/**
	 * 默认情况下，每10个cfs文本一合并
	 */
	@Test
	public void type3() throws Exception {
		Article article = new Article(1, "培训", "传智是一家IT培训机构", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		
		//设置合并因子，默认满10个一合并
//		indexWriter.setMergeFactor(3);
		indexWriter.close();
		
	}
	
	/**
	 * 使用RAMDirectory, 类似于内存索引库，能解决读取索引文件的速度问题
	 * @throws Exception
	 */
	@Test
	public void type4() throws Exception {
		Article article = new Article(1, "培训", "传智是一家IT培训机构", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		//硬盘索引库
		Directory fsDirectory = FSDirectory.open(new File("E:/IndexDB"));
		
		//内存索引库,因为硬盘索引库的内容要同步到内存索引库中
		Directory ramDirectory = new RAMDirectory(fsDirectory);
		
		//指向硬盘索引库的字符流,true表示如果内存索引库中和硬盘索引库中的相同的document对象时，先删除硬盘索引库中的document，
		//再将内存索引库中的document对象写入硬盘索引库中；
		//反之是false，默认为false，这个boolean值写在硬盘字符流的构造器中
		IndexWriter fsindexWriter = new IndexWriter(fsDirectory, LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		
		//指向内存索引库的字符流
		IndexWriter ramindexWriter = new IndexWriter(ramDirectory, LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		
		//将document对象写入内存索引库中
		ramindexWriter.addDocument(document);
		ramDirectory.close();
		
		//将内存索引库中的所有document对象同步到硬盘索引库中
		fsindexWriter.addIndexesNoOptimize(ramDirectory);
		fsindexWriter.close();
	}
	
	@Test
	public void findAll() throws Exception {
		String keywords = "是";
		List<Article> articleList = new ArrayList<Article>();
		
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
		QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(), "content", LuceneUtil.getAnalyzer());
		Query query = queryParser.parse(keywords);
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
