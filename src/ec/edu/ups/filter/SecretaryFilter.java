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
import ec.edu.ups.entities.management.Employee;

/**
 * Servlet Filter implementation class SecretaryFilter
 */
@WebFilter("/*")
public class SecretaryFilter implements Filter {
	
	private final List<String> loginSecretary = Configuration.getLoginSecretary();
	private static final String SIA_ID = "/SIA/";
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			String path = ((HttpServletRequest)request).getRequestURI().substring(((HttpServletRequest)request).getContextPath().length());
			if (loginSecretary.contains(path)) {
				Object user = ((HttpServletRequest)request).getSession(false).getAttribute("user");
				if (user == null) {
					((HttpServletResponse)response).sendRedirect(SIA_ID);
					return;			
				} else {
					Employee secretary = (Employee) user;
					if (secretary.getType() != 'C') {
						((HttpServletResponse)response).sendRedirect(SIA_ID);
						return;
					}
				}
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			((HttpServletResponse)response).sendRedirect(SIA_ID);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
