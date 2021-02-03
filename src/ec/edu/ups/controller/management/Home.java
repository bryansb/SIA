package ec.edu.ups.controller.management;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.entities.management.User;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Home.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	try {
    		request.setCharacterEncoding("UTF-8");
    		response.setCharacterEncoding("UTF-8");
    		Object user = getUserFromSession(request, response);
    		setGenericInformation(request, user);
    		redirectUserToHome(request, response, user);
    	} catch (Exception e) {
    		LOGGER.log(Level.INFO, e.toString());
		}
	}
	
	private void redirectUserToHome(HttpServletRequest request, 
			HttpServletResponse response, Object user) throws IOException, ServletException {
		String url = "";
		switch (user.getClass().getSimpleName()) {
		case "Student":
			url = "/JSP/private/home/studentHome.jsp";
			break;
		case "Teacher":
			url = "/JSP/private/home/teacherHome.jsp";
			break;
		case "Employee":
			url = "/JSP/private/home/secretaryHome.jsp";
			break;
		case "Admin":
			url = "/JSP/private/home/adminHome.jsp";
			break;
		default:
			response.sendRedirect("/SIA/Logout");
			break;
		}
		dispatch(request, response, url);
	}
	
	private void dispatch(HttpServletRequest request, 
			HttpServletResponse response, String url) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
	private Object getUserFromSession(HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			Object user = request.getSession().getAttribute("user");
			if (user == null) {
				response.sendRedirect("/SIA/Logout");
			}
			return user;
		} catch (Exception e) {
			String errorMessage = " >> getUserFromSession > " + e.toString();
			LOGGER.log(Level.INFO, errorMessage);
			return null;
		}
	}
	
	private void setGenericInformation(HttpServletRequest request, Object user) {
		User userLocal = (User) user;
		request.setAttribute("fullName", userLocal.getFullName());
	}

}
