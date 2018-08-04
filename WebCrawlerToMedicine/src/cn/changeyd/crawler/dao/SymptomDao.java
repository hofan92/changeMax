package cn.changeyd.crawler.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.changeyd.crawler.model.Symptom;
import cn.changeyd.jdbc.JdbcUtil;

public class SymptomDao {

	// 新建
	public void add(Symptom symptom) {

		Connection conn = null;
		String sql = "INSERT INTO t_symptom(symptom_chinese,symptom_trans) VALUES(?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(symptom.getSymptom_chinese());
		params.add(symptom.getSymptom_trans());

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);
		if (i > 0) {
			System.out.println(i + ".成功添加" + symptom.getSymptom_chinese() + "症状数据");
		}
	}

	public List<Symptom> readIsNull() {
		List<Symptom> sList = new ArrayList<Symptom>();

		String sql = "select symptom_id, symptom_chinese, symptom_trans from t_symptom where ISNULL(symptom_intro)";
		ResultSet rs = null;
		Connection conn = null;
		List<Object> params = new ArrayList<Object>();

		conn = JdbcUtil.getConnection();
		rs = JdbcUtil.executQuery(conn, sql, params);
		try {
			while (rs.next()) {
				Symptom symptom = new Symptom();
				symptom.setSymptom_id(rs.getInt("symptom_id"));
				symptom.setSymptom_chinese(rs.getString("symptom_chinese"));
				symptom.setSymptom_trans(rs.getString("symptom_trans"));
				sList.add(symptom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sList;

	}

	// 读s
	public Symptom read(int symptom_id) {
		Symptom symptom = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "SELECT * FROM t_symptom WHERE symptom_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(symptom_id);

		conn = JdbcUtil.getConnection();
		rs = JdbcUtil.executQuery(conn, sql, params);
		try {
			if (rs.next()) {
				symptom = new Symptom();

				symptom.setSymptom_id(rs.getInt("symptom_id"));
				symptom.setSymptom_chinese(rs.getString("symptom_chinese"));
				symptom.setSymptom_trans(rs.getString("symptom_trans"));
				symptom.setSymptom_intro(rs.getString("symptom_intro"));
				symptom.setSymptom_cause(rs.getString("symptom_cause"));
				symptom.setSymptomaticDetailsContent(rs.getString("symptomatic_details_content"));
				symptom.setSuggestedTreatmentDepartment(rs.getString("suggested_treatment_department"));

				System.out.println("*****************************************************成功读取"
						+ symptom.getSymptom_chinese() + "症状数据");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return symptom;
	}

	// 更新
	public void update(Symptom symptom) {

		Connection conn = null;
		String sql = "UPDATE t_symptom SET symptom_intro=?, symptom_cause=?, symptomatic_details_content=?, suggested_treatment_department=?"
				+ " WHERE symptom_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(symptom.getSymptom_intro());
		params.add(symptom.getSymptom_cause());
		params.add(symptom.getSymptomaticDetailsContent());
		params.add(symptom.getSuggestedTreatmentDepartment());

		params.add(symptom.getSymptom_id());

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);
		if (i > 0) {
			System.out.println("*****************************************************成功更新“"
					+ symptom.getSymptom_chinese() + "”中症状数据");
		}
	}
	
	
	// 更新
	public void update2(Symptom symptom) {

		Connection conn = null;
		String sql = "UPDATE t_symptom SET symptom_intro=?"
				+ " WHERE symptom_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(symptom.getSymptom_intro());

		params.add(symptom.getSymptom_id());

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);
		if (i > 0) {
			System.out.println("*****************************************************成功更新“"
					+ symptom.getSymptom_chinese() + "”中症状数据");
		}
	}

}
