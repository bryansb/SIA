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

@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static String ERROR_ROOT = ">>> Error >> GroupController:";
	private Logger logger;
	private TeacherDAO teacherDAO;
	private DegreeDAO degreeDAO;

	public TeacherController() {
		super();
		teacherDAO = DAOFactory.getFactory().getTeacherDAO();
		degreeDAO = DAOFactory.getFactory().getDegreeDAO();
	}
	
	private String createTeacher(HttpServletRequest request) {
		Teacher teacher;
		try {
			teacher = new Teacher();
			teacher.setAddress(request.getParameter("use_address"));
			teacher.setDni(request.getParameter("use_dni"));
			teacher.setEmail(request.getParameter("use_email"));
			teacher.setFullName(request.getParameter("use_full_name"));
			teacher.setPassword(request.getParameter("use_password"));
			teacher.setPhone(request.getParameter("use_phone"));
			teacher.setType(request.getParameter("use_type").charAt(0));
			teacher.setSalary(Double.parseDouble(request.getParameter("use_salary")));
			addDegree(request, teacher);
			teacherDAO.create(teacher);
			return "Success";
		}catch(Exception e) {
			String message = ERROR_ROOT + ":createTeacher > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}
	
	private String updateTeacher(HttpServletRequest request) {
		int teacherId;
		Teacher teacher;
		try {
			teacherId = Integer.parseInt(request.getParameter("use_id"));
			teacher = teacherDAO.read(teacherId);
			teacher.setAddress(request.getParameter("use_address"));
			teacher.setDni(request.getParameter("use_dni"));
			teacher.setEmail(request.getParameter("use_email"));
			teacher.setFullName(request.getParameter("use_full_name"));
			teacher.setPassword(request.getParameter("use_password"));
			teacher.setPhone(request.getParameter("use_phone"));
			teacher.setType(request.getParameter("use_type").charAt(0));
			teacher.setSalary(Double.parseDouble(request.getParameter("use_salary")));
			teacherDAO.update(teacher);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":updateTeacher > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}

	private Teacher readTeacher(HttpServletRequest request) {
		int teacherId;
		Teacher teacher;
		try {
			teacherId = Integer.parseInt(request.getParameter("use_id"));
			teacher = teacherDAO.read(teacherId);
			return teacher;
		} catch (Exception e) {
			return null;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option;
		String output = "";
		try {
			option = request.getParameter("option");
			switch (option) {
			case "create":
				output = createTeacher(request);
				break;
			case "update":
				output = updateTeacher(request);
				break;
			case "read":
				request.setAttribute("teacher", readTeacher(request));
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
		Teacher teacher;
		this.doGet(request, response);
		teacher = readTeacher(request);
		if(teacher == null) {
			response.getWriter().append("Error");
		}else {
			response.getWriter().append("Success");
		}
	}

	public void addDegree(HttpServletRequest request, Teacher teacher) {
		String[] degreeIds;
		Degree degree;
		try {
			degreeIds = request.getParameterValues("degreeId");
			for (String degreeId : degreeIds) {
				degree = degreeDAO.read(Integer.parseInt(degreeId));
				teacher.addDegree(degree);
			}
		}catch(Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
	}
}
