package manageuser.controllers;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.TblUserInfor;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Servlet implementation class EditUserInputController
 */
public class EditUserInputController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Xử lí khi click từ 05-03
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Khởi tạo đối tượng tblUserLogic
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		// tạo biến userId để lấy id trên Request
		int userId = Common.convertStringToInt(request.getParameter(Constant.USER_ID), Constant.NUMBER_DEFAULT);
		// Khởi tạo đối tượng userInfor
		TblUserInfor userInfor = new TblUserInfor();
		try {
			// Nếu id của user lớn hơn 0 và tồn tại trong DB thì thực hiện
			if (tblUserLogic.checkExistedUserById(userId)) {
				// set các giá trị cho selectbox
				Common.setDataLogic(request);
				// get giá trị default cho TblUserInfor
				userInfor = getDefaultValue(request);
				// Set giá trị của userInfor lên request
				request.setAttribute(Constant.USER_INFOR, userInfor);
				request.setAttribute(Constant.USER_ID, userId);
				// Chuyển đến trang ADM003
				RequestDispatcher rs = request.getRequestDispatcher(Constant.URL_ADM003);
				rs.forward(request, response);
			} else {
				// chuyển đến trang lỗi
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
	 * Xử lí cho TH click confirm
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			// Khởi tạo đối tượng validate
			ValidateUser validateUser = new ValidateUser();
			// Khởi tạo session
			HttpSession session = request.getSession();
			// Khởi tạo 1 listError để chứa các lỗi
			List<String> listError = new ArrayList<String>();
			// Thêm giá trị vào userInfor
			TblUserInfor userInfor = new TblUserInfor();
			// Lấy Id của user trên request
			int userId = Common.convertStringToInt(request.getParameter(Constant.USER_ID), Constant.NUMBER_DEFAULT);
			// Kiểm tra user có tồn tại không
			if (tblUserLogic.checkExistedUserById(userId)) {
				// Gắn giá trị mặc định cho userInfor
				userInfor = getDefaultValue(request);
				
				// thực hiện kiểm tra thông tin nhập vào có bị lỗi không
				listError = validateUser.validateUserInfor(userInfor);
				// Nếu không có lỗi
				if (listError.isEmpty()) {
					// Khởi tạo key lấy về thời gian hiện tại
					String key = Common.getKey();
					// set đối tượng userInfor lên session
					session.setAttribute(Constant.USER_INFOR + key, userInfor);
					// Set giá trị check lên sessin
					session.setAttribute(Constant.CHECK_03_04, Constant.OK);
					// Chuyển đến Url addUserConfirm.do với giá trị key
					response.sendRedirect(request.getContextPath() + Constant.URL_EDIT_USER_CONFIRM + "?key=" + key);
					// Ngược lại nếu có lỗi xảy ra
				} else {
					// set lại giá trị mặc định cho các selectbox
					Common.setDataLogic(request);
					// chuyển đến trang ADM003
					// gán danh sách lỗi này lên request để hiển thị trên màn
					request.setAttribute("listError", listError);
					request.setAttribute(Constant.USER_INFOR, userInfor);
					// Chuyển đến trang ADM003 và hiển thị thông báo lỗi
					RequestDispatcher rs = request.getRequestDispatcher(Constant.URL_ADM003);
					rs.forward(request, response);
				}
				// Nếu Id không tồn tại thì chuyển đến trang lỗi và hiển thị
				// thông báo lỗi
			} else {
				// Chuyển đến trang System_Error
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
	 * Get giá trị default cho màn hình ADM003
	 * 
	 * @param request
	 *            lấy dữ liệu về để gán cho TblUserInfor
	 * @return một TblUserInfor
	 */
	private TblUserInfor getDefaultValue(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		// Khởi tạo các đối tượng
		TblUserInfor userInfor = new TblUserInfor();
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		try {
			// Lấy userId trên request
			int userId = Common.convertStringToInt(request.getParameter(Constant.USER_ID), Constant.NUMBER_DEFAULT);
			//set userId cho userInfor
			userInfor.setUserId(userId);
			// Lấy action trên request
			String action = request.getParameter(Constant.ACTION);
			// Nếu action là back thì thực hiện
			if (Constant.ACTION_BACK.equals(action)) {
				// Tạo 1 session
				HttpSession session = request.getSession();
				// Lấy key trên request
				String key = (String) request.getParameter(Constant.KEY);
				// Lấy về 1 đối tượng userInfor
				userInfor = (TblUserInfor) session.getAttribute(Constant.USER_INFOR + key);
				// Nếu action là validate thì thực hiện
			} else if (Constant.ACTION_VALIDATE_003.equals(action)) {
				// lấy ngày tháng năm trên select box
				int yearBirth = Common.convertStringToInt(request.getParameter(Constant.YEAR_BIRTH),
						Constant.DEFAULFT_VALUE_TIME);
				int monthBirth = Common.convertStringToInt(request.getParameter(Constant.MONTH_BIRTH),
						Constant.DEFAULFT_VALUE_TIME);
				int dateBirth = Common.convertStringToInt(request.getParameter(Constant.DATE_BIRTH),
						Constant.DEFAULFT_VALUE_TIME);
				// set giá trị lấy được trên form vào đối tượng tblUser
				userInfor.setLoginName(request.getParameter(Constant.LOGGIN_NAME));
				userInfor.setGroupId(
						Common.convertStringToInt(request.getParameter(Constant.GROUP_ID), Constant.DEFAULT_GROUP_ID));
				userInfor.setFullName(request.getParameter(Constant.FULL_NAME));
				userInfor.setFullNameKana(request.getParameter(Constant.FULL_NAME_KANA));
				userInfor.setBirthday(Common.convertIntToTime(yearBirth, monthBirth, dateBirth));
				userInfor.setEmail(request.getParameter(Constant.EMAIL));
				userInfor.setTel(request.getParameter(Constant.TEL));
				// Lấy giá trị codeLevel trên selectbox
				String codeLevel = request.getParameter(Constant.CODE_LEVEL);
				if (Common.checkCodelevel(codeLevel)) {
					// Lấy ngày tháng năm bắt đầu của trình độ tiếng Nhật
					int yearStart = Common.convertStringToInt(request.getParameter(Constant.YEAR_START),
							Common.getYearNow());
					int monthStart = Common.convertStringToInt(request.getParameter(Constant.MONTH_START),
							Constant.DEFAULFT_VALUE_TIME);
					int dateStart = Common.convertStringToInt(request.getParameter(Constant.DATE_START),
							Constant.DEFAULFT_VALUE_TIME);
					// Lấy ngày tháng năm kết thúc
					int yearEnd = Common.convertStringToInt(request.getParameter(Constant.YEAR_END),
							Common.getYearNow());
					int monthEnd = Common.convertStringToInt(request.getParameter(Constant.MONTH_END),
							Constant.DEFAULFT_VALUE_TIME);
					int dateEnd = Common.convertStringToInt(request.getParameter(Constant.DATE_END),
							Constant.DEFAULFT_VALUE_TIME);
					// set giá trị vừa lấy được vào userInfor
					userInfor.setCodeLevel(codeLevel);
					userInfor.setStartDay(Common.convertIntToTime(yearStart, monthStart, dateStart));
					userInfor.setEndDate(Common.convertIntToTime(yearEnd, monthEnd, dateEnd));
					userInfor.setTotal(request.getParameter(Constant.TOTAL));
				}
				// Ngược lại nếu action là default thì thực hiện
				// set giá trị default là 1 đối tượng userInfor lấy trong DB
			} else {
				// Lấy về đối tượng userInfor theo id
				userInfor = tblUserLogic.getUserInforById(userId);
			}

		} catch (ClassNotFoundException | SQLException e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		// trả về một đối tượng userInfor
		return userInfor;

	}
}
