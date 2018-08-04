package cn.changeyd.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import cn.changeyd.crawler.model.Symptom;
import cn.changeyd.crawler.service.SymptomService;

public class FillNull {
	static SymptomService ss = new SymptomService();

	public static void main(String[] args) {
		List<Symptom> sList = ss.readIsNull();
		//
		for (Symptom symptom : sList) {
			String sTrans = symptom.getSymptom_trans();

			String shouYeUrl = "http://jbk.39.net/zhengzhuang/" + sTrans + "/";

			// String shouYeUrl = "http://jbk.39.net/zhengzhuang/nyzychjyw/";

			String shouYeHtml = clawer(shouYeUrl);

			shouYeHtml = removeLabel(anyString1(shouYeHtml));

			symptom.setSymptom_intro(shouYeHtml);
			
			ss.update2(symptom);
		}
	}

	// 首页
	public static String anyString1(String shouYeHtml) {

		String[] strings = shouYeHtml.split("\r\n");
		StringBuilder sb = new StringBuilder();
		String s = "<p class=\"sort2\">";
		boolean flag = false;

		for (String string : strings) {
			if (string.contains(s)) {
				flag = true;
			}
			// System.out.println(string);

			if (flag) {
				sb.append(string.trim());

			}

			if (flag) {
				if (string.contains("</p>")) {
					flag = false;
					break;
				}
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
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
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
