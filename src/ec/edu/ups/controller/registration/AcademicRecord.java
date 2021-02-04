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
	private static final Logger LOGGER = Logger.getLogger(AcademicRecord.class.getName());
	private final EnrollmentDAO enrollmentDAO;
	private final StudentDAO studentDAO;
	private List<Enrollment> enrollmentList;
	private Student student;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcademicRecord() {
        super();
        enrollmentDAO = DAOFactory.getFactory().getEnrollmentDAO();
        studentDAO = DAOFactory.getFactory().getStudentDAO();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	enrollmentList = null;
    	student = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int studentId = ((Student) request.getSession().getAttribute("user")).getId();
			request.setCharacterEncoding("UTF-8");
			student = studentDAO.read(studentId);
			enrollmentList = enrollmentDAO.getAcademicRecordByStudentId(studentId);
			request.setAttribute("student", student);
			request.setAttribute("enrollmentList", enrollmentList);
			redirectProcess(request, response);
		} catch (Exception e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
	
	private void redirectProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			getServletContext().getRequestDispatcher(URL).forward(request, response);
		} catch (ServletException | IOException e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}

}
