/**
 * Copyright(C) 2019 Luvina Software
 * TblUserLogicImpl.java, Dec 27, 2019, MDung
 */
package manageuser.logics.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblDetailUserJapan;
import manageuser.entities.TblUser;
import manageuser.entities.TblUserInfor;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * @author MDung
 *
 */
public class TblUserLogicImpl implements TblUserLogic {
	TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#checkExitAdmin(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean checkExitAdmin(String userName, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException, ClassNotFoundException {
		boolean check = false;
		try {
			// Khởi tại đối tượng TblUser
			TblUser tblUser = new TblUser();
			// gọi đến phương thức lấy đối tượng user
			tblUser = tblUserDaoImpl.getTblUserByLoginName(userName);
			// Nếu tìm thấy user
			if (tblUser != null) {
				check = Common.compareString(tblUser.getPassword(), Common.hashEncrypt(password, tblUser.getSalt()));
			}

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".TblUserLogicImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#checkLogin(java.lang.String)
	 */
	@Override
	public boolean checkLogin(String loginName) throws SQLException, ClassNotFoundException {
		boolean check = false;
		try {

			// Khởi tại đối tượng TblUser
			TblUser tblUser = new TblUser();
			// gọi đến phương thức lấy đối tượng user
			tblUser = tblUserDaoImpl.getTblUserByLoginName(loginName);
			// Nếu tìm thấy user
			if (tblUser != null) {
				check = true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".TblUserLogicImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getListUsers(int, int, int,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<TblUserInfor> getListUsers(int offset, int limit, int groupID, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws SQLException, ClassNotFoundException {
		List<TblUserInfor> listUser = new ArrayList<>();
		try {

			// validate fullName
			fullName = Common.validateWildCard(fullName);
			// Kiểm tra sortType
			sortType = Common.getColumeSort(sortType);
			// Trả về 1 list user
			listUser = tblUserDaoImpl.getListUsers(offset, limit, groupID, fullName, sortType, sortByFullName,
					sortByCodeLevel, sortByEndDate);
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".TblUserLogicImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		return listUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getTotalUser(int, java.lang.String)
	 */
	@Override
	public int getTotalUser(int groupId, String fullName) throws ClassNotFoundException, SQLException {
		int totalUser = 0;
		try {
			// validate giá trị fullName
			fullName = Common.validateWildCard(fullName);
			// trả về tổng số user
			totalUser = tblUserDaoImpl.getTotalUser(groupId, fullName);
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".TblUserLogicImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		return totalUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#checkLoginName(java.lang.String)
	 */
	@Override
	public boolean checkLoginName(String loginName) throws SQLException, ClassNotFoundException {

		boolean check = true;
		try {
			// lấy về giá trị LoginName
			String loginNameDb = tblUserDaoImpl.getLoginName(loginName);
			// Nếu loginName đã tồn tại trong DB trả về false
			if (loginNameDb != null && Constant.DEFAULT_STRING.equals(loginNameDb)) {
				check = false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".TblUserLogicImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#checkEmail(java.lang.String, int)
	 */
	@Override
	public boolean checkEmail(String email, int userId) throws SQLException, ClassNotFoundException {
		boolean check = false;
		try {
			// trả về true nếu có email tồn tai trong DB, trả về false nếu không
			// có
			String emailDb = tblUserDaoImpl.getEmail(email, userId);
			// Nếu tông tại email trong DB
			if (emailDb != null && !Constant.DEFAULT_STRING.equals(emailDb)) {
				check = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".TblUserLogicImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#createUser(manageuser.entities.
	 * TblUserInfor)
	 */
	@Override
	public void createUser(TblUserInfor tblUserInfor)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

		try {
			// Khởi tạo đối tượng tblDetailUserJapanDao để thao tác với bảng
			// tbl_detail_userJapan
			TblDetailUserJapanDao tblDetailUserJapanDao = new TblDetailUserJapanDaoImpl();
			// Khởi tạo đối tượng TblDetailUserJapanEntities
			TblDetailUserJapan tblDetail = new TblDetailUserJapan();
			// Mở chuỗi kết nối
			tblUserDaoImpl.openConnect();
			// set auto commit bằng false
			tblUserDaoImpl.setAutoCommit(false);
			// Tạo chuỗi kết nối
			Connection conn = tblUserDaoImpl.getConnect();
			// tạo tblUser từ thông tin có được của tblUserInfor
			TblUser tblUser = Common.createUser(tblUserInfor);
			// Thực hiện insert vào bảng tbl_user
			int userId = tblUserDaoImpl.insertUser(tblUser);
			// lấy codeLevel của user
			String codeLevel = tblUserInfor.getCodeLevel();
			// Trường hợp nếu chọn Code_level thì thực hiện
			if (Common.checkCodelevel(codeLevel)) {
				// Gắn chuỗi kết nối tblDetailUserJapanDao
				tblDetailUserJapanDao.setConnect(conn);
				// Set các giá trị cho tblDetail
				tblDetail = Common.createDetailUser(tblUserInfor);
				// set giá trị cho userId theo Id lấy được từ DB
				tblDetail.setUserId(userId);
				// Lấy các giá trị vừa gán
				// thêm vào bảng tblDetailUserJapanDao
				tblDetailUserJapanDao.insertDetailUserJapan(tblDetail);
			}
			// Thực hiện commit dữ liệu lên DB
			tblUserDaoImpl.commit();
			// Ngoại lệ xảy ra
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// Rollback lại dữ liệu đã insert vào bảng tbl_user
			tblUserDaoImpl.rollback();
			// Ném ngoại lệ
			throw e;
		} finally {
			// Đóng kết nối
			tblUserDaoImpl.closeConnect();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#checkExistedUserById(int)
	 */
	@Override

	public boolean checkExistedUserById(int userId) throws SQLException, ClassNotFoundException {
		// Khởi tạo biến check = false
		boolean check = false;
		try {
			// Khởi tạo đối tượng tblUser
			TblUser tblUser = new TblUser();
			// Lấy về đối tượng tblUser
			tblUser = tblUserDaoImpl.getUserByUserId(userId, Constant.RULE_USER);
			if (tblUser != null) {
				check = true;
			}

		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getUserInforById(int)
	 */
	@Override
	public TblUserInfor getUserInforById(int userId) throws SQLException, ClassNotFoundException {
		TblUserInfor tblUserInfor = new TblUserInfor();
		try {
			// lấy về 1 đối tượng TblUserInfor
			tblUserInfor = tblUserDaoImpl.getUserInforById(userId);
			// chuyển kiểu ngày tháng năm thành dd/mm/yyyy
			tblUserInfor.setBirthday(tblUserInfor.getBirthday().replace("-", "/"));
			String startDay = tblUserInfor.getStartDay();
			String endDate = tblUserInfor.getEndDate();
			if (startDay != null && endDate != null) {
				tblUserInfor.setStartDay(startDay.replace("-", "/"));
				tblUserInfor.setEndDate(endDate.replace("-", "/"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		// Trả về 1 đối tượng TblUserInfor
		return tblUserInfor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#updateUser(manageuser.entities.
	 * TblUserInfor, boolean)
	 */
	@Override
	public void updateUser(TblUserInfor userInfor, boolean checkDetail)
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
		try {
			// Khởi tạo đối tượng tblDetailUserJapanDao để thao tác với bảng
			// tbl_detail_userJapan
			TblDetailUserJapanDao tblDetailUserJapanDao = new TblDetailUserJapanDaoImpl();
			// Khởi tạo đối tượng TblDetailUserJapanEntities
			TblDetailUserJapan tblDetail = new TblDetailUserJapan();
			// Mở chuỗi kết nối
			tblUserDaoImpl.openConnect();
			// set auto commit bằng false
			tblUserDaoImpl.setAutoCommit(false);
			// Tạo chuỗi kết nối
			Connection connect = tblUserDaoImpl.getConnect();
			// tạo tblUser từ thông tin có được của tblUserInfor
			TblUser tblUser = Common.createUser(userInfor);
			// thực hiện update thông tin user
			tblUserDaoImpl.updatetUser(tblUser);
			int userId = userInfor.getUserId();
			// Gắn chuỗi kết nối cho tblDetailUserJapanDao
			tblDetailUserJapanDao.setConnect(connect);
			// lấy codeLevel của user
			String codeLevel = userInfor.getCodeLevel();
			// Nếu người dùng có chọn trình dộ JP
			if (Common.checkCodelevel(codeLevel)) {
				// Tạo đối tượng tblDetail từ userInfor
				tblDetail = Common.createDetailUser(userInfor);
				// set giá trị cho userId trong bảng Detail là userId được
				// update
				tblDetail.setUserId(userId);
				// Nếu user đã có codeLevel trong DB
				if (checkDetail) {
					// thực hiện update
					tblDetailUserJapanDao.updateDetailUserJapan(tblDetail);
					// nếu chưa thực hiện insert
				} else {
					// Thực hiện thêm vào bảng tbl_detail_user
					tblDetailUserJapanDao.insertDetailUserJapan(tblDetail);
				}
				// Nếu người dùng không chọn code level
			} else {
				// Nhưng trong Db lại có trình độ tiếng Nhật của user
				if (checkDetail) {
					// thực hiện xóa trình độ tiếng nhật của user
					tblDetailUserJapanDao.deleteDetailUserJapan(userId);
				}
			}
			// Thực hiện commit dữ liệu lên DB
			tblUserDaoImpl.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Rollback lại dữ liệu đã insert vào bảng tbl_user
			tblUserDaoImpl.rollback();
			// Ném ngoại lệ
			throw e;
		} finally {
			// Đóng kết nối
			tblUserDaoImpl.closeConnect();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#getTblUserByUserId(int)
	 */
	@Override
	public TblUser getTblUserByUserId(int userId) throws ClassNotFoundException, SQLException {
		// Khởi tạo đối tượng tblUser
		TblUser tblUser = new TblUser();
		try {
			// Lấy về tblUser với thông tin có rule và userId của user
			tblUser = tblUserDaoImpl.getTblUserByUserId(userId);
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		return tblUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.logics.TblUserLogic#deleteUser(int)
	 */
	@Override
	public void deleteUser(int userId) throws ClassNotFoundException, SQLException {
		// Khởi tạo đối tượng TblDetailUserJapanDao
		// Để gọi hàm xóa user có trình độ tiếng nhật
		TblDetailUserJapanDao tblDetailUserJapanDao = new TblDetailUserJapanDaoImpl();
		try {
			// Mở chuỗi kết nối
			tblDetailUserJapanDao.openConnect();
			// Khởi tạo phương thức setAutocommit bằng false
			tblDetailUserJapanDao.setAutoCommit(false);
			// Tạo chuỗi kết nối
			Connection connect = tblDetailUserJapanDao.getConnect();
			// Thực hiện xóa trong bảng tblDetailUserJapanDao
			tblDetailUserJapanDao.deleteDetailUserJapan(userId);
			// set chuỗi kết nối cho Tbl userDao
			tblUserDaoImpl.setConnect(connect);
			// Thực hiện xóa User trong bảng tbl_user
			tblUserDaoImpl.deleteUserById(userId);
			// Thực hiện commit dữ liệu lên DB
			tblDetailUserJapanDao.commit();

		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Trường hợp xảy ra ngoại lệ thì
			// Rollback lại dữ liệu đã xóa trong bảng tblDetailUserJapanDao
			tblDetailUserJapanDao.rollback();
			// Ném ngoại lệ
			throw e;
		} finally {
			// Đóng kết nối đến DB
			tblDetailUserJapanDao.closeConnect();
		}

	}
}