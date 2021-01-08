package ec.edu.ups.controller.management;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.DegreeDAO;
import ec.edu.ups.dao.management.TeacherDAO;
import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.entities.management.Degree;
import ec.edu.ups.entities.management.Teacher;
import ec.edu.ups.entities.offer.Group;

@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private TeacherDAO teacherDAO;
	private DegreeDAO degreeDAO;
	private GroupDAO groupDAO;

	public TeacherController() {
		super();
		teacherDAO = DAOFactory.getFactory().getTeacherDAO();
		degreeDAO = DAOFactory.getFactory().getDegreeDAO();
		groupDAO = DAOFactory.getFactory().getGroupDAO();
	}
	
	private String createTeacher(HttpServletRequest request) {
		Teacher teacher;
		int degreeId;
		int groupId;
		List<Degree> degreeList;
		List<Group> groupList;
		try {
			degreeId = Integer.parseInt(request.getParameter("deg_id"));
			degreeList = degreeDAO.find("", degreeId, 0);
			groupId = Integer.parseInt(request.getParameter("gro_id"));
			groupList = groupDAO.find("", groupId, 0);
			teacher = new Teacher();
			teacher.setAddress(request.getParameter("use_address"));
			teacher.setDni(request.getParameter("use_dni"));
			teacher.setEmail(request.getParameter("use_email"));
			teacher.setFullName(request.getParameter("use_full_name"));
			teacher.setPassword(request.getParameter("use_password"));
			teacher.setPhone(request.getParameter("use_phone"));
			teacher.setType(request.getParameter("use_type").charAt(0));
			teacher.setSalary(Double.parseDouble(request.getParameter("use_salary")));
			teacher.setDegreeList(degreeList);
			teacher.setGroupList(groupList);
			teacherDAO.create(teacher);
			return "Success";
		}catch(Exception e) {
			return "Error";
		}
	}
	
	private String updateTeacher(HttpServletRequest request) {
		int teacherId;
		Teacher teacher;
		int degreeId;
		int groupId;
		List<Degree> degreeList;
		List<Group> groupList;
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
			degreeId = Integer.parseInt(request.getParameter("deg_id"));
			degreeList = degreeDAO.find("", degreeId, 0);
			groupId = Integer.parseInt(request.getParameter("gro_id"));
			groupList = groupDAO.find("", groupId, 0);
			teacher.setDegreeList(degreeList);
			teacher.setGroupList(groupList);
			teacherDAO.update(teacher);
			return "Success";
		} catch (Exception e) {
			return "Error";
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

	private String deleteTeacher(HttpServletRequest request) {
		int teacherId;
		try {
			teacherId = Integer.parseInt(request.getParameter("use_id"));
			teacherDAO.deleteByID(teacherId);
			return "Success";
		}catch(Exception e) {
			return "Error";
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
			case "delete":
				output = deleteTeacher(request);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("output", output);
		response.getWriter().append(output);
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

}
