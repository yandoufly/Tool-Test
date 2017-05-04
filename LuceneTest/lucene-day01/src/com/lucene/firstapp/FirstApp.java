package com.lucene.firstapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.lucene.entity.Article;

/**
 * ����
 */
public class FirstApp {
	/**
	 * ����������
	 * ��Article��������������е�ԭʼ��¼���У��Ӷ��γɴʻ��
	 */
	@Test
	public void createIndexDB() throws Exception {
		//1������Article����
		Article article = new Article(1, "��ѵ", "����һ��IT��ѵ����");
		//2������document����
		Document document = new Document();
		//3����Article�����е���������ֵ�󶨵�document��
		/*
		 * ����һ��document�����е���������xid��article����������id����Ŀ���ᳫ��ͬ
		 * ��������document�����е�����xid��ֵ����article��������ͬ
		 * ���������Ƿ�xid����ֵ����ԭʼ��¼����ת����ʻ����
		 * 		Store. YES-����ʻ�� Store. NO-������ʻ��
		 * 		��Ŀ���ᳫ��idֵ������ʻ����
		 * �����ģ��Ƿ�xid����ֵ���зִ��㷨
		 * 		Index.ANALYZED��ʾ�����Ի���дʻ���, Index.NOT_ANALYZED��ʾ�����Բ�����дʻ���
		 * 		��Ŀ���ᳫ��idֵ�����дʻ���
		 */
		document.add(new Field("xid", article.getId().toString(), Store.YES, Index.ANALYZED));
		document.add(new Field("xtitle", article.getTitle(), Store.YES, Index.ANALYZED));
		document.add(new Field("xcontent", article.getContent(), Store.YES, Index.ANALYZED));
		
		//4������IndexWriter�ַ���
		/*
		 * ����һ��lucene���������ն�Ӧ��Ӳ���е�Ŀ¼�����磺 D��//IndexDB
		 * ������������ʲô���Խ��ı���֣�һ�����Ծ���һ�������ʵ����
		 * ����������ཫ�ı���ֳ����ٴʻ㣬LIMITED��ʾ1W������ֻȡǰ1W���ʻ�
		 */
		Directory directory = FSDirectory.open(new File("E:/IndexDB"));
		Version matchVersion = Version.LUCENE_30;
		Analyzer analyer = new StandardAnalyzer(matchVersion);
		MaxFieldLength maxFieldLength = MaxFieldLength.LIMITED;
		IndexWriter indexWriter = new IndexWriter(directory, analyer, maxFieldLength);
		//5����document����д��lucene������
		indexWriter.addDocument(document);
		//6���ر�IndexWriter�ַ�������
		indexWriter.close();
		
	}

	/**
	 * ���ݹؼ��ִ���������������������������
	 */
	@Test
	public void findIndexDB() throws Exception {
		//׼������
		String keyword = "��ѵ";
		List<Article> articleList = new ArrayList<Article>();
		Directory directory = FSDirectory.open(new File("E:/IndexDB"));
		Version version = Version.LUCENE_30;
		Analyzer analyer = new StandardAnalyzer(version);
		MaxFieldLength maxFieldLength = MaxFieldLength.LIMITED;
		
		//����IndexSearcher�ַ�������
		IndexSearcher IndexSearcher = new IndexSearcher(directory);
		
		//������ѯ����������
		/*
		 * ����һ��ʹ�÷ִ����汾���ᳫʹ�ø�jar���е���߰汾
		 * �����������document�����е��ĸ����Խ�������
		 */
		QueryParser queryParser = new QueryParser(version, "xcontent", analyer);
		//���������װ��ѯ�ؼ���
		Query query = queryParser.parse(keyword);

		//���ݹؼ��֣�ȥ������Ĵʻ�����
		/*
		 * ����һ����ʾ��װ�ؼ��ֲ�ѯ��������
		 * ��������MAX_RECORD��ʾ������ݹؼ������ѳ��������ݽ϶ֻ࣬ȡǰMAX_RECORD������
		 */
		int MAX_RECORD = 100;
		TopDocs topDocs = IndexSearcher.search(query, MAX_RECORD);
		//�����ʻ���з��������ı��
		for(int i = 0; i < topDocs.scoreDocs.length; i++){
			//ȡ����װ��źͷ�����ScoreDoc����
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			//ȡ��ÿһ����� ���磺0��1��2
			int doc = scoreDoc.doc;
			//���ݱ��ȥ�������е�ԭʼ��¼���в�ѯ��Ӧ��document����
			Document document = IndexSearcher.doc(doc);
			//��ȡdocument����������ֵ
			String xid = document.get("xid");
			String xtitle = document.get("xtitle");
			String xcontent = document.get("xcontent");
			
			//��װ��article������
			Article article = new Article(Integer.parseInt(xid), xtitle, xcontent);
			//��article������뵽list������
			articleList.add(article);
		}
		
		//���������
		for(Article article : articleList){
			System.out.println(article.toString());
		}
		
	}
}
