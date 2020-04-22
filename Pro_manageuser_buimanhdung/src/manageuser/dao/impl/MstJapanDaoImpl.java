/**
 * Copyright(C) 2019 Luvina Software
 * MstJapanDaoImpl.java, Dec 27, 2019, MDung
 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstJapanDao;
import manageuser.entities.MstJapan;

/**
 * Description: Class chứa các phương thức làm việc với bảng MstJapan
 * 
 * @author MDung
 *
 */
public class MstJapanDaoImpl extends BaseDaoImpl implements MstJapanDao {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getEmail(java.lang.String, int)
	 */
	@Override
	public String getCodelevel(String codelevel) throws SQLException{
		String codeLevel = "";
		try {
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// câu lệnh SQL select
			sqlCommand.append("SELECT code_level ");
			sqlCommand.append("FROM mst_japan ");
			sqlCommand.append("WHERE code_level = ?  ");
			
			int index = 1;
			// Tạo đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			preparedStatement.setString(index++, codelevel);
			
			// Thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			// Lấy về giá trị email
			if (resultSet.next()) {
				// Set giá trị vào tblUser
				codeLevel = resultSet.getString("code_level");
			} else {
				throw new SQLException("Không có codelevel trong Database!");
			}
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".tblUserDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		return codeLevel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.MstJapanDao#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() throws SQLException, ClassNotFoundException {
		// Tạo 1 listMst để chứa trình độ tiếng Nhật lấy được từ DB
		List<MstJapan> listMst = new ArrayList<MstJapan>();
		try {
			openConnect();
			// Tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// Thực hiện viết Lệnh SQl để lấy tất cả các nhóm
			sqlCommand.append("SELECT code_level , name_level ");
			sqlCommand.append("FROM mst_japan ");
			sqlCommand.append("ORDER BY name_level ASC ");
			// Gọi đối tượng prepareStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			// thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			MstJapan mstJapan;
			while (resultSet.next()) {
				// tạo đối tượng mstJapan
				mstJapan = new MstJapan();
				// Lấy giá trị id của mstJapan
				mstJapan.setCodeLevel(resultSet.getString("code_level"));
				// Lấy giá trị tên của mstJapan
				mstJapan.setNameLevel(resultSet.getString("name_level"));
				// Thêm vào list listMst các giá trị vừa lấy được
				listMst.add(mstJapan);
			}

		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstJapanDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối
			closeConnect();
		}
		// Trả về 1 listMst chứa các group lấy được trong DB
		return listMst;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.MstJapanDao#getMstJapanById(java.lang.String)
	 */
	@Override
	public MstJapan getMstJapanById(String codeLevel) throws SQLException, ClassNotFoundException {
		MstJapan mstJapan = new MstJapan();
		try {
			// Mở kết nối
			openConnect();
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// câu lệnh SQL select
			sqlCommand.append("SELECT name_level ");
			sqlCommand.append("FROM mst_japan ");
			sqlCommand.append("WHERE code_level = ? ; ");
			// Tạo đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			preparedStatement.setString(1, codeLevel);
			// Thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			// Kiểm tra kết quả sau khi thực thi
			if (resultSet.next()) {
				// Set giá trị vào tblUser
				mstJapan.setNameLevel(resultSet.getString("name_level"));
			}
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstJapanDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối đến DB
			closeConnect();
		}
		return mstJapan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.MstJapanDao#checkMstJapanByGroup(java.lang.String)
	 */
	@Override
	public boolean checkMstJapanByGroup(String codeLevel) throws SQLException, ClassNotFoundException {
		boolean check = false;
		try {
			openConnect();
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// câu lệnh SQL select
			sqlCommand.append("SELECT code_level , name_level ");
			sqlCommand.append("FROM mst_japan ");
			sqlCommand.append("WHERE code_level = ?  ; ");
			// Tạo đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			preparedStatement.setString(1, codeLevel);
			// Thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// Gán lại biến check
				check = true;
			}
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstJapanDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối đến DB
			closeConnect();
		}
		return check;
	}

}
