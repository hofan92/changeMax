package cn.changeyd.crawler.service;

import java.util.List;

import cn.changeyd.crawler.dao.DiseaseSyDao;
import cn.changeyd.crawler.model.DiseaseSy;
import cn.changeyd.crawler.model.Symptom;

public class DiseaseSyService {
	private static DiseaseSyDao dDao = new DiseaseSyDao();

	public static void add(Symptom symptom) {
		List<DiseaseSy> dList = symptom.getdList();
		for (DiseaseSy disease : dList) {
			dDao.add(disease, symptom.getSymptom_id(), symptom.getSymptom_chinese());
		}
	}
	
	public static void main(String[] args) {
		
	}

}
