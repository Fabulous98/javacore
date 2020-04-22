package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Constant;

/**
 * Servlet implementation class ErrorController
 */
public class ErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Hàm khởi tạo
	 */
	public ErrorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy Type trên request
		String type = request.getParameter(Constant.TYPE);
		// Tạo 1 chuỗi để chứa lỗi
		String erroMessage = MessageErrorProperties.getValueByKey(Constant.SYSTEM_ERROR);
		// Kiêm tra nếu type bằng insertSuccess
		if (type != null) {
			switch (type) {
			case Constant.ER020:
			case Constant.ER014:
			case Constant.ER013:
			case Constant.ERROR_003_LOGIN:
			case Constant.ERROR_003_EMAIL:
				// Thêm vào message với câu thông báo lỗi tương ứng
				erroMessage = MessageErrorProperties.getValueByKey(type);
				break;
			default:
				// Thêm vào message với câu thông báo SYSTEM_ERROR lỗi hệ thống
				// trong TH người dùng set câu thông báo khác lên request
				erroMessage = MessageErrorProperties.getValueByKey(Constant.SYSTEM_ERROR);
			}
		}
		// Set thông báo lên req để hiển thị lên màn hình
		request.setAttribute("erroMessage", erroMessage);
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
