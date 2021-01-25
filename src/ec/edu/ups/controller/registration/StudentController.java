package ec.edu.ups.controller.registration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.StudentDAO;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.management.Student;

public class StudentController {
	private StudentDAO studentDAO;
	private InscriptionDAO inscriptionDAO;
	
	public StudentController() {
		studentDAO = DAOFactory.getFactory().getStudentDAO();
		inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
	}
	
	public void createStudent(HttpServletRequest request, HttpServletResponse response, 
			String noticeClass, String output) {
		try {
			Student student = new Student();
			
		} catch (Exception e) {
			
		}
	}
	
	public Student searchStudentByDni(HttpServletRequest request, 
			String noticeClass, String output) {
		Student student;
		try {
			String dni = request.getParameter("dni");
			student = inscriptionDAO.getStudentByDni(dni);
		} catch (Exception e) {
			student = null;
		}
		return student;
	}
}
