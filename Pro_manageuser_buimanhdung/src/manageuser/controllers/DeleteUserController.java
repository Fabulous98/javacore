package manageuser.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.entities.TblUser;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Servlet implementation class DeleteUserController
 */
public class DeleteUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Xử lí khi click OK của alert
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Khởi tạo chuỗi URl để chứa đường dẫn đến các trang
		String url = "";
		try {
			// Khởi tạo các đối tượng
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			TblUser tblUser = new TblUser();
			// tạo biến userId để lấy id trên Request
			int userId = Common.convertStringToInt(request.getParameter(Constant.USER_ID), Constant.NUMBER_DEFAULT);
			//gọi hàm getTblUser xem user có tồn tại không
			tblUser = tblUserLogic.getTblUserByUserId(userId);
			if (tblUser == null) {
				url = Constant.URL_ERROR + Constant.ER014;
			} else {
				int rule = tblUser.getRule();
				// Kiểm tra nếu rule không phải admin
				if (rule != Constant.RULE_ADMIN) {
					// Xóa 1 user theo ID lấy được trên request
					tblUserLogic.deleteUser(userId);
					// Nếu xóa thành công thì chuyển đến successController
					url = Constant.SUCCESS + Constant.DELETE_SUCCESS;
					// kiểm tra nếu rule là 0 thì thực hiện
				} else {
					// Chuyển đến trang lỗi
					url = Constant.URL_ERROR + Constant.ER020;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Chuyển đến trang lỗi
			url = Constant.URL_ERROR;
		}
		// Chuyển đến 1 url
		response.sendRedirect(request.getContextPath() + url);
	}
}