package ec.edu.ups.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class All
 */
@WebFilter("/*")
public class All implements Filter {
	
	private static final List<String> EXCEPT_SERVLET = new ArrayList<>();
	private static final String INDEX_ID = "/index.jsp";
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String path = ((HttpServletRequest)request).getRequestURI().substring(((HttpServletRequest)request).getContextPath().length());
		try {
			if (path.equals("/") || path.equals("/index.html") || path.equals(INDEX_ID)
					|| path.equals("/Index")) {
				((HttpServletRequest)request).getSession().setAttribute("isOnIndex", true);
				request.getRequestDispatcher(INDEX_ID).forward(request, response);
			} else {
				((HttpServletRequest)request).getSession().setAttribute("isOnIndex", false);
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			((HttpServletResponse)response).sendRedirect("/JSP/public/error/error_404.jsp");
		}
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		EXCEPT_SERVLET.add("/Login");
		EXCEPT_SERVLET.add("/Logout");
		EXCEPT_SERVLET.add("/");
	}
}
