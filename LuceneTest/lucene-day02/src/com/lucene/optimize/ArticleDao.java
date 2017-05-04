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
 * ����������Ż�
 */
public class ArticleDao {
	/**
	 * ����document������������
	 * �������룺
	 */
	@Test
	public void add() throws Exception {
		Article article = new Article(1, "��ѵ", "������һ��IT��ѵ����", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		indexWriter.close();
	}
	
	/**
	 * �ϲ�cfs�ļ����ϲ����cfs�ļ��Ƕ�����ѹ���ַ����ܽ���ļ���С������������
	 */
	@Test
	public void type1() throws Exception {
		Article article = new Article(1, "��ѵ", "������һ��IT��ѵ����", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		//�ϲ�cfs�ļ�
		indexWriter.optimize();
		indexWriter.close();
		
	}

	/**
	 * �趨�ϲ����ӣ��Զ��ϲ�cfs�ļ�
	 */
	@Test
	public void type2() throws Exception {
		Article article = new Article(1, "��ѵ", "������һ��IT��ѵ����", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		
		//���úϲ����ӣ�������3��cfs�ı�һ�ϲ�
		indexWriter.setMergeFactor(3);
		indexWriter.close();
		
	}
	
	/**
	 * Ĭ������£�ÿ10��cfs�ı�һ�ϲ�
	 */
	@Test
	public void type3() throws Exception {
		Article article = new Article(1, "��ѵ", "������һ��IT��ѵ����", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);
		
		//���úϲ����ӣ�Ĭ����10��һ�ϲ�
//		indexWriter.setMergeFactor(3);
		indexWriter.close();
		
	}
	
	/**
	 * ʹ��RAMDirectory, �������ڴ������⣬�ܽ����ȡ�����ļ����ٶ�����
	 * @throws Exception
	 */
	@Test
	public void type4() throws Exception {
		Article article = new Article(1, "��ѵ", "������һ��IT��ѵ����", 10);
		Document document = LuceneUtil.javaBean2Document(article);
		//Ӳ��������
		Directory fsDirectory = FSDirectory.open(new File("E:/IndexDB"));
		
		//�ڴ�������,��ΪӲ�������������Ҫͬ�����ڴ���������
		Directory ramDirectory = new RAMDirectory(fsDirectory);
		
		//ָ��Ӳ����������ַ���,true��ʾ����ڴ��������к�Ӳ���������е���ͬ��document����ʱ����ɾ��Ӳ���������е�document��
		//�ٽ��ڴ��������е�document����д��Ӳ���������У�
		//��֮��false��Ĭ��Ϊfalse�����booleanֵд��Ӳ���ַ����Ĺ�������
		IndexWriter fsindexWriter = new IndexWriter(fsDirectory, LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		
		//ָ���ڴ���������ַ���
		IndexWriter ramindexWriter = new IndexWriter(ramDirectory, LuceneUtil.getAnalyzer(), LuceneUtil.getMaxFieldLength());
		
		//��document����д���ڴ���������
		ramindexWriter.addDocument(document);
		ramDirectory.close();
		
		//���ڴ��������е�����document����ͬ����Ӳ����������
		fsindexWriter.addIndexesNoOptimize(ramDirectory);
		fsindexWriter.close();
	}
	
	@Test
	public void findAll() throws Exception {
		String keywords = "��";
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
