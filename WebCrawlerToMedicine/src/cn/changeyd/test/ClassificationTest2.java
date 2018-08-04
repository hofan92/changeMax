package cn.changeyd.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import cn.changeyd.model.OrganService;
import cn.changeyd.model.PartService;
import cn.changeyd.service.DiseaseOrganService;
import cn.changeyd.service.SymptomOrganService;

public class ClassificationTest2 {

	static SymptomOrganService dos = new SymptomOrganService();

	public static void main(String[] args) {
		fun1();
	}

	private static void fun1() {
		String url = "http://jbk.39.net/bw/";

		String html;
		try {
			html = clawer(url);
		} catch (IOException e) {
			return;
		}
		Map<String, List<String>> sMapList = anyString11(html);

		Iterator<Map.Entry<String, List<String>>> it = sMapList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<String>> entry = it.next();

			String key = entry.getKey();
			if (key.contains("href")) {
				tiQuUrl2(key);
			}
			System.out.println("---------------------------------------------------");

			List<String> sList = entry.getValue();
			if (sList.size() > 0) {
				for (String string : sList) {
					tiQuUrl(string);
					System.out.println("----------------------------");

				}
			}

		}

	}

	private static void tiQuUrl2(String key) {
		for (int i = 0; i < 1; i++) {
			// System.out.println(string);
			String s11 = "href=\"";
			int i11 = key.indexOf(s11) + s11.length();
			int i12 = key.indexOf("\" ");

			String url = "http://jbk.39.net" + key.substring(i11, i12 - 1) + "_t2/";
			System.out.println(url);
			String name = removeLabel(key);

			String htmlString = null;
			try {
				htmlString = clawer(url);
			} catch (IOException e) {
				i = 1;
			}

			Map<String, String> sMap = anyString1(htmlString);
			if (sMap != null) {
				Iterator<Map.Entry<String, String>> it = sMap.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					String dStringI = entry.getKey();
					String dStringN = entry.getValue();
					dos.updateJiBin2(dStringI, dStringN, name);
				}
			} else {
				i = 1;
			}
		}

		for (int i = 1; i < 200; i++) {
			String s11 = "href=\"";
			int i11 = key.indexOf(s11) + s11.length();
			int i12 = key.indexOf("\" ");

			String url = "http://jbk.39.net" + key.substring(i11, i12 - 1) + "_t2_p" + i + "#ps";
			System.out.println(url);
			String name = removeLabel(key);
			String htmlString = null;
			try {
				htmlString = clawer(url);
			} catch (IOException e) {
				i = 200;
			}

			Map<String, String> sMap = anyString1(htmlString);
			if (sMap != null) {
				Iterator<Map.Entry<String, String>> it = sMap.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					String dStringI = entry.getKey();
					String dStringN = entry.getValue();
					dos.updateJiBin2(dStringI, dStringN, name);

				}
			} else {
				i = 200;
			}
		}
	}

	private static void tiQuUrl(String string) {
		for (int i = 1; i < 2; i++) {

			String s11 = "href=\"";
			int i11 = string.indexOf(s11) + s11.length();
			int i12 = string.indexOf("\" ");

			String url = "http://jbk.39.net" + string.substring(i11, i12 - 1) + "_t2/";
			System.out.println(url + "+++++++++++++++++++++++++++");

			String name = removeLabel(string);

			String htmlString = null;
			try {
				htmlString = clawer(url);
			} catch (IOException e) {
				i = 2;
			}

			Map<String, String> sMap = anyString1(htmlString);

			if (sMap != null) {
				Iterator<Map.Entry<String, String>> it = sMap.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					String dStringI = entry.getKey();
					String dStringN = entry.getValue();
					dos.updateJiBin(dStringI, dStringN, name);
				}
			} else {
				i = 2;
			}
		}

		System.out.println("***********************************************************************************");
		for (int i = 1; i < 200; i++) {
			String s11 = "href=\"";
			int i11 = string.indexOf(s11) + s11.length();
			int i12 = string.indexOf("\" ");

			String url = "http://jbk.39.net" + string.substring(i11, i12 - 1) + "_t2_p" + i + "#ps";

			http: // jbk.39.net/bw/qianbi_p1#ps

			System.out.println(url + "+++++++++++++++++++++++++++");
			String name = removeLabel(string);

			String htmlString = null;
			try {
				htmlString = clawer(url);
			} catch (IOException e) {
				i = 200;
			}

			Map<String, String> sMap = anyString1(htmlString);

			if (sMap != null) {
				Iterator<Map.Entry<String, String>> it = sMap.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					String dStringI = entry.getKey();
					String dStringN = entry.getValue();
					dos.updateJiBin(dStringI, dStringN, name);
				}
			} else {
				i = 200;
			}
		}
	}

	static int i = 0;

	// 首页
	public static Map<String, String> anyString1(String html) {
		if (html != null && !html.equals("")) {
			if (html.contains("抱歉，您访问的页面不存在") || html.contains("很抱歉，暂未找到符合条件的信息，请")) {
				return null;
			}
			i++;
			System.out.println(i);

			Map<String, String> sMap = new HashMap<String, String>();

			String[] strings = html.split("\r\n");
			List<String> sList = new ArrayList<String>();
			String s1 = "<div class=\"res_list\"";
			String s2 = "title";
			boolean flag = false;
			boolean flag2 = false;

			int i = 0;

			for (String string : strings) {
				if (string.trim().length() > 0) {

					if (string.contains(s1)) {
						i++;
						flag = true;
					}
					if (flag) {
						if (string.contains(s2) && string.contains("dt class=\"clearfix\">")) {
							sList.add(string);
						}
					}

					if (flag) {
						if (string.contains("</div>")) {
							i--;
							if (i == 0) {
								flag = false;
							}
						}
					}

				}
			}

			for (String string : sList) {

				int i11 = string.indexOf("title=\"") + "title=\"".length();
				int i12 = string.lastIndexOf("\">");
				String stringName = string.substring(i11, i12);

				String s21 = "href=\"http://jbk.39.net/zhengzhuang/";
				int i21 = string.lastIndexOf(s21) + s21.length();
				int i22 = string.indexOf("/\" title");
				String stringIn = string.substring(i21, i22);
				System.out.println(stringIn);
				sMap.put(stringIn, stringName);

			}
			return sMap;
		}
		return null;

	}

	// 初步分析，抓取关键部位
	public static Map<String, List<String>> anyString11(String shouYeHtml) {

		String[] strings = shouYeHtml.split("\r\n");
		List<String> sList = new ArrayList();
		String s = "<div class=\"cond_box clearfix\" id=\"cond_box1\">";
		int i = 0;
		boolean flag = false;

		for (String string : strings) {
			if (string.trim().length() > 0) {
				if (string.contains(s)) {
					flag = true;
				}

				if (flag) {
					if (string.contains("self")) {
						sList.add(string);
					}
				}

				if (flag) {

					if (string.contains("<div")) {
						i++;
					}

					if (i == 0) {
						flag = false;
						break;
					}

					if (string.contains("</div")) {
						i--;
					}
				}
			}
		}

		return anyList(sList);
	}

	private static Map<String, List<String>> anyList(List<String> sList) {
		// 抓取疾病介绍
		Map<String, List<String>> sMapList = new HashMap<String, List<String>>();
		for (String string : sList) {
			String[] strings = string.split("<");

			for (int i = 0; i < strings.length; i++) {
				strings[i] = "<" + strings[i];
			}

			boolean flag = false;
			boolean flag2 = false;
			List<String> newSList = null;
			String skey = "";
			for (String string2 : strings) {
				if (string2.contains("<dl") && string2.contains("class")) {
					flag = true;
					newSList = new ArrayList<String>();
				}

				if (flag) {
					if (string2.contains("<dt>")) {
						flag2 = true;
					}
					if (flag2) {
						skey = skey + string2;
					}
					if (string2.contains("</dt>")) {
						flag2 = false;
					}

					if (!flag2 && flag) {
						if (string2.contains("href")) {
							newSList.add(string2);
						}
					}

					if (string2.contains("</dl>")) {
						sMapList.put(skey, newSList);
						flag = false;
					}
				}

				if (string.contains("div") && string.contains("href")) {
					sMapList.put(string2, new ArrayList<String>());
				}
			}
		}
		return sMapList;

	}

	private static String removeLabel(String string) {
		string = string.replaceAll("&nbsp;", "");
		string = string.replaceAll("&amp;", "");
		string = string.replaceAll("rdquo;", "");
		string = string.replaceAll("ldquo;", "");
		string = string.replaceAll("mdash;", "");
		return string.replaceAll("</?[^>]+>", "");
	}

	public static String clawer(String myurl) throws UnsupportedEncodingException, IOException {
		int i = 0;
		StringBuffer sb = new StringBuffer("");
		URL url;

		url = new URL(myurl);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "gbk"));
		String s = "";
		while ((s = br.readLine()) != null) {
			i++;
			sb.append(s + "\r\n");
		}

		return sb.toString();
	}
}
