package ec.edu.ups.controller.registration;

import java.io.IOException;
import java.util.GregorianCalendar;
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
	private static final String URL = "/JSP/private/registration/secretary/studentInscription.jsp";
	private InscriptionDAO inscriptionDAO;
	private StudentDAO studentDAO;
	private CareerDAO careerDAO;
	private StudentController studentController;
	
	private Logger logger;
	private String noticeClass;
	private String output;
	private String option;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionController() {
        super();
        inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
        studentDAO = DAOFactory.getFactory().getStudentDAO();
        careerDAO = DAOFactory.getFactory().getCareerDAO();
        studentController = new StudentController();
        noticeClass = "none";
        output = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		output = "";
		noticeClass = "none";
		option = "none";
		try {
			option = request.getParameter("option");
			switch (option) {
			case "create":
				createInscription(request);
				redirectProcess(request, response);
				break;
			case "read":
				request.setAttribute("inscription", readInscription(request));
				break;
			case "inscribe":
				createInscriptionProcess(request, response);
				break;
			case "createStudent":
				studentController.createStudent(request, response, noticeClass, output);
				break;
			case "createStudentProcess":
				redirectProcess(request, response);
				break;
			default:
				option = "none";
				noticeClass = "bt-danger";
				output = "No se encontró una opción válida";
				redirectProcess(request, response);
				break;
			}
			
		} catch (Exception e) {
			option = "none";
			logger.log(Level.INFO, ERROR_ROOT + e.getMessage());
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void createInscriptionProcess(HttpServletRequest request, HttpServletResponse response) {
		searchStudent(request);
		redirectProcess(request, response);
	}
	
	private void redirectProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("option", option);
			request.setAttribute("output", output);
			request.setAttribute("noticeClass", noticeClass);
			setCareerListToRequest(request);
			getServletContext().getRequestDispatcher(URL).forward(request, response);
		} catch (ServletException | IOException e) {
			logger.log(Level.INFO, e.getMessage());
		}
	}
	
	private void searchStudent(HttpServletRequest request) {
		Student student = null;
		String dni = request.getParameter("dni");
		
		if (dni != null) {
			student = studentController.searchStudentByDni(request, 
					noticeClass, output);
			if (student == null) {
				noticeClass = "bg-danger";
				output = "No se encontró al Estudiente";
			} else  {
				Inscription inscription = inscriptionDAO.getCurrentInscrited(student.getId());
				if (inscription != null) {
					noticeClass = "bg-info";
					output = "El Estudiante ya está inscrito en la carrera de " 
							+ inscription.getCareer().getName();
				}
			}
		}
		request.setAttribute("student", student);
	}
	
	private void setCareerListToRequest(HttpServletRequest request) {
		List<Career> careerList = careerDAO.find("name", 0, 0);
		request.setAttribute("careerList", careerList);
	}
	
	private boolean validStudent(int studentId) {
		Inscription inscription = inscriptionDAO.getCurrentInscrited(studentId);
		
		if (inscription == null) {
			return true;
		}
		output = "El Estudiante ya está inscrito";
		noticeClass = "bg-danger";
		return false;
	}
	
	public void createInscription(HttpServletRequest request) {
		int studentId;
		int careerId;
		Student student;
		Career career;
		Inscription inscription;
		
		try {
			studentId = Integer.parseInt(request.getParameter("studentId"));
			if (!validStudent(studentId)) {
				return;
			}
			careerId = Integer.parseInt(request.getParameter("careerId"));
			student = studentDAO.read(studentId);
			career = careerDAO.read(careerId);
			inscription = new Inscription(new GregorianCalendar(), 'A', student, career);
			
			inscriptionDAO.create(inscription);
			this.noticeClass = "bg-success";
			this.output = "Inscripción creada con éxito";
		} catch (Exception e) {
			this.noticeClass = "bg-danger";
			this.output = "No se pudo crear la Inscripción, asegúrese de seleccionar un Estudiante y Carrera";
		}
	}
	
	public Inscription readInscription(HttpServletRequest request) {
		int inscriptionId;
		Inscription inscription;
		
		try {
			inscriptionId = Integer.parseInt(request.getParameter("inscriptionId"));
			inscription = inscriptionDAO.read(inscriptionId);
			if (inscription == null) {
				this.noticeClass = "bg-danger";
				this.output = "No se encuentra la inscripción";
			}
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
			this.noticeClass = "bg-danger";
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
