package ec.edu.ups.controller.offer;

import java.io.IOException;

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
		String output = "";
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
			System.out.println(">>> Error >> Servlet:ScheduleController:"
					+ "createSchedule: > " + e);
		}
		
		return "Error";
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
			System.out.println(">>> Error >> Servlet:ScheduleController:"
					+ "updateSchedule: > " + e);
		}
		
		return "Error";
	}

	public void doTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Schedule schedule;
		this.doGet(request, response);
		schedule = readSchedule(request);
		if (schedule == null) {
			response.getWriter().append("Error");
		} else {
			response.getWriter().append("Success");
		}
	}
	
}
