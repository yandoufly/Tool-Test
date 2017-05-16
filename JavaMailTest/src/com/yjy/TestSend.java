package com.yjy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

public class TestSend {
	public static void main(String[] args) throws MessagingException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		SendMail mail = new SendMail("326015540@qq.com", "jpxicmbuuxkfbhgd"); // 用户名&&验证码
		map.put("mail.smtp.host", "smtp.qq.com"); // 指定发送邮件的主机为 smtp.qq.com
		map.put("mail.smtp.auth", "true"); // 开启认证

		// 处理错误：530 Error: A secure connection is requiered(such as ssl)”
		map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		map.put("mail.smtp.port", "465");
		map.put("mail.smtp.socketFactory.port", "465");
		
		mail.setPros(map);
		mail.initMessage();
		
		/*添加收件人有三种方法： 1,单人添加(单人发送)调用setRecipient(str);发送String类型*/
		// 测试单人发送
		mail.setRecipient("447865628@qq.com");
		
		// 测试群发(List集合)
		/*List<String> list = new ArrayList<String>();
		list.add("***@qq.com"); 
		//list.add("***92@sina.cn");
		list.add("****@163.com"); 
		mail.setRecipients(list);*/
		
		// 测试群发(StringBuffer类型)
		/*StringBuffer sb = new StringBuffer("429353942@qq.com"); 
		sb.append(",316121113@qq.com");
		mail.setRecipients(sb);*/
		
		mail.setSubject("测试邮箱"); // 邮件主题.
		mail.setDate(new Date()); // 邮件时间
		mail.setFrom("suerfly");  //发送人名字
		// mail.setMultipart("D:你你你.txt"); //发送附件
		mail.setContent("谢谢合作", "text/html; charset=UTF-8");
		
		/* 测试上传文件
		 * List<String> fileList = new ArrayList<String>();
		 * fileList.add("D:1.jpg"); fileList.add("D:activation.zip");
		 * fileList.add("D:dstz.sql"); fileList.add("D:软件配置要求.doc");
		 * mail.setMultiparts(fileList);
		 */
		String message = mail.sendMessage(); //发送邮件
		System.out.println(message);
	}

}
