package cn.changeyd.crawler.service;

import cn.changeyd.crawler.dao.DiseaseDao;
import cn.changeyd.crawler.dao.DiseaseDiDao;

public class DiseaseDiService {
	DiseaseDiDao ddd = new DiseaseDiDao();

	public void add(String string2, int disease_id, String disease_name) {
		ddd.add( string2,  disease_id,  disease_name);
	}
	
	

}
