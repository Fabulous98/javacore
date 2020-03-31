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
 * Servlet implementation class ListUserController
 */
public class ListUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Xử lí cho màn hình listUser
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy ra session phiên hiện tại nếu phiên tồn tại và nếu không có
			// phiên hợp lệ, phiên mới sẽ không được tạo, nó sẽ trả về null.
			HttpSession session = request.getSession(false);
			// Khởi tạo các đối tượng
			MstGroupLogic mstGroupImpl = new MstGroupLogicImpl();
			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			List<TblUserInfor> listTblUserInfor = new ArrayList<TblUserInfor>();
			List<Integer> listPaging = new ArrayList<Integer>();

			// gắn giá trị Vào listMstGroup
			List<MstGroup> listGroup = mstGroupImpl.getAllMstGroup();

			// Gán tham số mặc định cho TH hiển thị ban đầu
			int offset = Constant.DELAULT_OFFSET;
			int limit = Common.getLimit();
			int groupID = Constant.DEFAULT_GROUP_ID;
			String fullName = Constant.DEFAULT_STRING;
			String sortType = Constant.SORT_DEFAULT;
			String sortByFullName = Constant.SORT_ASC;
			String sortByCodeLevel = Constant.SORT_ASC;
			String sortByEndDate = Constant.SORT_DESC;
			int currentPage = Constant.PAGE_CURRENT_DEFAULT;
			int limitPage = Common.getLimitPage();
			String sortValue = Constant.DEFAULT_STRING;
			// Lấy action
			String action = request.getParameter(Constant.ACTION);
			// nếu action là search, sort, paging
			if (Constant.ACTION_SEARCH.equals(action) || Constant.ACTION_SORT.equals(action)
					|| Constant.ACTION_PAGING.equals(action)) {
				// Lấy tên tìm kiếm nhập trên textbox
				if (request.getParameter(Constant.FULL_NAME) != null) {
					fullName = request.getParameter(Constant.FULL_NAME);
				}
				// Lấy Group ID trên text box
				if (request.getParameter(Constant.GROUP_ID) != null) {
					groupID = Common.convertStringToInt(request.getParameter(Constant.GROUP_ID),
							Constant.NUMBER_DEFAULT);
				}
				// nếu action là sort hoặc paging
				if (Constant.ACTION_SORT.equals(action) || Constant.ACTION_PAGING.equals(action)) {
					// Lấy hạng mục cần sort để biết thứ tự sắp xếp ưu tiên
					if (request.getParameter(Constant.SORT_TYPE) != null) {
						sortType = request.getParameter(Constant.SORT_TYPE);
					}
					// Nếu action là sort
					if (Constant.ACTION_SORT.equals(action)) {
						// Lấy giá trị sortValue trên request là ASC hay DESC
						if (request.getParameter(Constant.SORT_VALUE) != null) {
							sortValue = request.getParameter(Constant.SORT_VALUE);
						}
						switch (sortType) {
						// type sort bằng full name
						case Constant.TYPE_FULL_NAME:
							sortByFullName = sortValue;
							break;
						// type sort bằng codelevel
						case Constant.TYPE_CODE_LEVEL:
							sortByCodeLevel = sortValue;
							break;
						// type sort bằng EndDay
						case Constant.TYPE_END_DATE:
							sortByEndDate = sortValue;
							break;
						default:
							break;
						}
						// nếu action là paging
					} else if (Constant.ACTION_PAGING.equals(action)) {
						// Lấy các giá trị sắp xếp trên request về để hiển thị
						// đúng trình tự sắp xếp
						sortByFullName = request.getParameter(Constant.SORT_BY_FULL_NAME);
						sortByCodeLevel = request.getParameter(Constant.SORT_BY_CODE_LEVEL);
						sortType = request.getParameter(Constant.SORT_TYPE);
					}
					sortType = request.getParameter(Constant.SORT_TYPE);
					// Lấy giá trị current page hiện tại trên request để
					// hiển thị đúng trang
					currentPage = Common.convertStringToInt(request.getParameter(Constant.CURRENT_PAGE),
							Constant.PAGE_CURRENT_DEFAULT);
				}
				// Nếu action là back thì thực hiện
			} else if (Constant.ACTION_BACK.equals(action)) {
				// Lấy các điều kiện search và điều kiện sort từ sesion về để hiển thị
				fullName = (String) session.getAttribute(Constant.FULL_NAME);
				groupID = (int) session.getAttribute(Constant.GROUP_ID);
				sortType = (String) session.getAttribute(Constant.SORT_TYPE);
				sortByFullName = (String) session.getAttribute(Constant.SORT_BY_FULL_NAME);
				sortByCodeLevel = (String) session.getAttribute(Constant.SORT_BY_CODE_LEVEL);
				sortByEndDate = (String) session.getAttribute(Constant.SORT_BY_END_DATE);
				currentPage = (int) session.getAttribute(Constant.CURRENT_PAGE);
			}
			// Lấy user thỏa mãn điều kiện để tính getTotalPage
			int totalUser = tblUserLogicImpl.getTotalUser(groupID, fullName);
			// gán tổng số page ban đầu = 0
			int totalPage = Constant.NUMBER_DEFAULT;
			// nếu totalUser >0
			if (totalUser > 0) {
				// tính tổng số page
				totalPage = Common.getTotalPage(totalUser, limit);
				// Nếu số page hiện tại lớn hơn tổng số page
				if (currentPage > totalPage) {
					// Số page hiện tại bằng tổng số page
					currentPage = totalPage;
				}
				// Lấy vị trí data hiện tại
				offset = Common.getOffset(currentPage, limit);
				// Tính list paggings
				listPaging = Common.getListPaging(totalUser, limit, currentPage);
				// set giá trị cho list listTblUserInfors
				listTblUserInfor = tblUserLogicImpl.getListUsers(offset, limit, groupID, fullName, sortType,
						sortByFullName, sortByCodeLevel, sortByEndDate);

			} else {
				// Thông báo lỗi
				String message = MessageProperties.getValueByKey("MSG_005");
				// set thông báo lỗi lên
				request.setAttribute("message", message);
			}
			// Set giá trị lên request để hiển thị cho ADM004
			request.setAttribute("listGroup", listGroup);
			request.setAttribute("listTblUserInfor", listTblUserInfor);
			request.setAttribute("listPaging", listPaging);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("limitPage", limitPage);

			// set các giá trị lên session cho TH back
			session.setAttribute(Constant.FULL_NAME, fullName);
			session.setAttribute(Constant.GROUP_ID, groupID);
			session.setAttribute(Constant.SORT_TYPE, sortType);
			session.setAttribute(Constant.SORT_BY_FULL_NAME, sortByFullName);
			session.setAttribute(Constant.SORT_BY_CODE_LEVEL, sortByCodeLevel);
			session.setAttribute(Constant.SORT_BY_END_DATE, sortByEndDate);
			session.setAttribute(Constant.CURRENT_PAGE, currentPage);
			// Chuyến đến tran ADM002
			RequestDispatcher rs = request.getRequestDispatcher(Constant.URL_ADM002);
			rs.forward(request, response);
		} catch (Exception e) {
			// Ghi log
			System.out.println("Class: " + this.getClass().getName() + ", Method: "
					+ e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
			// Chuyển đến trang system Eror
			response.sendRedirect(request.getContextPath() + Constant.URL_ERROR);
		}
	}
}