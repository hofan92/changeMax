package cn.changeyd.crawler.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;

import cn.changeyd.crawler.service.SymptomService;

/**
 * 基于Jsoup抓取网页内容,抓取症状
 * 
 * @author
 */
public class JsoupTest {

	static SymptomService ss = new SymptomService();

	public static void main(String[] args) throws IOException {
		// 目标页面
		String url = "http://jbk.39.net/bw_t2/";
		// 使用Jsoup连接目标页面,并执行请求,获取服务器响应内容
		String html = Jsoup.connect(url).execute().body();
		// 打印页面内容
		anyString(html);

		for (int i = 1; i < 200; i++) {
			// 目标页面
			String url1 = "http://jbk.39.net/bw_t2_p" + i + "#ps";
			// 使用Jsoup连接目标页面,并执行请求,获取服务器响应内容
			String html1 = Jsoup.connect(url1).execute().body();
			// 打印页面内容
			anyString(html1);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void anyString(String html) {

		String[] string = html.split("\r\n");
		for (String string2 : string) {
			if (string2.contains("<dt class=\"clearfix\"><span class=\"link\">") && string2.contains("title")) {
				String string3 = string2.trim();
				int i11 = "<dt class=\"clearfix\"><span class=\"link\"><a href=\"http://jbk.39.net/zhengzhuang/"
						.length();
				int i12 = string3.indexOf("/zzqy/\">");

				String t = string3.substring(i11, i12);

				int i21 = string3.indexOf("title=\"") + "title=\"".length();
				int i22 = string3.lastIndexOf("\">");
				String c = string3.substring(i21, i22);

				// System.out.println(t + c);

				ss.add(c, t);

			}
		}

	}
}