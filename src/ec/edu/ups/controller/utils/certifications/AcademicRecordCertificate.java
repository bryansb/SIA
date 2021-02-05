package ec.edu.ups.controller.utils.certifications;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.registration.Inscription;

/**
 * Servlet implementation class AcademicRecordCertificate
 */
@WebServlet("/AcademicRecordCertificate")
public class AcademicRecordCertificate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> EnrollmentReceipt > ";
	private static final String URL = "/JSP/private/registration/secretary/studentAcademicRecord.jsp";
	private static final String URL_PDF = "/JSP/private/utils/pdfPages/studentAcademicRecordPDF.jsp";
	private static final Logger LOGGER = Logger.getLogger(AcademicRecordCertificate.class.getName());
	private static final String CHARACTER_ENCODING = "UTF-8";
	private final InscriptionDAO inscriptionDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcademicRecordCertificate() {
        super();
        inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
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
    		if (option != null && option.equals("download")) {
    			downloadAcademicRecordCertificate(request, response);
    		} else {
    			redirectProcess(request, response);
    		}
    	} catch (Exception e) {
    		String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}

	private void downloadAcademicRecordCertificate(HttpServletRequest request, 
    		HttpServletResponse response) {
    	try {
			int inscriptionId = Integer.parseInt(request.getParameter("inscriptionId"));
			Inscription inscription = inscriptionDAO.getAcademicRecordByInscriptionId(inscriptionId);
			LocalDate currentDate = LocalDate.now();
			request.setAttribute("currentDate", currentDate);
			request.setAttribute("inscription", inscription);
			getServletContext().getRequestDispatcher(URL_PDF).forward(request, response);
		} catch (ServletException | IOException e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
	
	private void redirectProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			setStudentCareerList(request);
			getServletContext().getRequestDispatcher(URL).forward(request, response);
		} catch (ServletException | IOException e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
	
	private void setStudentCareerList(HttpServletRequest request) {
		try {
			String dni = request.getParameter("dni");
			if (dni == null) {
				return;
			}
			List<Inscription> inscriptionList = inscriptionDAO.getInscriptionByStudentDni(dni);
			request.setAttribute("inscriptionList", inscriptionList);
		} catch (Exception e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}

}
