package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
<<<<<<< HEAD
@WebServlet("/Bye")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloWorld() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String Uname=request.getParameter("Uname");
		String Upass=request.getParameter("Upass");
		String result="";	
		if(Uname.equals("Luvina") && Upass.equals("Luvina")){
			result="Welcome";}
		else{
			result="Invalid username or password";}
		out.println("<html><head><title>Login Servlet</title></head><body>");
		out.println("<h1>Hello World!</h1>");
		out.println("<h2>" + result + "</h2>");
=======
//@WebServlet("/Bye")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		out.println("<html><head><title>Hello World</title></head><body>");
		out.println("<h1>Hello World</h1>");
		out.println("<p>Welcome to JSP course</p>");
>>>>>>> 253e7d072463836af46e224053629b3f0435e5c3
		out.println("</body></html>");
	}

	/**
<<<<<<< HEAD
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
=======
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
>>>>>>> 253e7d072463836af46e224053629b3f0435e5c3
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
