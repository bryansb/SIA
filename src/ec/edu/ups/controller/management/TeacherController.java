package ec.edu.ups.controller.management;

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

import ec.edu.ups.controller.utils.SiaTool;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.DegreeDAO;
import ec.edu.ups.dao.management.TeacherDAO;
import ec.edu.ups.entities.management.Degree;
import ec.edu.ups.entities.management.Teacher;
import ec.edu.ups.entities.offer.Group;

@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static String ERROR_ROOT = ">>> Error >> TeacherController:";
	private Logger logger = Logger.getLogger(DegreeController.class.getName());
	private TeacherDAO teacherDAO;
	private DegreeDAO degreeDAO;
	private String output;
	private static final String URL = "/JSP/private/management/teacher.jsp";
	private String noticeClass;

	public TeacherController() {
		super();
		teacherDAO = DAOFactory.getFactory().getTeacherDAO();
		degreeDAO = DAOFactory.getFactory().getDegreeDAO();
	}
	
	public String getOutput() {
		return output;
	}


	public String getNoticeClass() {
		return noticeClass;
	}
	
	private void createTeacher(HttpServletRequest request) {
		Teacher teacher;
		try {
			teacher = new Teacher();
			teacher.setAddress(request.getParameter("address"));
			teacher.setDni(request.getParameter("dni"));
			teacher.setEmail(request.getParameter("email"));
			teacher.setFullName(request.getParameter("fullName"));
			String password = SiaTool.getSha256(teacher.getDni());
			teacher.setPassword(password);
			teacher.setPhone(request.getParameter("phone"));
			teacher.setType('T');
			teacher.setSalary(Double.parseDouble(request.getParameter("salary")));
			teacherDAO.create(teacher);
		}catch(Exception e) {
			String message = ERROR_ROOT + ":createTeacher > " +e.toString();
			this.logger.log(Level.INFO, message);
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
			teacherId = Integer.parseInt(request.getParameter("teacherId"));
			teacher = teacherDAO.read(teacherId);
			return teacher;
		} catch (Exception e) {
			return null;
		}
	}

	private String deleteTeacher(HttpServletRequest request) {
		int teacherId;
		Teacher teacher;
		try {
			teacherId = Integer.parseInt(request.getParameter("use_id"));
			teacher = teacherDAO.read(teacherId);
			if (teacher.isDeleted()) {
				teacher.setDeleted(false);
				teacherDAO.update(teacher);
				return "No eliminado";
			}else {
				teacher.setDeleted(true);
				teacherDAO.update(teacher);
				return "Eliminado";
			}
		}catch(Exception e) {
			String message = ERROR_ROOT + ":createStudent > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}
	
	public List<Teacher> listTeacher(HttpServletRequest request) {
		List<Teacher> teachers;
		try {
			teachers = teacherDAO.find(null, 0, 0);
		} catch (Exception e) {
			teachers = null;
		}
		return teachers;
	}
	
	public List<Degree> listDegree(HttpServletRequest request) {
		List<Degree> degrees;
		try {
			degrees = degreeDAO.find("name", 0, 0);
		} catch (Exception e) {
			degrees = null;
		}
		return degrees;
	}
	
	public List<Degree> listDegreeByTeacher(HttpServletRequest request){
		List<Degree> degrees;
		Teacher teacher;
		try {
			teacher = readTeacher(request);
			degrees = teacher.getDegreeList();
		}catch(Exception e) {
			degrees = null;
		}
		return degrees;
	}
	
	private void selectDegree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("teacherId", Integer.parseInt(request.getParameter("teacherId")));
		request.setAttribute("degrees", listDegree(request));
		request.setAttribute("degreeList", listDegreeByTeacher(request));
		RequestDispatcher view;
		view = request.getRequestDispatcher("/JSP/private/management/teacherPages/addDegree.jsp");
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			option = request.getParameter("option");
			if (option == null) {
				option = "none";
			}
			switch (option) {
			case "create":
				createTeacher(request);
				updateRequest(request, response);
				break;
			case "update":
				output = updateTeacher(request);
				updateRequest(request, response);
				break;
			case "read":
				request.setAttribute("teacher", readTeacher(request));
				break;
			case "delete":
				output = deleteTeacher(request);
				updateRequest(request, response);
				break;
			case "selectDegree":
				selectDegree(request, response);
				break;
			case "addDegree":
				addDegree(request);
				selectDegree(request, response);
				break;
			case "deleteDegree":
				deleteDegree(request);
				selectDegree(request, response);
				break;
			default:
				updateRequest(request, response);
				break;
			}
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
		request.setAttribute("output", output);
	}

	private void updateRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("output", output);
		request.setAttribute("noticeClass", noticeClass);
		request.setAttribute("teachers", listTeacher(request));
		request.setAttribute("degrees", listDegree(request));
		request.setAttribute("readTeacher", null);
		getServletContext().getRequestDispatcher(URL).forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
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

	public void addDegree(HttpServletRequest request) {
		Degree degree;
		Teacher teacher;
		try {
			teacher = readTeacher(request);
			degree = degreeDAO.read(Integer.parseInt(request.getParameter("degreeId")));
			degree.addTeacher(teacher);
			teacher.addDegree(degree);
			teacherDAO.update(teacher);
		}catch(Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
	}
	
	private void deleteDegree(HttpServletRequest request) {
		Degree degree;
		Teacher teacher;
		try {
			teacher = readTeacher(request);
			degree = degreeDAO.read(Integer.parseInt(request.getParameter("degreeId")));
			degree.getTeacherList().remove(teacher);
			teacher.removeDegree(degree);
			degreeDAO.update(degree);
			teacherDAO.update(teacher);
		}catch(Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
	}
}
