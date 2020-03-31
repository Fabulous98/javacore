/**
 * Copyright(C) 2019 Luvina Software
 * LoginValidate.java, Dec 27, 2019, MDung
 */
package manageuser.validates;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Constant;

/**
 * @author MDung
 *
 */
public class LoginValidate {
	/**
	 * Hàm validate tên đăng nhập và mật khẩu
	 * 
	 * @param username : tên đăng nhập
	 *            
	 * @param password : mật khẩu
	 *            
	 * @return một list các lỗi
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<String> validateLogin(String username, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException, ClassNotFoundException {
		List<String> listEr= new ArrayList<String>();
		try {
			// Khởi tao đối tượng TblUserLogicImpl
			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			// Nếu tên đăng nhập rỗng
			if (Constant.DEFAULT_STRING.equals(username)) {
				// hiển thị thông báo
				// hiển thị thông báo
				listEr.add(MessageErrorProperties.getValueByKey(Constant.ERROR_001_LOGINNAME));
			}
			// Nếu password rỗng
			if (Constant.DEFAULT_STRING.equals(password)) {
				// hiển thị thông báo lỗi
				listEr.add(MessageErrorProperties.getValueByKey(Constant.ERROR_001_PASS));
			}
			// Nếu tên đăng nhập và mật khẩu không rỗng
			if (listEr.isEmpty()) {
				// Nếu tên đăng nhập và mật khẩu không đúng
				if (!tblUserLogicImpl.checkExitAdmin(username, password)) {
					// Hiển thị thông báo
					listEr.add(MessageErrorProperties.getValueByKey("ER016_LOGIN"));
				}
			}

		} catch (Exception e) {
			// Ghi log và ném ngoại lệ
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		// trả về list validate
		return listEr;
	}
}
