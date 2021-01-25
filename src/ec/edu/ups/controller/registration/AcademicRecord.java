package ec.edu.ups.controller.registration;

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
import ec.edu.ups.dao.registration.EnrollmentDAO;
import ec.edu.ups.entities.management.Student;
import ec.edu.ups.entities.registration.Enrollment;

/**
 * Servlet implementation class AcademicRecord
 */
@WebServlet("/AcademicRecord")
public class AcademicRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> AcademicRecord";
	private static final String URL = "/JSP/private/registration/student/studentAcademicRecord.jsp";
	private EnrollmentDAO enrollmentDAO;
	private StudentDAO studentDAO;
	private List<Enrollment> enrollmentList;
	private Student student;
	private Logger logger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcademicRecord() {
        super();
        enrollmentDAO = DAOFactory.getFactory().getEnrollmentDAO();
        studentDAO = DAOFactory.getFactory().getStudentDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get id from session;
		try {
			int studentId = 1;
			request.setCharacterEncoding("UTF-8");
			student = studentDAO.read(studentId);
			enrollmentList = enrollmentDAO.getAcademicRecordByStudentId(studentId);
			request.setAttribute("student", student);
			request.setAttribute("enrollmentList", enrollmentList);
			redirectProcess(request, response);
		} catch (Exception e) {
			logger.log(Level.INFO, ERROR_ROOT + e.getMessage());
		}
	}
	
	private void redirectProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			getServletContext().getRequestDispatcher(URL).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.log(Level.INFO, ERROR_ROOT + e.getMessage());
		}
	}

}
