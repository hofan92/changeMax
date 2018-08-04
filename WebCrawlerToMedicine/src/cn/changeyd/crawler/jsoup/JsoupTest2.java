package cn.changeyd.crawler.jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.changeyd.crawler.model.DiseaseSy;
import cn.changeyd.crawler.model.Symptom;
import cn.changeyd.crawler.service.DiseaseSyService;
import cn.changeyd.crawler.service.SymptomService;

/**
 * 基于Jsoup抓取网页内容抓取症状相关
 * 
 * @author
 */
public class JsoupTest2 {

	static SymptomService ss = new SymptomService();
	static DiseaseSyService ds = new DiseaseSyService();
	static Symptom symptom;

	public static void main(String[] args) throws IOException {

		for (int i = 1; i <= 2000; i++) {
			// 获取症状对象
			symptom = ss.read(i);

			// 获取症状对象名称的音译
			String sTrans = symptom.getSymptom_trans();
			// 目标页面
			String shouYeUrl = "http://jbk.39.net/zhengzhuang/" + sTrans + "/";
			String qiYinUrl = "http://jbk.39.net/zhengzhuang/" + sTrans + "/zzqy/";
			String zDXXUrl = "http://jbk.39.net/zhengzhuang/" + sTrans + "/zdxs/";
			String zhiNanUrl = "http://jbk.39.net/zhengzhuang/" + sTrans + "/jzzn/";
			// 使用Jsoup连接目标页面,并执行请求,获取服务器响应内容
			// String html = Jsoup.connect(url).execute().body();

			String shouYeHtml = clawer(shouYeUrl);

			String qiYinHtml = clawer(qiYinUrl);

			String zDXXHtml = clawer(zDXXUrl);

			String zhiNanHtml = clawer(zhiNanUrl);

			// 打印页面内容
			anyString1(shouYeHtml);

			// 打印页面内容
			anyString2(qiYinHtml);

			// 获取数据
			anyString4(zDXXHtml);

			// 打印页面内容
			anyString3(zhiNanHtml);

			// 数据入库
			ss.update(symptom);
			ds.add(symptom);

		}

	}

	// 诊断
	private static void anyString4(String zDXXHtml) {
		String[] ss = zDXXHtml.split("\r\n");
		for (String string : ss) {
			if (string.contains("<div class=\"item catalogItem\">")) {

				String[] ss2 = string.split("</p>");
				StringBuilder sb = new StringBuilder();
				for (String string3 : ss2) {
					string3 = string3.trim();
					string3 = string3.replaceAll("&nbsp;", "");
					string3 = string3.replaceAll("&amp;", "");
					string3 = string3.replaceAll("rdquo;", "");
					string3 = string3.replaceAll("ldquo;", "");
					string3 = string3.replaceAll("mdash;", "");
					sb.append(string3.trim().replaceAll("</?[^>]+>", "") + "\r\n");
				}
				// // 剔出<html>的标签
				String str = sb.toString();
				str = jinJianStr2(str);
				if (!str.equals("")) {
					System.out.println("setSymptomaticDetailsContent is ok!");
				}else {
					System.out.println("setSymptomaticDetailsContent is no!");
				}
				symptom.setSymptomaticDetailsContent(str);
			}
		}

	}

	// 指南
	private static void anyString3(String zhiNanUrl) {
		String s11 = "<!-- 主体 -->";
		String s12 = "<!-- /主体 -->";
		int i11 = zhiNanUrl.indexOf(s11) + s11.length();
		int i12 = zhiNanUrl.indexOf(s12);
		String zhuTiString = zhiNanUrl.substring(i11, i12);

		String[] ss = zhuTiString.split("\r\n");

		List<List<String>> listList = new ArrayList<List<String>>();
		List<String> list = null;
		boolean flag = false;
		for (String string : ss) {
			if (string.contains("<dl>")) {
				flag = true;
				list = new ArrayList<String>();
			}

			if (flag) {
				list.add(string);
			}

			if (string.contains("</dl>")) {
				flag = false;
				listList.add(list);
			}
		}

		List<DiseaseSy> dList = new ArrayList<DiseaseSy>();
		String std1 = "";
		boolean flag2 = false;
		boolean flag3 = false;
		for (List<String> l : listList) {
			for (String string : l) {
				if (string.contains("建议就诊科室")) {
					flag2 = true;
				}
				if (flag2 && string.contains("dd")) {
					string = string.trim();
					string = string.substring(string.indexOf("<dd>") + "<dd>".length(), string.indexOf("</dd>"));
					
					if (!string.equals("")) {
						System.out.println("setSuggestedTreatmentDepartment is ok!");
					}else {
						System.out.println("setSuggestedTreatmentDepartment is no!");
					}
					
					// 建议就诊科室
					symptom.setSuggestedTreatmentDepartment(string);
				}
				if (string.contains("/dl")) {
					flag2 = false;
				}

				if (string.contains("可能疾病")) {
					flag3 = true;
				}
				if (flag3 && string.contains("dd")) {
					string = string.trim();
					string = string.substring(string.indexOf("<dd>") + "<dd>".length(), string.indexOf("</dd>"));
					// 可能疾病
					String[] ss2 = string.split("，");
					String keNengName = ss2[0].substring(3, ss2[0].length());
					String keNengSy = ss2[1];
					DiseaseSy d = new DiseaseSy();
					d.setDiseaseName(keNengName);
					d.setDiseaseAccomSy(keNengSy);
					dList.add(d);
				}
				if (string.contains("/dl")) {
					flag3 = false;
					
					if (dList.size() != 0) {
						System.out.println("setdList is ok!");
					}else {
						System.out.println("setdList is no!");
					}
					
					symptom.setdList(dList);
				}

			}
		}

	}

	// 起因
	private static void anyString2(String qiYinHtml) {
		String s = "<div class=\"item catalogItem\">";
		String[] strings = qiYinHtml.split("\r\n");
		boolean flag = false;
		StringBuilder sb = new StringBuilder();
		for (String string : strings) {
			if (string.contains(s)) {
				flag = true;
			}
			// System.out.println(string);

			if (flag) {
				sb.append(string.trim());

			}

			if (flag) {
				if (string.contains("</div>")) {
					flag = false;
					// return;
				}
			}

		}
		String str = sb.toString();

		String[] qyss = str.split("</p>");
		StringBuilder sb2 = new StringBuilder();
		for (String string : qyss) {
			string = removeLabel(string);
			sb2.append(string + "\r\n");
		}

		String str2 = sb2.toString();
		str2 = jinJianStr(str2);
		
		if (!str2.equals("")) {
			System.out.println("setSymptom_cause is ok!");
		}else {
			System.out.println("setSymptom_cause is no!");
		}

		symptom.setSymptom_cause(str2);

	}

	// 首页
	public static void anyString1(String shouYeHtml) {
		String[] string = shouYeHtml.split("\r\n");
		for (String string2 : string) {
			if (string2.contains("<p class=\"sort2\">") && string2.contains("</p>")) {
				string2 = string2.trim();
				int i11 = "<p class=\"sort2\">".length();
				int i12 = string2.lastIndexOf("</p>");
				String symptom_intro = string2.substring(i11, i12);// 症状简介
				
				if (!symptom_intro.equals("")) {
					System.out.println("setSymptom_intro is ok!");
				}else {
					System.out.println("setSymptom_intro is no!");
				}

				symptom.setSymptom_intro(symptom_intro);
			}
		}
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

	// 对长数据字符串，进行精简
	private static String jinJianStr(String str) {
		if (str.length() > 2000) {
			String s1 = str.substring(0, 1500);
			String s2 = str.substring((str.length() - 490), str.length());
			str = s1 + "......" + s2;
			return str;
		} else {
			return str;
		}
	}

	private static String jinJianStr2(String str) {
		if (str.length() > 3000) {
			String s1 = str.substring(0, 2500);
			String s2 = str.substring((str.length() - 490), str.length());
			str = s1 + "......" + s2;
			return str;
		} else {
			return str;
		}
	}

	private static String removeLabel(String string) {
		string = string.replaceAll("&nbsp;", "");
		string = string.replaceAll("&amp;", "");
		string = string.replaceAll("rdquo;", "");
		string = string.replaceAll("ldquo;", "");
		string = string.replaceAll("mdash;", "");
		return string.replaceAll("</?[^>]+>", "");
	}

}