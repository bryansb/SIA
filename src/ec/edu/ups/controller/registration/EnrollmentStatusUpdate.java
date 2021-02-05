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
import ec.edu.ups.entities.registration.Enrollment;

/**
 * Servlet implementation class EnrollmentStatusUpdate
 */
@WebServlet("/EnrollmentStatusUpdate")
public class EnrollmentStatusUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> EnrollmentReceipt > ";
	private static final String URL = "/JSP/private/registration/secretary/enrollmentStatusUpdate.jsp";
	private static final Logger LOGGER = Logger.getLogger(EnrollmentStatusUpdate.class.getName());
	private static final String CHARACTER_ENCODING = "UTF-8";
	private final EnrollmentDAO enrollmentDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollmentStatusUpdate() {
        super();
        enrollmentDAO = DAOFactory.getFactory().getEnrollmentDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
    		response.setCharacterEncoding(CHARACTER_ENCODING);
    		request.setCharacterEncoding(CHARACTER_ENCODING);
    		String option = request.getParameter("option");
    		if (option != null && option.equals("update")) {
    			updateEnrollmentStatus(request);
    		}
    		redirectProcess(request, response);
    	} catch (Exception e) {
    		String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
	
	private void updateEnrollmentStatus(HttpServletRequest request) {
    	try {
			int enrollmentId = Integer.parseInt(request.getParameter("enrollmentId"));
			char status = request.getParameter("status").charAt(0);
			Enrollment enrollment = enrollmentDAO.read(enrollmentId);
			enrollment.setStatus(status);
			enrollmentDAO.update(enrollment);
			
		} catch (Exception e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
	
	private void redirectProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			setStudentEnrollmentList(request);
			getServletContext().getRequestDispatcher(URL).forward(request, response);
		} catch (ServletException | IOException e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
	
	private void setStudentEnrollmentList(HttpServletRequest request) {
		try {
			String dni = request.getParameter("dni");
			if (dni == null) {
				return;
			}
			List<Enrollment> enrollmentList = enrollmentDAO.getEnrollmentByStudentDni(dni);
			request.setAttribute("enrollmentList", enrollmentList);
		} catch (Exception e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}

}
