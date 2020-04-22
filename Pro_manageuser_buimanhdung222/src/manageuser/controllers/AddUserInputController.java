package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.TblUserInfor;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Servlet implementation class AddUserInputController
 */
public class AddUserInputController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Xử lý khi click vào button Add của ADM002
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			// Khởi tạo đối tượng TblUserInfor
			TblUserInfor userInfor = new TblUserInfor();
			// Set giá trị mặc định cho selecbox
			Common.setDataLogic(request);
			// get giá trị default cho TblUserInfor
			userInfor = getDefaultValue(request);
			// set giá trị userInfor lên request
			request.setAttribute(Constant.USER_INFOR, userInfor);
			// Chuyển đến trang ADM003
			RequestDispatcher rs = request.getRequestDispatcher(Constant.URL_ADM003);
			rs.forward(request, response);
		} catch (Exception e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Chuyển đến trang system Eror
			response.sendRedirect(request.getContextPath() + Constant.URL_ERROR);
		}
	}

	/**
	 * Hàm doPost xử lí khi click xác nhận
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Khởi tạo đối tượng validate để kiểm tra giá trị nhập vào trên
			// request
			ValidateUser validateUser = new ValidateUser();
			// Khởi tạo 1 listError để chứa các lỗi
			List<String> listError = new ArrayList<String>();
			// Lấy session
			HttpSession session = request.getSession();
			// Thêm giá trị vào userInfor
			TblUserInfor userInfor = getDefaultValue(request);
			// Thêm lỗi vào list lỗi
			listError = validateUser.validateUserInfor(userInfor);

			// Nếu không có lỗi xảy ra
			if (listError.isEmpty()) {
				// Khởi tạo key lấy về thời gian hiện tại t
				String key = Common.getKey();
				// set đối tượng userInfor lên session
				session.setAttribute(Constant.USER_INFOR + key, userInfor);
				// Set giá trị check lên session
				session.setAttribute(Constant.CHECK_03_04, Constant.OK);
				// Chuyển đến Url addUserConfirm.do với giá trị key
				response.sendRedirect(request.getContextPath() + Constant.URL_ADD_USER_CONFIRM + "?key=" + key);
				// Nếu xảy ra lỗi
			} else {
				// gán danh sách lỗi này lên request để hiển thị trên màn hình
				request.setAttribute("listError", listError);
				// Set lại các giá trị mặc định cho ADM003
				Common.setDataLogic(request);
				// set đối tượng userInfor lên request
				request.setAttribute(Constant.USER_INFOR, userInfor);
				// chuyển đến trang ADM003
				RequestDispatcher rs = request.getRequestDispatcher(Constant.URL_ADM003);
				rs.forward(request, response);
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
	private TblUserInfor getDefaultValue(HttpServletRequest request) {
		// Khởi tạo đối tượng TblUserInfor
		TblUserInfor userInfor = new TblUserInfor();
		try {
			// Gán giá trị mặc đinh cho các thuộc tính của tblUserInfor
			String loginName = Constant.DEFAULT_STRING;
			String fullName = Constant.DEFAULT_STRING;
			String fullNameKana = Constant.DEFAULT_STRING;
			String email = Constant.DEFAULT_STRING;
			String tel = Constant.DEFAULT_STRING;
			String pass = Constant.DEFAULT_STRING;
			String confirmPass = Constant.DEFAULT_STRING;
			String total = Constant.DEFAULT_STRING;
			String action = request.getParameter(Constant.ACTION);
			// Nếu action là back
			if (Constant.ACTION_BACK.equals(action)) {
				// lấy session
				HttpSession session = request.getSession();
				// Lấy key trên request
				String key = (String) request.getParameter(Constant.KEY);
				// Lấy về 1 đối tượng userInfor
				userInfor = (TblUserInfor) session.getAttribute(Constant.USER_INFOR + key);
				// Nếu action là validate thì thực hiện
			} else if (Constant.ACTION_VALIDATE_003.equals(action)) {
				// Lấy giá trị trên form để thực hiện kiểm tra validate
				loginName = request.getParameter(Constant.LOGGIN_NAME);
				int groupId = Common.convertStringToInt(request.getParameter(Constant.GROUP_ID),
						Constant.DEFAULT_GROUP_ID);
				fullName = request.getParameter(Constant.FULL_NAME);
				fullNameKana = request.getParameter(Constant.FULL_NAME_KANA);
				int yearBirth = Common.convertStringToInt(request.getParameter(Constant.YEAR_BIRTH),
						Constant.DEFAULFT_VALUE_TIME);
				int monthBirth = Common.convertStringToInt(request.getParameter(Constant.MONTH_BIRTH),
						Constant.DEFAULFT_VALUE_TIME);
				int dateBirth = Common.convertStringToInt(request.getParameter(Constant.DATE_BIRTH),
						Constant.DEFAULFT_VALUE_TIME);
				// Chuyển ngày, tháng, năm, vừa lấy được thành chuỗi
				String birthday = Common.convertIntToTime(yearBirth, monthBirth, dateBirth);
				// lấy email,SĐT, password,confirmPass trên request
				email = request.getParameter(Constant.EMAIL);
				tel = request.getParameter(Constant.TEL);
				pass = request.getParameter(Constant.PASS);
				confirmPass = request.getParameter(Constant.CONFIRM_PASS);
				// set giá trị lấy được trên form vào đối tượng tblUser
				userInfor.setLoginName(loginName);
				userInfor.setGroupId(groupId);
				userInfor.setBirthday(birthday);
				// Lấy giá trị codeLevel trên selectbox
				String codeLevel = request.getParameter(Constant.CODE_LEVEL);
				// Nếu chọn trình độ tiếng nhật thì thực hiện
				if (Common.checkCodelevel(codeLevel)) {
					// Lấy ngày tháng năm bắt đầu của trình độ tiếng Nhật
					int yearStart = Common.convertStringToInt(request.getParameter(Constant.YEAR_START),
							Common.getYearNow());
					int monthStart = Common.convertStringToInt(request.getParameter(Constant.MONTH_START),
							Constant.DEFAULFT_VALUE_TIME);
					int dateStart = Common.convertStringToInt(request.getParameter(Constant.DATE_START),
							Constant.DEFAULFT_VALUE_TIME);
					// Chuyển ngày tháng năm thành chuỗi
					String startDate = Common.convertIntToTime(yearStart, monthStart, dateStart);
					// Lấy ngày tháng năm kết thúc
					int yearEnd = Common.convertStringToInt(request.getParameter(Constant.YEAR_END),
							Common.getYearNow());
					int monthEnd = Common.convertStringToInt(request.getParameter(Constant.MONTH_END),
							Constant.DEFAULFT_VALUE_TIME);
					int dateEnd = Common.convertStringToInt(request.getParameter(Constant.DATE_END),
							Constant.DEFAULFT_VALUE_TIME);
					// Chuyển ngày tháng năm thành 1 chuỗi
					String endDate = Common.convertIntToTime(yearEnd, monthEnd, dateEnd);
					total = request.getParameter(Constant.TOTAL);
					// set giá trị vừa lấy được vào userInfor
					userInfor.setCodeLevel(codeLevel);
					userInfor.setStartDay(startDate);
					userInfor.setEndDate(endDate);
					userInfor.setTotal(total);
				}
				// Nếu action khác back
				if (!action.equals(Constant.ACTION_BACK)) {
					// set các giá trị cho userInfor
					userInfor.setLoginName(loginName);
					userInfor.setFullName(fullName);
					userInfor.setFullNameKana(fullNameKana);
					userInfor.setEmail(email);
					userInfor.setTel(tel);
					userInfor.setPass(pass);
					userInfor.setConfirmPass(confirmPass);
					userInfor.setTotal(total);
				}
			}
		} catch (Exception e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Ném ngoại lệ
			throw e;
		}
		// trả về 1 list userInfor
		return userInfor;
	}

}
