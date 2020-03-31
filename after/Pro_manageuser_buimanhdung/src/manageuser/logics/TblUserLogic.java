/**
 * Copyright(C) 2019 Luvina Software
 * TblUserLogic.java, Dec 27, 2019, MDung
 */
package manageuser.logics;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import manageuser.entities.TblUser;
import manageuser.entities.TblUserInfor;

/**
 * @author MDung
 *
 */
public interface TblUserLogic {
	/**
	 * Kiểm tra tên đăng nhập và mật khẩu
	 * 
	 * @param username
	 *            tên đăng nhập
	 * @param password
	 *            mật khẩu
	 * @return true nếu đúng tên đăng nhập và mật khẩu false nếu không
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws ClassNotFoundException
	 */
	public boolean checkExitAdmin(String username, String password)
			throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException, ClassNotFoundException;

	/**
	 * Lấy thông tin user
	 * 
	 * @param offset
	 *            vị trí bản ghi cần lấy
	 * @param limit
	 *            số bản ghi hiển thị
	 * @param groupID
	 *            mã nhóm
	 * @param fullName
	 *            tên user
	 * @param sortType
	 *            trường ưu tiên sắp xếp
	 * @param sortByFullName
	 *            giá trị sắp xếp theo trường fullName
	 * @param sortByCodeLevel
	 *            giá trị sắp xếp theo trường codeLevel
	 * @param sortByEndDate
	 *            giá trị sắp xếp theo trường EndDate
	 * @return danh sách user
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<TblUserInfor> getListUsers(int offset, int limit, int groupID, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws SQLException, ClassNotFoundException;

	/**
	 * Lấy tổng số user
	 * 
	 * @param groupID
	 *            groupId của user
	 * @param fullName
	 *            fullName user
	 * @return số user thỏa mãn groupId và fullName
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public int getTotalUser(int groupID, String fullName) throws ClassNotFoundException, SQLException;

	/**
	 * Phương thức kiểm tra tồn tại email trong DB
	 * 
	 * @param email
	 *            điều kiện kiểm tra
	 * @return true: Đã tồn tại trong DB, False: chưa tồn tại
	 * @throws SQLException
	 *             ngoại lệ sai lệnh Sql
	 * @throws ClassNotFoundException
	 *             ngoại lệ không tìm thấy file
	 */
	boolean checkEmail(String email, int userId) throws SQLException, ClassNotFoundException;

	/**
	 * Kiểm tra loginName đã tồn tại trong DB chưa
	 * 
	 * @param loginName
	 *            loginName cần kiểm tra
	 * @return true nếu đã tồn tại, false nếu chưa
	 * @throws SQLException
	 *             lỗi SQL
	 * @throws ClassNotFoundException
	 *             lỗi không kết nối với DRIVER
	 */
	boolean checkLoginName(String loginName) throws SQLException, ClassNotFoundException;

	/**
	 * Kiểm tra việc insert user thành công hay không
	 * 
	 * @param userInfor
	 *            user cần insert
	 * @return true nếu insert thành công, false nếu không
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void createUser(TblUserInfor userInfor)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException;

	/**
	 * Kiểm tra xem đối tượng đang thao tác có phải là admin hay không
	 * 
	 * @param loginName
	 *            tên đăng nhập ở màn hình login
	 * @return true nếu đúng admin trong DB, false nếu không
	 * @throws SQLException
	 *             lỗi SQl
	 * @throws ClassNotFoundException
	 *             lỗi không tìm thấy DRIVER
	 */
	boolean checkLogin(String loginName) throws SQLException, ClassNotFoundException;

	/**
	 * Lấy về 1 userInfor
	 * 
	 * @param userId
	 *            userId của User muốn lấy về
	 * @return một đối tượng userInfor
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TblUserInfor getUserInforById(int userId) throws SQLException, ClassNotFoundException;

	/**
	 * Kiểm tra xem userid có tồn tại trong DB hay ko true: có; false: không
	 * 
	 * @param userId
	 *            id của user muốn kiểm tra
	 * @return true nếu id có tồn tại false ngược lại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean checkExistedUserById(int userId) throws SQLException, ClassNotFoundException;

	/**
	 * hàm thực hiện edit User
	 * 
	 * @param userInfor
	 *            userInfor muốn thay đổi
	 * @return true nếu update thành công false nếu không
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public void updateUser(TblUserInfor userInfor, boolean check)
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException;

	/**
	 * Hàm lấy về đối tượng tbluser
	 * 
	 * @param userId
	 *            id của user muốn lấy rule
	 * @return một đối tượng tblUser
	 * @throws ClassNotFoundException
	 *             Lỗi load DRIVER
	 * @throws SQLException
	 *             lỗi SQL
	 */
	TblUser getTblUserByUserId(int userId) throws ClassNotFoundException, SQLException;

	/**
	 * Hàm xóa một user
	 * 
	 * @param userId
	 *            id của user cần xóa
	 * @return true nếu xóa thành công, false nếu không
	 * @throws ClassNotFoundException
	 *             lỗi load DRIVER
	 * @throws SQLException
	 *             lỗi SQl
	 */
	public void deleteUser(int userId) throws ClassNotFoundException, SQLException;
}
