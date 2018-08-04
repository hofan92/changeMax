package cn.changeyd.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.changeyd.crawler.model.Department;
import cn.changeyd.crawler.service.DepartmentService;

/**
 * 对百度词条进行搜索，分析结果
 * 
 * @author WangJi
 *
 */
public class DepartmentTest {
	static DepartmentService ds = new DepartmentService();

	public static void main(String[] args) {
		List<String> sList = new ArrayList<String>();

		// 创建字符缓冲输入流对象
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("File.txt"));
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				String[] strings = line.split("--");
				sList.add(strings[1]);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String string : sList) {
			String url = "https://baike.baidu.com/item/" + string;
			String htmlString = clawer(url);
			String fenXi = anyString1(htmlString);

			String fenXi2 = removeLabel(fenXi);
			System.out.println(fenXi2);
			Department d = new Department();
			d.setDepartment_name(string);
			if (fenXi2.trim().length() == 0) {

				fenXi2 = "暂无简介。";
			}
			d.setDepartment_intro(fenXi2.trim());

			ds.add(d);

		}

	}

	// 初步分析，抓取关键部位
	public static String anyString1(String shouYeHtml) {

		String[] strings = shouYeHtml.split("\r\n");
		StringBuilder sb = new StringBuilder();
		String s = "<div class=\"para\" label-module=\"para\">";
		int i = 0;
		boolean flag = false;

		for (String string : strings) {
			if (string.trim().length() > 0) {
				if (string.contains(s)) {
					flag = true;
				}

				if (flag) {
					sb.append(string + "\r\n");
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
		return sb.toString();
	}

	private static String removeLabel(String string) {
		string = string.replaceAll("&nbsp;", " ");
		string = string.replaceAll("&amp;", "");
		string = string.replaceAll("rdquo;", "");
		string = string.replaceAll("ldquo;", "");
		string = string.replaceAll("mdash;", "");
		string = string.replaceAll("</?[^>]+>", "");
		return string.trim();
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
