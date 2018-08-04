package cn.changeyd.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.changeyd.crawler.model.Department;
import cn.changeyd.jdbc.JdbcUtil;

public class DepartmentDao {

	public void add(Department d) {
		Connection conn = null;
		String sql = "INSERT INTO t_department(department_name, department_intro) VALUES(?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(d.getDepartment_name());
		params.add(d.getDepartment_intro());

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);

	}

}
