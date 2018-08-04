package cn.changeyd.crawler.service;

import cn.changeyd.crawler.dao.SymptomDiDao;

public class SymptomDiService {

	SymptomDiDao sdd = new SymptomDiDao();

	public void add(String string, int disease_id, String disease_name) {
		sdd.add(string, disease_id, disease_name);
	}

}
