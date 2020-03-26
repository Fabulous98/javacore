package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.entities.TblUserInfor;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.MessageProperties;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Servlet implementation class ViewUserController
 */
public class ViewUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Khởi tạo đối tượng tblLogic
		// Để kiểm tra id tồn tại trong DB chưa
		// Để gọi hàm lấy 1 đối tượng userInfor theo ID
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		// tạo biến userId để lấy id trên Request
		int userId = Common.convertStringToInt(request.getParameter(Constant.USER_ID), Constant.NUMBER_DEFAULT);
		// Khởi tạo đối tượng userInfor
		TblUserInfor userInfor = new TblUserInfor();
		try {
			// Nếu id của user lớn hơn 0 và tồn tại trong DB thì thực hiện
			if (userId > 0 && tblUserLogic.checkExistedUserById(userId)) {
				// Thực hiện lấy thông tin của userInfor theo ID trong DB
				userInfor = tblUserLogic.getUserInforById(userId);
				// lấy câu thông báo msg004
				String msg004 = MessageProperties.getValueByKey("MSG_004");
				// set userId lên request
				request.setAttribute(Constant.USER_ID, userId);
				// Set giá trị của userInfor lên request
				request.setAttribute(Constant.USER_INFOR, userInfor);
				// set thông báo xác nhận xóa
				request.setAttribute("msg004", msg004);
				// Chuyển đến màn hình ADM005
				RequestDispatcher rs = request.getRequestDispatcher(Constant.URL_ADM005);
				rs.forward(request, response);
			} else {
				// chuyển đến trang lỗi với câu thông báo 13
				response.sendRedirect(request.getContextPath() + Constant.URL_ERROR + Constant.ER013);
			}
		} catch (Exception e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Chuyển đến trang system Eror
			response.sendRedirect(request.getContextPath() + Constant.URL_ERROR);
		}
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
