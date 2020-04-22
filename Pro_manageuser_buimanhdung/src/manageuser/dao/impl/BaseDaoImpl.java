/**
 * Copyright(C) 2019 Luvina Software
 * BaseDaoImpl.java, Dec 27, 2019, MDung
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.BaseDao;
import manageuser.properties.DatabaseProperties;

/**
 * Description: Class để thiết lập các kết nối cơ bản đến Database
 * 
 * @author MDung
 *
 */
public abstract class BaseDaoImpl implements BaseDao {
	// Khai báo các biến để kết nối với DB
	protected Connection connect;
	String DRIVER = DatabaseProperties.getValueByKey("DRIVER");
	String URL = DatabaseProperties.getValueByKey("URL");
	String USER = DatabaseProperties.getValueByKey("USER");
	String PASS_DB = DatabaseProperties.getValueByKey("PASS");
	
	// Khởi tạo columSort chứa các cột của tblUserInfor
	private static List<String> columnSort = new ArrayList<>();

	/**
	 * Hàm trả về một trường cần sắp xếp nếu trường ấy đúng trong cơ sở dữ liệu
	 * nếu trường nhập vào không đúng với trường nào thuộc cơ sở dữ liệu thì trả
	 * về null
	 * 
	 * @param sortField : trường muốn sắp xếp
	 *            
	 * @return null hoặc sort
	 * @throws ClassNotFoundException
	 */
	public String getColumeSort(String sortField) throws SQLException, ClassNotFoundException {
		// Gán sort = sortField
		String sort = sortField;
		try {
			if (columnSort.size() == 0) {
				// tạo đối tượng preparedStatement
				PreparedStatement preparedStatement;
				// tạo đối tượng stringbuider
				StringBuilder sqlCommand = new StringBuilder();
				// gán câu lệnh SQL cho sqlCommand
				sqlCommand.append("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS ");
				sqlCommand.append("WHERE TABLE_SCHEMA = '21_buimanhdung_manageuser' ");
				sqlCommand.append("AND (TABLE_NAME = 'tbl_user' OR TABLE_NAME = 'tbl_detail_user_japan'); ");
				// Tạo đối tượng preparedStatement
				preparedStatement = connect.prepareStatement(sqlCommand.toString());
				
				// Thực thi câu lệnh SQL
				ResultSet resultSet = preparedStatement.executeQuery();
				// lấy tên các cột thêm vào columnSort
				while (resultSet.next()) {
					columnSort.add(resultSet.getString("COLUMN_NAME"));
				}	
			}
			
			// Kiểm tra field cần order by có nằm trong danh sách field cho phép
			// sort hay không nếu không gán = null
			if (!columnSort.contains(sort)) {
				sort = null;
			}
			
		} catch (SQLException | NullPointerException e) {
			// Ghi log và ném ngoại lệ
			System.err.println("Class:BaseDaoImpl , Function: getColumeSort, " + e.getMessage());
			throw e;
		}
		return sort;
	}

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
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Đóng kết nối với database
	 */
	@Override
	public void closeConnect() throws SQLException, NullPointerException {
		try {
			// Nếu connect khác null hoặc chưa đóng
			if (connect != null && !connect.isClosed()) {
				// đóng kết nối
				connect.close();
			}
		} catch (SQLException | NullPointerException e) {
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
