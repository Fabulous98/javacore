/**
 * Copyright(C) 2019 Luvina Software
 * MstJapanDao.java, Dec 27, 2019, MDung
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstJapan;

/**
 * @author MDung
 *
 */
public interface MstJapanDao {
	/**
	 * Lấy danh sách trình độ tiếng Nhật
	 * 
	 * @return danh sách trình độ tiếng Nhật
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<MstJapan> getAllMstJapan() throws SQLException, ClassNotFoundException;

	/**
	 * Lấy mstJapan khi biết code_level
	 * 
	 * @param codeLevel
	 *            mã trình độ tiếng Nhật
	 * @return đối tượng mstJapan
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	MstJapan getMstJapanById(String codeLevel) throws SQLException, ClassNotFoundException;

	/**
	 * Kiểm tra có tồn tại trình độ tiếng Nhật
	 * 
	 * @param codeLevel
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	boolean checkMstJapanByGroup(String codeLevel) throws SQLException, ClassNotFoundException;
}
