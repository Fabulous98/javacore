/**
 * Copyright(C) 2019 Luvina Software
 * MstJapanLogicImpl.java, Dec 27, 2019, MDung
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstJapanDao;
import manageuser.dao.impl.MstJapanDaoImpl;
import manageuser.entities.MstJapan;
import manageuser.logics.MstJapanLogic;

/**
 * @author MDung
 *
 */
public class MstJapanLogicImpl implements MstJapanLogic {
	
	/* (non-Javadoc)
	 * @see manageuser.logics.MstJapanLogic#getAllMstJapan()
	 */
	@Override
	public List<MstJapan> getAllMstJapan() throws SQLException, ClassNotFoundException {
		List<MstJapan> listMstJapan = new ArrayList<>();
		try {
			// Khởi tạo đối tượng mstGroupImpl
			MstJapanDao mstJapanImpl = new MstJapanDaoImpl();
			// Trả về 1 list mstGroupImpl
			listMstJapan = mstJapanImpl.getAllMstJapan();

		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstJapanLogicImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		return listMstJapan;
	}

	
	/* (non-Javadoc)
	 * @see manageuser.logics.MstJapanLogic#getMstJapanByCodeLevel(java.lang.String)
	 */
	@Override
	public MstJapan getMstJapanByCodeLevel(String codeLevel) throws SQLException, ClassNotFoundException {
		MstJapan mstJapan = new MstJapan();
		try {
			// Khởi tạo đối tượng MstJapanDapImpl
			MstJapanDao mst = new MstJapanDaoImpl();
			// Trả về một đối tượng MstJapan
			mstJapan = mst.getMstJapanById(codeLevel);
		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstJapanLogicImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		return mstJapan;
	}

	
	/* (non-Javadoc)
	 * @see manageuser.logics.MstJapanLogic#checkMstJapanByGroup(java.lang.String)
	 */
	@Override
	public boolean checkMstJapanByGroup(String codeLevel) throws SQLException, ClassNotFoundException {
		boolean check = false;
		try {
			// Khởi tạo đối tượng MstJapanDapImpl
			MstJapanDao mstJapanDao = new MstJapanDaoImpl();
			// trả về true nếu tồn tại trình độ JP trả về false nếu không tồn
			// tại
			check = mstJapanDao.checkMstJapanByGroup(codeLevel);
		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".MstJapanLogicImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		return check;
	}
}

