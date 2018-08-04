package cn.changeyd.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.changeyd.crawler.dao.DiseaseDiDao;
import cn.changeyd.crawler.model.Disease;
import cn.changeyd.crawler.service.DiseaseDiService;
import cn.changeyd.crawler.service.DiseaseService;
import cn.changeyd.crawler.service.SymptomDiService;

public class DiseaseTest2 {

	static DiseaseService ds = new DiseaseService();
	static SymptomDiService sds = new SymptomDiService();
	static DiseaseDiService ddd = new DiseaseDiService();

	public static void main(String[] args) {

		for (int i = 1; i <= 2000; i++) {
			Disease disease = ds.getDisease(i);

			String htmlUrl = "http://jbk.39.net/" + disease.getDisease_trans() + "/jbzs/";

			String htmlString = clawer(htmlUrl);

			htmlString = anyString1(htmlString);// 分析出整个网页重要信息

			String jieShao = removeLabel(anyString2(htmlString));// 分析出疾病介绍信息
			// System.out.println(jieShao);
			disease.setDisease_intro(jinJianStr(jieShao));
			System.out.println(
					"***********************************************************************************************************");
			System.out.println(jieShao);
			System.out.println(
					"***********************************************************************************************************");
			// String jiBen = anyString3(htmlString);// 分析出疾病基本信息
			//
			// String bieMing = removeLabel(anyString(jiBen, "别名："));// 分析出别名
			// System.out.println(bieMing);
			// disease.setDisease_alias(bieMing);
			// String faBinBuWei = anyStringList(jiBen, "发病部位：");// 分析出发病部位集
			// System.out.println(faBinBuWei);
			// disease.setDisease_incidence_site(faBinBuWei);
			//
			// String chuanRanXin = removeLabel(anyString(jiBen, "传染性："));//
			// 分析出有无传染性
			// System.out.println(chuanRanXin);
			// disease.setDisease_contagious(chuanRanXin);
			// String duoFaRenQun = removeLabel(anyString(jiBen, "多发人群："));//
			// 分析出多发人群
			// System.out.println(duoFaRenQun);
			// disease.setDisease_multiple_people(duoFaRenQun);
			//
			// String zhenLiao = anyString32(htmlString);// 诊疗知识
			// String jianYiKeShi = anyStringList(zhenLiao, "就诊科室：");// 建议就诊科室
			// System.out.println(jianYiKeShi);
			// disease.setDisease_visit_department(jianYiKeShi);
			//
			// String zhiYuLv = removeLabel(anyString(zhenLiao, "治愈率："));// 治愈率
			// System.out.println(zhiYuLv);
			// disease.setDisease_cure_rate(zhiYuLv);

			// ds.update1(disease);

			String htmlUrl3 = "http://jbk.39.net/" + disease.getDisease_trans() + "/bfbz/";
			String htmlString3 = clawer(htmlUrl3);
			String binFaZheng = anyString333333(htmlString3);// 并发症

			List<String> syList2 = anyStringList(binFaZheng, "常见并发症");// 建议就诊科室
			StringBuilder sb2 = new StringBuilder();
			for (String string2 : syList2) {
				ddd.add(string2, disease.getDisease_id(), disease.getDisease_name());
				sb2.append(string2 + " ");
			}
			System.out.println("常见并发症：" + sb2.toString());
			disease.setDisease_complication(sb2.toString());

			String binFaZhangJieShao = jinJianStr(anyStringJieShao(htmlString3));// 并发症介绍
			disease.setDisease_complication_intro(binFaZhangJieShao);

			System.out.println("常见并发症介绍：" + binFaZhangJieShao);
			System.out.println(
					"********************************************************************************************************************");

			String htmlUrl2 = "http://jbk.39.net/" + disease.getDisease_trans() + "/zztz/";
			String htmlString2 = clawer(htmlUrl2);

			String zhenZhang = anyString2222222(htmlString2);// 症状

			String zaoQi = removeLabel(anyString(zhenZhang, "早期症状："));// 分析出早期症状
			System.out.println("早期症状：" + zaoQi);
			disease.setDisease_symptom_early(zaoQi);
			String wanQi = removeLabel(anyString(zhenZhang, "晚期症状："));// 分析出晚期症状
			System.out.println("晚期症状：" + wanQi);
			disease.setDisease_symptom_late(wanQi);

			List<String> syList = anyStringList(zhenZhang, "相关症状：");// 建议就诊科室
			StringBuilder sb = new StringBuilder();
			for (String string : syList) {
				sds.add(string, disease.getDisease_id(), disease.getDisease_name());
				sb.append(string + " ");
			}
			System.out.println("相关症状：" + sb.toString());
			disease.setDisease_symptom_related(sb.toString());

			String zhenZhuangJieShao = jinJianStr(anyStringJieShao(htmlString2));// 相关症状介绍
			// System.out.println(zhenZhuangJieShao);
			disease.setDisease_symptom_intro(zhenZhuangJieShao);

			ds.update2(disease);
		}

	}

	private static String anyString333333(String htmlString3) {
		String[] strings = htmlString3.split("\r\n");
		StringBuilder sb = new StringBuilder();
		String s = "<dl class=\"links\">";
		int i = 0;
		boolean flag = false;
		boolean flag2 = false;

		for (String string1 : strings) {
			if (string1.trim().length() > 0) {

				if (string1.contains(s)) {
					flag2 = true;
				}
				if (flag2) {
					if (string1.contains("常见并发症</dt>")) {
						flag = true;
					}
				}
				if (flag) {
					sb.append(string1 + "\r\n");
				}

				if (flag) {
					if (string1.contains("</dl")) {
						flag = false;
						break;
					}
				}

			}
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

	private static String anyStringJieShao(String htmlString2) {

		String[] strings = htmlString2.split("\r\n");
		StringBuilder sb = new StringBuilder();
		String s = "<div class=\"art-box\">";
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
		String htmlJieShao = sb.toString();

		String[] strings2 = htmlJieShao.split("</p>");

		StringBuilder sb2 = new StringBuilder();

		for (String string : strings2) {
			string = removeLabel(string);
			sb2.append(string + "\r\n");
		}

		return sb2.toString();
	}

	private static String anyString2222222(String htmlString2) {
		String[] strings = htmlString2.split("\r\n");
		StringBuilder sb = new StringBuilder();
		String s = "<dl class=\"links\">";
		int i = 0;
		boolean flag = false;
		boolean flag2 = false;

		for (String string1 : strings) {
			if (string1.trim().length() > 0) {

				if (string1.contains(s)) {
					flag2 = true;
				}
				if (flag2) {
					if (string1.contains("症状</dt>")) {
						flag = true;
					}
				}
				if (flag) {
					sb.append(string1 + "\r\n");
				}

				if (flag) {
					if (string1.contains("</dl")) {
						flag = false;
						break;
					}
				}

			}
		}
		return sb.toString();
	}

	private static String anyString12(String htmlString2) {

		String[] strings = htmlString2.split("\r\n");
		StringBuilder sb = new StringBuilder();
		String s = "<div class=\"content clearfix\">";
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

	private static String anyString32(String htmlString) {

		String[] strings = htmlString.split("\r\n");
		StringBuilder sb = new StringBuilder();
		String s = "<dl class=\"info\">";
		int i = 0;
		boolean flag = false;
		boolean flag2 = false;

		for (String string : strings) {
			if (string.trim().length() > 0) {

				if (string.contains(s)) {
					flag2 = true;
				}
				if (flag2) {
					if (string.contains("诊疗知识</dt>")) {
						flag = true;
					}
				}
				if (flag) {
					sb.append(string + "\r\n");
				}

				if (flag) {
					if (string.contains("</dl")) {
						flag = false;
						break;
					}
				}

			}
		}
		return sb.toString();
	}

	private static String anyString(String jiBen, String keyWord) {
		String[] strings = jiBen.split("\r\n");
		StringBuilder sb = new StringBuilder();
		int i = 0;
		boolean flag = false;

		for (String string : strings) {
			if (string.trim().length() > 0) {

				if (string.contains(keyWord)) {
					// 把传染性：去除
					string = string.substring(string.indexOf(keyWord) + keyWord.length(), string.length());

					flag = true;
				}

				if (flag) {

					sb.append(string + "\r\n");
				}

				if (flag) {
					if (string.contains("</dd")) {
						flag = false;
						break;
					}
				}

			}
		}
		return sb.toString();
	}

	private static List<String> anyStringList(String jiBen, String keyWord) {

		String[] strings = jiBen.split("\r\n");
		StringBuilder sb = new StringBuilder();
		int i = 0;
		boolean flag = false;

		for (String string : strings) {
			if (string.trim().length() > 0) {

				if (string.contains(keyWord)) {
					string = string.substring(string.indexOf(keyWord) + keyWord.length(), string.length());
					flag = true;
				}

				if (flag) {

					sb.append(string + "\r\n");
				}

				if (flag) {
					if (string.contains("</dd")) {
						flag = false;
						break;
					}
				}

			}
		}

		List<String> syList = new ArrayList<String>();
		StringBuilder sb1 = new StringBuilder();
		String[] strings2 = sb.toString().split("</a>");
		for (String string : strings2) {
			string = removeLabel(string);
			if (string.trim().length() > 0) {
				syList.add(string);
			}
		}

		return syList;
	}

	private static String anyString3(String htmlString) {

		String[] strings = htmlString.split("\r\n");
		StringBuilder sb = new StringBuilder();
		String s = "<dl class=\"info\">";
		int i = 0;
		boolean flag = false;
		boolean flag2 = false;

		for (String string : strings) {
			if (string.trim().length() > 0) {

				if (string.contains(s)) {
					flag2 = true;
				}
				if (flag2) {
					if (string.contains("基本知识</dt>")) {
						flag = true;
					}
				}
				if (flag) {
					sb.append(string + "\r\n");
				}

				if (flag) {
					if (string.contains("</dl")) {
						flag = false;
						break;
					}
				}

			}
		}
		return sb.toString();
	}

	// 抓取疾病介绍
	public static String anyString2(String htmlString) {

		String[] strings = htmlString.split("\r\n");
		StringBuilder sb = new StringBuilder();
		String s = "简介</dt>";
		int i = 0;
		boolean flag = false;

		for (String string : strings) {
			if (string.trim().length() > 0) {

				if (flag) {
					sb.append(string + "\r\n");
				}

				if (flag) {
					if (string.contains("</dd")) {
						flag = false;
						break;
					}
				}

				if (string.contains(s)) {
					flag = true;
				}
			}
		}
		return sb.toString();
	}

	// 初步分析，抓取关键部位
	public static String anyString1(String shouYeHtml) {

		String[] strings = shouYeHtml.split("\r\n");
		StringBuilder sb = new StringBuilder();
		String s = "<div class=\"chi-know\">";
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
