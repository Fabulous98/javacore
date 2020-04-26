package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Constant;

/**
 * Controller xử lý màn hình lỗi
 */
public class ErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy Type lỗi trên request
		String type = request.getParameter(Constant.TYPE);
		// Tạo 1 chuỗi để chứa lỗi
		String errorMessage = MessageErrorProperties.getValueByKey(Constant.SYSTEM_ERROR);
		// Kiêm tra nếu type khác rỗng thì type là lỗi nào
		if (type != null) {
			switch (type) {
			case "ERROR_020":
			case "ERROR_014":
			case "ERROR_013":
			case "ERROR_003_LOGINNAME":
			case "ERROR_003_EMAIL":
				// Thêm vào errorMessage với câu thông báo lỗi tương ứng
				errorMessage = MessageErrorProperties.getValueByKey(type);
				break;
			default:
				// Thêm vào message với câu thông báo SYSTEM_ERROR lỗi hệ thống
				// trong TH người dùng set câu thông báo khác lên request
				errorMessage = MessageErrorProperties.getValueByKey(Constant.ERROR_SYSTEM);
			}
		}
		// Set thông báo lên req để hiển thị lên màn hình
		request.setAttribute("errorMessage", errorMessage);
		// Chuyển đến trang ADM006 để hiển thị thông báo
		request.getRequestDispatcher(Constant.URL_SYS_ERR).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
