package cn.changeyd.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.changeyd.jdbc.JdbcUtil;

public class OrganDao {

	public void add(String value, String key) {
		Connection conn = null;
		String sql = "INSERT INTO t_organ(organ_name, organ_own_part) VALUES(?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(value);
		params.add(key);
		
		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);

	}

}
