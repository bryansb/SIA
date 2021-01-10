package ec.edu.ups.controller.management;

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
import ec.edu.ups.dao.management.DegreeDAO;
import ec.edu.ups.dao.management.TeacherDAO;
import ec.edu.ups.entities.management.Degree;
import ec.edu.ups.entities.management.Teacher;

@WebServlet("/DegreeController")
public class DegreeController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static String ERROR_ROOT = ">>> Error >> GroupController:";
	private Logger logger;
	private DegreeDAO degreeDAO;
	private TeacherDAO teacherDAO;
	
	public DegreeController() {
		super();
		degreeDAO = DAOFactory.getFactory().getDegreeDAO();
		teacherDAO = DAOFactory.getFactory().getTeacherDAO();
	}
	
	private String createDegree(HttpServletRequest request) {
		Degree degree;
		int teacherId;
		List<Teacher> teacherList;
		try {
			teacherId = Integer.parseInt(request.getParameter("use_id"));
			teacherList = teacherDAO.find("", teacherId, 0);
			degree = new Degree();
			degree.setName(request.getParameter("deg_name"));
			degree.setTeacherList(teacherList);
			degreeDAO.create(degree);
			return "Success";
		}catch(Exception e) {
			String message = ERROR_ROOT + ":createDegree > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}
	
	private String updateDegree(HttpServletRequest request) {
		int degreeId;
		Degree degree;
		int teacherId;
		List<Teacher> teacherList;
		try {
			degreeId = Integer.parseInt(request.getParameter("deg_id"));
			degree = degreeDAO.read(degreeId);
			teacherId = Integer.parseInt(request.getParameter("use_id"));
			teacherList = teacherDAO.find("", teacherId, 0);
			degree.setName(request.getParameter("deg_name"));
			degree.setTeacherList(teacherList);
			degreeDAO.update(degree);
			return "Success";
		}catch(Exception e) {
			String message = ERROR_ROOT + ":updateDegree > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}
	
	private Degree readDegree(HttpServletRequest request) {
		int degreeId;
		Degree degree;
		try {
			degreeId = Integer.parseInt(request.getParameter("deg_id"));
			degree = degreeDAO.read(degreeId);
			return degree;
		}catch(Exception e) {
			return null;
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String option;
		String output = "";
		try {
			option = request.getParameter("option");
			switch (option) {
			case "create":
				output = createDegree(request);
				break;
			case "update":
				output = updateDegree(request);
				break;
			case "read":
				request.setAttribute("degree", readDegree(request));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
		request.setAttribute("output", output);
	}

	public void doTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Degree degree;
		this.doGet(request, response);
		degree = readDegree(request);
		if(degree == null) {
			response.getWriter().append("Error");
		}else {
			response.getWriter().append("Success");
		}
	}
	
}
