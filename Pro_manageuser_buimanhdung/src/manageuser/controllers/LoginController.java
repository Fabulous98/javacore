package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Constant;
import manageuser.validates.LoginValidate;

/**
 * Controller xử lý màn hình login
 */

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// khởi tạo đối tượng Validate
			LoginValidate validateLogin = new LoginValidate();
			// khởi tạo 1 aray list Error
			List<String> listError = new ArrayList<String>();
			// Lấy tên đăng nhập trên form
			String loginName = request.getParameter(Constant.LOGGIN_NAME).trim();
			// Lấy mật khẩu trên form
			String pass = request.getParameter(Constant.PASS).trim();
			// gọi đến phương thức kiểm tra đăng nhập
			listError = validateLogin.validateLogin(loginName, pass);
			// nếu size =0 login thành công
			if (listError.isEmpty()) {
				// Khởix tạo session
				HttpSession session = request.getSession();
				// Set giá trị lên session
				session.setAttribute(Constant.SESSION_LOGIN, loginName);
				// chuyển đến trang ADM002
				response.sendRedirect(request.getContextPath() + Constant.URL_LIST_USER);
			}
			// ngược lại
			else {
				// gán danh sách lỗi này lên request để hiển thị trên màn hình
				request.setAttribute("listError", listError);
				request.setAttribute(Constant.LOGGIN_NAME, loginName);
				// chuyển đến trang ADM001
				RequestDispatcher rs = request.getRequestDispatcher(Constant.URL_ADM001);
				rs.forward(request, response);
			}
		} catch (Exception e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Chuyển đến trang system error
			response.sendRedirect(request.getContextPath() + Constant.URL_ERROR + Constant.ERROR_SYSTEM);
		}
	}

}
