package ec.edu.ups.controller.offer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.entities.offer.Career;
import ec.edu.ups.entities.offer.Subject;

/**
 * Servlet implementation class Subject
 */
@WebServlet("/SubjectController")
public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ERROR_ROOT = ">>> Error >> SubjectController:";
	private Logger logger;
	private String output;
	
	private SubjectDAO subjectDAO;
	private CareerDAO careerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectController() {
        super();
        logger = Logger.getLogger(SubjectController.class.getName());
        subjectDAO = DAOFactory.getFactory().getSubjectDAO();
        careerDAO = DAOFactory.getFactory().getCareerDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String option;
		try {
			option = request.getParameter("option");
			switch (option) {
			case "create":
				output = createSubject(request);
				break;
			case "read":
				request.setAttribute("subject", readSubject(request));
				break;
			case "update":
				updateSubject(request);
				break;
			case "delete":
				deleteSubject(request);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
			this.output = "Error al buscar una opción";
		}
		request.setAttribute("output", output);
		updateRequest(request, response);
	}
	
	private void updateRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("careers", listCareer(request));
		request.setAttribute("subjects", listSubject(request));
		RequestDispatcher view;
		view = request.getRequestDispatcher("/JSP/private/offer/subject.jsp");
		view.forward(request, response);
	}
	
	private void deleteSubject(HttpServletRequest request) {
		int id;	
		id = Integer.parseInt(request.getParameter("id"));
		Subject subject = subjectDAO.read(id);
		if (subject.isDeleted()) {
			subject.setDeleted(false);
		}else {
			subject.setDeleted(true);
		}
		subjectDAO.update(subject);
	}

	public List<Career> listCareer(HttpServletRequest request) {
		List<Career> careers;
		try {
			careers = careerDAO.find(null, 0, 0);
		} catch (Exception e) {
			careers = null;
		}
		return careers;
	}
	
	public List<Subject> listSubject(HttpServletRequest request) {
		List<Subject> subjects;
		try {
			subjects = subjectDAO.find(null, 0, 0);
		} catch (Exception e) {
			subjects = null;
		}
		return subjects;
	}

	public String createSubject(HttpServletRequest request) {
		
		String name;
		int credits;
		double cost;
		int hours;
		int level;
		int careerId;
		Career career;
		Subject subject;
		
		try {
			name = request.getParameter("name");
			credits = Integer.parseInt(request.getParameter("credits"));
			cost = Double.parseDouble(request.getParameter("cost"));
			hours = Integer.parseInt(request.getParameter("hours"));
			level = Integer.parseInt(request.getParameter("level"));
			careerId = Integer.parseInt(request.getParameter("careerId"));
			career = careerDAO.read(careerId);
			subject = new Subject(name, credits, cost, hours, level, career);
			subjectDAO.create(subject);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":createSubject > " + e.toString();
			this.logger.log(Level.INFO, message);
			return "Error " + e.getMessage();
		}
	}
	
	public Subject readSubject(HttpServletRequest request) {
		Subject subject;
		int subjectId;
		try {
			subjectId = Integer.parseInt(request.getParameter("subjectId"));
			subject = subjectDAO.read(subjectId);
			subject.setEditable(true);
		} catch (Exception e) {
			subject = null;
		}
		return subject;
	}
	
	public String updateSubject(HttpServletRequest request) {
		
		String name;
		int credits;
		double cost;
		int hours;
		int level;
		int careerId;
		Career career;
		Subject subject;
		
		try {
			name = request.getParameter("name");
			credits = Integer.parseInt(request.getParameter("credits"));
			cost = Double.parseDouble(request.getParameter("cost"));
			hours = Integer.parseInt(request.getParameter("hours"));
			level = Integer.parseInt(request.getParameter("level"));
			careerId = Integer.parseInt(request.getParameter("careerId"));
			career = careerDAO.read(careerId);
			subject = readSubject(request);
			subject.setName(name);
			subject.setCredits(credits);
			subject.setCost(cost);
			subject.setHours(hours);
			subject.setLevel(level);
			subject.setCareer(career);
			subjectDAO.update(subject);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":updateSubject > " + e.toString();
			this.logger.log(Level.INFO, message);
			return "Error " + e.getMessage();
		}
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Subject subject;
		this.doGet(request, response);
		subject = readSubject(request);
		if (subject == null) {
			response.getWriter().append(this.output);
		} else {
			response.getWriter().append(this.output);
		}
	}

}
