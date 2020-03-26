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
import manageuser.logics.TblDetailUserJapanLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.logics.impl.TblDetailUserJapanLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Servlet implementation class EditUserConfirmController
 */
public class EditUserConfirmController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Xử lí khi click confirm từ 3-4
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy ra session phiên hiện tại nếu phiên tồn tại và nếu không có
			// phiên hợp lệ, phiên mới sẽ không được tạo, nó sẽ trả về null.
			HttpSession session = request.getSession(false);
			// Kiểm tra xem có phải từ màn hình 03 sang không
			if (Constant.OK.equals(session.getAttribute(Constant.CHECK_03_04))) {
				// Xóa session
				session.removeAttribute(Constant.CHECK_03_04);
				// Khởi tạo đối tượng TblUserInfor
				TblUserInfor userInfor = new TblUserInfor();
				String key = "";
				// Lấy biến key từ request về
				if (request.getParameter(Constant.KEY) != null) {
					key = request.getParameter(Constant.KEY);
				}
				// Lấy userInfor được lưu trên session
				userInfor = (TblUserInfor) session.getAttribute(Constant.USER_INFOR + key);

				// Khởi tạo các đối tượng để từ group_id có groupName và từ
				// code_Level có nameLevel
				MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
				MstGroup mstGroup = new MstGroup();
				// Gọi hàm getMstGroupById để lấy về một đối tượng mstGroup
				mstGroup = mstGroupLogic.getMstGroupById(userInfor.getGroupId());
				// Set giá trị setGroupName và nameLevel cho userInfor
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
				// set các giá trị lên request
				request.setAttribute(Constant.USER_INFOR, userInfor);
				request.setAttribute(Constant.KEY, key);
				// Chuyển đến trang ADM004
				RequestDispatcher rs = request.getRequestDispatcher(Constant.URL_ADM004);
				rs.forward(request, response);
				// Ngược lại thì trả về Màn hình SyStem error
			} else {
				// CHuyển đến màn hình System error
				response.sendRedirect(request.getContextPath() + Constant.URL_ERROR);
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
	 * Xử lí trường hợp O4 click OK
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "";
		try {
			// Khởi tạo các đối tượng
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			TblDetailUserJapanLogic tblDetailUserJapanLogic = new TblDetailUserJapanLogicImpl();
			// Lấy session về
			HttpSession session = request.getSession();
			// Lấy key trên session
			String key = request.getParameter(Constant.KEY);
			// Lấy đối tượng TblUserInfor trên session
			TblUserInfor userInfor = (TblUserInfor) session.getAttribute(Constant.USER_INFOR + key);
			// Xóa session userInfor
			session.removeAttribute(Constant.USER_INFOR + key);
			// Khởi tạo biến chứa id và email của user
			int userId = userInfor.getUserId();
			String email = userInfor.getEmail();
			//Kiểm tra xem email có bị trùng không và user có tồn tại không
				if ( tblUserLogic.checkExistedUserById(userId) && !tblUserLogic.checkEmail(email, userId) ) {
					// Kiểm tra user có tồn tại trong bảng detail không
					boolean checkDetailById = tblDetailUserJapanLogic.checkExitDetailUserJapan(userInfor.getUserId());
					// thì thực hiện update dữ liệu vào DB
					tblUserLogic.updateUser(userInfor, checkDetailById);
					// Chuyển đến màn hình ADM006 với thông báo thành công
					url = Constant.SUCCESS + Constant.EDIT_SUCCESS;
					// Nếu update thành công
				} else {
					// Nếu email tồn tại hiển thị màn hình lỗi đã tồn tại email
					if (tblUserLogic.checkEmail(email, userId)) {
						url = Constant.URL_ERROR + Constant.ERROR_003_EMAIL;
					}
				}
			
		} catch (Exception e) {
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