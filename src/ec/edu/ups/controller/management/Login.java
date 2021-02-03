package ec.edu.ups.controller.management;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.UserDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Login.class.getName());
	
	private static UserDAO userDAO = DAOFactory.getFactory().getUserDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @throws  
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String message = "";
		try {
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			String key = request.getParameter("use_email");
			String password = request.getParameter("use_password");
			Object user = userDAO.login(key, password);
			if (user == null) {
				message = "Credenciales incorrectas&e_notice_error";
			} else {
				setUserTypeSession(request, user);
				message = " &e_notice_sucess";
			}
		} catch (Exception e) {
			message = "Credenciales incorrectas&e_notice_error";
			LOGGER.log(Level.INFO, e.toString());
		}
		setMessageToRequest(response, message);
	}
	
	private void setMessageToRequest(HttpServletResponse response, String message) {
		try {
			response.getWriter().append(message);
		} catch (IOException e) {
			LOGGER.log(Level.INFO, e.toString());
		}
	}
	
	private void setUserTypeSession(HttpServletRequest request, Object user) {
		HttpSession session = request.getSession(true);
		session.setAttribute("isLogged", true);
		session.setAttribute("user", user);
	}

}
