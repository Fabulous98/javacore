package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

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
		try {
			listUser(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = common.listAllUsers();
		RequestDispatcher dispatcher;
		String action = request.getServletPath();
		System.out.println(action);
		if (action == "/listUser") {
			request.setAttribute("listUser", listUser);
			dispatcher = request.getRequestDispatcher("listUser.jsp");
		} else {
			request.setAttribute("listUser2", listUser);
			dispatcher = request.getRequestDispatcher("listUser2.jsp");
		}
		
		dispatcher.forward(request, response);
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
