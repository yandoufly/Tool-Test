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
		SendMail mail = new SendMail("326015540@qq.com", "jpxicmbuuxkfbhgd"); // �û���&&��֤��
		map.put("mail.smtp.host", "smtp.qq.com"); // ָ�������ʼ�������Ϊ smtp.qq.com
		map.put("mail.smtp.auth", "true"); // ������֤

		// �������530 Error: A secure connection is requiered(such as ssl)��
		map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		map.put("mail.smtp.port", "465");
		map.put("mail.smtp.socketFactory.port", "465");
		
		mail.setPros(map);
		mail.initMessage();
		
		/*����ռ��������ַ����� 1,�������(���˷���)����setRecipient(str);����String����*/
		// ���Ե��˷���
		mail.setRecipient("447865628@qq.com");
		
		// ����Ⱥ��(List����)
		/*List<String> list = new ArrayList<String>();
		list.add("***@qq.com"); 
		//list.add("***92@sina.cn");
		list.add("****@163.com"); 
		mail.setRecipients(list);*/
		
		// ����Ⱥ��(StringBuffer����)
		/*StringBuffer sb = new StringBuffer("429353942@qq.com"); 
		sb.append(",316121113@qq.com");
		mail.setRecipients(sb);*/
		
		mail.setSubject("��������"); // �ʼ�����.
		mail.setDate(new Date()); // �ʼ�ʱ��
		mail.setFrom("suerfly");  //����������
		// mail.setMultipart("D:������.txt"); //���͸���
		mail.setContent("лл����", "text/html; charset=UTF-8");
		
		/* �����ϴ��ļ�
		 * List<String> fileList = new ArrayList<String>();
		 * fileList.add("D:1.jpg"); fileList.add("D:activation.zip");
		 * fileList.add("D:dstz.sql"); fileList.add("D:�������Ҫ��.doc");
		 * mail.setMultiparts(fileList);
		 */
		String message = mail.sendMessage(); //�����ʼ�
		System.out.println(message);
	}

}
