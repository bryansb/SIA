package ec.edu.ups.controller.offer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.dao.offer.ScheduleDAO;
import ec.edu.ups.entities.offer.Group;
import ec.edu.ups.entities.offer.Schedule;

/**
 * Servlet implementation class GroupController
 */
@WebServlet("/ScheduleController")
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ERROR_ROOT = ">>> Error >> ScheduleController:";
	private Logger logger;
	private String output;
	
	private ScheduleDAO scheduleDAO;
	private GroupDAO groupDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleController() {
        super();
        scheduleDAO = DAOFactory.getFactory().getScheduleDAO();
        groupDAO = DAOFactory.getFactory().getGroupDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option;
		try {
			option = request.getParameter("option");
			switch (option) {
			case "create":
				output = createSchedule(request);
				break;
			case "read":
				request.setAttribute("schedule", readSchedule(request));
				break;
			case "update":
				updateSchedule(request);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
			this.output = "Error al buscar una opción";
		}
		request.setAttribute("output", output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public String createSchedule(HttpServletRequest request) {
		
		String day;
		String startTime;
		String endTime;
		int groupId;
		Group group;
		Schedule schedule;
		
		try {
			day = request.getParameter("day");
			startTime = request.getParameter("startTime");
			endTime = request.getParameter("endTime");
			groupId = Integer.parseInt(request.getParameter("groupId"));
			group = groupDAO.read(groupId);
			schedule = new Schedule(day, startTime, endTime, group);
			scheduleDAO.create(schedule);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":createSchedule > " + e.toString();
			this.logger.log(Level.INFO, message);
			return "Error " + e.getMessage();
		}
	}
	
	public Schedule readSchedule(HttpServletRequest request) {
		Schedule schedule;
		int scheduleId;
		try {
			scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
			schedule = scheduleDAO.read(scheduleId);
		} catch (Exception e) {
			schedule = null;
		}
		return schedule;
	}
	
	public String updateSchedule(HttpServletRequest request) {
		String day;
		String startTime;
		String endTime;
		Schedule schedule;
		
		try {
			day = request.getParameter("day");
			startTime = request.getParameter("startTime");
			endTime = request.getParameter("endTime");
			schedule = readSchedule(request);
			schedule.setDay(day);
			schedule.setStartTime(startTime);
			schedule.setEndTime(endTime);
			scheduleDAO.update(schedule);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":updateSchedule > " + e.toString();
			this.logger.log(Level.INFO, message);
			return "Error " + e.getMessage();
		}
	}

	public void doTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Schedule schedule;
		this.doGet(request, response);
		schedule = readSchedule(request);
		if (schedule == null) {
			response.getWriter().append(this.output);
		} else {
			response.getWriter().append(this.output);
		}
	}
	
}
