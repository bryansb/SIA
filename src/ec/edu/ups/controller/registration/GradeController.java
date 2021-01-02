package ec.edu.ups.controller.registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.dao.registration.EnrollmentDAO;
import ec.edu.ups.dao.registration.GradeDAO;
import ec.edu.ups.entities.offer.Group;
import ec.edu.ups.entities.registration.Enrollment;
import ec.edu.ups.entities.registration.Grade;

/**
 * Servlet implementation class GradeController
 */
@WebServlet("/GradeController")
public class GradeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GradeDAO gradeDAO;
	private EnrollmentDAO enrollmentDAO;
	private GroupDAO groupDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeController() {
        super();
        gradeDAO = DAOFactory.getFactory().getGradeDAO();
        enrollmentDAO = DAOFactory.getFactory().getEnrollmentDAO();
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
				output = createGrade(request);
				break;
			case "read":
				request.setAttribute("grade", readGrade(request));
				break;
			case "update":
				updateGrade(request);
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
	

	public String createGrade(HttpServletRequest request) {
		int enrollmentId;
		int groupId;
		String description;
		double gradeValue;
		Enrollment enrollment;
		Group group;
		Grade grade;
		try {
			enrollmentId = Integer.parseInt(request.getParameter("enr_id"));
			groupId = Integer.parseInt(request.getParameter("gro_id"));
			description = request.getParameter("gra_description");
			gradeValue = Double.parseDouble(request.getParameter("gra_grade"));
			enrollment = enrollmentDAO.read(enrollmentId);
			group = groupDAO.read(groupId);
			grade = new Grade(description, gradeValue, enrollment, group);
			gradeDAO.create(grade);
			return "Success";
		} catch (Exception e) {
			System.out.println(">>> Error >> Servlet:GradeController:"
					+ "createGrade: > " + e);
		}
		return "Error";
	}
	
	public Grade readGrade(HttpServletRequest request) {
		int gradeId;
		Grade grade;
		try {
			gradeId = Integer.parseInt(request.getParameter("gra_id"));
			grade = gradeDAO.read(gradeId);
		} catch (Exception e) {
			grade = null;
		}
		return grade;
	}
	
	public String updateGrade(HttpServletRequest request) {
		String description;
		double gradeValue;
		Grade grade;
		try {
			description = request.getParameter("gra_description");
			gradeValue = Double.parseDouble(request.getParameter("gra_grade"));
			grade = readGrade(request);
			grade.setDescription(description);
			grade.setGrade(gradeValue);
			gradeDAO.update(grade);
			return "Success";
		} catch (Exception e) {
			System.out.println(">>> Error >> Servlet:GradeController:"
					+ "updateGrade: > " + e);
		}
		return "Error";
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Grade grade;
		this.doGet(request, response);
		grade = readGrade(request);
		if (grade == null) {
			response.getWriter().append("Error");
		} else {
			response.getWriter().append("Success " + grade.getGrade());
		}
	}

}
