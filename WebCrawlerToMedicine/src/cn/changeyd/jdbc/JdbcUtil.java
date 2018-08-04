package cn.changeyd.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public final class JdbcUtil {
	private static String url = "jdbc:mysql://localhost:3306/medical_library?useSSL=false&useUnicode=true&characterEncoding=utf8";
	private static String username = "root";
	private static String password = "123456";
	private static String driver = "com.mysql.jdbc.Driver";

	/**
	 * 连接数据
	 *
	 * @return conn
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}


	/**
	 * 关闭连接对象
	 *
	 * @param conn
	 *            连接对象
	 * @param pstmt
	 *            预编译对象
	 * @param rs
	 *            结果集
	 */
	public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增删改操作
	 *
	 * @param sql
	 *            SQL命令
	 * @param param
	 *            参数
	 * @return
	 */
	public static int executUpdate(Connection conn, String sql, List<?> params) {
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			// 填充sql语句中的占位符
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
				}
			}
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
		return result;
	}

	/**
	 * 查询
	 *
	 * @return int
	 * @date 2015-7-25 上午11:10:06
	 */
	public static ResultSet executQuery(Connection conn, String sql, List<?> params) {
		PreparedStatement pstmt = null;
		ResultSet result = null;
		int index = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
				}
			}
			result = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}
