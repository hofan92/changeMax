package cn.changeyd.crawler.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.changeyd.jdbc.JdbcUtil;

public class SymptomDiDao {

	public void add(String string, int disease_id, String disease_name) {
		Connection conn = null;
		String sql = "INSERT INTO t_symptom_di(symptom_di_name, symptom_association_disease_id, symptom_association_disease_name) "
				+ "VALUES(?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(string);
		params.add(disease_id);
		params.add(disease_name);

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);
		if (i > 0) {
			System.out.println(i + ".成功添加“" + disease_name + "”疾病可能“" + string + "”病症数据");
		}
	}
	

}
