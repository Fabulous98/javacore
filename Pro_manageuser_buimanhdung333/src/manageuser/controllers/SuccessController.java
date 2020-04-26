package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.properties.MessageProperties;
import manageuser.utils.Constant;

/**
 * Controller xử lý màn hình thông báo thành công
 */
public class SuccessController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Lấy Type trên request
		String type = request.getParameter(Constant.TYPE);
		// Tạo 1 chuỗi để nội dung thông báo
		String message = "";
		// Thêm vào message câu thông báo ứng với type thành công (MESSAGE001)
		message = MessageProperties.getValueByKey(type);
		// set giá trị message lên request
		request.setAttribute("message", message);
		// Chuyển đến trang ADM006
		request.getRequestDispatcher(Constant.URL_ADM006).forward(request, response);
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
