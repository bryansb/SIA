package ec.edu.ups.filter;

import java.io.IOException;
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

import ec.edu.ups.controller.utils.Configuration;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
	
	private List<String> loginUrl;
	private final Configuration configuration = new Configuration(); 
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			String path = ((HttpServletRequest)request).getRequestURI().substring(((HttpServletRequest)request).getContextPath().length());
			if (loginUrl.contains(path)) {
				Object user = ((HttpServletRequest)request).getSession(false).getAttribute("user");
				if (user == null) {
					((HttpServletResponse)response).sendRedirect("/SIA/");
					return;			}
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			((HttpServletResponse)response).sendRedirect("/SIA/");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		loginUrl = configuration.getLoginServlet();
				
	}

}
