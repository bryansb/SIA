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
import ec.edu.ups.dao.registration.EnrollmentDAO;
import ec.edu.ups.entities.management.Student;
import ec.edu.ups.entities.registration.Enrollment;

/**
 * Servlet implementation class EnrollmentReceipt
 */
@WebServlet("/EnrollmentReceipt")
public class EnrollmentReceipt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> EnrollmentReceipt > ";
	private static final String URL = "/JSP/private/registration/student/enrollmentReceipt.jsp";
	private static final String URL_PDF = "/JSP/private/utils/pdfPages/enrollmentReceiptPDF.jsp";
	private static final Logger LOGGER = Logger.getLogger(EnrollmentReceipt.class.getName());
	private static final String CHARACTER_ENCODING = "UTF-8";
	private final EnrollmentDAO enrollmentDAO;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollmentReceipt() {
        super();
        enrollmentDAO = DAOFactory.getFactory().getEnrollmentDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	try {
    		response.setCharacterEncoding(CHARACTER_ENCODING);
    		request.setCharacterEncoding(CHARACTER_ENCODING);
    		String option = request.getParameter("option");
    		if (option != null && option.equals("download")) {
    			downloadEnrollmentReceipt(request, response);
    		}
    		redirectProcess(request, response);
    	} catch (Exception e) {
    		String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
    
    private void downloadEnrollmentReceipt(HttpServletRequest request, 
    		HttpServletResponse response) {
    	try {
    		int enrollmentId = Integer.parseInt(request.getParameter("enrollmentId"));
    		Enrollment enrollment = enrollmentDAO.read(enrollmentId);
    		request.setAttribute("enrollment", enrollment);
			getServletContext().getRequestDispatcher(URL_PDF).forward(request, response);
		} catch (ServletException | IOException e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}

	private void redirectProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			int studentId = ((Student) request.getSession().getAttribute("user")).getId();
			List<Enrollment> enrollmentList = enrollmentDAO.getEnrollmentByStudentId(studentId);
			request.setAttribute("enrollmentList", enrollmentList);
			getServletContext().getRequestDispatcher(URL).forward(request, response);
		} catch (ServletException | IOException e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}

}
