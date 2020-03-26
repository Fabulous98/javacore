/**
 * Copyright(C) 2019 Luvina Software
 * TblUserDao.java, Dec 27, 2019, MDung
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.TblUser;
import manageuser.entities.TblUserInfor;

/**
 * @author MDung
 *
 */
public interface TblUserDao extends BaseDao {
	/**
	 * Lấy về một user có login_name truyền vào
	 * 
	 * @param userName:
	 *            login_name của user
	 * @return user có login_name bằng tham số truyền vào
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	TblUser getTblUserByLoginName(String userName) throws SQLException, ClassNotFoundException;

	/**
	 * Lấy thông tin tất cả các user được sắp xếp theo điều kiện
	 * 
	 * @param offset
	 *            vị trí data cần lấy
	 * @param limit
	 *            số bản ghi lấy
	 * @param groupID
	 *            mã nhóm
	 * @param fullName
	 *            tên user
	 * @param sortType
	 *            trường sắp xếp
	 * @param sortByFullName:
	 *            Giá trị sắp xếp của cột Tên(ASC or DESC)
	 * @param sortByCodeLevel:
	 *            Giá trị sắp xếp của cột Trình độ tiếng nhật(ASC or DESC)
	 * @param sortByEndDate:
	 *            Giá trị sắp xếp của cột Ngày kết hạn(ASC or DESC)
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<TblUserInfor> getListUsers(int offset, int limit, int groupID, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws SQLException, ClassNotFoundException;

	/**
	 * Kiểm tra xem email đã tồn tại hay chưa
	 * 
	 * @param email
	 * @param userId
	 * @return email của user
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	String getEmail(String email, int userId) throws SQLException, ClassNotFoundException;

	/**
	 * Kiểm tra loginName đã tồn tại hay chưa
	 * 
	 * @param loginName
	 *            tên đăng nhập muốn kiểm tra tồn tại
	 * @return loginName của user
	 * @throws SQLException
	 *             lỗi thao tác với CSDL
	 * @throws ClassNotFoundException
	 *             lỗi không tìm thấy DRIVER
	 */
	String  getLoginName(String loginName) throws SQLException, ClassNotFoundException;

	/**
	 * Thêm mới một user vào DB
	 * 
	 * @param tblUser
	 *            tblUser Đối tượng chứa thông tin của user
	 * @return UserId vừa thêm mới
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	int insertUser(TblUser tblUser) throws SQLException, ClassNotFoundException;

	/**
	 * Hàm lấy về tổng số user
	 * 
	 * @param groupId
	 *            mã nhóm tìm kiếm
	 * @param fullName
	 *            tên nhóm tìm kiếm
	 * @return số bản ghi kết quả
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	int getTotalUser(int groupId, String fullName) throws SQLException, ClassNotFoundException;

	/**
	 * Lấy tblUser từ id và rule của user
	 * 
	 * @param userId
	 *            id của user
	 * @param rule
	 *            rule của user = 0 (admin) , =1 (user)
	 * @return một tblUser
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	TblUser getUserByUserId(int userId, int rule) throws ClassNotFoundException, SQLException;

	/**
	 * Lấy về 1 tblUserInfor
	 * 
	 * @param userId
	 *            id user cần lấy thông tin
	 * @return tblUserInfor
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	TblUserInfor getUserInforById(int userId) throws SQLException, ClassNotFoundException;

	/**
	 * Update user
	 * 
	 * @param tblUser
	 *            thông tin user cần update
	 * @return id của user đã update, 0 nếu không update thành công
	 * @throws SQLException
	 *             lỗi SQL
	 */
	void updatetUser(TblUser tblUser) throws SQLException;

	/**
	 * Lấy về tblUser của một user khi biết userId
	 * 
	 * @param userId
	 *            id của user muốn lấy rule
	 * @return một đối tượng tblUser
	 * @throws SQLException
	 *             lỗi SQL
	 * @throws ClassNotFoundException
	 *             lỗi load DRIVER
	 */
	TblUser getTblUserByUserId(int userId) throws SQLException, ClassNotFoundException;

	/**
	 * Xóa đi một user
	 * 
	 * @param userId
	 *            id của user muốn xóa
	 * @throws SQLException
	 *             lỗi SQL
	 * @throws ClassNotFoundException
	 *             lỗi load DRIVER
	 */

	void deleteUserById(int userId) throws SQLException, ClassNotFoundException;

	
}