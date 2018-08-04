package cn.changeyd.crawler.service;

import java.util.List;

import cn.changeyd.crawler.dao.SymptomDao;
import cn.changeyd.crawler.model.Symptom;

public class SymptomService {
	private static SymptomDao sDao = new SymptomDao();

	public static void add(String c, String t) {
		Symptom symptom = new Symptom();
		symptom.setSymptom_chinese(c);
		symptom.setSymptom_trans(t);
		sDao.add(symptom);
	}

	public static Symptom read(int symptom_id) {
		return sDao.read(symptom_id);
	}

	public static void update(Symptom symptom) {
		sDao.update(symptom);
	}
	
	public static void update2(Symptom symptom) {
		sDao.update2(symptom);
	}

	public static void main(String[] args) {
		readIsNull();
	}

	public static List<Symptom> readIsNull() {
		List<Symptom> sList = sDao.readIsNull();
		return sList;
	}

}
