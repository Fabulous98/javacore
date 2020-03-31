package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.properties.MessageProperties;
import manageuser.utils.Constant;

/**
 * Servlet implementation class SuccessController
 */
public class SuccessController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Xử lí thông báo khi insert hay update thành công
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
