/**
 * Copyright(C) 2019 Luvina Software
 * ValidateUser.java, Dec 27, 2019, MDung
 */
package manageuser.validates;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import manageuser.entities.TblUserInfor;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.MstJapanLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * @author MDung
 *
 */
public class ValidateUser {
	/**
	 * Phương thức kiểm tra giá trị cho hạng mục login name: mã lỗi 001_Không
	 * nhập/Mã lỗi 019_Sai fomat/Mã lỗi 007_Độ dài khoảng/Mã lỗi 003_Tồn tại
	 * trong DB
	 * 
	 * @param loginName
	 *            giá trị truyền vào
	 * @return error: chuỗi lỗi, nếu không lỗi thì trả về null
	 * @throws SQLException
	 *             ngoại lệ sai lệnh SQl
	 * @throws ClassNotFoundException
	 *             ngoại lệ không timg thấy class
	 */
	public String checkLoginName(String loginName) throws SQLException, ClassNotFoundException {
		// Khởi tạo đối tượng TblUserLogic
		// Để kiểm tra login name đã tồn tại chưa
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		// Tạo ra chuỗi String để chứa các lỗi
		String error = "";
		try {
			// Kiểm tra giá trị nhập vào nếu rỗng Gán lỗi
			if (Common.checkIsEmpty(loginName)) {
				// Hiển thị thông báo với mã lỗi ERROR_001_LOGINNAME
				error = MessageErrorProperties.getValueByKey(Constant.ERROR_001_LOGINNAME);
				// Kiểm tra giá trị nếu sai fomat
			} else if (!Common.checkFormat(loginName, Constant.FORMAT_LOGIN_NAME)) {
				// gán chuỗi lỗi với mã lỗi là ERROR_019_LOGINNAME
				error = MessageErrorProperties.getValueByKey("ERROR_019_LOGINNAME");
				// Kiểm tra giá trị nhập vào có đúng khoảng giá trị hay không
			} else if (Common.checkLength(loginName, 4, 15)) {
				// gán chuỗi lỗi với mã lỗi là ERROR_007_LOGINNAME
				error = MessageErrorProperties.getValueByKey("ERROR_007_LOGINNAME");
				// Kiểm tra giá trị nnhập vào đã tồn tại trong DB chưa
			} else if (tblUserLogic.checkLoginName(loginName)) {
				// Nếu đã tại thì thêm vào chuỗi lỗi với mã lỗi là
				// ERROR_003_LOGINNAME
				error = MessageErrorProperties.getValueByKey("ERROR_003_LOGINNAME");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		// Trả về chuỗi lỗi
		return error;

	}

	/**
	 * Phương thức kiểm tra giá trị nhập vào cho hạng mục group kiểm tra các mã
	 * lỗi 002_Chưa chọn 004_ không tồn tại
	 * 
	 * @param groupId
	 *            giá trị đầu vào
	 * @return error: chuỗi lỗi, Nếu không có lỗi thì trả về rỗng thêm vào danh
	 *         sách lỗi
	 * @throws SQLException,
	 *             ClassNotFoundException ngoại lệ sai lệnh SQL, không tìm thấy
	 *             class
	 */
	public String checkGroup(int groupId) throws SQLException, ClassNotFoundException {
		// Khởi tạo đối tượng mstGroup
		// Để gọi phương thức kiểm tra tồn tại của group trong DB
		MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
		// Tạo 1 chuỗi để lưu các lỗi
		String error = "";
		try {
			// Nếu không chọn Group
			if (groupId == 0) {
				// gán chuỗi lỗi với mã lỗi là ERROR_002_GROUP
				error = MessageErrorProperties.getValueByKey("ERROR_002_GROUP");
				// Nếu như chọn Group không có trong DB
			} else if (!mstGroupLogic.checkGroup(groupId)) {
				// gán chuỗi lỗi với thông báo là ERROR_004_GROUP
				error = MessageErrorProperties.getValueByKey("ERROR_004_GROUP");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		// Trả về 1 chuỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra giá trị nhập vào của fullName các mã lỗi 001_Không
	 * nhâp 006_check maxlength
	 * 
	 * @param fullName
	 *            giá trị nhập vào
	 * @return error Chuối lỗi, Nếu không có lỗi thì trả về rỗng thêm vào danh
	 *         sách lỗi
	 */
	public String checkFullName(String fullName) {
		// Khởi tạo chuỗi để lưu các lỗi
		String error = "";
		// Nếu không nhập fullName
		if (Common.checkIsEmpty(fullName)) {
			// Hiển thị thông báo với mã lỗi ERROR_001_FULLNAME
			error = MessageErrorProperties.getValueByKey("ERROR_001_FULLNAME");
			// Kiểm tra nếu giá trị nhập vào quá số kí tự
		} else if (Common.checkMaxLength(fullName, Constant.MAX_LENGTH)) {
			// gán lỗi với mã lỗi ERROR_006_FULLNAME
			error = MessageErrorProperties.getValueByKey("ERROR_006_FULLNAME");
			// Kiểm tra giá trị nhập vào nếu khác format
		}
		// Trả về 1 chuỗi lỗi
		return error;
	}

	/**
	 * Phương trức kiểm tra giá trị nhập vào cho hạng mục FullNameKana các mã
	 * lỗi 009_kiểm tra kí tự kana 006_Kiểm tra maxlength
	 * 
	 * @param fullNameKana
	 *            giá trị nhập vào
	 * @return error: chuổi lỗi, Nếu không có lỗi thì trả về rỗng thêm vào danh
	 *         sách lỗi
	 */
	public String checkFullNameKana(String fullNameKana) {
		// Khởi tạo chuỗi để lưu các lỗi
		String error = "";
		// Kiểm tra nếu rỗng Gán lỗi thì thực hiện
		if (fullNameKana != "") {
			// Kiểm tra giá trị nhập đúng format không
			if (!Common.checkFormat(fullNameKana, Constant.FORMAT_KANA)) {
				// Nếu lỗi thì gán mã lỗi là ERROR_009_NAMEKANA
				error = MessageErrorProperties.getValueByKey("ERROR_009_NAMEKANA");
				// Kiểm tra giá trị nhập vào nếu lớn hơn giá trị cho phép
			} else if (Common.checkMaxLength(fullNameKana, Constant.MAX_LENGTH)) {
				// gán chuỗi lỗi với mã lỗi ERROR_006_NAMEKANA
				error = MessageErrorProperties.getValueByKey("ERROR_006_NAMEKANA");
			}
		}
		// Trả về chuỗi lỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra ngày nhập vào cho hạng mục birthday các mã lỗi 011_
	 * ngày không hợp lệ
	 * 
	 * @param birthday
	 *            ngày nhập váo
	 * @return error chuỗi lỗi, Nếu không có lỗi thì trả về rỗng thêm vào danh
	 *         sách lỗi
	 * @throws ParseException
	 */
	public String checkBirthday(String birthday) {
		// Tạo ra 1 chuối để chứa lỗi
		String error = "";
		// Kiếm tra ngày nhập vào có đúng không
		if (!Common.isValidateDate(birthday)) {
			// Start fix bug ID 21 – ThoaDT 2019/12/25
			// nếu sai thì thêm vào mã lỗi ERROR_0011_BirTHDAY
			error = MessageErrorProperties.getValueByKey("ERROR_0011_BIRTHDAY");
			// End fix bug ID 21 – ThoaDT 2019/12/26
		}
		// Trả về chuỗi lỗi
		return error;
	}

	/**
	 * Phương thứ kiểm tra cho hạng mục email các mã lỗi 001_Không nhập
	 * 006_Maxlength 005_ sai format 003_đã tồn tại
	 * 
	 * @param email
	 *            email nhập vào
	 * @return error chuỗi lỗi, nếu không có lỗi thì trả về rỗng thêm vào danh
	 *         sách lỗi
	 * @exception SQLException
	 *                ngoại lệ sai lệnh SQl
	 * @exception ClassNotFoundException
	 *                ngoại lệ không tìm thấy class
	 */
	public String checkEmail(String email, int userId) throws SQLException, ClassNotFoundException {
		// Khởi tạo 1 chuỗi để chứa lỗi
		String error = "";
		// Khởi tạo đối tượng TblUserLogic
		// Để kiểm tra email đã tồn tại trong DB chưa
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		try {
			// Kiểm tra nếu không nhập vào giá trị
			if (Common.checkIsEmpty(email)) {
				// gán lỗi với mã lỗi ERROR_001_EMAIL
				error = MessageErrorProperties.getValueByKey("ERROR_001_EMAIL");
				// Kiểm tra nêu sai format
			} else if (Common.checkMaxLength(email, Constant.MAX_LENGTH)) {
				// gán với mã lỗi là ERROR_005_EMAIL
				error = MessageErrorProperties.getValueByKey("ERROR_006_EMAIL");
				// Kiểm tra nếu email đã tồn tại
			} else if (!Common.checkFormat(email, Constant.FORMAT_EMAIL)) {
				// gán với mã lỗi ERROR_005_EMAIL
				error = MessageErrorProperties.getValueByKey("ERROR_005_EMAIL");
				// Kiểm tra nếu nhập quá số kí tự là 255
			} else if (tblUserLogic.checkEmail(email, userId)) {
				// gán với mã lỗi là ERROR_003_EMAIL
				error = MessageErrorProperties.getValueByKey("ERROR_003_EMAIL");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Ném lỗi đi
			throw e;
		}
		// Trả về 1 chuỗi lỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra cho hạng mục số điện thoại các lỗi 001_Không nhập
	 * 005_Sai format
	 * 
	 * @param tel
	 *            giá trị nhập vào
	 * @return error chuỗi lỗi, Nếu không lỗi thì trả về chuỗi rỗng thêm vào
	 *         danh sách lỗi
	 */
	public String checkTel(String tel) {
		// tạo chuỗi để lưu giá trị
		String error = "";
		// Kiểm tra nếu không nhập
		if (Common.checkIsEmpty(tel)) {
			// gán lỗi với mã lỗi ERROR_001_TEL
			error = MessageErrorProperties.getValueByKey("ERROR_001_TEL");
			// Kiểm tra nếu vượt quá độ dài trong khoảng
		} else if (!Common.checkFormat(tel, Constant.FORMAT_TEL)) {
			// gán với mã lỗi ERROR_005_TEL
			error = MessageErrorProperties.getValueByKey("ERROR_005_TEL");
		}
		// Trả về 1 chuỗi lỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra giá trị nhập vào cho hạng mục pasword các lỗi
	 * 001_Không nhập 008_check kí tự 1 byte 007_Check độ dài khoảng
	 * 
	 * @param pass
	 *            giá trị nhập vào
	 * @return error trả về chuỗi lỗi, nếu không lỗi thì trả về rỗng thêm vào
	 *         danh sách lỗi
	 * @throws UnsupportedEncodingException
	 */
	public String checkPass(String pass) throws UnsupportedEncodingException {
		// Khởi tạo chuỗi để lưu lỗi
		String error = "";
		// Kiểm tra giá trị nhập vào có rỗng Gán lỗi không
		if (Common.checkIsEmpty(pass)) {
			// gán lỗi với mã lỗi ERROR_001_PASS
			error = MessageErrorProperties.getValueByKey(Constant.ERROR_001_PASS);
			// Kiểm tra giá trị nhập vào có vượt quá khoảng không
		} else if (!Common.checkOneByte(pass)) {
			// gán lỗi với mã lỗi ERROR_PASS_HASHSIZE
			error = MessageErrorProperties.getValueByKey("ERROR_008_PASS");
		} else if (Common.checkLength(pass, Constant.MIN_PASS, Constant.MAX_PASS)) {
			// Gán với mã lỗi ERROR_007_PASS
			error = MessageErrorProperties.getValueByKey("ERROR_007_PASS");
			// Kiểm tra giá trị nhập vào nếu khác format
		}
		// Trả về 1 chuỗi lỗi
		return error;
	}

	/**
	 * Check pass nhập lại có đúng pass nhập ban đầu không 017_mật khẩu xác
	 * nhận sai
	 * 
	 * @param pass
	 *            pass nhập ban đầu
	 * @param passConfirm
	 *            pass nhập lại
	 * @return câu thông báo lỗi nếu pass nhập lại khác pass ban đầu
	 */
	public String checkComfirmPass(String pass, String passConfirm) {
		String error = "";
		if (!passConfirm.equals(pass)) {
			error = MessageErrorProperties.getValueByKey("ERROR_017_CONFIRMPASS");
		}
		return error;
	}

	/**
	 * Phương thức kiểm tra cho hạng mục codeLevel các mã lỗi 004_Không tồn tại
	 * 
	 * @param codeLevel
	 *            giá trị nhập vào
	 * @return error trả về 1 chuỗi lỗi, nếu không lỗi thì trả về rỗng thêm vào
	 *         danh sách lỗi
	 * @throws ClassNotFoundException
	 *             ngoại lệ không tìm thấy class
	 * @throws SQLException
	 *             ngoại lệ sai lệnh SQL
	 */
	public String checkCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException {
		// tạo chuỗi để chứa các lỗi
		String error = "";
		// Khởi tạo đối tượng MstJapanLogic để kiểm tra tồn tại
		MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
		try {
			// Nếu code level khác 0 thì thực hiện
			if (Common.checkCodelevel(codeLevel)) {
				// Nếu không tồn tại code Leve
				if (!mstJapanLogic.checkMstJapanByGroup(codeLevel)) {
					// Thì gán lỗi với mã lỗi ERROR_004_CODE_LEVEL
					error = MessageErrorProperties.getValueByKey("ERROR_004_CODE_LEVEL");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Ném ngoại lệ
			throw e;

		}
		// Trả về 1 chuỗi lỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra cho hạng mục ngày bắt đầu lỗi 011_ngày không hợp lệ
	 * 
	 * @param startDay
	 *            giá trị nhập vào
	 * @return error chuỗi lỗi, nếu không có lối thì trả về rỗng thêm vào danh
	 *         sách lỗi
	 */
	public String CheckStartDay(String startDay) {
		// Tạo chuỗi để lưu các lỗi
		String error = "";
		// Kiểm tra ngày nhập vào nếu sai
		if (!Common.isValidateDate(startDay)) {
			// Gán với mã lỗi ERROR_0011_STARTDATE
			error = MessageErrorProperties.getValueByKey("ERROR_011_STARTDATE");
		}
		// Trả về 1 chuỗi lỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra cho hạng mục ngày kết thúc( kiểm tra 2 mã lỗi ngày
	 * hợp lệ và ngày bắt đầu < ngày kết thúc)
	 * 
	 * @param startDay
	 *            giá trị nhập vào
	 * @return error chuỗi lỗi, nếu không có lối thì trả về rỗng
	 * @throws ParseException
	 */
	public String checkEndDay(String startDay, String endDate) throws ParseException {
		// Khởi tạo một chuỗi lỗi
		String error = "";
		// Kiểm tra ngày nhập vào có đúng không
		if (!Common.isValidateDate(endDate)) {
			// Nếu sai thì Gán lỗi với mã lỗi ERROR_0011_ENDDATE
			error = MessageErrorProperties.getValueByKey("ERROR_011_ENDDATE");
			// nếu ngày nhập đúng thì kiểm tra logic ngày bắt đầu và ngày kết
			// thúc
		} else if (Common.isValidateDate(endDate)) {
			// Chuyển chuỗi thành kiểu ngày
			Date startDate = Common.toDate(startDay);
			Date dateEnd = Common.toDate(endDate);
			// Nếu ngày bắt đầu lớn hơn ngày kết thúc thì thêm lỗi
			if (startDate.after(dateEnd)) {
				error = MessageErrorProperties.getValueByKey("ERROR_012_ENDDATE");
			}
		}
		// Trả về 1 chuỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra giá trị nhập vào cho hạng mục total 001_không nhập,
	 * 018_check số halfsize, 006_check maxlength
	 * 
	 * @param total
	 *            giá trị nhập vào
	 * @return error trả về 1 chuỗi lỗi, nếu không có lỗi trả về rỗng thêm vào
	 *         danh sách lỗi
	 */
	public String checkTotal(String total) {
		// Khởi tạo đối tượng để chứa lỗi
		String error = "";
		// kiểm tra giá trị nhập vào là rỗng Gán lỗi
		if (Common.checkIsEmpty(total)) {
			// Gán lỗi với mã lỗi là ERROR_001_TOTAL
			error = MessageErrorProperties.getValueByKey("ERROR_001_TOTAL");
			// Kiểm tra giá trị nhập vào nếu vượt quá kí tự
		} else if (!Common.checkFormat(total, Constant.FORMAT_HALF_SIZE)) {
			// Gán lỗi với mã lỗi ERROR_0018_TOTAL
			error = MessageErrorProperties.getValueByKey("ERROR_018_TOTAL");
		} else if (Common.checkMaxLength(total, Constant.MAX_TOTAL)) {
			// gán lỗi với mã lỗi ERROR_006_TOTAL
			error = MessageErrorProperties.getValueByKey("ERROR_006_TOTAL");
			// Trường hợp giá trị nhập vào khác format
		}
		// Trả về 1 chuỗi lỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra màn hình 003 đã hợp lệ hay chưa
	 * 
	 * @param userInfor
	 *            đối tượng userInfor
	 * @return listError trả về 1 danh sách lỗi
	 * @throws ClassNotFoundException
	 *             ngoại lệ không tìm thấy class
	 * @throws SQLException
	 *             ngoại lệ sai lệnh SQL
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	public List<String> validateUserInfor(TblUserInfor userInfor)
			throws ParseException, ClassNotFoundException, SQLException, UnsupportedEncodingException {
		// Khởi tạo 1 list để chứa các lỗi
		List<String> listError = new ArrayList<>();
		try {
			// Nếu userId = 0 : addUser check lỗi LoginName
			if (userInfor.getUserId() == 0) {
				// Thêm lỗi loginName vào danh sách
				Common.addListError(listError, checkLoginName(userInfor.getLoginName()));
			}
			// Kiểm tra và thêm lỗi các hạng mục vào danh sách
			Common.addListError(listError, checkGroup(userInfor.getGroupId()));
			Common.addListError(listError, checkFullName(userInfor.getFullName()));
			Common.addListError(listError, checkFullNameKana(userInfor.getFullNameKana()));
			Common.addListError(listError, checkBirthday(userInfor.getBirthday()));
			Common.addListError(listError, checkEmail(userInfor.getEmail(), userInfor.getUserId()));
			Common.addListError(listError, checkTel(userInfor.getTel()));
			// Nếu là TH add thì thực hiện kiểm tra
			if (userInfor.getUserId() == 0) {
				// Thêm lỗi vào danh sách
				Common.addListError(listError, checkPass(userInfor.getPass()));
				// Nếu pass nhập lại sai thêm lỗi vào danh sách
				Common.addListError(listError, checkComfirmPass(userInfor.getPass(), userInfor.getConfirmPass()));
			}
			// Nếu có chọn codeLevel thì
			if (Common.checkCodelevel(userInfor.getCodeLevel())) {
				// Kiểm tra các hạng mục và thêm lỗi
				Common.addListError(listError, checkCodeLevel(userInfor.getCodeLevel()));
				Common.addListError(listError, CheckStartDay(userInfor.getStartDay()));
				Common.addListError(listError, checkEndDay(userInfor.getStartDay(), userInfor.getEndDate()));
				Common.addListError(listError, checkTotal(userInfor.getTotal()));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		// Trả về 1 list lỗi
		return listError;
	}
}
