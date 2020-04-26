package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.MstGroup;
import manageuser.entities.MstJapan;
import manageuser.entities.TblUserInfor;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.MstJapanLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller xử lý màn hình ADM004 hiển thị thông tin user muốn thêm
 */
public class AddUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy ra session phiên hiện tại nếu có còn không trả về null
			HttpSession session = request.getSession(false);

			// Kiểm tra xem có phải từ 03 qua hay không
			if (Constant.OK.equals(session.getAttribute(Constant.CHECK_MOVE))) {

				// Khởi tạo đối tượng TblUserInfor
				TblUserInfor userInfor = new TblUserInfor();
				String key = Constant.DEFAULT_STRING;

				// Lấy key từ request
				if (request.getParameter(Constant.KEY) != null) {
					key = request.getParameter(Constant.KEY);
				}

				// Lấy userInfor lưu trên session
				userInfor = (TblUserInfor) session.getAttribute(Constant.USER_INFOR + key);

				// Khởi tạo các đối tượng MstGroupLogic, mstGroup
				MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
				MstGroup mstGroup = new MstGroup();

				// Gọi hàm getMstGroupById để lấy về một đối tượng mstGroup
				mstGroup = mstGroupLogic.getMstGroupById(userInfor.getGroupId());

				// Set giá trị setGroupName cho userInfor
				userInfor.setGroupName(mstGroup.getGroupName());

				// Kiểm tra người dùng có chọn codeLevel không
				if (Common.checkCodelevel(userInfor.getCodeLevel())) {
					MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
					MstJapan mstJapan = new MstJapan();

					// Gọi hàm getMstJapanByCodeLevel để lấy về một đối tượng
					// mstJapan
					mstJapan = mstJapanLogic.getMstJapanByCodeLevel(userInfor.getCodeLevel());
					userInfor.setNameLevel(mstJapan.getNameLevel());
				}

				// set giá trị của đối tượng userInfor lên request để hiển thị ở view
				request.setAttribute(Constant.USER_INFOR, userInfor);
				request.setAttribute(Constant.KEY, key);

				// Chuyển đến trang ADM004
				RequestDispatcher rs = request.getRequestDispatcher(Constant.URL_ADM004);
				rs.forward(request, response);

				// Ngược lại thì trả về Màn hình SyStem error
			} else {
				// CHuyển đến màn hình System error
				response.sendRedirect(request.getContextPath() + Constant.URL_ERROR + Constant.ERROR_SYSTEM);
			}
		} catch (Exception e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Chuyển đến trang system Eror
			response.sendRedirect(request.getContextPath() + Constant.URL_ERROR + Constant.ERROR_SYSTEM);
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Tạo biến lưu url
		String url = Constant.DEFAULT_STRING;
		try {
			// Khởi tạo đối tượng TblUserLogic
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			// Lấy ra session nếu tồn tại
			HttpSession session = request.getSession(false);
			// Lấy key từ request
			String key = request.getParameter(Constant.KEY);
			// Lấy đối tượng TblUserInfor trên session
			TblUserInfor userInfor = (TblUserInfor) session.getAttribute(Constant.USER_INFOR + key);

			// Xóa userInfor trên session
			session.removeAttribute(Constant.USER_INFOR + key);

			// Trường hợp user cần thêm chưa tồn tại trong DB và không bị trùng email
			if (!tblUserLogic.checkLoginName(userInfor.getLoginName())
					&& !tblUserLogic.checkEmail(userInfor.getEmail())) {
				// thì thực hiện insert dữ liệu vào DB
				tblUserLogic.createUser(userInfor);
				// chuyển đến controller success
				url = Constant.SUCCESS + Constant.INSERT_SUCCESS;
				// Trường hợp tên đăng nhập hoặc email tồn tại
				// gán trang báo lỗi cho url
			} else if (tblUserLogic.checkLoginName(userInfor.getLoginName())) {
				url = Constant.URL_ERROR + Constant.ERROR_001_LOGINNAME;
			} else {
				url = Constant.URL_ERROR + Constant.ERROR_003_EMAIL;
			}

		} catch (Exception e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// gán trang báo lỗi cho url
			url = Constant.URL_ERROR + Constant.ERROR_SYSTEM;
		}
		// cuối cùng redirect tới url
		response.sendRedirect(request.getContextPath() + url);
	}

}
