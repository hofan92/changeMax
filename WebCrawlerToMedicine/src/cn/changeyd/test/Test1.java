package cn.changeyd.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test1 {

	public static void main(String[] args) {
	//	String s = readString("file.txt");
		// System.out.println(s);

		fun("<sadfsadfasdf/><sadfsadfasdf>网上的风格<sadfsadfasdf/>");
	}

	public static String readString(String fileName) {
		int len = 0;
		StringBuffer str = new StringBuffer("");
		File file = new File(fileName);
		try {
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader in = new BufferedReader(isr);
			String line = null;
			while ((line = in.readLine()) != null) {
				if (len != 0) { // 处理换行符的问题
					// System.out.println(fun(line));
					str.append("\r\n" + line);
				} else {
					str.append(line);
				}
				len++;
			}
			in.close();
			isr.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str.toString();
	}

	public static String fun(String s) {
		if (s.contains("<")) {
			int start = s.indexOf("<");
			
			String string = s.substring(start + 1, s.length());
			System.out.println(string);
			int end = string.indexOf(">");
			String s2 = s.substring(end, s.length());
			if (s2.length() <= 0) {
				return s2;
			}
			// fun(s2);

		}
		return s;

	}
	
	

}
