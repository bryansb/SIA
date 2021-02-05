package ec.edu.ups.controller.management;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.controller.utils.SiaTool;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.StudentDAO;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.management.Student;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static String ERROR_ROOT = ">>> Error >> GroupController:";
	private Logger logger = Logger.getLogger(StudentController.class.getName());
	private final StudentDAO studentDAO;
	private final InscriptionDAO inscriptionDAO;
	private static final String URL = "/JSP/private/management/student.jsp";
	private String noticeClass;
	private String output;
	
	public StudentController() {
		super();
		studentDAO = DAOFactory.getFactory().getStudentDAO();
		inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
	}
	
	public String getOutput() {
		return output;
	}


	public String getNoticeClass() {
		return noticeClass;
	}
	
	private void createStudent(HttpServletRequest request) {
		Student student;
		System.out.println("ENTRA CREATE");
		try {
			String dni = request.getParameter("dni");
			/*
			if (validDni(dni)) {
				noticeClass = "bg-danger";
				return;
			}
			String email = request.getParameter("email");
			if (validEmail(email)) {
				output = "Email ya existe";
				noticeClass = "bg-danger";
				return;
			}
			*/
			String email = request.getParameter("email");
			student = new Student();
			String fullName = request.getParameter("fullName");
			String address = request.getParameter("address");
			String birthdate = request.getParameter("birthdate");
			String phone = request.getParameter("phone");
			char gender = request.getParameter("gender").charAt(0);
			String password = request.getParameter("password");
			student.setDni(dni);
			student.setEmail(email);
			student.setFullName(fullName);
			student.setAddress(address);
			student.setBirthdate(birthdate);
			student.setPhone(phone);
			student.setGender(gender);
			student.setPassword(password);
			student.setType('S');
			studentDAO.create(student);
			System.out.println(student.getFullName());
		}catch(Exception e) {
			System.out.println("ERROR");
			String message = ERROR_ROOT + ":createStudent > " +e.toString();
			this.logger.log(Level.INFO, message);
		}
	}
	
	private String updateStudent(HttpServletRequest request) {
		int studentId;
		Student student;
		try {
			studentId = Integer.parseInt(request.getParameter("id"));
			student = studentDAO.read(studentId);
			student.setAddress(request.getParameter("address"));
			student.setDni(request.getParameter("dni"));
			student.setEmail(request.getParameter("email"));
			student.setFullName(request.getParameter("fullName"));
			student.setPassword(request.getParameter("password"));
			student.setPhone(request.getParameter("phone"));
			student.setType(request.getParameter("type").charAt(0));
			student.setBirthdate(request.getParameter("birthdate"));
			student.setGender(request.getParameter("gender").charAt(0));
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
			studentId = Integer.parseInt(request.getParameter("studentId"));
			student = studentDAO.read(studentId);
		} catch (Exception e) {
			return null;
		}
		return student;
	}
	
	private List<Student> listStudent(HttpServletRequest request) {
		List<Student> students;
		System.out.println("Entra al listar1");
		try {
			System.out.println("Entra al listar2");
			students = studentDAO.find(null, 0, 0);
		}catch(Exception e) {
			students = null;

			System.out.println("Entra al listar3");
		}	
		return students;
	}
	
	private String deleteStudent(HttpServletRequest request) {
		int studentId;
		Student student;
		System.out.println("ELiminacion Estudiante1");
		try {
			System.out.println("ELiminacion Estudiante2");
			studentId = Integer.parseInt(request.getParameter("studentId"));
			student = studentDAO.read(studentId);
			if (student.isDeleted()) {
				student.setDeleted(false);
				studentDAO.update(student);
				return "No eliminado";
			}else {
				student.setDeleted(true);
				studentDAO.update(student);
				return "Eliminado";
			}
		}catch(Exception e) {
			System.out.println("ELiminacion Estudiante2");
			String message = ERROR_ROOT + ":deleteStudent > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			option = request.getParameter("option");
			if (option == null) {
				option = "none";
			}
			switch (option) {
			case "create":
				createStudent(request);
				updateRequest(request, response);
				break;
			case "update":
				output = updateStudent(request);
				updateRequest(request, response);
				break;
			case "read":
				request.setAttribute("student", readStudent(request));
				break;
			case "list":
				request.setAttribute("students", listStudent(request));
				updateRequest(request, response);
				break;
			case "delete":
				output = deleteStudent(request);
				updateRequest(request, response);
				break;
			default:
				updateRequest(request, response);
				break;
			}
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
		request.setAttribute("output", output);
		//updateRequest(request, response);
	}
	
	private void updateRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("output", output);
		request.setAttribute("noticeClass", noticeClass);
		request.setAttribute("students", listStudent(request));
		request.setAttribute("readStudent", null);
		getServletContext().getRequestDispatcher(URL).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
		//redirectProcess(request, response);
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
	
	public boolean validDni(String dni) {
		if (!SiaTool.validateDNI(dni)) {
			output = "Cedula es incorrecta";
			return true;
		}
		
		if (inscriptionDAO.isStudentCreated(dni)) {
			output = "Cedula ya existe";
			return true;
		}
		return false;
	}
	
	public boolean validEmail (String email) {
		return inscriptionDAO.isEmailCreated(email);
	}
	
	private void redirectProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("output", output);
			request.setAttribute("noticeClass", noticeClass);
			request.setAttribute("students", listStudent(request));
			request.setAttribute("readStudent", null);
			getServletContext().getRequestDispatcher(URL).forward(request, response);
		} catch (ServletException | IOException e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			logger.log(Level.INFO, errorMessage);
		}
	}
	
}
