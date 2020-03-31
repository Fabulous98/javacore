/**
 * Copyright(C) 2019 Luvina Software
 * MstGroupDaoImpl.java, Dec 27, 2019, MDung
 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstGroupDao;
import manageuser.entities.MstGroup;

/**
 * @author MDung
 *
 */
public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.MstGroupDao#getAllMstGroup()
	 */
	@Override
	public List<MstGroup> getAllMstGroup() throws SQLException, ClassNotFoundException {
		// Tạo 1 listMst để chứa các nhóm lấy được từ DB
		List<MstGroup> listMst = new ArrayList<MstGroup>();
		try {
			// Mở kết nối
			openConnect();
			// Tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// Thực hiện viết Lệnh SQl để lấy tất cả các nhóm
			sqlCommand.append("SELECT group_id , group_name ");
			sqlCommand.append("FROM mst_group ");
			sqlCommand.append("ORDER BY group_id ASC ");
			// Gọi đối tượng prepareStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			// thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			MstGroup mstGroup;
			while (resultSet.next()) {
				// tạo đối tượng MstGruoup
				mstGroup = new MstGroup();
				// Lấy giá trị id của group
				mstGroup.setGroupId(resultSet.getInt("group_id"));
				// Lấy giá trị tên của group
				mstGroup.setGroupName(resultSet.getString("group_name"));
				// Thêm vào list listMst các giá trị vừa lấy được
				listMst.add(mstGroup);
			}
			//
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstGroupDaoImpl" + ", Method: "
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
	 * @see manageuser.dao.MstGroupDao#getMstGroupById(int)
	 */
	@Override
	public MstGroup getMstGroupById(int groupId) throws SQLException, ClassNotFoundException {
		MstGroup mstGroup = new MstGroup();
		try {
			// Mở kết nối
			openConnect();
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// câu lệnh SQL select
			sqlCommand.append("SELECT group_name ");
			sqlCommand.append("FROM mst_group ");
			sqlCommand.append("WHERE group_id = ?  ; ");
			// Tạo đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			preparedStatement.setInt(1, groupId);
			// Thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			// Kiểm tra kết quả sau khi thực thi
			if (resultSet.next()) {
				// Set giá trị vào tblUser
				mstGroup.setGroupName(resultSet.getString("group_name"));
			}
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstGroupDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối đến DB
			closeConnect();
		}
		return mstGroup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.MstGroupDao#checkGroup(int)
	 */
	@Override
	public boolean checkGroup(int groupId) throws SQLException, ClassNotFoundException {
		boolean check = false;
		try {
			// Mở kết nối
			openConnect();
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// câu lệnh SQL select
			sqlCommand.append("SELECT group_name ");
			sqlCommand.append("FROM mst_group ");
			sqlCommand.append("WHERE group_id = ? ; ");
			// Tạo đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			// Truyền tham số cho lệnh SQl
			preparedStatement.setInt(1, groupId);
			// Thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			// Kiểm tra kết quả sau khi thực thi
			if (resultSet.next()) {
				check = true;
			}
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstGroupDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối đến DB
			closeConnect();
		}
		// Trả về true, false
		return check;
	}

}
