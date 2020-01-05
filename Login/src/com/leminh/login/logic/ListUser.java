/**
 * Copy(C) 2020 Luvina SoftWare Company
 * ListUser.java Jan 5, 2020 2020 Minh
 * 
 */
package com.leminh.login.logic;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Minh
 *
 */
public class ListUser {
	private List<user> list;

	public ListUser() {

		list = new ArrayList<user>();
	}

	public void addList(String UserName, String passWord) {
		user user1 = new user();
		user1.setUserName(UserName);
		user1.setPassWord(passWord);
		list.add(user1);
	}

	public void connectDatabase() {
		try {
			// Tải lớp Driver
			Class.forName("com.mysql.jdbc.Drive");
			String url = "jdbc:mysql://localhost:3306/user";
			String user = "LeDacMinh";
			String password = "LeMinh96";
			// Tạo đối tượng connection
			Connection con = DriverManager.getConnection(url, user, password);

			// Tạo đối tượng Statement
			Statement stt = (Statement) con.createStatement();

			// Truy vấn Resultset
			ResultSet rs = ((java.sql.Statement) stt).executeQuery("select *from user");
			while (rs.next()) {
				addList(rs.getString(1), rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean login(String userName, String passWord) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserName().equals(userName) && list.get(i).equals(passWord)) {
				return true;
			}
		}
		return false;
	}

}
