package ec.edu.ups.controller.offer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.entities.offer.Career;
import ec.edu.ups.entities.offer.Group;
import ec.edu.ups.entities.offer.Subject;

/**
 * Servlet implementation class Subject
 */
@WebServlet("/SubjectController")
public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SubjectDAO subjectDAO;
	private CareerDAO careerDAO;
	private GroupDAO groupDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectController() {
        super();
        subjectDAO = DAOFactory.getFactory().getSubjectDAO();
        groupDAO = DAOFactory.getFactory().getGroupDAO();
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
		int groupId;
		List<Group> groupList;
		Subject subject;
		
		try {
			name = request.getParameter("sub_name");
			credits = Integer.parseInt(request.getParameter("sub_credits"));
			cost = Double.parseDouble(request.getParameter("sub_cost"));
			hours = Integer.parseInt(request.getParameter("sub_hours"));
			level = Integer.parseInt(request.getParameter("sub_level"));
			careerId = Integer.parseInt(request.getParameter("car_id"));
			career = careerDAO.read(careerId);
			groupId = Integer.parseInt(request.getParameter("gro_id"));
			groupList = groupDAO.find("", groupId, 0);
			subject = new Subject(name, credits, cost, hours, level, career, groupList);
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
			subjectId = Integer.parseInt(request.getParameter("sub_id"));
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
			name = request.getParameter("sub_name");
			credits = Integer.parseInt(request.getParameter("sub_credits"));
			cost = Double.parseDouble(request.getParameter("sub_cost"));
			hours = Integer.parseInt(request.getParameter("sub_hours"));
			level = Integer.parseInt(request.getParameter("sub_level"));
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
