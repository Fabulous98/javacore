/**
 * Copyright(C) 2019 Luvina Software
 * MstGroupLogicImpl.java, Dec 27, 2019, MDung
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstGroupDao;
import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.entities.MstGroup;
import manageuser.logics.MstGroupLogic;

/**
 * @author MDung
 *
 */
public class MstGroupLogicImpl implements MstGroupLogic {
	
	/* (non-Javadoc)
	 * @see manageuser.logics.MstGroupLogic#getAllMstGroup()
	 */
	@Override
	public List<MstGroup> getAllMstGroup() throws SQLException, ClassNotFoundException {
		List<MstGroup> listGroup = new ArrayList<>();
		try {
			// Khởi tạo đối tượng mstGroupImpl
			MstGroupDaoImpl mstGroupImpl = new MstGroupDaoImpl();
			// lấy về 1 list mstGroupImpl
			listGroup = mstGroupImpl.getAllMstGroup();
		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstGroupLogicImpl " + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		return listGroup;
	}

	
	/* (non-Javadoc)
	 * @see manageuser.logics.MstGroupLogic#getMstGroupById(int)
	 */
	@Override
	public MstGroup getMstGroupById(int groupId) throws SQLException, ClassNotFoundException {
		MstGroup mstGroup = new MstGroup();
		try {
			// Khởi tạo đối tượng mstGroupDaoImpl
			MstGroupDao mstGroupDao = new MstGroupDaoImpl();
			// lấy về true nếu group tồn tại, false nếu không tồn tại
			mstGroup = mstGroupDao.getMstGroupById(groupId);
		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstGroupLogicImpl " + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		return mstGroup;
	}

	/* (non-Javadoc)
	 * @see manageuser.logics.MstGroupLogic#checkGroup(int)
	 */
	@Override
	public boolean checkGroup(int groupId) throws SQLException, ClassNotFoundException {
		boolean check = false;
		try {
			// Khởi tạo đối tượng MstGroupDao
			MstGroupDao mstGroupDao = new MstGroupDaoImpl();
			// Kiểm tra group đã tồn tại hay chưa
			check = mstGroupDao.checkGroup(groupId);
		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstGroupLogicImpl " + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		// trả về true nếu đã tồn tại false nếu chưa
		return check;
	}

}
