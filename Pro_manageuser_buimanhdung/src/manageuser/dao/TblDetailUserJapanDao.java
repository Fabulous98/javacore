/**
 * Copyright(C) 2019 Luvina Software
 * TblDetailUserJapanDao.java, Dec 27, 2019, MDung
 */
package manageuser.dao;

import java.sql.SQLException;

import manageuser.entities.TblDetailUserJapan;
/**
 * @author MDung
 *
 */
public interface TblDetailUserJapanDao extends BaseDao{
	/**
	 * Thêm một đối tượng tblDetail vào DB
	 * 
	 * @param tblDetail
	 *            một đối tượng chứa các thuộc tính của tblDetail
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	void insertDetailUserJapan(TblDetailUserJapan tblDetail) throws SQLException, ClassNotFoundException;

	/**
	 * Xóa trình độ tiếng Nhật của 1 user
	 * 
	 * @param userId
	 *            id của user muốn xóa
	 * @throws SQLException
	 *             lỗi SQL
	 */
	void deleteDetailUserJapan(int userId) throws SQLException;

	/**
	 * Update thông tin trình độ tiếng Nhật của user
	 * 
	 * @param tblDetail
	 *            thông tin về trình đột tiếng Nhật của user cần update
	 * @throws SQLException
	 *             lỗi SQL
	 */

	void updateDetailUserJapan(TblDetailUserJapan tblDetail) throws SQLException;

	/**
	 * lấy về userId trong bảng detail để kiểm tra user đó đã có trình đột tiếng
	 * Nhật hay chưa
	 * 
	 * @param userId
	 *            id của user muốn kiểm tra
	 * @return idUser nếu có trong bảng trình đọ tiếng Nhật, trả về 0 nếu user
	 *         chưa có trình đột tiêng Nhật
	 * @throws SQLException
	 *             lỗi SQL
	 * @throws ClassNotFoundException
	 *             Lỗi kết nối DRIVER
	 */

	int getExitDetailUserJapan(int userId) throws SQLException, ClassNotFoundException;
}
