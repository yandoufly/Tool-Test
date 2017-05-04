package com.lucene.fenye.service;

import java.util.List;

import com.lucene.fenye.dao.ArticleDao;
import com.lucene.fenye.entity.Article;
import com.lucene.fenye.entity.Page;

public class ArticleService {
	
	//�־ò�
	private ArticleDao articleDao = new ArticleDao();
	
	/**
	 * ���ݹؼ��ֺ�ҳ�ţ���ѯ����
	 */
	public Page show(String keywords, int currPageNO) throws Exception {
		Page page = new Page();
		
		//��װ��ǰҳ��
		page.setCurrPageNO(currPageNO);
		
		//��װ�ܼ�¼��
		int allRecordNO = articleDao.getAllRecord(keywords);
		page.setAllRecordNO(allRecordNO);
		
		//��װ��ҳ��
		int allPageNO = 0;
		if(page.getAllRecordNO()%page.getPerPageSize() == 0){
			allPageNO = page.getAllRecordNO() / page.getPerPageSize();
		}else{
			allPageNO = page.getAllRecordNO() / page.getPerPageSize() + 1;
		}
		page.setAllPageNO(allPageNO);
		
		//��װ����
		int size = page.getPerPageSize();
		int start = (page.getCurrPageNO()-1) * size;
		List<Article> articleList = articleDao.findAll(keywords, start, size);
		page.setArticleList(articleList);
		
		return page;
	}
	
	public static void main(String[] args) throws Exception {
		ArticleService service = new ArticleService();
		Page page = service.show("��ѵ", 4);
		
		for(Article a : page.getArticleList()){
			System.out.println(a);
		}
		
	}
}
