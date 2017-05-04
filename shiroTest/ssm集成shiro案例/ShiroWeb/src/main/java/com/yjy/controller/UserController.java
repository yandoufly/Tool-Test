package com.yjy.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yjy.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	
	//用户登录
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request){
		
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword().toCharArray(), null);
		//shiro验证登陆
		try{
			SecurityUtils.getSubject().login(token);
			return "redirect:/success.jsp";
		}catch(Exception e){
			request.setAttribute("errorMsg", "用户名或密码错误！");
			return "index";
		}
	}
	

}
