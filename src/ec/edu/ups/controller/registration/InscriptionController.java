package ec.edu.ups.controller.registration;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.StudentDAO;
import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.management.Student;
import ec.edu.ups.entities.offer.Career;
import ec.edu.ups.entities.registration.Inscription;

/**
 * Servlet implementation class InscriptionController
 */
@WebServlet("/InscriptionController")
public class InscriptionController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> InscriptionController";
	private InscriptionDAO inscriptionDAO;
	private StudentDAO studentDAO;
	private CareerDAO careerDAO;
	private String output;
	private Logger logger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionController() {
        super();
        inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
        studentDAO = DAOFactory.getFactory().getStudentDAO();
        careerDAO = DAOFactory.getFactory().getCareerDAO();
        output = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String option;
		
		try {
			option = request.getParameter("option");
			switch (option) {
			case "create":
				output = createInscription(request);
				break;
			case "read":
				request.setAttribute("inscription", readInscription(request));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
			this.output = "Error al buscar una opción";
		}
		request.setAttribute("output", output);
	}
	
	public String createInscription(HttpServletRequest request) {
		int studentId;
		int careerId;
		Student student;
		Career career;
		Inscription inscription;
		
		try {
			studentId = Integer.parseInt(request.getParameter("studentId"));
			careerId = Integer.parseInt(request.getParameter("careerId"));
			student = studentDAO.read(studentId);
			career = careerDAO.read(careerId);
			inscription = new Inscription(new GregorianCalendar(), student, career);
			inscriptionDAO.create(inscription);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":createInscription > " + e.toString();
			this.logger.log(Level.INFO, message);
		}
		return "Error";
	}
	
	public Inscription readInscription(HttpServletRequest request) {
		int inscriptionId;
		Inscription inscription;
		
		try {
			inscriptionId = Integer.parseInt(request.getParameter("inscriptionId"));
			inscription = inscriptionDAO.read(inscriptionId);
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
			this.output = "No se encuentra la inscripción";
			inscription = null;
		}
		return inscription;
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Inscription inscription;
		
		this.doGet(request, response);
		inscription = readInscription(request);
		if (inscription == null) {
			response.getWriter().append(output);
		} else {
			response.getWriter().append(output);
		}
	}
	
}
