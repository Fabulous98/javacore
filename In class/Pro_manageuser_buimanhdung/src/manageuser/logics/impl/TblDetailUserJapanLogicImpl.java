/**
 * Copyright(C) 2019 Luvina Software
 * TblDetailUserJapanLogicImpl.java, Dec 27, 2019, MDung
 */
package manageuser.logics.impl;

import java.sql.SQLException;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.logics.TblDetailUserJapanLogic;
import manageuser.utils.Constant;

/**
 * @author MDung
 *
 */
public class TblDetailUserJapanLogicImpl implements TblDetailUserJapanLogic {
	
	/* (non-Javadoc)
	 * @see manageuser.logics.TblDetailUserJapanLogic#checkExitDetailUserJapan(int)
	 */
	@Override
	public boolean checkExitDetailUserJapan(int userId) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			// Khởi tạo đối tượng TblDetailUserJapanDao
			TblDetailUserJapanDao tblDetailUserJapanDao = new TblDetailUserJapanDaoImpl();
			// Check bằng true
			int userIdDetail = tblDetailUserJapanDao.getExitDetailUserJapan(userId);
			// Nếu tồn tại userId đã có trình độ tiếng Nhật
			if (userIdDetail != Constant.NUMBER_DEFAULT) {
				check = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		// Trả về check là false
		return check;
	}

}
