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
import ec.edu.ups.dao.offer.ScheduleDAO;
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.dao.registration.GradeDAO;
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
	private ScheduleDAO scheduleDAO;
	private SubjectDAO subjectDAO;
	private GradeDAO gradeDAO;
	private TeacherDAO teacherDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupController() {
        super();
        groupDAO = DAOFactory.getFactory().getGroupDAO();
        scheduleDAO = DAOFactory.getFactory().getScheduleDAO();
        subjectDAO = DAOFactory.getFactory().getSubjectDAO();
        gradeDAO = DAOFactory.getFactory().getGradeDAO();
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
		int scheduleId;
		int subjectId;
		int gradeId;
		int teacherId;
		List<Schedule> scheduleList;
		Subject subject;
		List<Grade> gradeList;
		List<Teacher> teacherList;
		Group group;
		
		try {
			academicPeriod = request.getParameter("gro_academic_period");
			physicalSpace = request.getParameter("gro_physical_space");
			quota = Integer.parseInt(request.getParameter("gro_quota"));
			scheduleId = Integer.parseInt(request.getParameter("sch_id"));
			scheduleList = scheduleDAO.find("", scheduleId, 0);
			subjectId = Integer.parseInt(request.getParameter("sub_id"));
			subject = subjectDAO.read(subjectId);
			gradeId = Integer.parseInt(request.getParameter("gra_id"));
			gradeList = gradeDAO.find("", gradeId, 0);
			teacherId = Integer.parseInt(request.getParameter("tea_id"));
			teacherList = teacherDAO.find("", teacherId, 0);
			group = new Group(academicPeriod, physicalSpace, quota, scheduleList, subject, gradeList, teacherList);
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
	
}
