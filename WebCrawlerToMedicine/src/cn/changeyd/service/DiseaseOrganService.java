package cn.changeyd.service;

import cn.changeyd.dao.DiseaseOrganDao;

public class DiseaseOrganService {
	DiseaseOrganDao dod = new DiseaseOrganDao();

	public void updateJiBin(String dStringI, String dStringN, String name) {
		dod.updateJiBin(dStringI, dStringN, name);
	}

	public void updateJiBin2(String dStringI, String dStringN, String name) {
		dod.updateJiBin2(dStringI, dStringN, name);
		
	}
}
