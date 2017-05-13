package com.yjy.test2;

import java.io.IOException;

import org.jsoup.Jsoup;
/*测试爬虫解析器jsoup*/
public class JsoupTest {
	public static void main(String[] args) throws IOException {
		// 目标页面
		String url = "http://www.imooc.com/video/14339";
		// 使用Jsoup连接目标页面,并执行请求,获取服务器响应内容
		String html = Jsoup.connect(url).execute().body();
		// 打印页面内容
		System.out.println(html);
	}
}
