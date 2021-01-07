package ec.edu.ups.controller.offer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.TeacherDAO;
import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.entities.management.Teacher;
import ec.edu.ups.entities.offer.Group;
import ec.edu.ups.entities.offer.Schedule;
import ec.edu.ups.entities.offer.Subject;
import ec.edu.ups.entities.registration.Grade;

/**
 * Servlet implementation class GroupController
 */
@WebServlet("/GroupController")
public class GroupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GroupDAO groupDAO;
	private SubjectDAO subjectDAO;
	private TeacherDAO teacherDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupController() {
        super();
        groupDAO = DAOFactory.getFactory().getGroupDAO();
        subjectDAO = DAOFactory.getFactory().getSubjectDAO();
        teacherDAO = DAOFactory.getFactory().getTeacherDAO();
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
				output = createGroup(request);
				break;
			case "read":
				request.setAttribute("group", readGroup(request));
				break;
			case "update":
				updateGroup(request);
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
	
	public String createGroup(HttpServletRequest request) {
		String academicPeriod;
		String physicalSpace;
		int quota;
		int subjectId;
		Subject subject;
		Group group;
		
		try {
			academicPeriod = request.getParameter("gro_academic_period");
			physicalSpace = request.getParameter("gro_physical_space");
			quota = Integer.parseInt(request.getParameter("gro_quota"));
			group = new Group(academicPeriod, physicalSpace, quota);
			
			subjectId = Integer.parseInt(request.getParameter("sub_id"));
			subject = subjectDAO.read(subjectId);
			group.setSubject(subject);
			
			createSchedule(request, group);
			addTeacher(request, group);
			
			groupDAO.create(group);
			
			return "Success";
		} catch (Exception e) {
			System.out.println(">>> Error >> Servlet:GroupController:"
					+ "createGroup: > " + e);
		}
		
		return "Error";
	}
	
	public Group readGroup(HttpServletRequest request) {
		Group group;
		int groupId;
		try {
			groupId = Integer.parseInt(request.getParameter("gro_id"));
			group = groupDAO.read(groupId);
		} catch (Exception e) {
			group = null;
		}
		return group;
	}
	
	public String updateGroup(HttpServletRequest request) {
		String academicPeriod;
		String physicalSpace;
		int quota;
		Group group;
		
		try {
			academicPeriod = request.getParameter("gro_academic_period");
			physicalSpace = request.getParameter("gro_physical_space");
			quota = Integer.parseInt(request.getParameter("gro_quota"));
			group = readGroup(request);
			group.setAcademicPeriod(academicPeriod);
			group.setPhysicalSpace(physicalSpace);
			group.setQuota(quota);
			groupDAO.create(group);
			return "Success";
		} catch (Exception e) {
			System.out.println(">>> Error >> Servlet:GroupController:"
					+ "updateGroup: > " + e);
		}
		
		return "Error";
	}

	public void doTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Group group;
		this.doGet(request, response);
		group = readGroup(request);
		if (group == null) {
			response.getWriter().append("Error");
		} else {
			response.getWriter().append("Success");
		}
	}
	
	public void createSchedule(HttpServletRequest request, Group group) {
		String[] days;
		String[] startTimes;
		String[] endTimes;
		int parameterSize;
		
		try {
			
			days = request.getParameterValues("day");
			startTimes = request.getParameterValues("startTimes");
			endTimes = request.getParameterValues("endTimes");
			
			//Falta metodo validar tamaño de los 3 return tamaño
			parameterSize = days.length;
			
			for (int i = 0; i < parameterSize; i++) {
				group.createSchedule(days[i], startTimes[i], endTimes[i], group);
			}
			
		} catch (Exception e) {
			
		}
		
	}
	
	public void addTeacher(HttpServletRequest request, Group group) {
		String[] teacherIds;
		Teacher teacher;
		
		try {
			
			teacherIds = request.getParameterValues("teacherId");
			
			for (String teacherId : teacherIds) {
				teacher = teacherDAO.read(Integer.parseInt(teacherId));
				group.addTeacher(teacher);
			}
		} catch (Exception e) {
			
		}
		
	}
	
}
