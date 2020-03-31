/**
 * Copyright(C) 2019 Luvina Software
 * MstGroupLogic.java, Dec 27, 2019, MDung
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstGroup;

/**
 * @author MDung
 *
 */
public interface MstGroupLogic {
	/**
	 * Lấy danh sách group
	 * 
	 * @return list các group
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<MstGroup> getAllMstGroup() throws SQLException, ClassNotFoundException;

	/**
	 * Lấy tên group khi biết groupID
	 * 
	 * @param groupId
	 *            id của group
	 * @return mstGroup
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	MstGroup getMstGroupById(int groupId) throws SQLException, ClassNotFoundException;

	/**
	 * Kiểm tra group đã tồn tại hay chưa
	 * 
	 * @param groupId
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	boolean checkGroup(int groupId) throws SQLException, ClassNotFoundException;
}
