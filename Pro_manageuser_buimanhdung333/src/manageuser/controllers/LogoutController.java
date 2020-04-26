package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Constant;

/**
 * Controller xử lý tính năng logout
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Hủy session hiện tại
			request.getSession().invalidate();
			// Redirect sang màn hình login
			response.sendRedirect(request.getContextPath() + Constant.URL_LOGIN);
		} catch (Exception e) {
			// Ghi log
			System.out.println("Class: " + e.getMessage());
			// Chuyển đến màn hình lỗi
			response.sendRedirect(request.getContextPath() + Constant.URL_SYS_ERR);
		}
	}
}
