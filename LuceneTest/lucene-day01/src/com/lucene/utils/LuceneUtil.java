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
 * 工具类
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
	
	//不让外界new该类对象
	private LuceneUtil(){}
	
	//将JavaBean对象转成Document对象
	public static Document javaBean2Document(Object obj) throws Exception {
		//创建document对象
		Document document = new Document();
		//获取 obj 引用的对象字节码
		Class clazz = obj.getClass();
		//通过对象字节码获取私有的属性
		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		//迭代
		for(java.lang.reflect.Field reflectField : fields){
			//强力反射
			reflectField.setAccessible(true);
			//获取属性名 id/title/content
			String name = reflectField.getName();

			//手工拼接方法名
			String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
			//获取方法, 例如：getId()/getTitle()
			Method method = clazz.getMethod(methodName,null);
			//执行方法
			String value = method.invoke(obj, null).toString();
			//加入到Document对象中去,javabean的属性与document对象中相同
			document.add(new Field(name, value, Store.YES, Index.ANALYZED));
		}
		
		return document;
	}

	//将Document对象转成JavaBean对象
	public static Object document2JavaBean(Document document, Class clazz) throws Exception {
		Object obj = clazz.newInstance();
		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		for(java.lang.reflect.Field reflectField : fields){
			reflectField.setAccessible(true);
			String name = reflectField.getName(); //id/title/content
			String value = document.get(name); // 1/培训/这是一家IT培训机构
			BeanUtils.setProperty(obj, name, value); //封装到javabean对应的属性中去，通过setXxx()方法
			
		}
		return obj;
	}
	
	//测试
	public static void main(String[] args) throws Exception {
		Article article = new Article(1, "培训", "这是一家IT培训机构");
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
