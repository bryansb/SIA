package ec.edu.ups.controller.management;

import java.io.IOException;

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
			return "Error";
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
			return "Success";
		} catch (Exception e) {
			return "Error";
		}
	}

	private String readEmployee(HttpServletRequest request) {
		int employeeId;
		Employee employee;
		try {
			employeeId = Integer.parseInt(request.getParameter("use_id"));
			employee = employeeDAO.read(employeeId);
			return "Success";
		} catch (Exception e) {
			return "Error";
		}
	}

	private String deleteEmployee(HttpServletRequest request) {
		int employeeId;
		try {
			employeeId = Integer.parseInt(request.getParameter("use_id"));
			employeeDAO.deleteByID(employeeId);
			return "Success";
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
				output = createEmployee(request);
				break;
			case "update":
				output = updateEmployee(request);
				break;
			case "read":
				output = readEmployee(request);
				break;
			case "delete":
				output = deleteEmployee(request);
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
