/**
 * Copyright(C) 2019 Luvina Software
 * TblDetailUserJapanDaoImpl.java, Dec 27, 2019, MDung
 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.entities.TblDetailUserJapan;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * @author MDung
 *
 */
public class TblDetailUserJapanDaoImpl extends BaseDaoImpl implements TblDetailUserJapanDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.dao.TblDetailUserJapanDao#insertDetailUserJapan(manageuser.
	 * entities.TblDetailUserJapan)
	 */
	@Override
	public void insertDetailUserJapan(TblDetailUserJapan tblDetail) throws SQLException, ClassNotFoundException {
		try {
			// Khởi tạo đối tượng PreparedStatement
			PreparedStatement preparedStatement;
			StringBuilder sqlCommand = new StringBuilder();
			// Thực hiện lệnh Sql
			sqlCommand.append(
					"insert into tbl_detail_user_japan (start_date, end_date, total, user_id, code_level) value(?, ?, ?, ?, ?); ");
			// Khởi tạo biến inđex
			int index = 1;
			// Gọi đối tượng preparedStatement
			preparedStatement = getConnect().prepareStatement(sqlCommand.toString(), Statement.RETURN_GENERATED_KEYS);
			// Truyền tham số cho lệnh SQl
			preparedStatement.setString(index++, tblDetail.getStartDate());
			preparedStatement.setString(index++, tblDetail.getEndDate());
			preparedStatement.setInt(index++, Integer.parseInt(tblDetail.getTotal()));
			preparedStatement.setInt(index++, tblDetail.getUserId());
			preparedStatement.setString(index++, tblDetail.getCodeLevel());
			// Thực thi câu lệnh Sql
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".TblDetailUserJapanDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblDetailUserJapanDao#deleteDetailUserJapan(int)
	 */
	@Override
	public void deleteDetailUserJapan(int userId) throws SQLException {
		try {
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// Tạo đối tượng Stringbuider để cộng các chuỗi SQl
			StringBuilder sqlCommand = new StringBuilder();
			// thực hiện viết lệnh SQl
			sqlCommand.append("DELETE FROM tbl_detail_user_japan ");
			sqlCommand.append("WHERE user_id = ?; ");
			// Gọi đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			// Truyền tham số cho lệnh SQL
			preparedStatement.setInt(1, userId);
			// Thực thi câu lệnh SQl
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * manageuser.dao.TblDetailUserJapanDao#updateDetailUserJapan(manageuser.
	 * entities.TblDetailUserJapan)
	 */
	@Override
	public void updateDetailUserJapan(TblDetailUserJapan tblDetail) throws SQLException {
		try {
			// Khởi tạo đối tượng PreparedStatement
			PreparedStatement preparedStatement;
			// Tạo đối tượng Stringbuider để cộng các chuỗi SQl
			StringBuilder sqlCommand = new StringBuilder();
			// Cộng chuỗi tên trường cần insert
			sqlCommand.append("UPDATE tbl_detail_user_japan SET code_level = ?, ");
			sqlCommand.append(" start_date = ?, end_date = ?, total = ? ");
			sqlCommand.append(" WHERE user_id = ? ");
			// Khởi tạo biến index
			int index = 1;
			// truyền tham số cho câu lệnh SQL
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			preparedStatement.setString(index++, tblDetail.getCodeLevel());
			preparedStatement.setString(index++, tblDetail.getStartDate());
			preparedStatement.setString(index++, tblDetail.getEndDate());
			preparedStatement.setInt(index++, Common.convertStringToInt(tblDetail.getTotal(), Constant.NUMBER_DEFAULT));
			preparedStatement.setInt(index++, tblDetail.getUserId());
			// Thực thi câu lệnh update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblDetailUserJapanDao#getExitDetailUserJapan(int)
	 */
	@Override
	public int getExitDetailUserJapan(int userId) throws SQLException, ClassNotFoundException {
		int userIdDetail = 0;
		try {
			// Mở kết nối
			openConnect();
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// câu lệnh SQL select
			sqlCommand.append("SELECT user_id ");
			sqlCommand.append("FROM tbl_detail_user_japan ");
			sqlCommand.append("WHERE user_id = ?  ; ");
			// gọi đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			// Truyền tham số cho câu lệnh SQL
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// Lấy giá trị đầu tiên trong bảng vừa in
				userIdDetail = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		// Trả về check là false
		return userIdDetail;
	}

}
