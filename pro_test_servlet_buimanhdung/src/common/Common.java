/**
 * Copyright(C) 2019 Luvina Software
 * Common.java, Dec 27, 2019, MDung
 */
package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class chứa các phương thức đóng mở kết nối Database: openConnect(),
 * closeConnect() Chứa phương thức listAllUsers() trả về 1 list gồm các User
 * trong Database
 * 
 * @author MDung
 *
 */
public class Common {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection con;

	/**
	 * Constructor
	 */
	public Common() {
		super();
	}

	/**
	 * Constructor chứa tham số
	 * 
	 * @param jdbcURL
	 * @param jdbcUsername
	 * @param jdbcPassword
	 */
	public Common(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	/**
	 * Hàm mở kết nối DB
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected void openConnect() throws ClassNotFoundException, SQLException {

		if (con == null || con.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	/**
	 * Hàm đóng kết nối DB
	 * 
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public void closeConnect() throws SQLException, NullPointerException {

		if (!con.isClosed() || con != null) {
			con.close();
		}
	}

	/**
	 * Hàm trả về list các user trong DB
	 * 
	 * @return listUser
	 * @throws SQLException
	 */
	public List<User> searchUsers(String key) throws SQLException {
		List<User> listUser = new ArrayList<>();

		// Câu Sql lấy toàn bộ dữ liệu từ bảng User lưu trong biến sql
		String sql = "SELECT * FROM User Where name LIKE '%" + key + "%'";

		// Mở kết nối
		try {
			openConnect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		// Lấy từng bản ghi trong resultSet thêm vào listUser
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String birthday = resultSet.getString(3);
			String birthplace = resultSet.getString(4);

			// Khởi tạo 1 User với input id, name, birthday, birthplace
			User User = new User(id, name, birthday, birthplace);
			// Thêm User vừa tạo vào listUser
			listUser.add(User);
		}
		
		// Đóng resultSet, statement
		resultSet.close();
		statement.close();
		
		// Đóng kết nối
		closeConnect();
		return listUser;
	}
}
