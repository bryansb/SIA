package ec.edu.ups.controller.management;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.StudentDAO;
import ec.edu.ups.entities.management.Student;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;
	public StudentController() {
		super();
		studentDAO = DAOFactory.getFactory().getStudentDAO();
	}
	private String createStudent(HttpServletRequest request) {
		Student student;
		SimpleDateFormat birthdate;
		try {
			student = new Student();
			birthdate = new SimpleDateFormat("dd/MM/yyyy");
			student.setAddress(request.getParameter("use_address"));
			student.setDni(request.getParameter("use_dni"));
			student.setEmail(request.getParameter("use_email"));
			student.setFullName(request.getParameter("use_full_name"));
			student.setPassword(request.getParameter("use_password"));
			student.setPhone(request.getParameter("use_phone"));
			student.setType(request.getParameter("use_type").charAt(0));
			student.setBirthdate(birthdate.parse(request.getParameter("use_birthdate")));
			student.setGender(request.getParameter("use_gender").charAt(0));
			studentDAO.create(student);
			return "Success";
		}catch(Exception e) {
			return "Error";
		}
	}
	
	private String updateStudent(HttpServletRequest request) {
		int studentId;
		Student student;
		SimpleDateFormat birthdate;
		try {
			birthdate = new SimpleDateFormat("dd/MM/yyyy");
			studentId = Integer.parseInt(request.getParameter("use_id"));
			student = studentDAO.read(studentId);
			student.setAddress(request.getParameter("use_address"));
			student.setDni(request.getParameter("use_dni"));
			student.setEmail(request.getParameter("use_email"));
			student.setFullName(request.getParameter("use_full_name"));
			student.setPassword(request.getParameter("use_password"));
			student.setPhone(request.getParameter("use_phone"));
			student.setType(request.getParameter("use_type").charAt(0));
			student.setBirthdate(birthdate.parse(request.getParameter("use_birthdate")));
			student.setGender(request.getParameter("use_gender").charAt(0));
			return "Success";
		} catch (Exception e) {
			return "Error";
		}
	}

	private String readStudent(HttpServletRequest request) {
		int studentId;
		Student student;
		try {
			studentId = Integer.parseInt(request.getParameter("use_id"));
			student = studentDAO.read(studentId);
			return "Success";
		} catch (Exception e) {
			return "Error";
		}
	}

	private String deleteStudent(HttpServletRequest request) {
		int studentId;
		try {
			studentId = Integer.parseInt(request.getParameter("use_id"));
			studentDAO.deleteByID(studentId);
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
				output = createStudent(request);
				break;
			case "update":
				output = updateStudent(request);
				break;
			case "read":
				output = readStudent(request);
				break;
			case "delete":
				output = deleteStudent(request);
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
