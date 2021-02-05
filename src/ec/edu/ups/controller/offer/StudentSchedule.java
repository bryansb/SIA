package ec.edu.ups.controller.offer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.registration.EnrollmentDAO;
import ec.edu.ups.entities.management.Student;
import ec.edu.ups.entities.registration.Enrollment;

/**
 * Servlet implementation class StudentSchedule
 */
@WebServlet("/StudentSchedule")
public class StudentSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ERROR_ROOT = ">>> Error >> StudentSchedule:";
	private Logger logger;
	
	private EnrollmentDAO enrollmentDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentSchedule() {
        super();
        logger = Logger.getLogger(StudentSchedule.class.getName());
        enrollmentDAO = DAOFactory.getFactory().getEnrollmentDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		updateRequest(request, response);
	}
	
	private void updateRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			Student student = (Student) request.getSession().getAttribute("user");
			Enrollment enrollment = enrollmentDAO.getEnrollmentCurrentSchedule(student.getId());
			request.setAttribute("enrollment", enrollment);
			RequestDispatcher view;
			view = request.getRequestDispatcher("/JSP/private/offer/studentSchedule.jsp");
			view.forward(request, response);
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.toString());
		}
	}
}
