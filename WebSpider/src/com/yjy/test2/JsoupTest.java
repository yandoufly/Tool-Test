package com.yjy.test2;

import java.io.IOException;

import org.jsoup.Jsoup;
/*�������������jsoup*/
public class JsoupTest {
	public static void main(String[] args) throws IOException {
		// Ŀ��ҳ��
		String url = "http://www.imooc.com/video/14339";
		// ʹ��Jsoup����Ŀ��ҳ��,��ִ������,��ȡ��������Ӧ����
		String html = Jsoup.connect(url).execute().body();
		// ��ӡҳ������
		System.out.println(html);
	}
}
