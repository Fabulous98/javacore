package manageuser.controllers;

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

import manageuser.entities.MstGroup;
import manageuser.entities.TblUserInfor;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.MessageProperties;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller xử lý màn hình ADM002 chức năng hiển thị thông tin user
 */
public class ListUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy ra session phiên hiện tại nếu nếu không có trả về null.
			HttpSession session = request.getSession(false);
			// Khởi tạo các đối tượng MstGroupLogic, TblUserLogic
			MstGroupLogic mstGroupImpl = new MstGroupLogicImpl();
			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			// Khởi tạo các list đối tượng kiểu TblUserInfor, Integer
			List<TblUserInfor> listTblUserInfor = new ArrayList<TblUserInfor>();
			List<Integer> listPaging = new ArrayList<Integer>();

			// lấy tất cả MstGroup gán vào listMstGroup
			List<MstGroup> listGroup = mstGroupImpl.getAllMstGroup();

			// Set tham số mặc định trường hợp hiển thị ban đầu
			int offset = Constant.DELAULT_OFFSET;
			int limit = Common.getLimit();
			int groupID = Constant.DEFAULT_GROUP_ID;
			int currentPage = Constant.PAGE_CURRENT_DEFAULT;
			int limitPage = Common.getLimitPage();
			
			// Set giá trị mặc định cho ngày tháng năm sinh, ngày = tháng = 1, năm = năm hiện tại
			int dateBirth = Constant.DEFAULFT_VALUE_TIME;
			int monthBirth = Constant.DEFAULFT_VALUE_TIME;
			int yearBirthDefault = Common.getYearNow();
			int yearBirth = yearBirthDefault;
			
			// tạo biến để lưu trường birthday, mặc định là 0
			int year = 0;
			int month = 0;
			int date = 0;
			
			// tạo biến để lưu trường checkbox, mặc định là 0
			String byYear = Constant.DEFAULT_CHECK;
			String byMonth = Constant.DEFAULT_CHECK;
			String byDate = Constant.DEFAULT_CHECK;
			
			// tạo biến để lưu các trường fullname, và sort
			String fullName = Constant.DEFAULT_STRING;
			String sort_type = Constant.SORT_DEFAULT;
			String sortByFullName = Constant.SORT_ASC;
			String sortByCodeLevel = Constant.SORT_ASC;
			String sortByEndDate = Constant.SORT_DESC;
			String sortValue = Constant.DEFAULT_STRING;
			
			// Lấy action từ request
			String action = request.getParameter(Constant.ACTION);
			// Trường hợp action là search hoặc sort hoặc paging
			if (Constant.ACTION_SEARCH.equals(action) || Constant.ACTION_SORT.equals(action)
					|| Constant.ACTION_PAGING.equals(action)) {
				// Nếu tên tìm kiếm nhập trên textbox khác null thì gán cho fullName
				if (request.getParameter(Constant.FULL_NAME) != null) {
					fullName = request.getParameter(Constant.FULL_NAME);
				}
				// Lấy thông tin trên select box
				if (request.getParameter(Constant.GROUP_ID) != null) {
					groupID = Common.convertStringToInt(request.getParameter(Constant.GROUP_ID),
							Constant.NUMBER_DEFAULT);
				}
				
				// Lấy giá trị các trường ngày tháng năm từ request, nếu không có mặc định bằng 1 với ngày, tháng, bằng năm hiện tại với năm
				dateBirth = Common.convertStringToInt(request.getParameter(Constant.DATE_BIRTH),Constant.DEFAULFT_VALUE_TIME);
				monthBirth = Common.convertStringToInt(request.getParameter(Constant.MONTH_BIRTH),Constant.DEFAULFT_VALUE_TIME);
				yearBirth = Common.convertStringToInt(request.getParameter(Constant.YEAR_BIRTH),yearBirthDefault);
				
				// Lấy thông tin Year, Month, Date trên request nếu checkbox tương ứng checked
				// Giá trị mặc định checkbok là 0
				if (Common.convertStringToInt(request.getParameter(Constant.BY_YEAR), 0) != 0) {
					year = yearBirth;
					byYear = request.getParameter(Constant.BY_YEAR);
				}
				if (Common.convertStringToInt(request.getParameter(Constant.BY_MONTH), 0) != 0) {
					month = monthBirth;
					byMonth = request.getParameter(Constant.BY_MONTH);
				}
				if (Common.convertStringToInt(request.getParameter(Constant.BY_DATE), 0) != 0) {
					date = dateBirth;
					byDate = request.getParameter(Constant.BY_DATE);
				}
				// Trường hợp action là sort hoặc paging
				if (Constant.ACTION_SORT.equals(action) || Constant.ACTION_PAGING.equals(action)) {
					// Lấy hạng mục cần sort để thiết lập thứ tự ưu tiên
					if (request.getParameter(Constant.SORT_TYPE) != null) {
						sort_type = request.getParameter(Constant.SORT_TYPE);
					}
					// Nếu action là sort
					if (Constant.ACTION_SORT.equals(action)) {
						// Lấy giá trị sortValue trên request xem là ASC hay DESC
						if (request.getParameter(Constant.SORT_VALUE) != null) {
							sortValue = request.getParameter(Constant.SORT_VALUE);
						}
						switch (sort_type) {
						// TH sort bằng full name
						case Constant.TYPE_FULL_NAME:
							sortByFullName = sortValue;
							break;
						// TH sort bằng codelevel
						case Constant.TYPE_CODE_LEVEL:
							sortByCodeLevel = sortValue;
							break;
						// TH sort bằng EndDay
						case Constant.TYPE_END_DATE:
							sortByEndDate = sortValue;
							break;
						default:
							break;
						}
						// Nếu action là paging
					} else if (Constant.ACTION_PAGING.equals(action)) {
						// Lấy các giá trị đã sắp xếp trên request về để hiển thị
						sortByFullName = request.getParameter(Constant.SORT_BY_FULL_NAME);
						sortByCodeLevel = request.getParameter(Constant.SORT_BY_CODE_LEVEL);
						sort_type = request.getParameter(Constant.SORT_TYPE);
					}
					sort_type = request.getParameter(Constant.SORT_TYPE);
					
					// Lấy giá trị current page hiện tại trên request
					currentPage = Common.convertStringToInt(request.getParameter(Constant.CURRENT_PAGE),
							Constant.PAGE_CURRENT_DEFAULT);
				}
				// Nếu action là back thì thực hiện
			} else if (Constant.ACTION_BACK.equals(action)) {
				// Lấy các điều kiện search và điều kiện sort từ sesion về để hiển thị
				fullName = (String) session.getAttribute(Constant.FULL_NAME);
				groupID = (int) session.getAttribute(Constant.GROUP_ID);
				dateBirth = (int) session.getAttribute(Constant.DATE_BIRTH);
				monthBirth = (int) session.getAttribute(Constant.MONTH_BIRTH);
				yearBirth = (int) session.getAttribute(Constant.YEAR_BIRTH);
				
				// Lấy giá trị của checkbox ngày, tháng, năm sinh trên session
				byDate = (String) session.getAttribute(Constant.BY_DATE);
				byMonth = (String) session.getAttribute(Constant.BY_MONTH);
				byYear = (String) session.getAttribute(Constant.BY_YEAR);
				
				// Lấy các giá trị liên quan đến trường sort trên session
				sort_type = (String) session.getAttribute(Constant.SORT_TYPE);
				sortByFullName = (String) session.getAttribute(Constant.SORT_BY_FULL_NAME);
				sortByCodeLevel = (String) session.getAttribute(Constant.SORT_BY_CODE_LEVEL);
				sortByEndDate = (String) session.getAttribute(Constant.SORT_BY_END_DATE);
				// Lấy chỉ số của page hiện tại trên session
				currentPage = (int) session.getAttribute(Constant.CURRENT_PAGE);
			}
			// Lấy user thỏa mãn điều kiện để tính TotalPage
			int totalUser = tblUserLogicImpl.getTotalUser(groupID, fullName, year, month, date);
			// gán tổng số page ban đầu = 0
			int totalPage = Constant.NUMBER_DEFAULT;
			// nếu totalUser >0
			if (totalUser > 0) {
				// tính tổng số page
				totalPage = Common.getTotalPage(totalUser, limit);
				// Trường hợp số page hiện tại lớn hơn tổng số page
				if (currentPage > totalPage) {
					// Trường hợp Số page hiện tại bằng tổng số page
					currentPage = totalPage;
				}
				// Lấy vị trí data hiện tại
				offset = Common.getOffset(currentPage, limit);
				// Tính list paggings
				listPaging = Common.getListPaging(totalUser, limit, currentPage);
				
				// set giá trị cho list listTblUserInfors
				listTblUserInfor = tblUserLogicImpl.getListUsers(offset, limit, groupID, fullName, sort_type,
						sortByFullName, sortByCodeLevel, sortByEndDate, year, month, date);

			} else {
				// Thông báo lỗi
				String message = MessageProperties.getValueByKey(Constant.MSG005);
				// set thông báo lỗi lên
				request.setAttribute("message", message);
			}
			// Set giá trị lên request để hiển thị ADM004
			request.setAttribute("listGroup", listGroup);
			request.setAttribute("listTblUserInfor", listTblUserInfor);
			
			// Set các giá trị liên quan đến paging
			request.setAttribute("listPaging", listPaging);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("limitPage", limitPage);
			
			// Khởi tạo listDate để gắn lên request
			setDataLogic(request);
			
			// set các giá trị liên quan tính năng search lên session
			session.setAttribute(Constant.FULL_NAME, fullName);
			session.setAttribute(Constant.GROUP_ID, groupID);
			session.setAttribute(Constant.DATE_BIRTH, dateBirth);
			session.setAttribute(Constant.MONTH_BIRTH, monthBirth);
			session.setAttribute(Constant.YEAR_BIRTH, yearBirth);
			session.setAttribute(Constant.BY_DATE, byDate);
			session.setAttribute(Constant.BY_MONTH, byMonth);
			session.setAttribute(Constant.BY_YEAR, byYear);
			
			// set các giá trị liên quan đến sort lên session
			session.setAttribute(Constant.SORT_TYPE, sort_type);
			session.setAttribute(Constant.SORT_BY_FULL_NAME, sortByFullName);
			session.setAttribute(Constant.SORT_BY_CODE_LEVEL, sortByCodeLevel);
			session.setAttribute(Constant.SORT_BY_END_DATE, sortByEndDate);
			// set giá trị page hiện tại lên session
			session.setAttribute(Constant.CURRENT_PAGE, currentPage);
			// Chuyến đến màn hình ADM002
			RequestDispatcher rs = request.getRequestDispatcher(Constant.URL_ADM002);
			rs.forward(request, response);
		} catch (Exception e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Reidirect đến trang system Eror
			response.sendRedirect(request.getContextPath() + Constant.URL_ERROR + Constant.ERROR_SYSTEM);
		}
	}
	
	/**
	 * Thực hiện set giá trị cho hạng mục selectbox birthday ở màn hình ADM002
	 * 
	 * @param request : set giá trị lên request
	 *            
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void setDataLogic(HttpServletRequest request){
		try { 
			
			// Khởi tạo listDate để gắn lên request
			List<Integer> listDate = Common.getListDay();
			// Khởi tạo listMonth để gắn lên request
			List<Integer> listMonth = Common.getListMonth();
			// Khởi tạo listYear để gắn lên request
			List<Integer> listYear = Common.getListYear(Constant.YEARS_START, Common.getYearNow() + 1);

			// Set các list lên request để hiển thị chúng trên selectbox của ADM002
			request.setAttribute("listDate", listDate);
			request.setAttribute("listMonth", listMonth);
			request.setAttribute("listYear", listYear);
		} catch (Exception e) {
			System.out.println("Class: Common" + " Method: " + e.getStackTrace()[0].getMethodName() + ", Error: "
					+ e.getMessage());
			// Ném ngoại
			throw e;
		}
	}
}