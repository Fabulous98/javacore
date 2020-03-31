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
	 * Phương thức kiểm tra giá trị cho hạng mục login name: mã lỗi 001_Không
	 * nhập/Mã lỗi 019_Sai fomat/Mã lỗi 007_Độ dài khoảng/Mã lỗi 003_Tồn tại
	 * trong DB
	 * 
	 * @param loginName : giá trị truyền vào
	 *            
	 * @return error: chuỗi lỗi, nếu không lỗi thì trả về null
	 */
	public String checkLoginName(String loginName) throws SQLException, ClassNotFoundException {
		
		String error = "";
	
		return error;

	}

	/**
	 * Phương thức kiểm tra giá trị nhập vào cho hạng mục group kiểm tra các mã
	 * lỗi 002_Chưa chọn và 004_ không tồn tại
	 * 
	 * @param groupId : giá trị đầu vào
	 *            
	 * @return error: chuỗi lỗi, Nếu không có lỗi thì trả về rỗng thêm vào danh
	 */
	public String checkGroup(int groupId) throws SQLException, ClassNotFoundException {
		// Khởi tạo đối tượng mstGroup
		MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
		// Tạo 1 chuỗi để lưu các lỗi
		String error = "";
		return error;
	}

	/**
	 * Phương thức kiểm tra giá trị nhập vào của fullName các mã lỗi 001_Không
	 * nhâp 006_check maxlength
	 * 
	 * @param fullName : giá trị nhập vào
	 *            
	 * @return error Chuối lỗi, Nếu không có lỗi thì trả về rỗng thêm vào danh
	 *         sách lỗi
	 */
	public String checkFullName(String fullName) {
		// Khởi tạo chuỗi để lưu các lỗi
		String error = "";
		
		// Trả về 1 chuỗi lỗi
		return error;
	}

	/**
	 * Phương trức kiểm tra giá trị nhập vào cho hạng mục FullNameKana các mã
	 * lỗi 009_kiểm tra kí tự kana 006_Kiểm tra maxlength
	 * 
	 * @param fullNameKana : giá trị nhập vào
	 *            
	 * @return error: chuổi lỗi, Nếu không có lỗi thì trả về rỗng thêm vào danh
	 *         sách lỗi
	 */
	public String checkFullNameKana(String fullNameKana) {
		// Khởi tạo chuỗi để lưu các lỗi
		String error = "";
		
		// Trả về chuỗi lỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra ngày nhập vào cho hạng mục birthday các mã lỗi 011_
	 * ngày không hợp lệ
	 * 
	 * @param birthday : ngày nhập váo
	 *            
	 * @return error chuỗi lỗi, Nếu không có lỗi thì trả về rỗng thêm vào danh sách lỗi
	 *        
	 */
	public String checkBirthday(String birthday) {
		// Tạo ra 1 chuối để chứa lỗi
		String error = "";
		return error;
	}

	/**
	 * Phương thứ kiểm tra cho hạng mục email các mã lỗi 001_Không nhập
	 * 006_Maxlength 005_ sai format 003_đã tồn tại
	 * 
	 * @param email : email nhập vào
	 *            
	 * @return error chuỗi lỗi, nếu không có lỗi thì trả về rỗng thêm vào danh
	 *         sách lỗi
	 */
	public String checkEmail(String email, int userId) throws SQLException, ClassNotFoundException {
		// Khởi tạo 1 chuỗi để chứa lỗi
		String error = "";
		return error;
	}

	/**
	 * Phương thức kiểm tra cho hạng mục số điện thoại các lỗi 001_Không nhập
	 * 005_Sai format
	 * 
	 * @param tel : giá trị nhập vào
	 *            
	 * @return error chuỗi lỗi, Nếu không lỗi thì trả về chuỗi rỗng thêm vào
	 *         danh sách lỗi
	 */
	public String checkTel(String tel) {
		// tạo chuỗi để lưu giá trị
		String error = "";
		return error;
	}

	/**
	 * Phương thức kiểm tra giá trị nhập vào cho hạng mục pasword các lỗi
	 * 001_Không nhập 008_check kí tự 1 byte 007_Check độ dài khoảng
	 * 
	 * @param pass : giá trị nhập vào
	 *            
	 * @return error trả về chuỗi lỗi, nếu không lỗi thì trả về rỗng thêm vào
	 *         danh sách lỗi
	 */
	public String checkPass(String pass) throws UnsupportedEncodingException {
		// Khởi tạo chuỗi để lưu lỗi
		String error = "";
		return error;
	}

	/**
	 * Check pass nhập lại có đúng pass nhập ban đầu không 017_mật khẩu xác
	 * nhận sai
	 * 
	 * @param pass : pass nhập ban đầu
	 *            
	 * @param passConfirm : pass nhập lại
	 *            
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
	 * @param codeLevel : giá trị nhập vào
	 *            
	 * @return error trả về 1 chuỗi lỗi, nếu không lỗi thì trả về rỗng thêm vào : danh sách lỗi
	 *         
	 * @throws ClassNotFoundException : ngoại lệ không tìm thấy class
	 *             
	 * @throws SQLException : ngoại lệ sai lệnh SQL
	 *             
	 */
	public String checkCodeLevel(String codeLevel) throws ClassNotFoundException, SQLException {
		// tạo chuỗi để chứa các lỗi
		String error = "";
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
		// Trả về 1 chuỗi lỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra cho hạng mục ngày kết thúc( kiểm tra 2 mã lỗi ngày
	 * hợp lệ và ngày bắt đầu < ngày kết thúc)
	 * 
	 * @param startDay : giá trị nhập vào
	 *            
	 * @return error chuỗi lỗi, nếu không có lối thì trả về rỗng
	 * @throws ParseException
	 */
	public String checkEndDay(String startDay, String endDate) throws ParseException {
		// Khởi tạo một chuỗi lỗi
		String error = "";
		// Trả về 1 chuỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra giá trị nhập vào cho hạng mục total 001_không nhập,
	 * 018_check số halfsize, 006_check maxlength
	 * 
	 * @param total : giá trị nhập vào
	 *            
	 * @return error trả về 1 chuỗi lỗi, nếu không có lỗi trả về rỗng thêm vào
	 *         danh sách lỗi
	 */
	public String checkTotal(String total) {
		// Khởi tạo đối tượng để chứa lỗi
		String error = "";
		// Trả về 1 chuỗi lỗi
		return error;
	}

	/**
	 * Phương thức kiểm tra màn hình 003 đã hợp lệ hay chưa
	 * 
	 * @param userInfor : đối tượng userInfor
	 *            
	 * @return listError trả về 1 danh sách lỗi
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
		// Trả về 1 list lỗi
		return listError;
	}
}
