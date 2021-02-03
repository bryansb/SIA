package ec.edu.ups.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class All
 */
@WebFilter("/*")
public class All implements Filter {
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String path = ((HttpServletRequest)request).getRequestURI().substring(((HttpServletRequest)request).getContextPath().length());

		if (path.equals("/") || path.equals("/index.html") || path.equals("/index.jsp")
				|| path.equals("/Index")) {
			((HttpServletRequest)request).getSession().setAttribute("isOnIndex", true);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			((HttpServletRequest)request).getSession().setAttribute("isOnIndex", false);
			chain.doFilter(request, response);
		}
	}
}
