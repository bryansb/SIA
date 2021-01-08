package ec.edu.ups.controller.management;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.UserDAO;
import ec.edu.ups.entities.management.User;

@WebServlet("/UserController")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;

	public UserController() {
		super();
		userDAO = DAOFactory.getFactory().getUserDAO();
	}

	private String createUser(HttpServletRequest request) {
		char type;
		User user;
		try {
			user = new User();
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
			return "Error";
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
			return "Success";
		} catch (Exception e) {
			return "Error";
		}
	}

	private String readUser(HttpServletRequest request) {
		int userId;
		User user;
		try {
			userId = Integer.parseInt(request.getParameter("use_id"));
			user = userDAO.read(userId);
			return "Success";
		} catch (Exception e) {
			return "Error";
		}
	}

	private String deleteUser(HttpServletRequest request) {
		int userId;
		User user;
		try {
			userId = Integer.parseInt(request.getParameter("use_id"));
			userDAO.deleteByID(userId);
			return "success";
		}catch(Exception e) {
			return "Error";
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
				output = readUser(request);
				break;
			case "delete":
				output = deleteUser(request);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("output", output);
		response.getWriter().append(output);
	}

	public void doTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}