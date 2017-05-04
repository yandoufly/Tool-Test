package com.lucene.fenye.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lucene.fenye.entity.Page;
import com.lucene.fenye.service.ArticleService;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		try {
			//获取关键字
			String keywords = request.getParameter("keywords");
			if(keywords==null || keywords.trim().length()==0){
				keywords = "培训";
			}
			//获取当前号
			String temp = request.getParameter("currPageNO");
			if(temp==null || temp.trim().length()==0){
				temp = "1";
			}
			
			//调用业务层
			ArticleService articleService = new ArticleService();
			Page page = articleService.show(keywords, Integer.parseInt(temp));
			
			request.setAttribute("page", page);  //回显数据
			request.setAttribute("KEYWORDS", keywords); //将关键字绑定到原来页面
			request.getRequestDispatcher("/index.jsp").forward(request, response); //转发
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
