/**
 * Copyright(C) 2019 Luvina Software
 * BaseDaoImpl.java, Dec 27, 2019, MDung
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import manageuser.dao.BaseDao;
import manageuser.properties.DatabaseProperties;

/**
 * @author MDung
 *
 */
public abstract class BaseDaoImpl implements BaseDao {

	protected Connection connect;
	String DRIVER = DatabaseProperties.getValueByKey("DRIVER");
	String URL = DatabaseProperties.getValueByKey("URL");
	String USER = DatabaseProperties.getValueByKey("USER");
	String PASS_DB = DatabaseProperties.getValueByKey("PASS");

	/**
	 * Mở kết nối với database
	 * 
	 * @throws ClassNotFoundException
	 */
	@Override
	public void openConnect() throws SQLException, ClassNotFoundException {
		try {
			Class.forName(DRIVER);
			connect = DriverManager.getConnection(URL, USER, PASS_DB);
		} catch (SQLException e) {
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Đóng kết nối với database
	 */
	@Override
	public void closeConnect() throws SQLException {
		try {
			// Nếu connect khác null hoặc chưa đóng
			if (connect != null && !connect.isClosed()) {
				// đóng kết nối
				connect.close();
			}
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.err.println("Class:BaseConnectionImpl , Function: closeConnect, " + e.getMessage());
			throw e;
		}
	}

	/**
	 * @return the connect
	 */
	@Override
	public Connection getConnect() {
		return connect;
	}

	/**
	 * @param connect
	 *            the connect to set
	 */
	@Override
	public void setConnect(Connection connect) {
		this.connect = connect;
	}

	/**
	 * Hàm commit dữ liệu vào DB
	 */
	@Override
	public void commit() throws SQLException {
		try {
			// Nếu chuỗi kết nối khác null
			if (connect != null) {
				// Thực hiện commit
				connect.commit();
			}
		} catch (SQLException e) {
			System.out.println("BaseDaoImpl.commit: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Rollback lại dữ liệu
	 */
	@Override
	public void rollback() throws SQLException {
		try {
			// Nếu chuỗi kết nối khác null
			if (connect != null) {
				// Thực hiện rollback dữ liệu
				connect.rollback();
			}
		} catch (SQLException e) {
			System.out.println("BaseDaoImpl.rollback: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * set tự động commit
	 */
	@Override
	public void setAutoCommit(boolean isCommit) throws SQLException {
		try {
			// Nếu chuỗi kết nối khác null
			if (connect != null) {
				// Thực hiện autoCommit
				connect.setAutoCommit(isCommit);
			}
		} catch (SQLException e) {
			System.out.println("BaseDaoImpl.setAutoCommit: " + e.getMessage());
			throw e;
		}
	}
}
