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
import ec.edu.ups.dao.management.TeacherDAO;
import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.dao.offer.ScheduleDAO;
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.dao.utils.ParameterDAO;
import ec.edu.ups.entities.management.Teacher;
import ec.edu.ups.entities.offer.Group;
import ec.edu.ups.entities.offer.Schedule;
import ec.edu.ups.entities.offer.Subject;

/**
 * Servlet implementation class GroupController
 */
@WebServlet("/GroupController")
public class GroupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ERROR_ROOT = ">>> Error >> GroupController:";
	private Logger logger;
	private String output;
	
	private GroupDAO groupDAO;
	private SubjectDAO subjectDAO;
	private TeacherDAO teacherDAO;
	private ParameterDAO  parameterDAO;
	
	private String academicPeriod;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupController() {
        super();
        logger = Logger.getLogger(GroupController.class.getName());
        groupDAO = DAOFactory.getFactory().getGroupDAO();
        subjectDAO = DAOFactory.getFactory().getSubjectDAO();
        teacherDAO = DAOFactory.getFactory().getTeacherDAO();
        parameterDAO = DAOFactory.getFactory().getParameterDAO();
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
			if (option == null) {
				option = "none";
			}
			switch (option) {
			case "create":
				output = createGroup(request);
				updateRequest(request, response);
				break;
			case "read":
				request.setAttribute("group", readGroup(request));
				updateRequest(request, response);
				break;
			case "update":
				updateGroup(request);
				updateRequest(request, response);
				break;
			case "delete":
				deleteGroup(request);
				updateRequest(request, response);
				break;
			case "selectTeacher":
				selectTeacher(request, response);
				break;
			case "addTeacher":
				addTeacher(request);
				selectTeacher(request, response);
				break;
			case "deleteTeacher":
				deleteTeacher(request);
				selectTeacher(request, response);
				break;
			default:
				updateRequest(request, response);
				break;
			}
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
			this.output = "Error al buscar una opción";
		}
		request.setAttribute("output", output);
		
	}

	private void selectTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("groupId", Integer.parseInt(request.getParameter("groupId")));
		request.setAttribute("teachers", listTeacher(request));
		request.setAttribute("teacherList", listTeacherByGroup(request));
		RequestDispatcher view;
		view = request.getRequestDispatcher("/JSP/private/offer/groupPages/addTeacher.jsp");
		view.forward(request, response);
	}

	private void deleteGroup(HttpServletRequest request) {
		groupDAO.deleteByID(Integer.parseInt(request.getParameter("id")));
	}

	private void updateRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		academicPeriod = parameterDAO.findByKey("CURRENT_ACADEMIC_PERIOD").getValue();
		request.setAttribute("period", academicPeriod);
		request.setAttribute("subjects", listSubject(request));
		request.setAttribute("teachers", listTeacher(request));
		request.setAttribute("groups", listGroup(request));
		RequestDispatcher view;
		view = request.getRequestDispatcher("/JSP/private/offer/group.jsp");
		view.forward(request, response);
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

	public List<Teacher> listTeacher(HttpServletRequest request) {
		List<Teacher> teachers;
		try {
			teachers = teacherDAO.find("fullName", 0, 0);
		} catch (Exception e) {
			teachers = null;
		}
		return teachers;
	}
	
	public List<Teacher> listTeacherByGroup(HttpServletRequest request) {
		List<Teacher> teachers;
		Group group;
		try {
			group = readGroup(request);
			teachers = group.getTeacherList();
		} catch (Exception e) {
			teachers = null;
		}
		return teachers;
	}
	
	public List<Group> listGroup(HttpServletRequest request) {
		List<Group> groups;
		try {
			groups = groupDAO.find(null, 0, 0);
		} catch (Exception e) {
			groups = null;
		}
		return groups;
	}
	
	public String createGroup(HttpServletRequest request) {
		String physicalSpace;
		int quota;
		int subjectId;
		Subject subject;
		Group group;
		
		try {
			
			physicalSpace = request.getParameter("physicalSpace");
			quota = Integer.parseInt(request.getParameter("quota"));
			group = new Group(parameterDAO.findByKey("CURRENT_ACADEMIC_PERIOD")
					.getValue(), physicalSpace, quota);
			subjectId = Integer.parseInt(request.getParameter("subjectId"));
			subject = subjectDAO.read(subjectId);
			group.setSubject(subject);
			groupDAO.create(group);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":createGroup > " + e.toString();
			this.logger.log(Level.INFO, message);
			return "Error " + e.getMessage();
		}		
	}
	
	public Group readGroup(HttpServletRequest request) {
		Group group;
		try {
			group = groupDAO.read(Integer.parseInt(request.getParameter("groupId")));
			group.setEditable(true);
		} catch (Exception e) {
			group = null;
		}
		return group;
	}
	
	public String updateGroup(HttpServletRequest request) {
		String physicalSpace;
		int quota;
		int subjectId;
		Group group;
		Subject subject;
		
		try {
			physicalSpace = request.getParameter("physicalSpace");
			quota = Integer.parseInt(request.getParameter("quota"));
			subjectId = Integer.parseInt(request.getParameter("subjectId"));
			subject = subjectDAO.read(subjectId);
			group = readGroup(request);
			group.setAcademicPeriod(academicPeriod);
			group.setPhysicalSpace(physicalSpace);
			group.setQuota(quota);
			group.setSubject(subject);
			groupDAO.update(group);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":updateGroup > " + e.toString();
			this.logger.log(Level.INFO, message);
			return "Error " + e.getMessage();
		}
	}
	
	public void addTeacher(HttpServletRequest request) {
		Group group;
		Teacher teacher;
		
		try {
			group = readGroup(request);
			teacher = teacherDAO.read(Integer.parseInt(request.getParameter("teacherId")));
			teacher.addGroup(group);
			group.addTeacher(teacher);
			groupDAO.update(group);

		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
		
	}
	
	private void deleteTeacher(HttpServletRequest request) {
		Group group;
		Teacher teacher;
		
		try {
			group = readGroup(request);
			teacher = teacherDAO.read(Integer.parseInt(request.getParameter("teacherId")));
			group.removeTeacher(teacher);
			groupDAO.update(group);
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Group group;
		this.doGet(request, response);
		group = readGroup(request);
		if (group == null) {
			response.getWriter().append(this.output);
		} else {
			response.getWriter().append(this.output);
		}
	}
	
}
