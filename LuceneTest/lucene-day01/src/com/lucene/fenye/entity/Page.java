package com.lucene.fenye.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * ����Article�ķ�ҳ��
 */
public class Page {
	private Integer currPageNO; //��ǰҳ
	private Integer perPageSize = 2; //ÿҳ��ʾ��¼����Ĭ��Ϊ2
	private Integer allRecordNO; //�ܼ�¼��
	private Integer allPageNO; //��ҳ��
	private List<Article> articleList = new ArrayList<Article>();
	
	public Page(){}

	public Integer getCurrPageNO() {
		return currPageNO;
	}
	public void setCurrPageNO(Integer currPageNO) {
		this.currPageNO = currPageNO;
	}

	public Integer getPerPageSize() {
		return perPageSize;
	}
	public void setPerPageSize(Integer perPageSize) {
		this.perPageSize = perPageSize;
	}

	public Integer getAllRecordNO() {
		return allRecordNO;
	}
	public void setAllRecordNO(Integer allRecordNO) {
		this.allRecordNO = allRecordNO;
	}

	public Integer getAllPageNO() {
		return allPageNO;
	}
	public void setAllPageNO(Integer allPageNO) {
		this.allPageNO = allPageNO;
	}

	public List<Article> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}
}
