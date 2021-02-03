package ec.edu.ups.controller.registration;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.controller.utils.SiaTool;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.StudentDAO;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.management.Student;

public class StudentController {
	
	private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());
	
	private final StudentDAO studentDAO;
	private final InscriptionDAO inscriptionDAO;
	
	private String noticeClass;
	private String output;
	
	
	
	public StudentController() {
		studentDAO = DAOFactory.getFactory().getStudentDAO();
		inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
	}
	
	public void createStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String dni = request.getParameter("dni");
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
			String fullName = request.getParameter("fullName");
			String address = request.getParameter("address");
			String birthdate = request.getParameter("birthdate");
			String phone = request.getParameter("phone");
			char gender = request.getParameter("gender").charAt(0);
			String password = SiaTool.getSha256(dni);
			Student student = new Student();
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
			output = "Estudiante Creado";
			noticeClass = "bg-success";
		} catch (Exception e) {
			output = "No se pudo crear al Estudiante";
			noticeClass = "bg-danger";
			LOGGER.log(Level.INFO, e.toString());
		}
	}
	
	public Student searchStudentByDni(HttpServletRequest request) {
		Student student;
		try {
			String dni = request.getParameter("dni");
			student = inscriptionDAO.getStudentByDni(dni);
		} catch (Exception e) {
			student = null;
		}
		return student;
	}
	
	public boolean validDni(String dni) {
		if (!SiaTool.validateDNI(dni)) {
			output = "Cédula es incorrecta";
			return true;
		}
		
		if (inscriptionDAO.isStudentCreated(dni)) {
			output = "Cédula ya existe";
			return true;
		}
		return false;
	}
	
	public boolean validEmail (String email) {
		return inscriptionDAO.isEmailCreated(email);
	}

	public String getNoticeClass() {
		return noticeClass;
	}

	public String getOutput() {
		return output;
	}
}
