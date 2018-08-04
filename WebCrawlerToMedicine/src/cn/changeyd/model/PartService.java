package cn.changeyd.model;

import cn.changeyd.dao.PardDao;

public class PartService {
	PardDao pd = new PardDao();
	
	public void add(String key, String string) {
		pd.add(key, string);
	}

}
