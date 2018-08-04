package cn.changeyd.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import cn.changeyd.crawler.service.DiseaseService;

public class DiseaseTest {
	static DiseaseService ds = new DiseaseService();

	public static void main(String[] args) {
		String url = "http://jbk.39.net/bw_t1/";
		String html = clawer(url);
		anyString1(html);

		for (int i = 1; i < 200; i++) {
			String url2 = "http://jbk.39.net/bw_t1_p" + i + "#ps";
			String html2 = clawer(url2);
			anyString1(html2);
		}
	}

	// 首页
	public static String anyString1(String html) {

		String[] strings = html.split("\r\n");
		StringBuilder sb = new StringBuilder();
		String s1 = "<dt class=\"clearfix\"><span class=\"link\">";
		String s2 = "title";
		boolean flag = false;

		for (String string : strings) {
			if (string.contains(s1) && string.contains(s2)) {
				String s = "/\" title=\"";
				int i11 = string.indexOf(s) + s.length();
				int i12 = string.lastIndexOf("\">");
				String name = string.substring(i11, i12);

				String s3 = "<a href=\"http://jbk.39.net/";
				int i21 = string.indexOf(s3) + s3.length();
				int i22 = string.indexOf("/blby/\">[病因]");

				String trans = string.substring(i21, i22);

				ds.addName(name, trans);
			}
		}

		return sb.toString();

	}

	private static String removeLabel(String string) {
		string = string.replaceAll("&nbsp;", "");
		string = string.replaceAll("&amp;", "");
		string = string.replaceAll("rdquo;", "");
		string = string.replaceAll("ldquo;", "");
		string = string.replaceAll("mdash;", "");
		return string.replaceAll("</?[^>]+>", "");
	}

	public static String clawer(String myurl) {
		int i = 0;
		StringBuffer sb = new StringBuffer("");
		URL url;
		try {
			url = new URL(myurl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "gbk"));
			String s = "";
			while ((s = br.readLine()) != null) {
				i++;
				sb.append(s + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
