package com.lucene.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.lucene.entity.Article;

/**
 * ������
 */
public class LuceneUtil {
	private static Directory directory;
	private static Version version;
	private static Analyzer analyzer;
	private static MaxFieldLength maxFieldLength;
	
	static{
		try {
			directory = FSDirectory.open(new File("E:/IndexDB"));
			version = Version.LUCENE_30;
			analyzer = new StandardAnalyzer(version);
			maxFieldLength = MaxFieldLength.LIMITED;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//�������new�������
	private LuceneUtil(){}
	
	//��JavaBean����ת��Document����
	public static Document javaBean2Document(Object obj) throws Exception {
		//����document����
		Document document = new Document();
		//��ȡ obj ���õĶ����ֽ���
		Class clazz = obj.getClass();
		//ͨ�������ֽ����ȡ˽�е�����
		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		//����
		for(java.lang.reflect.Field reflectField : fields){
			//ǿ������
			reflectField.setAccessible(true);
			//��ȡ������ id/title/content
			String name = reflectField.getName();

			//�ֹ�ƴ�ӷ�����
			String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
			//��ȡ����, ���磺getId()/getTitle()
			Method method = clazz.getMethod(methodName,null);
			//ִ�з���
			String value = method.invoke(obj, null).toString();
			//���뵽Document������ȥ,javabean��������document��������ͬ
			document.add(new Field(name, value, Store.YES, Index.ANALYZED));
		}
		
		return document;
	}

	//��Document����ת��JavaBean����
	public static Object document2JavaBean(Document document, Class clazz) throws Exception {
		Object obj = clazz.newInstance();
		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		for(java.lang.reflect.Field reflectField : fields){
			reflectField.setAccessible(true);
			String name = reflectField.getName(); //id/title/content
			String value = document.get(name); // 1/��ѵ/����һ��IT��ѵ����
			BeanUtils.setProperty(obj, name, value); //��װ��javabean��Ӧ��������ȥ��ͨ��setXxx()����
			
		}
		return obj;
	}
	
	//����
	public static void main(String[] args) throws Exception {
		Article article = new Article(1, "��ѵ", "����һ��IT��ѵ����");
		Document document = LuceneUtil.javaBean2Document(article);
//		System.out.println(document.get("id"));
//		System.out.println(document.get("title"));
//		System.out.println(document.get("content"));
//		System.out.println("---------------------");
		
		
		Article article1 = (Article) LuceneUtil.document2JavaBean(document,Article.class);
		System.out.println(article1);
		

	}
	
	
	public static Directory getDirectory() {
		return directory;
	}
	public static void setDirectory(Directory directory) {
		LuceneUtil.directory = directory;
	}
	public static Version getVersion() {
		return version;
	}
	public static void setVersion(Version version) {
		LuceneUtil.version = version;
	}
	public static Analyzer getAnalyzer() {
		return analyzer;
	}
	public static void setAnalyzer(Analyzer analyzer) {
		LuceneUtil.analyzer = analyzer;
	}
	public static MaxFieldLength getMaxFieldLength() {
		return maxFieldLength;
	}
	public static void setMaxFieldLength(MaxFieldLength maxFieldLength) {
		LuceneUtil.maxFieldLength = maxFieldLength;
	}
}
