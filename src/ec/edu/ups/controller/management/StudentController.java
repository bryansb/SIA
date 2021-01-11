package ec.edu.ups.controller.management;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.StudentDAO;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.management.Student;
import ec.edu.ups.entities.registration.Inscription;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static String ERROR_ROOT = ">>> Error >> GroupController:";
	private Logger logger;
	private StudentDAO studentDAO;
	private InscriptionDAO inscriptionDAO;
	
	public StudentController() {
		super();
		studentDAO = DAOFactory.getFactory().getStudentDAO();
		inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
	}
	private String createStudent(HttpServletRequest request) {
		Student student;
		try {
			student = new Student();
			student.setAddress(request.getParameter("use_address"));
			student.setDni(request.getParameter("use_dni"));
			student.setEmail(request.getParameter("use_email"));
			student.setFullName(request.getParameter("use_full_name"));
			student.setPassword(request.getParameter("use_password"));
			student.setPhone(request.getParameter("use_phone"));
			student.setType(request.getParameter("use_type").charAt(0));
			student.setBirthdate(request.getParameter("use_birthdate"));
			student.setGender(request.getParameter("use_gender").charAt(0));
			studentDAO.create(student);
			return "Success";
		}catch(Exception e) {
			String message = ERROR_ROOT + ":createStudent > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}
	
	private String updateStudent(HttpServletRequest request) {
		int studentId;
		Student student;
		try {
			studentId = Integer.parseInt(request.getParameter("use_id"));
			student = studentDAO.read(studentId);
			student.setAddress(request.getParameter("use_address"));
			student.setDni(request.getParameter("use_dni"));
			student.setEmail(request.getParameter("use_email"));
			student.setFullName(request.getParameter("use_full_name"));
			student.setPassword(request.getParameter("use_password"));
			student.setPhone(request.getParameter("use_phone"));
			student.setType(request.getParameter("use_type").charAt(0));
			student.setBirthdate(request.getParameter("use_birthdate"));
			student.setGender(request.getParameter("use_gender").charAt(0));
			studentDAO.update(student);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":updateStudent > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}

	private Student readStudent(HttpServletRequest request) {
		int studentId;
		Student student;
		try {
			studentId = Integer.parseInt(request.getParameter("use_id"));
			student = studentDAO.read(studentId);
			return student;
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
				output = createStudent(request);
				break;
			case "update":
				output = updateStudent(request);
				break;
			case "read":
				request.setAttribute("student", readStudent(request));
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
		Student student;
		this.doGet(request, response);
		student = readStudent(request);
		if (student == null) {
			response.getWriter().append("Error");
		}else {
			response.getWriter().append("Success");
		}
	}
}
