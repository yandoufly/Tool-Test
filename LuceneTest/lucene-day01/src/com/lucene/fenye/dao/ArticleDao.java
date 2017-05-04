package com.lucene.fenye.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.lucene.fenye.entity.Article;
import com.lucene.utils.LuceneUtil;

public class ArticleDao {
	/**
	 * ���ݹؼ��֣���ȡ�ܼ�¼��
	 * @return �ܼ�¼��
	 */
	public int getAllRecord(String keywords) throws Exception {
		List<Article> articleList = new ArrayList<Article>();
		QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"content",LuceneUtil.getAnalyzer());
		Query query = queryParser.parse(keywords);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
		TopDocs topDocs = indexSearcher.search(query, 2);
		//���ط�����������ʵ�ܼ�¼��������2��Ӱ��
		return topDocs.totalHits;
		//���ط�����������ʵ�ܼ�¼������2��Ӱ��
//		return topDocs.scoreDocs.length;
	}
	
	/**
	 * ���ݹؼ��֣�������ѯ��¼
	 * @param start �ӵڼ�����¼�������ſ�ʼ��ѯ�������Ŵ�0�ſ�ʼ
	 * @param size ����ѯ������¼�������������Ŀʱ��ʵ��Ϊ׼
	 * @return Article����
	 */
	public List<Article> findAll(String keywords, int start, int size) throws Exception {
		List<Article> list = new ArrayList<Article>();
		
		QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"content",LuceneUtil.getAnalyzer());
		Query query = queryParser.parse(keywords);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
		TopDocs topDocs = indexSearcher.search(query, 100);
		
		int middle = Math.min(start+size, topDocs.totalHits);
		for(int i = start; i < middle; i++){
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			int no = scoreDoc.doc;
			Document document = indexSearcher.doc(no);
			Article article = (Article) LuceneUtil.document2JavaBean(document, Article.class);
			list.add(article);
		}
		return list;
	}
	
	public static void main(String[] args) throws Exception {
		ArticleDao dao = new ArticleDao();
//		System.out.println(dao.getAllRecord("��ѵ"));
//		
//		System.out.println("-------------");
		System.out.println("��һҳ");
		List<Article> list1 = dao.findAll("��ѵ", 0, 2);
		for(Article a : list1){
			System.out.println(a);
		}
		
		System.out.println("�ڶ�ҳ");
		List<Article> list2 = dao.findAll("��ѵ", 2, 2);
		for(Article a : list2){
			System.out.println(a);
		}
		
		System.out.println("����ҳ");
		List<Article> list3 = dao.findAll("��ѵ", 4, 2);
		for(Article a : list3){
			System.out.println(a);
		}
		
		System.out.println("����ҳ");
		List<Article> list4 = dao.findAll("��ѵ", 6, 2);
		for(Article a : list4){
			System.out.println(a);
		}
		
	}
}
