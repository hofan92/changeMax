package cn.changeyd.model;

import cn.changeyd.dao.OrganDao;

public class OrganService {
	OrganDao od = new OrganDao();
	
	public void add(String value, String key) {
		od.add(value, key);
	}

}
