package cn.changeyd.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.changeyd.jdbc.JdbcUtil;

public class PardDao {

	public void add(String key, String string) {
		Connection conn = null;
		String sql = "INSERT INTO t_part(part_name, part_contained_organs) VALUES(?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(key);
		params.add(string);

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);

	}

}
