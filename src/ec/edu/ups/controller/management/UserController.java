package ec.edu.ups.controller.management;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.UserDAO;
import ec.edu.ups.entities.management.Employee;
import ec.edu.ups.entities.management.User;

@WebServlet("/UserController")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static String ERROR_ROOT = ">>> Error >> GroupController:";
	private Logger logger;
	private UserDAO userDAO;

	public UserController() {
		super();
		userDAO = DAOFactory.getFactory().getUserDAO();
	}

	private String createUser(HttpServletRequest request) {
		User user;
		try {
			user = new Employee();
			user.setAddress(request.getParameter("use_address"));
			user.setDni(request.getParameter("use_dni"));
			user.setEmail(request.getParameter("use_email"));
			user.setFullName(request.getParameter("use_full_name"));
			user.setPassword(request.getParameter("use_password"));
			user.setPhone(request.getParameter("use_phone"));
			user.setType(request.getParameter("use_type").charAt(0));
			userDAO.create(user);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":createUser > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}

	private String updateUser(HttpServletRequest request) {
		int userId;
		User user;
		try {
			userId = Integer.parseInt(request.getParameter("use_id"));
			user = userDAO.read(userId);
			user.setAddress(request.getParameter("use_address"));
			user.setDni(request.getParameter("use_dni"));
			user.setEmail(request.getParameter("use_email"));
			user.setFullName(request.getParameter("use_full_name"));
			user.setPassword(request.getParameter("use_password"));
			user.setPhone(request.getParameter("use_phone"));
			user.setType(request.getParameter("use_type").charAt(0));
			userDAO.update(user);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":updateUser > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}

	private User readUser(HttpServletRequest request) {
		int userId;
		User user;
		try {
			userId = Integer.parseInt(request.getParameter("use_id"));
			user = userDAO.read(userId);
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option;
		String output = "";
		try {
			option = request.getParameter("option");
			switch (option) {
			case "create":
				output = createUser(request);
				break;
			case "update":
				output = updateUser(request);
				break;
			case "read":
				request.setAttribute("user", readUser(request));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
		request.setAttribute("output", output);
	}

	public void doTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user;
		this.doGet(request, response);
		user = readUser(request);
		if(user == null) {
			response.getWriter().append("Error");
		}else {
			response.getWriter().append("Success");
		}
	}
}