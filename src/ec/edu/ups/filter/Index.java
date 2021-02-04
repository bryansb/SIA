package ec.edu.ups.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


/**
 * Servlet Filter implementation class Index
 */
@WebFilter({"/", "/Index", "/index.html", "/index.jsp"})
public class Index implements Filter {

	private static final Logger LOGGER = Logger.getLogger(Index.class.getName());
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		try {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
    	} catch (Exception e) {
    		LOGGER.log(Level.INFO, e.toString());
		}
		
	}
}
