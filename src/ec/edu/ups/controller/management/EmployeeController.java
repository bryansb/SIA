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
import ec.edu.ups.dao.management.EmployeeDAO;
import ec.edu.ups.entities.management.Employee;

@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	private static String ERROR_ROOT = ">>> Error >> GroupController:";
	private Logger logger;
	private EmployeeDAO employeeDAO;

	public EmployeeController() {
		super();
		employeeDAO = DAOFactory.getFactory().getEmployeeDAO();
	}
	
	private String createEmployee(HttpServletRequest request) {
		Employee employee;
		try {
			employee = new Employee();
			employee.setAddress(request.getParameter("use_address"));
			employee.setDni(request.getParameter("use_dni"));
			employee.setEmail(request.getParameter("use_email"));
			employee.setFullName(request.getParameter("use_full_name"));
			employee.setPassword(request.getParameter("use_password"));
			employee.setPhone(request.getParameter("use_phone"));
			employee.setType(request.getParameter("use_type").charAt(0));
			employee.setSalary(Double.parseDouble(request.getParameter("use_salary")));
			employeeDAO.create(employee);
			return "Success";
		}catch(Exception e) {
			e.printStackTrace();
			String message = ERROR_ROOT + ":createEmployee > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}
	
	private String updateEmployee(HttpServletRequest request) {
		int employeeId;
		Employee employee;
		try {
			employeeId = Integer.parseInt(request.getParameter("use_id"));
			employee = employeeDAO.read(employeeId);
			employee.setAddress(request.getParameter("use_address"));
			employee.setDni(request.getParameter("use_dni"));
			employee.setEmail(request.getParameter("use_email"));
			employee.setFullName(request.getParameter("use_full_name"));
			employee.setPassword(request.getParameter("use_password"));
			employee.setPhone(request.getParameter("use_phone"));
			employee.setType(request.getParameter("use_type").charAt(0));
			employee.setSalary(Double.parseDouble(request.getParameter("use_salary")));
			employeeDAO.update(employee);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":updateEmployee > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}

	private Employee readEmployee(HttpServletRequest request) {
		int employeeId;
		Employee employee;
		try {
			employeeId = Integer.parseInt(request.getParameter("use_id"));
			employee = employeeDAO.read(employeeId);
			return employee;
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
				output = createEmployee(request);
				break;
			case "update":
				output = updateEmployee(request);
				break;
			case "read":
				request.setAttribute("employee", readEmployee(request));
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
		Employee employee;
		this.doGet(request, response);
		employee = readEmployee(request);
		if(employee == null) {
			response.getWriter().append("Error");
		}else {
			response.getWriter().append("Success");
		}
	}
}
