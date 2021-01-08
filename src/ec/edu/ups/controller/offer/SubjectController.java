package ec.edu.ups.controller.offer;

import java.io.IOException;

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
	
	private SubjectDAO subjectDAO;
	private CareerDAO careerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectController() {
        super();
        subjectDAO = DAOFactory.getFactory().getSubjectDAO();
        careerDAO = DAOFactory.getFactory().getCareerDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option;
		String output = "";
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
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("output", output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
			System.out.println(">>> Error >> Servlet:SubjectController:"
					+ "createSubject: > " + e);
		}
		return "Error";
	}
	
	public Subject readSubject(HttpServletRequest request) {
		Subject subject;
		int subjectId;
		try {
			subjectId = Integer.parseInt(request.getParameter("subjectId"));
			subject = subjectDAO.read(subjectId);
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
		Subject subject;
		
		try {
			name = request.getParameter("name");
			credits = Integer.parseInt(request.getParameter("credits"));
			cost = Double.parseDouble(request.getParameter("cost"));
			hours = Integer.parseInt(request.getParameter("hours"));
			level = Integer.parseInt(request.getParameter("level"));
			subject = readSubject(request);
			subject.setName(name);
			subject.setCredits(credits);
			subject.setCost(cost);
			subject.setHours(hours);
			subject.setLevel(level);
			subjectDAO.update(subject);
			return "Success";
		} catch (Exception e) {
			System.out.println(">>> Error >> Servlet:SubjectController:"
					+ "updateSubject: > " + e);
		}
		return "Error";
		
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Subject subject;
		this.doGet(request, response);
		subject = readSubject(request);
		if (subject == null) {
			response.getWriter().append("Error");
		} else {
			response.getWriter().append("Success");
		}
	}

}
