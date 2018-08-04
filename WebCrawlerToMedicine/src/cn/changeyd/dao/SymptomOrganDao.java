package cn.changeyd.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.changeyd.jdbc.JdbcUtil;

public class SymptomOrganDao {

	public void updateJiBin(String dStringI, String dStringN, String name) {
		Connection conn = null;
		String sql = "INSERT INTO t_symptom_or_pa(symptom_name, symptom_trans, organ_name) VALUES(?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(dStringN);
		params.add(dStringI);
		params.add(name);

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);

	}
	
	public void updateJiBin2(String dStringI, String dStringN, String key) {
		Connection conn = null;
		String sql = "INSERT INTO t_symptom_or_pa(symptom_name, symptom_trans, part_name) VALUES(?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(dStringN);
		params.add(dStringI);
		params.add(key);

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);

	}
}
