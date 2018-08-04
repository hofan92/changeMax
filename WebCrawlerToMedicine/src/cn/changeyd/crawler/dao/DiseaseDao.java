package cn.changeyd.crawler.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.changeyd.crawler.model.Disease;
import cn.changeyd.crawler.model.Symptom;
import cn.changeyd.jdbc.JdbcUtil;

public class DiseaseDao {

	public void add(Disease disease) {
		/*
		 * private String disease_name;// 疾病名称 private String disease_trans;//
		 * 疾病名称音译 private String disease_alias;// 疾病别名 private String
		 * disease_intro;// 疾病简介 private List<String> partList;// 疾病发病部位集
		 * private String disease_contagious;// 疾病的传染性 private String
		 * disease_multiple_people;// 疾病多发人群 private String
		 * disease_symptom_early;// 疾病早期症状 private String
		 * disease_symptom_late;// 疾病晚期症状 private String
		 * disease_symptom_related;// 疾病相关症状 private String
		 * disease_symptom_intro;// 疾病相关症状介绍 private String
		 * disease_complication;// 疾病并发症 private String
		 * disease_complication_intro;// 疾病并发症介绍 private String
		 * disease_visit_department;// 疾病就诊科室 private String
		 * disease_cure_rate;// 疾病治愈率
		 * 
		 */
		Connection conn = null;
		String sql = "INSERT INTO t_disease("
				+ "disease_name, disease_trans, disease_alias, disease_intro, disease_incidence_site, "
				+ "disease_contagious, disease_multiple_people, disease_symptom_early, disease_symptom_late, "
				+ "disease_symptom_related, disease_symptom_intro, disease_complication, disease_complication_intro, "
				+ "disease_visit_department, disease_cure_rate" + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(disease.getDisease_name());
		params.add(disease.getDisease_trans());
		params.add(disease.getDisease_alias());
		params.add(disease.getDisease_intro());
		params.add(disease.getDisease_incidence_site());
		params.add(disease.getDisease_contagious());
		params.add(disease.getDisease_multiple_people());
		params.add(disease.getDisease_symptom_early());
		params.add(disease.getDisease_symptom_late());
		params.add(disease.getDisease_symptom_related());
		params.add(disease.getDisease_symptom_intro());
		params.add(disease.getDisease_complication());
		params.add(disease.getDisease_complication_intro());
		params.add(disease.getDisease_visit_department());
		params.add(disease.getDisease_cure_rate());

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);
		if (i > 0) {
			System.out.println(i + ".成功添加“" + disease.getDisease_name() + "”疾病数据");
		}
	}

	public void addName(String name, String trans) {
		Connection conn = null;
		String sql = "INSERT INTO t_disease(disease_name, disease_trans) VALUES(?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(trans);

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);
		if (i > 0) {
			System.out.println(i + ".成功添加“" + name + "”疾病数据");
		}
	}

	public Disease getDisease(int i) {
		Disease disease = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "SELECT * FROM t_disease WHERE disease_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(i);

		conn = JdbcUtil.getConnection();
		rs = JdbcUtil.executQuery(conn, sql, params);
		try {
			if (rs.next()) {
				disease = new Disease();

				disease.setDisease_id(rs.getInt("disease_id"));
				disease.setDisease_trans(rs.getString("disease_trans"));
				disease.setDisease_name(rs.getString("disease_name"));

				System.out.println("*****************************************************成功读取"
						+ disease.getDisease_name() + "症状数据");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return disease;
	}

	public void update1(Disease disease) {
		// Disease [disease_id=0, disease_name=null, disease_trans=null,
		// disease_alias=消渴,
		// disease_intro=null, disease_incidence_site=胰腺 全身 ,
		// disease_contagious=无传染性,
		// disease_multiple_people=四十岁以上的中年人, disease_symptom_early=null,
		// disease_symptom_late=null,
		// disease_symptom_related=null, disease_symptom_intro=null,
		// disease_complication=null,
		// disease_complication_intro=null, disease_visit_department=内分泌科 老年科 ,
		// disease_cure_rate=药物可控制，不易根治]

		Connection conn = null;
		String sql = "UPDATE t_disease SET " + "disease_alias=?, disease_incidence_site=?, "
				+ "disease_contagious=?, disease_multiple_people=?, "
				+ "disease_visit_department=?, disease_cure_rate=?" + " WHERE disease_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(disease.getDisease_alias());
		params.add(disease.getDisease_incidence_site());
		params.add(disease.getDisease_contagious());
		params.add(disease.getDisease_multiple_people());
		params.add(disease.getDisease_visit_department());
		params.add(disease.getDisease_cure_rate());

		params.add(disease.getDisease_id());

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);
		if (i > 0) {
			System.out.println("*****************************************************成功更新“"
					+ disease.getDisease_name() + "”中症状数据");
		}
	}

	public void update2(Disease disease) {
		Connection conn = null;
		String sql = "UPDATE t_disease SET " + "disease_intro=?, disease_symptom_early=?, disease_symptom_late=?, "
				+ "disease_symptom_related=?, disease_symptom_intro=?, disease_complication=?, disease_complication_intro=?" 
				+ " WHERE disease_id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(disease.getDisease_intro());
		params.add(disease.getDisease_symptom_early());
		params.add(disease.getDisease_symptom_late());
		params.add(disease.getDisease_symptom_related());
		params.add(disease.getDisease_symptom_intro());
		params.add(disease.getDisease_complication());
		params.add(disease.getDisease_complication_intro());

		params.add(disease.getDisease_id());

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);
		if (i > 0) {
			System.out.println("*****************************************************成功更新“"
					+ disease.getDisease_name() + "”中症状数据");
		}
	}

}
