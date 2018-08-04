package cn.changeyd.crawler.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.changeyd.crawler.model.DiseaseSy;
import cn.changeyd.jdbc.JdbcUtil;

public class DiseaseSyDao {

	// 新建
	public void add(DiseaseSy disease, int id, String chinese) {

		Connection conn = null;
		String sql = "INSERT INTO t_disease_sy(disease_name, disease_accom_sy, disease_ossible_sy_id, disease_ossible_sy_chinese) VALUES(?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(disease.getDiseaseName());
		params.add(disease.getDiseaseAccomSy());
		params.add(id);
		params.add(chinese);

		conn = JdbcUtil.getConnection();
		int i = JdbcUtil.executUpdate(conn, sql, params);
		if (i > 0) {
			System.out.println(i + ".成功添加“" + chinese + "”症状可能“" + disease.getDiseaseName() + "”病症数据");
		}
	}

}
