/**
 * Copyright(C) 2019 Luvina Software
 * LoginFilter.java, Dec 27, 2019, MDung
 */
package manageuser.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * @author MDung
 *
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * Hàm xử lí logic login
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			// Lấy về session
			HttpSession httpSession = req.getSession();
			// Khởi tạo biến checkLogin kiểm tra user đã đăng nhập hay chưa
			boolean checkLogin = Common.checkLogin(httpSession);
			// Khởi tạo biến contextPath
			String contextPath = req.getContextPath() + "/";
			// Khởi tạo biến loginUrl
			String urlLogin = req.getContextPath() + "/login.do";
			// Khởi tạo biến checkLoginRequest để kiểm tra xem đường dẫn hiện
			// tại có phải là đường dẫn mặc định hoặc là đường dẫn đến servlet
			// login.do hay không
			boolean checkLoginRequest = req.getRequestURI().equals(contextPath) || req.getRequestURI().equals(urlLogin);
			// Nếu đã login và đường dẫn là login
			if (checkLogin && checkLoginRequest) {
				// Chuyển đến màn hình ADM002
				resp.sendRedirect(req.getContextPath() + Constant.URL_LIST_USER);
				// Nếu checkLogin = true hoặc checkLoginRequest = true thì cho
				// qua
			} else if (checkLogin || checkLoginRequest) {
				chain.doFilter(request, response);
				// Ngược lại chuyển đến màn hình login
			} else {
				resp.sendRedirect(contextPath);
			}
		} catch (Exception e) {
			// <!-- Start fix bug ID 70 – ThoaDT 2019/12/25 -->
			System.out.println("LogFilter - doFilter " + e.getMessage());
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			// Chuyển đến trang system error
			resp.sendRedirect(req.getContextPath() + Constant.URL_ERROR);
			// <!-- End fix bug ID 73 – ThoaDT 2019/12/25 -->
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
