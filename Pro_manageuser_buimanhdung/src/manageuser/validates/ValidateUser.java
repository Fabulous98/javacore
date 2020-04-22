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
 * Class chứa các phương thưc validate
 * 
 * @author MDung
 *
 */
public class ValidateUser {
	/**
	 * Phương thức kiểm tra giá trị cho hạng mục login name: gồm mã lỗi 001_Không
	 * nhập, Mã lỗi 019_Sai fomat/Mã lỗi 007_Độ dài khoảng, Mã lỗi 003_Tồn tại
	 * trong DB
	 * 
	 * @param loginName : giá trị truyền vào
	 *            
	 * @return error: chuỗi lỗi, nếu không lỗi thì trả về null
	 */
	public String checkLoginName(String loginName) throws SQLException, ClassNotFoundException {
		
		// Khởi tạo đối tượng TblUserLogic
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		
		// Tạo ra chuỗi String để chứa các lỗi
		String error = "";
		
		try {
			// Kiểm tra giá trị nhập vào nếu rỗng Gán lỗi
			if (Common.checkIsEmpty(loginName)) {
				// Gán lỗi với mã lỗi ERROR_001_LOGINNAME
				error = MessageErrorProperties.getValueByKey(Constant.ERROR_001_LOGINNAME);
				
				// Kiểm tra giá trị nếu sai fomat
			} else if (!Common.checkFormat(loginName, Constant.FORMAT_LOGIN_NAME)) {
				// Gán lỗi với mã lỗi ERROR_019_LOGINNAME
				error = MessageErrorProperties.getValueByKey("ERROR_019_LOGINNAME");
				
				// Kiểm tra giá trị nhập vào có đúng khoảng giá trị hay không
			} else if (Common.checkLength(loginName, 4, 15)) {
				// Gán lỗi với mã lỗi ERROR_007_LOGINNAME
				error = MessageErrorProperties.getValueByKey("ERROR_007_LOGINNAME");
				
				// Kiểm tra giá trị nnhập vào đã tồn tại trong DB chưa
			} else if (tblUserLogic.checkLoginName(loginName)) {
				// Gán lỗi với mã lỗi ERROR_003_LOGINNAME
				error = MessageErrorProperties.getValueByKey("ERROR_003_LOGINNAME");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			throw e;
		}
		
		// Trả về chuỗi chứa các lỗi
		return error;
	}
	
	/**
	 * Phương thức kiểm tra giá trị nhập vào của hạng mục group
	 * kiểm tra các mã: 002_Chưa chọn, 004_ không tồn tại
	 * 
	 * @param groupId : giá trị đầu vào
	 *            
	 * @return error: chuỗi lỗi, Nếu không có lỗi thì trả về rỗng
	 * @throws SQLException, ClassNotFoundException
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
	 * Phương thức kiểm tra giá trị nhập vào cho pasword các lỗi sau:
	 * 001_Không nhập, 008_check kí tự one byte, 007_Check độ dài khoảng
	 * 
	 * @param pass : giá trị nhập vào
	 *            
	 * @return error trả về chuỗi lưu các lỗi
	 *         
	 * @throws UnsupportedEncodingException
	 */
	public String checkPass(String pass) throws UnsupportedEncodingException {
		
		// Khởi tạo chuỗi để lưu lỗi
		String error = "";
		
		// Kiểm tra lỗi password rỗng
		if (Common.checkIsEmpty(pass)) {
			// gán lỗi ERROR_001_PASS
			error = MessageErrorProperties.getValueByKey(Constant.ERROR_001_PASS);
			
			// Kiểm tra pass có phải ký tự onebyte không
		} else if (!Common.checkOneByte(pass)) {
			// gán mã lỗi ERROR_PASS_HASHSIZE
			error = MessageErrorProperties.getValueByKey("ERROR_008_PASS");
			
			// Kiểm tra maxlength
		} else if (Common.checkLength(pass, Constant.MIN_PASS, Constant.MAX_PASS)) {
			// Gán mã lỗi ERROR_007_PASS
			error = MessageErrorProperties.getValueByKey("ERROR_007_PASS");
			
		}
		// Trả về chuỗi lỗi
		return error;
	}

	/**
	 * Check pass nhập lại có đúng pass nhập ban đầu không, mã lỗi
	 * 017_Xác nhận sai pass
	 * @param pass : pass nhập ban đầu
	 *            
	 * @param passConfirm : pass nhập lại
	 *            
	 * @return Thông báo lỗi nếu nhập lại pass không khớp pass ban đầu
	 */
	public String checkComfirmPass(String pass, String passConfirm) {
		String error = "";
		if (!passConfirm.equals(pass)) {
			error = MessageErrorProperties.getValueByKey("ERROR_017_CONFIRMPASS");
		}
		return error;
	}
	
	/**
	 * Phương thức kiểm tra giá trị nhập vào của fullName 
	 * các mã lỗi : 001_Không nhâp, 006_check maxlength
	 * 
	 * @param fullName : giá trị đầu vào
	 *            
	 * @return error : Chuối lỗi, Nếu không có lỗi thì trả về rỗng
	 */
	public String checkFullName(String fullName) {
		// Khởi tạo chuỗi để lưu các lỗi
		String error = "";
		// Nếu không nhập fullName
		if (Common.checkIsEmpty(fullName)) {
			// gán lỗi với mã lỗi ERROR_001_FULLNAME
			error = MessageErrorProperties.getValueByKey("ERROR_001_FULLNAME");
			// Kiểm tra maxlength
		} else if (Common.checkMaxLength(fullName, Constant.MAX_LENGTH)) {
			// gán lỗi với mã lỗi ERROR_006_FULLNAME
			error = MessageErrorProperties.getValueByKey("ERROR_006_FULLNAME");
		}
		// Trả về 1 chuỗi lỗi
		return error;
	}
	
	/**
	 * Phương trức kiểm tra giá trị nhập vào cho hạng mục FullNameKana
	 * các mã lỗi 009_kiểm tra kí tự kana, 006_Kiểm tra maxlength
	 * 
	 * @param fullNameKana : giá trị nhập vào
	 *            
	 * @return error: chuỗi lỗi, Nếu không có lỗi thì trả về rỗng
	 */
	public String checkFullNameKana(String fullNameKana) {
		// Khởi tạo chuỗi để lưu các lỗi
		String error = "";
		// Nếu fullnamekana khác rỗng thì thực hiện
		if (fullNameKana != "") {
			// Kiểm tra format nhập vào
			if (!Common.checkFormat(fullNameKana, Constant.FORMAT_KANA)) {
				// Nếu lỗi format thì gán mã lỗi là ERROR_009_NAMEKANA
				error = MessageErrorProperties.getValueByKey("ERROR_009_NAMEKANA");
				// Kiểm tra maxlength
			} else if (Common.checkMaxLength(fullNameKana, Constant.MAX_LENGTH)) {
				// gán chuỗi lỗi với mã lỗi ERROR_006_NAMEKANA
				error = MessageErrorProperties.getValueByKey("ERROR_006_NAMEKANA");
			}
		}
		// Trả về chuỗi lỗi
		return error;
	}
	
	/**
	 * Phương thức kiểm tra nhập vào hạng mục birthday
	 * các mã lỗi: 011_ngày không hợp lệ
	 * 
	 * @param birthday : đầu vào
	 *            
	 * @return error : chuỗi lỗi, Nếu không có lỗi thì trả về rỗng
	 * @throws ParseException
	 */
	public String checkBirthday(String birthday) {
		// Tạo ra 1 chuối để chứa lỗi
		String error = "";
		// Kiếm tra ngày nhập vào có đúng không
		if (!Common.isValidateDate(birthday)) {
			
			// nếu sai thì thêm mã lỗi ERROR_0011_BirTHDAY
			error = MessageErrorProperties.getValueByKey("ERROR_0011_BIRTHDAY");
			
		}
		// Trả về chuỗi lỗi
		return error;
	}
	
	/**
	 * Phương thứ kiểm tra hạng mục email 
	 * các mã lỗi : 001_Không nhập, 006_Maxlength, 005_ sai format, 003_đã tồn tại
	 * 
	 * @param email : email nhập vào
	 *            
	 * @return error chuỗi lỗi, nếu không có lỗi thì trả về rỗng
	 * @exception SQLException
	 *                
	 * @exception ClassNotFoundException
	 *                
	 */
	public String checkEmail(String email, int userId) throws SQLException, ClassNotFoundException {
		// Khởi tạo 1 chuỗi để chứa lỗi
		String error = "";
		// Khởi tạo đối tượng TblUserLogic
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		try {
			// Kiểm tra lỗi không nhập
			if (Common.checkIsEmpty(email)) {
				// gán lỗi với mã lỗi ERROR_001_EMAIL
				error = MessageErrorProperties.getValueByKey("ERROR_001_EMAIL");
				// Kiểm tra maxlength
			} else if (Common.checkMaxLength(email, 100)) {
				// gán với mã lỗi là ERROR_005_EMAIL
				error = MessageErrorProperties.getValueByKey("ERROR_006_EMAIL");
				// check format
			} else if (!Common.checkFormat(email, Constant.FORMAT_EMAIL)) {
				// gán với mã lỗi ERROR_005_EMAIL
				error = MessageErrorProperties.getValueByKey("ERROR_005_EMAIL");
				// Kiểm tra tồn tại email
			} else if ((userId == 0 && tblUserLogic.checkEmail(email)) || (userId > 0 && tblUserLogic.checkExistEmail(email, userId))) {
				// gán với mã lỗi là ERROR_003_EMAIL
				error = MessageErrorProperties.getValueByKey("ERROR_003_EMAIL");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Ném lỗi
			throw e;
		}
		// Trả về 1 chuỗi lỗi
		return error;
	}
	
	/**
	 * Phương thức kiểm tra hạng mục codeLevel : mã lỗi 004_Không tồn tại
	 * 
	 * @param codeLevel : giá trị nhập vào
	 *           
	 * @return error trả về 1 chuỗi lỗi, nếu không lỗi thì trả về rỗng
	 * @throws ClassNotFoundException
	 *             
	 * @throws SQLException
	 *             
	 */
	public String checkCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException {
		// tạo chuỗi để chứa các lỗi
		String error = "";
		// Khởi tạo đối tượng MstJapanLogic
		MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
		try {
			// Nếu code level khác 0 (mặc định) thì thực hiện
			if (Common.checkCodelevel(codeLevel)) {
				// Kiểm tra tồn tại code Level
				if (!mstJapanLogic.checkMstJapanByGroup(codeLevel)) {
					// Nếu không tồn tại thì gán lỗi ERROR_004_CODE_LEVEL
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
	 * Phương thức kiểm tra hạng mục ngày bắt đầu : lỗi 011_ngày không hợp lệ
	 * 
	 * @param startDay : giá trị nhập vào
	 *            
	 * @return error chuỗi lỗi, nếu không có lối thì trả về rỗng
	 */
	public String CheckStartDay(String startDay) {
		// Tạo chuỗi để lưu các lỗi
		String error = "";
		// Kiểm tra ngày nhập vào đúng không
		if (!Common.isValidateDate(startDay)) {
			// Gán với mã lỗi ERROR_0011_STARTDATE nếu có lỗi
			error = MessageErrorProperties.getValueByKey("ERROR_011_STARTDATE");
		}
		// Trả về 1 chuỗi lỗi
		return error;
	}
	
	/**
	 * Phương thức kiểm tra hạng mục ngày kết thúc
	 * Các mã lỗi: lỗi 011_ngày không hợp lệ, lỗi 012_ngày kết thúc nhỏ hơn ngày bắt đầu
	 * 
	 * @param startDay : giá trị nhập vào
	 *            
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
			// nếu ngày nhập đúng thì kiểm tra logic ngày bắt đầu và kết thúc
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
	 * Phương thức kiểm tra giá trị nhập vào cho hạng mục total 
	 * Gồm các lỗi: 001_không nhập, 018_check số halfsize, 006_check maxlength
	 * 
	 * @param total : giá trị nhập vào
	 *            
	 * @return error trả về chuỗi lỗi nếu có
	 *         
	 */
	public String checkTotal(String total) {
		// Khởi tạo đối tượng chứa lỗi
		String error = "";
		// kiểm tra giá trị nhập vào rỗng hay không
		if (Common.checkIsEmpty(total)) {
			// Gán lỗi với mã lỗi ERROR_001_TOTAL
			error = MessageErrorProperties.getValueByKey("ERROR_001_TOTAL");
			// Kiểm tra format giá trị nhập
		} else if (!Common.checkFormat(total, Constant.FORMAT_HALF_SIZE)) {
			// Gán lỗi với mã lỗi ERROR_0018_TOTAL
			error = MessageErrorProperties.getValueByKey("ERROR_018_TOTAL");
			// Kiểm tra maxlength
		} else if (Common.checkMaxLength(total, Constant.MAX_TOTAL)) {
			// gán lỗi với mã lỗi ERROR_006_TOTAL
			error = MessageErrorProperties.getValueByKey("ERROR_006_TOTAL");
		}
		// Trả về 1 chuỗi lỗi
		return error;
	}
	
	/**
	 * Phương thức kiểm tra cho hạng mục số điện thoại 
	 * các lỗi: 001_Không nhập, 005_Sai format
	 * 
	 * @param tel : giá trị nhập vào
	 *            
	 * @return error chuỗi lỗi, Nếu không lỗi thì trả về chuỗi rỗng thêm vào
	 *         danh sách lỗi
	 */
	public String checkTel(String tel) {
		// tạo chuỗi để lưu lỗi
		String error = "";
		// Kiểm tra lỗi không nhập
		if (Common.checkIsEmpty(tel)) {
			// gán lỗi ERROR_001_TEL
			error = MessageErrorProperties.getValueByKey("ERROR_001_TEL");
			// Kiểm tra format
		} else if (!Common.checkFormat(tel, Constant.FORMAT_TEL)) {
			// gán với mã lỗi ERROR_005_TEL
			error = MessageErrorProperties.getValueByKey("ERROR_005_TEL");
			// Kiểm tra maxlength
		} else if (Common.checkLength(tel, 5, 14)) {
			// gán với mã lỗi ERROR_005_TEL
			error = MessageErrorProperties.getValueByKey("ERROR_006_TEL");
		}
		// Trả về 1 chuỗi lỗi
		return error;
	}

	/**
	 * Phương thức validate cho màn hình 003 xem đã hợp lệ hay chưa
	 * 
	 * @param userInfor : đối tượng userInfor
	 *            
	 * @return listError : 1 danh sách các lỗi
	 * @throws ClassNotFoundException
	 *             
	 * @throws SQLException
	 *             
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	public List<String> validateUserInfor(TblUserInfor userInfor)
			throws ParseException, ClassNotFoundException, SQLException, UnsupportedEncodingException {
		
		// Khởi tạo 1 list để chứa các lỗi
		List<String> listError = new ArrayList<>();
		try {
			// Kiểm tra xem userID có đang mặc định bằng 0
			if (userInfor.getUserId() == 0) {
				// check lỗi LoginName nếu có thì thêm lỗi loginName vào danh sách
				Common.addListError(listError, checkLoginName(userInfor.getLoginName()));
			}
			
				// Kiểm tra và thêm lỗi các hạng mục vào danh sách
				Common.addListError(listError, checkGroup(userInfor.getGroupId()));
				Common.addListError(listError, checkFullName(userInfor.getFullName()));
				Common.addListError(listError, checkFullNameKana(userInfor.getFullNameKana()));
				Common.addListError(listError, checkBirthday(userInfor.getBirthday()));
				Common.addListError(listError, checkEmail(userInfor.getEmail(), userInfor.getUserId()));
				Common.addListError(listError, checkTel(userInfor.getTel()));
			
			// Kiểm tra xem userID có đang mặc định bằng 0
			if (userInfor.getUserId() == 0) {
				// Thêm lỗi checkPass vào danh sách lỗi nếu có
				Common.addListError(listError, checkPass(userInfor.getPass()));
				
				// Thêm lỗi confirmPass vào danh sách lỗi nếu có
				Common.addListError(listError, checkComfirmPass(userInfor.getPass(), userInfor.getConfirmPass()));
			}
			
			// Kiểm tra có chọn codeLevel hay không
			if (Common.checkCodelevel(userInfor.getCodeLevel())) {
				// Nếu có kiểm tra các hạng mục của vùng này và thêm lỗi nếu phát hiện
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
		// Trả về list các lỗi
		return listError;
	}
	
}
