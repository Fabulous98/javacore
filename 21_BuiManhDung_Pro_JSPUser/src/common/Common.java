/**
 * Copyright(C) 2019 Luvina Software
 * Common.java, Dec 27, 2019, MDung
 */
package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * @author MDung
 *
 */
public class Common {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection con;

	public Common() {
		super();
	}

	public Common(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

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

	public void closeConnect() throws SQLException, NullPointerException {

		if (!con.isClosed() || con != null) {
			con.close();
		}
	}

	public List<User> listAllUsers() throws SQLException {
		List<User> listUser = new ArrayList<>();

		String sql = "SELECT * FROM User";

		try {
			openConnect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String birthday = resultSet.getString(3);
			String birthplace = resultSet.getString(4);

			User User = new User(id, name, birthday, birthplace);
			listUser.add(User);
		}

		resultSet.close();
		statement.close();

		closeConnect();

		return listUser;
	}
}
