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
		// Lấy Type trên request
		String type = request.getParameter(Constant.TYPE);
		// Tạo 1 chuỗi để chứa lỗi
		String message = "";
		// Thêm vào message với câu thông báo MESSAGE001
		message = MessageProperties.getValueByKey(type);
		// set giá trị lên request
		request.setAttribute("message", message);
		// Chuyển đến trang ADM006 để hiển thị thông báo
		request.getRequestDispatcher(Constant.URL_ADM006).forward(request, response);
	}
}
