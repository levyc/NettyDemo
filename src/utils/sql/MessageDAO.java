package utils.sql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import singlechat.server.message.dao.MessageModel;

public class MessageDAO {
	private static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/singlechat";
		String username = "root";
		String password = "";
		Connection conn = null;
		try {
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static List<MessageModel> getAll(String receiver) {
		Connection conn = getConn();
		String sql = "select * from message where receive=" + receiver
				+ "and state=" + 2;// 通过接收者名字和未读状态2查找出未读信息
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			System.out.println("============================");
			List<MessageModel> messagesList = new ArrayList<>();
			while (rs.next()) {
				for (int i = 1; i <= col; i++) {
					System.out.print(rs.getString(i) + "\t");
					if ((i == 2) && (rs.getString(i).length() < 8)) {
						System.out.print("\t");
					}
				}
				System.out.println("");
			}
			rs.close();
			pstmt.close();
			conn.close();
			System.out.println("============================");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
