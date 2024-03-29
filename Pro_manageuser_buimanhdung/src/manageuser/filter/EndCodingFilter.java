/**
 * Copyright(C) 2019 Luvina Software
 * EndCodingFilter.java, Dec 27, 2019, MDung
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

import manageuser.utils.Constant;

/**
 * @author MDung
 *
 */
public class EndCodingFilter  implements Filter{
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletRequest resp = (HttpServletRequest) request;
		//set utf8
		req.setCharacterEncoding(Constant.UTF8);
		resp.setCharacterEncoding(Constant.UTF8);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
