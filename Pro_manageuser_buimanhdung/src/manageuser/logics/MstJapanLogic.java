/**
 * Copyright(C) 2019 Luvina Software
 * MstJapanLogic.java, Dec 27, 2019, MDung
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstJapan;

/**
 * @author MDung
 *
 */
public interface MstJapanLogic {
	/**
	 * Lấy danh sách trình độ tiếng nhật
	 * 
	 * @return danh sách trình độ tiếng Nhật
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	List<MstJapan> getAllMstJapan() throws SQLException, ClassNotFoundException;

	/**
	 * Lấy về nameLevel
	 * 
	 * @param codeLevel
	 *            code_level của trình độ tiếng Nhật
	 * @return đối tượng mstJapan
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	MstJapan getMstJapanByCodeLevel(String codeLevel) throws SQLException, ClassNotFoundException;

	/**
	 * Kiểm tra trình độ tiếng Nhật có tồn tại hay không
	 * 
	 * @param codeLevel
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	boolean checkMstJapanByGroup(String codeLevel) throws SQLException, ClassNotFoundException;
}
