/**
 * Copyright(C) 2019 Luvina Software
 * TblUserDaoImpl.java, Dec 27, 2019, MDung
 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.TblUserDao;
import manageuser.entities.TblUser;
import manageuser.entities.TblUserInfor;
import manageuser.utils.Constant;

/**
 * Description: Class chứa các phương thức làm việc với bảng TblUser
 * 
 * @author MDung
 *
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {

	/* (non-Javadoc)
	 * @see manageuser.dao.TblUserDao#getTblUserByLoginName(java.lang.String)
	 */
	@Override
	public TblUser getTblUserByLoginName(String userName) throws SQLException, ClassNotFoundException {
		TblUser tblUser = null;
		try {
			// Mở kết nối
			openConnect();
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// gán câu lệnh SQL cho sqlCommand
			sqlCommand.append("SELECT  login_name, password as pass, salt ");
			sqlCommand.append("FROM tbl_user ");
			sqlCommand.append("WHERE rule = ? AND login_name = ?  ; ");
			// Tạo đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			// Tạo và gán index = 1
			int index = 1;
			
			// Set giá trị cho preparedStatement
			preparedStatement.setInt(index++, Constant.RULE_ADMIN);
			preparedStatement.setString(index++, userName);
			// Thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			// Kiểm tra kết quả sau khi thực thi
			if (resultSet.next()) {
				tblUser = new TblUser();
				// Set giá trị vào tblUser
				tblUser.setLoginName(resultSet.getString("login_name"));
				tblUser.setPassword(resultSet.getString("pass"));
				tblUser.setSalt(resultSet.getString("salt"));
			}
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".tblUserDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối
			closeConnect();
		}
		return tblUser;
	}

	
	/* (non-Javadoc)
	 * @see manageuser.dao.TblUserDao#getListUsers(int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<TblUserInfor> getListUsers(int offset, int limit, int groupID, String fullName, String sort_type,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate, int yearBirth, int monthBirth, int dateBirth)
			throws SQLException, ClassNotFoundException {
		// Khởi tạo 1 list đối tượng UserInfor
		List<TblUserInfor> listUserInfor = new ArrayList<TblUserInfor>();
		try {
			// Khởi tạo đối tượng preparedStatement để thực hiện truy vấn tham
			// số
			PreparedStatement preparedStatement;
			// Mở kết nối đến DB
			openConnect();
			// Kiểm tra sort_type
			sort_type = getColumeSort(sort_type);
			// Tạo ra đối tượng Stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// Thực hiện câu lệnh SQl
			sqlCommand.append("SELECT u.user_id, u.full_name, u.birthday, g.group_name, u.email, ");
			sqlCommand.append("u.tel, j.name_level, d.end_date, d.total ");
			sqlCommand.append("FROM tbl_user u ");
			sqlCommand.append("INNER JOIN mst_group g ");
			sqlCommand.append("ON u.group_id = g.group_id ");
			sqlCommand.append("LEFT JOIN (tbl_detail_user_japan d ");
			sqlCommand.append("INNER JOIN mst_japan j ");
			sqlCommand.append("ON d.code_level = j.code_level) ");
			sqlCommand.append("ON u.user_id = d.user_id ");
			sqlCommand.append("WHERE u.rule = ? ");
			// trường hợp groupID khác mặc định
			if (Constant.DEFAULT_GROUP_ID != groupID) {
				// Thêm câu lệnh SQl
				sqlCommand.append("AND g.group_id = ? ");
			}
			// trường hợp fullName khác mặc định
			if (!Constant.DEFAULT_STRING.equals(fullName)) {
				// Thêm câu lệnh
				sqlCommand.append("AND u.full_name  LIKE ? ESCAPE '!' ");
			}
			
				sqlCommand.append(" AND (YEAR(u.birthday)  = ? OR " + yearBirth + " <= 0 ) ");
			
			
				sqlCommand.append(" AND (MONTH(u.birthday)  = ? OR " + monthBirth + " <= 0 ) ");
			
			
				sqlCommand.append(" AND (DAY(u.birthday)  = ? OR " + dateBirth + " <= 0 ) ");
			
			if (sort_type != null) {
				// Lệnh order By
				sqlCommand.append("ORDER BY ");
				// Trường hợp sort_type là full_name
				if (sort_type.equals(Constant.TYPE_FULL_NAME)) {
					// Thêm các lệnh SQl ưu tiên theo thứ tự sắp xếp
					sqlCommand.append("u.full_name " + sortByFullName + ", ");
					sqlCommand.append("j.code_level " + sortByCodeLevel + ", ");
					sqlCommand.append("d.end_date " + sortByEndDate + " ");
					// Trường hợp sort_type là code_level
				} else if (sort_type.equals(Constant.TYPE_CODE_LEVEL)) {
					// Thêm các lệnh SQl ưu tiên theo thứ tự sắp xếp
					sqlCommand.append("j.code_level " + sortByCodeLevel + ", ");
					sqlCommand.append("u.full_name " + sortByFullName + ", ");
					sqlCommand.append("d.end_date " + sortByEndDate + " ");
					// Trường hợp sort_type là end_date
				} else if (sort_type.equals(Constant.TYPE_END_DATE)) {
					// Thêm các lệnh SQl ưu tiên theo thứ tự sắp xếp
					sqlCommand.append("d.end_date " + sortByEndDate + ", ");
					sqlCommand.append("u.full_name " + sortByFullName + ", ");
					sqlCommand.append("j.code_level " + sortByCodeLevel + " ");
					
				}
			}
			sqlCommand.append("LIMIT ? OFFSET ?;");
			// Thêm câu truy vấn vào prepareStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			// tạo biến index
			int index = 1;
			// set giá trị cho Rule
			preparedStatement.setInt(index++, Constant.RULE_USER);
			// nếu groupID khác mặc định
			if (Constant.DEFAULT_GROUP_ID != groupID) {
				// set giá trị cho groupID
				preparedStatement.setInt(index++, groupID);
			}
			// Nếu full khác giá trị mặc định là rỗng
			if (!Constant.DEFAULT_STRING.equals(fullName)) {
				// Set giá trị cho fullname
				preparedStatement.setString(index++, "%" + fullName + "%");
			}
			
			// Set giá trị cho birthday
			preparedStatement.setInt(index++, yearBirth);
			preparedStatement.setInt(index++, monthBirth);
			preparedStatement.setInt(index++, dateBirth);
			
			// set giá trị cho limit
			preparedStatement.setInt(index++, limit);
			// set giá trị cho offset
			preparedStatement.setInt(index++, offset);
			// thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// Tạo đối tượng TblUserInfor
				TblUserInfor userInfor = new TblUserInfor();
				// truyền dữ liệu lấy được từ DB vào userInfor
				userInfor.setUserId(resultSet.getInt("user_id"));
				userInfor.setFullName(resultSet.getString("full_name"));
				userInfor.setBirthday(resultSet.getString("birthday"));
				userInfor.setGroupName(resultSet.getString("group_name"));
				userInfor.setEmail(resultSet.getString("email"));
				userInfor.setTel(resultSet.getString("tel"));
				userInfor.setNameLevel(resultSet.getString("name_level"));
				userInfor.setEndDate(resultSet.getString("end_date"));
				userInfor.setTotal(resultSet.getString("total"));
				// Thêm vào list listUserInfor
				//System.out.println(sqlCommand);
				listUserInfor.add(userInfor);
			}

		} catch (SQLException e) {
			// ghi log và ném lỗi
			System.out.println("Class: " + this.getClass().getName() + ".tblUserDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối
			closeConnect();
		} // Trả về 1 list danh sách user
		return listUserInfor;
	}

	
	/* (non-Javadoc)
	 * @see manageuser.dao.TblUserDao#getTotalUser(int, java.lang.String)
	 */
	@Override
	public int getTotalUser(int groupId, String fullName, int year, int month, int date) throws SQLException, ClassNotFoundException {
		// Khởi tạo biến totalUser
		int totalUser = 0;
		try {
			// Mở kết nối đến DB
			openConnect();
			// Khởi tạo đối tượng String buider
			StringBuilder sql = new StringBuilder();
			// thực hiện câu lệnh SQL để lấy tất cả user thỏa mãn điều kiện
			sql.append("SELECT COUNT(u.user_id) AS TOTAL");
			sql.append(" FROM (tbl_user as u INNER JOIN mst_group as g");
			sql.append(" ON u.group_id = g.group_id)");
			sql.append(" WHERE u.rule = ?");
			// Nêu group > 0 thì thực hiện
			if (groupId > 0) {
				// Cộng thêm điều kiện vào chuỗi
				sql.append(" AND g.group_id = ?");
			}
			// Nêu fullName khác rỗng
			if (!Constant.DEFAULT_STRING.equals(fullName)) {
				// Công thêm điều kiện vào chuỗi
				sql.append(" AND u.full_name LIKE ? ESCAPE '!' ");
			}
			
			sql.append(" AND (YEAR(u.birthday)  = ? OR " + year + " <= 0 )");
			
			
			sql.append(" AND (MONTH(u.birthday)  = ? OR " + month + " <= 0 )");
		
		
			sql.append(" AND (DAY(u.birthday)  = ? OR " + date + " <= 0 )");
			
			// Tham số hóa câu kệnh truy vấn
			PreparedStatement ps = connect.prepareStatement(sql.toString());
			// Khởi tạo biến index
			int index = 1;
			// Gán tham số cho user
			ps.setInt(index++, Constant.RULE_USER);
			// Nêu group > 0.
			if (groupId > 0) {
				// Gán tham số cho groupId
				ps.setInt(index++, groupId);
			}
			// Nếu fullName khác rỗng thì gán tham số cho fullname .
			if (!fullName.equals(Constant.DEFAULT_STRING)) {
				ps.setString(index++, "%" + fullName + "%");
			}
			
			// Set giá trị cho birthday
			ps.setInt(index++, year);
			ps.setInt(index++, month);
			ps.setInt(index++, date);
			
			// Thực thi câu truy vấn
			ResultSet rs = ps.executeQuery();
			// Lấy dữ kiệu trong bảng ghi
			if (rs.next()) {
				// gán giá trị cho totalUser
				totalUser = rs.getInt("TOTAL");
			}

		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".tblUserDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;

		} finally {
			// Đóng kết nối đến DB
			closeConnect();
		}
		// Trả về tổng số totalUser
		return totalUser;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getEmail(java.lang.String)
	 */
	@Override
	public String getEmail(String email) throws SQLException, ClassNotFoundException {
		String emailDb = "";
		try {
			// Mở kết nối
			openConnect();
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// câu lệnh SQL select
			sqlCommand.append("SELECT email ");
			sqlCommand.append("FROM tbl_user ");
			sqlCommand.append("WHERE email = ?  ");
			
			int index = 1;
			// Tạo đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			preparedStatement.setString(index++, email);
			
			// Thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			// Lấy về giá trị email
			if (resultSet.next()) {
				// Set giá trị vào tblUser
				emailDb = resultSet.getString("email");
			}
		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".tblUserDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		} finally {
			// Đóng kết nối đến DB
			closeConnect();
		}
		return emailDb;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getEmail(java.lang.String, int)
	 */
	@Override
	public boolean checkEmailByUserId(String email, int userId) throws SQLException, ClassNotFoundException {
		boolean check = false;
		String emailDb = "";
		try {
			// Mở kết nối
			openConnect();
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// câu lệnh SQL select
			sqlCommand.append("SELECT email ");
			sqlCommand.append("FROM tbl_user ");
			sqlCommand.append("WHERE email = ? AND NOT user_id = ?  ");
			
			int index = 1;
			// Tạo đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			preparedStatement.setString(index++, email);
			preparedStatement.setInt(index++, userId);
			
			// Thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			// Lấy về giá trị email
			if (resultSet.next()) {
				// Set giá trị vào tblUser
				emailDb = resultSet.getString("email");
			}
			if (!"".contentEquals(emailDb)) {
				check = true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".tblUserDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		} finally {
			// Đóng kết nối đến DB
			closeConnect();
		}
		return check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getLoginName(java.lang.String)
	 */
	@Override
	public String getLoginName(String loginName) throws SQLException, ClassNotFoundException {
		String loginNameDb = "";
		try {
			// Mở kết nối
			openConnect();
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// tạo đối tượng stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// câu lệnh SQL select
			sqlCommand.append("SELECT login_name ");
			sqlCommand.append("FROM tbl_user ");
			sqlCommand.append("WHERE login_name = ? ;");
			// Tạo đối tượng preparedStatement
			int index = 1;
			// Tạo đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			preparedStatement.setString(index++, loginName);
			// Thực thi câu lệnh SQL
			ResultSet resultSet = preparedStatement.executeQuery();
			// Lấy về giá trị loginName
			if (resultSet.next()) {
				// Set giá trị vào tblUser
				loginNameDb = resultSet.getString("login_name");
			}
		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".tblUserDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		} finally {
			// Đóng kết nối đến DB
			closeConnect();
		}
		return loginNameDb;
	}

	
	/* (non-Javadoc)
	 * @see manageuser.dao.TblUserDao#insertUser(manageuser.entities.TblUser)
	 */
	@Override
	public int insertUser(TblUser tblUser) throws SQLException{
		int userId = 0;
		try {
			// Khởi tạo đối tượng PreparedStatement
			PreparedStatement preparedStatement;
			// Khởi tạo đối tượng StringBuilder
			StringBuilder sqlCommand = new StringBuilder();
			// Gán câu lệnh sql cho sqlCommand
			sqlCommand.append("insert into tbl_user (login_name, password,full_name, ");
			sqlCommand.append("full_name_kana, email, tel, birthday, rule, salt, group_id, gender) ");
			sqlCommand.append("value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			// KHởi tạo biến index
			int index = 1;
			
			// gọi đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString(), Statement.RETURN_GENERATED_KEYS);
			
			// Truyền tham số cho preparedStatement
			preparedStatement.setString(index++, tblUser.getLoginName());
			preparedStatement.setString(index++, tblUser.getPassword());
			preparedStatement.setString(index++, tblUser.getFullName());
			preparedStatement.setString(index++, tblUser.getFullNameKana());
			preparedStatement.setString(index++, tblUser.getEmail());
			preparedStatement.setString(index++, tblUser.getTel());
			preparedStatement.setString(index++, tblUser.getBirthday());
			preparedStatement.setInt(index++, Constant.RULE_USER);
			preparedStatement.setString(index++, tblUser.getSalt());
			preparedStatement.setInt(index++, tblUser.getGroupId());
			preparedStatement.setString(index++, tblUser.getGender());
			
			// Thực thi câu lệnh
			preparedStatement.executeUpdate();
			userId = getUserIdByEmail(tblUser.getEmail());
			
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".tblUserDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		// trả về 1 user_id
		return userId;
	}
	
	/* (non-Javadoc)
	 * @see manageuser.dao.TblUserDao#getUserID(String)
	 */
	@Override
	public int getUserIdByEmail(String email) throws SQLException {
		// TODO Auto-generated method stub
		int ketqua = 0;
		try {
		String sql = "Select user_id from tbl_user where email = ?";
		PreparedStatement preparedStatement;
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				ketqua = resultSet.getInt("user_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketqua;
	}
	
	/* (non-Javadoc)
	 * @see manageuser.dao.TblUserDao#getUserByUserId(int, int)
	 */
	@Override
	public TblUser getUserByUserId(int userId, int rule) throws ClassNotFoundException, SQLException {
		TblUser tblUser = null;
		try {
			// Mở kết nối
			openConnect();
			// khởi tạo đối tượng PreparedStatement
			PreparedStatement preparedStatement;
			// Khởi tạo đối tượng StringBuilder
			StringBuilder sql = new StringBuilder();
			// Khởi tạo biến index = 1
			int index = 1;
			// Tạo câu lệnh sql
			sql.append("Select user_id , rule, full_name from tbl_user where user_id = ? and rule = ?");
			preparedStatement = connect.prepareStatement(sql.toString());
			// truyền tham số loginName
			preparedStatement.setInt(index++, userId);
			// Truyền tham số rule
			preparedStatement.setInt(index++, rule);
			// Thực thi câu lệnh SQl
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				tblUser = new TblUser();
				tblUser.setUserId(resultSet.getInt("user_id"));
				tblUser.setRule(resultSet.getInt("rule"));
				tblUser.setFullName(resultSet.getString("full_name"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("TblUserDaoImpl : getUserByUserId  " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối
			closeConnect();
		}
		return tblUser;
	}

	
	/* (non-Javadoc)
	 * @see manageuser.dao.TblUserDao#getUserInforById(int)
	 */
	@Override
	public TblUserInfor getUserInforById(int userId) throws SQLException, ClassNotFoundException {
		TblUserInfor tblUserInfor = new TblUserInfor();
		try {
			// Mở chuỗi kết nối
			openConnect();
			// khởi tạo đối tượng PreparedStatement
			PreparedStatement preparedStatement;
			// Tạo ra đối tượng Stringbuider
			StringBuilder sqlCommand = new StringBuilder();
			// Thực hiện câu lệnh select để lấy dữ liệu trong bảng tbl_user
			sqlCommand.append("SELECT u.user_id, u.login_name, u.full_name, u.full_name_kana, u.birthday, g.group_name, u.email, ");
			sqlCommand.append("u.tel, u.gender, j.name_level, d.start_date, d.end_date, d.total,g.group_id,d.code_level  ");
			sqlCommand.append("FROM tbl_user u ");
			sqlCommand.append("INNER JOIN mst_group g ");
			sqlCommand.append("ON u.group_id=g.group_id ");
			sqlCommand.append("LEFT JOIN (tbl_detail_user_japan d ");
			sqlCommand.append("INNER JOIN mst_japan j ");
			sqlCommand.append("ON d.code_level=j.code_level) ");
			sqlCommand.append("ON u.user_id=d.user_id ");
			sqlCommand.append("WHERE u.rule = ? and u.user_id=? ");
			// Gọi đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			// Truyền tham số truy vấn cho id
			preparedStatement.setInt(1, Constant.RULE_USER);
			preparedStatement.setInt(2, userId);
			// Thực thi câu lệnh SQl
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// Truyền dữ liệu vừa lấy được trong DB
				// Truyền vào đối tượng tblUserInfor
				tblUserInfor.setUserId(resultSet.getInt("user_id"));
				tblUserInfor.setLoginName(resultSet.getString("login_name"));
				tblUserInfor.setFullName(resultSet.getString("full_name"));
				tblUserInfor.setFullNameKana(resultSet.getString("full_name_kana"));
				tblUserInfor.setBirthday(resultSet.getString("birthday"));
				tblUserInfor.setGroupName(resultSet.getString("group_name"));
				tblUserInfor.setEmail(resultSet.getString("email"));
				tblUserInfor.setTel(resultSet.getString("tel"));
				tblUserInfor.setNameLevel(resultSet.getString("name_level"));
				tblUserInfor.setStartDay(resultSet.getString("start_date"));
				tblUserInfor.setEndDate(resultSet.getString("end_date"));
				tblUserInfor.setTotal(resultSet.getString("total"));
				tblUserInfor.setGroupId(resultSet.getInt("group_id"));
				tblUserInfor.setCodeLevel(resultSet.getString("code_level"));
				tblUserInfor.setGender(resultSet.getString("gender"));

			}
		} catch (SQLException | ClassNotFoundException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ".tblUserDaoImpl" + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		} finally {
			// Đóng kết nối đến DB
			closeConnect();
		}
		return tblUserInfor;
	}

	/* (non-Javadoc)
	 * @see manageuser.dao.TblUserDao#updatetUser(manageuser.entities.TblUser)
	 */
	@Override
	public void updatetUser(TblUser tblUser) throws SQLException {
		try {
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// Tạo đối tượng Stringbuider để cộng các chuỗi SQl
			StringBuilder sqlCommand = new StringBuilder();
			// Thực hiện cộng chuỗi câu lệnh SQL
			sqlCommand.append("UPDATE tbl_user SET group_id=?, full_name=?, full_name_kana=?,");
			sqlCommand.append(" email=?, tel=?, birthday=?, gender=? ");
			sqlCommand.append(" WHERE user_id = ? AND rule = ?");
			// Gọi đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			int index = 1;
			// truyền tham số truy vấn cho các trường
			preparedStatement.setInt(index++, tblUser.getGroupId());
			preparedStatement.setString(index++, tblUser.getFullName());
			preparedStatement.setString(index++, tblUser.getFullNameKana());
			preparedStatement.setString(index++, tblUser.getEmail());
			preparedStatement.setString(index++, tblUser.getTel());
			preparedStatement.setString(index++, tblUser.getBirthday());
			preparedStatement.setString(index++, tblUser.getGender());
			preparedStatement.setInt(index++, tblUser.getUserId());
			preparedStatement.setInt(index++, Constant.RULE_USER);
			// Thực thi câu lệnh SQl
			preparedStatement.executeUpdate();
			

		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}		
	}

	
	/* (non-Javadoc)
	 * @see manageuser.dao.TblUserDao#getTblUserByUserId(int)
	 */
	@Override
	public TblUser getTblUserByUserId(int userId) throws SQLException, ClassNotFoundException {
		TblUser tblUser = null;
		try {
			// Mở chuỗi kết nối
			openConnect();
			// Khởi tạo đối tượng PreparedStatement
			PreparedStatement preparedStatement;
			// khởi tạo đối tượng StringBuilder để cộng chuỗi lệnh SQl
			StringBuilder sqlCommand = new StringBuilder();
			// Thực hiện viết lệnh SQl
			sqlCommand.append(" SELECT user_id , rule FROM tbl_user ");
			sqlCommand.append(" WHERE user_id = ? ; ");
			// Gọi đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			// Truyền tham số truy vấn cho các trường
			preparedStatement.setInt(1, userId);
			// thực thi câu lệnh SQl
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				tblUser = new TblUser();
				// Lấy gía trị của trường rule
				tblUser.setRule(resultSet.getInt("rule"));
				tblUser.setUserId(resultSet.getInt("user_id"));
			}

		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		return tblUser;
	}

	
	/* (non-Javadoc)
	 * @see manageuser.dao.TblUserDao#deleteUserById(int)
	 */
	@Override
	public void deleteUserById(int userId) throws SQLException {
		try {
			// tạo đối tượng preparedStatement
			PreparedStatement preparedStatement;
			// Tạo đối tượng Stringbuider để cộng các chuỗi SQl
			StringBuilder sqlCommand = new StringBuilder();
			// Viết lệnh SQl xóa 1 user gán vào sqlCommand
			sqlCommand.append("DELETE FROM tbl_user ");
			sqlCommand.append("WHERE rule = ? and user_id= ?; ");
			// Gọi đối tượng preparedStatement
			preparedStatement = connect.prepareStatement(sqlCommand.toString());
			// Khởi tạo index = 1
			int index = 1;
			// Truyền tham số truy vấn cho các trường
			preparedStatement.setInt(index++, Constant.RULE_USER);
			preparedStatement.setInt(index++, userId);
			// Thực thi câu lệnh SQl
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}	
	}
}
