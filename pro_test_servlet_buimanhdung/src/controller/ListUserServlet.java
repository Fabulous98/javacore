package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Common;
import common.User;

/**
 * Servlet implementation class HelloWorld
 */
//@WebServlet("/listUser")
public class ListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Common common;

	/**
	 * Phương thức init() để lấy jdbcURL, jdbcUsername, jdbcPassword từ web.xml và
	 * tạo biến common
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		common = new Common(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Chạy listUser() để lấy dữ liệu gửi cho listUser.jsp
		try {
			listUser(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * Hàm này lấy listUser từ Database rồi setAttribute cho listUser để gửi
	 * request, respond đến listUser.jsp
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 * @throws ServletException
	 */
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String searchValue = (request.getParameter("searchValue") == null) ? "" : request.getParameter("searchValue").trim();

		// Lấy tất cả User vào listUser
		RequestDispatcher dispatcher;
		String action = request.getServletPath();
		HttpSession session = request.getSession();

		List<User> listUser = common.searchUsers(searchValue);
		session.setAttribute("listUser", listUser);
		session.setAttribute("keySearch", searchValue);
		dispatcher = request.getRequestDispatcher("listUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
