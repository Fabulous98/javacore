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
 * Controller Xử lí khi click confirm từ 3 sang 4 và click Ok ở ADM004
 */
public class EditUserConfirmController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy ra session phiên hiện tại, trả về null nếu không có session
			HttpSession session = request.getSession(false);
			// Kiểm tra có phải từ màn hình 003 sang hay không
			if (Constant.OK.equals(session.getAttribute(Constant.CHECK_MOVE))) {
				
				// Khởi tạo đối tượng TblUserInfor
				TblUserInfor userInfor = new TblUserInfor();
				
				// Khởi tạo biến key
				String key = Constant.DEFAULT_STRING;
				
				// Lấy key từ request về
				if (request.getParameter(Constant.KEY) != null) {
					key = request.getParameter(Constant.KEY);
				}
				
				// Lấy userInfor trên session
				userInfor = (TblUserInfor) session.getAttribute(Constant.USER_INFOR + key);

				// Khởi tạo các đối tượng để lấy groupName từ group_id
				MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
				MstGroup mstGroup = new MstGroup();
				
				// Gọi hàm getMstGroupById để lấy về một đối tượng mstGroup
				mstGroup = mstGroupLogic.getMstGroupById(userInfor.getGroupId());
				
				// Set giá trị groupName cho userInfor
				userInfor.setGroupName(mstGroup.getGroupName());
				
				// Kiểm tra người dùng có chọn codeLevel không
				if (Common.checkCodelevel(userInfor.getCodeLevel())) {
					// Khởi tạo các đối tượng để lấy nameLevel từ codeLevel
					MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
					MstJapan mstJapan = new MstJapan();
					// Lấy đối tượng MstJapan theo codeLevel
					mstJapan = mstJapanLogic.getMstJapanByCodeLevel(userInfor.getCodeLevel());
					// Set nameLevel cho userInfor
					userInfor.setNameLevel(mstJapan.getNameLevel());
				}
				// set các giá trị lên request
				request.setAttribute(Constant.USER_INFOR, userInfor);
				request.setAttribute(Constant.KEY, key);
				// Chuyển đến trang ADM004
				RequestDispatcher rs = request.getRequestDispatcher(Constant.URL_ADM004);
				rs.forward(request, response);
				
				// Nếu không phải từ 003 sang 004 thì chuyển đến SyStem error
			} else {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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
			// Xóa trên session userInfor, originalEmail
			session.removeAttribute(Constant.USER_INFOR + key);
			// Khởi tạo biến chứa id của user
			int userId = userInfor.getUserId();
			// Khởi tạo biến chứa email của user
			String email = userInfor.getEmail();
			//Kiểm tra xem email nếu sửa thì có bị trùng không và user có tồn tại không
				if ( tblUserLogic.checkExistedUserById(userId) && !tblUserLogic.checkExistEmail(email, userId) ) {
					// Kiểm tra user có tồn tại trong bảng detail không
					boolean checkDetail = tblDetailUserJapanLogic.checkExistDetailUserJapan(userInfor.getUserId());
					// thì thực hiện update dữ liệu vào DB
					tblUserLogic.updateUser(userInfor, checkDetail);
					// Chuyển đến màn hình ADM006 với thông báo thành công
					url = Constant.SUCCESS + Constant.EDIT_SUCCESS;
					// Nếu tồn tại user nhưng email đã tồn tại
				} else if(tblUserLogic.checkExistedUserById(userId)) {
					// Thì hiển thị màn hình lỗi đã tồn tại email
						url = Constant.URL_ERROR + Constant.ERROR_003_EMAIL;
					// nếu không tồn tại user ( đã xóa )
				} else {
					url = Constant.URL_ERROR + Constant.ERROR_SYSTEM;
				}
			
		} catch (Exception e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Chuyển đến trang lỗi
			url = Constant.URL_ERROR + Constant.ERROR_SYSTEM;

		}
		// Chuyển đến 1 url
		response.sendRedirect(request.getContextPath() + url);
	}
}