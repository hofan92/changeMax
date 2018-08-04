package cn.changeyd.crawler.service;

import cn.changeyd.crawler.dao.DiseaseDao;
import cn.changeyd.crawler.model.Disease;

public class DiseaseService {
	static DiseaseDao dd = new DiseaseDao();
	
	public void addName(String name, String trans){
		dd.addName(name, trans);
	}

	public Disease getDisease(int i) {
		// TODO Auto-generated method stub
		return dd.getDisease(i);
	}

	public void update1(Disease disease) {
		dd.update1(disease);
	}

	public void update2(Disease disease) {
		dd.update2(disease);

	}
	

}
