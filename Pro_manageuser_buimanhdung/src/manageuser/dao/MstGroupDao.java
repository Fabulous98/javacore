/**
 * Copyright(C) 2019 Luvina Software
 * MstGroupDao.java, Dec 27, 2019, MDung
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstGroup;


/**
 * @author MDung
 *
 */
public interface MstGroupDao {
	/**
	 * Hàm lất tất cả các nhóm
	 * 
	 * @return danh sách các nhóm trong DB
	 * @throws SQLException : ném ngoại lệ SQLException
	 *             
	 * @throws ClassNotFoundException : ném ngoại lệ ClassNotFoundException
	 *             
	 */
	List<MstGroup> getAllMstGroup() throws SQLException, ClassNotFoundException;

	/**
	 * Lấy về mstGroup khi biết ID
	 * 
	 * @param groupId : id nhóm
	 *            
	 * @return một đối tượng mstGroup
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	MstGroup getMstGroupById(int groupId) throws SQLException, ClassNotFoundException;

	/**
	 * Kiểm tra group có tồn tại hay không
	 * 
	 * @param groupId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	boolean checkGroup(int groupId) throws SQLException, ClassNotFoundException;
}
