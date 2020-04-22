/**
 * Copyright(C) 2019 Luvina Software
 * TblDetailUserJapanLogic.java, Dec 27, 2019, MDung
 */
package manageuser.logics;

import java.sql.SQLException;

/**
 * @author MDung
 *
 */
public interface TblDetailUserJapanLogic {
	/**
	 * Kiểm tra xem user đã có trình độ tiếng Nhật hay chưa
	 * 
	 * @param userId id của user cần kiểm tra
	 * @return true nếu có trình độ tiếng Nhật, false nếu không
	 * @throws ClassNotFoundException lỗi load DRIVER
	 * @throws SQLException lỗi SQL
	 */
	boolean checkExistDetailUserJapan(int userId) throws ClassNotFoundException, SQLException;
}
