package cn.changeyd.crawler.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.changeyd.jdbc.JdbcUtil;

public class DiseaseDiDao {

	public void add(String string2, int disease_id, String disease_name) {
		Connection conn = null;
		String sql = "INSERT INTO t_complication(complication_name, complication_association_disease_id, complication_association_disease_name) "
				+ "VALUES(?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(string2);
		params.add(disease_id);
		params.add(disease_name);

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);
		if (i > 0) {
			System.out.println(i + ".成功添加“" + disease_name + "”疾病可能“" + string2 + "”疾病数据");
		}
	}

}
