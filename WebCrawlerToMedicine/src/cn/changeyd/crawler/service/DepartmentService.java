package cn.changeyd.crawler.service;

import cn.changeyd.crawler.model.Department;
import cn.changeyd.dao.DepartmentDao;

public class DepartmentService {
	DepartmentDao dd = new DepartmentDao();
	public void add(Department d) {
		dd.add(d);
	}

}
