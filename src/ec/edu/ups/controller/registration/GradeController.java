package ec.edu.ups.controller.registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.dao.registration.GradeDAO;
import ec.edu.ups.entities.offer.Group;
import ec.edu.ups.entities.registration.Grade;

/**
 * Servlet implementation class GradeController
 */
@WebServlet("/GradeController")
public class GradeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> GradeController";
	private static final Logger LOGGER = Logger.getLogger(GradeController.class.getName());
	
	private final GradeDAO gradeDAO;
	private final GroupDAO groupDAO;
	
	private String output;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeController() {
        super();
        gradeDAO = DAOFactory.getFactory().getGradeDAO();
        groupDAO = DAOFactory.getFactory().getGroupDAO();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	output = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option;
		
		try {
			option = request.getParameter("option");
			switch (option) {
			case "read":
				request.setAttribute("grade", readGrade(request));
				break;
			case "update":
				output = updateGrade(request);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, e.getMessage());
			output = "Error al buscar una opción";
		}
		request.setAttribute("output", output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			doGet(request, response);
		} catch (ServletException | IOException e) {
			String message = ERROR_ROOT + ":doPost > " + e.toString();
			LOGGER.log(Level.INFO, message);
		}
	}
	
	public Grade readGrade(HttpServletRequest request) {
		int gradeId;
		Grade grade;
		
		try {
			gradeId = Integer.parseInt(request.getParameter("gradeId"));
			grade = gradeDAO.read(gradeId);
		} catch (Exception e) {
			grade = null;
			String message = ERROR_ROOT + ":readGrade > " + e.toString();
			LOGGER.log(Level.INFO, message);
			output = "Error"; 
		}
		return grade;
	}
	
	public String updateGrade(HttpServletRequest request) {
		String description;
		double gradeValue;
		Grade grade;
		
		try {
			description = request.getParameter("description");
			gradeValue = Double.parseDouble(request.getParameter("gradeValue"));
			grade = readGrade(request);
			grade.setDescription(description);
			grade.setGradeValue(gradeValue);
			gradeDAO.update(grade);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":updateGrade > " + e.toString();
			LOGGER.log(Level.INFO, message);
		}
		return "Error";
	}
	
	public List<Group> getGroupListByIdList(List<Integer> idList) {
		Group group;
		
		List<Group> groupList = new ArrayList<>();
		for (Integer groupId : idList) {
			group = groupDAO.read(groupId);
			groupList.add(group);
		}
		return groupList;
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) {
		Grade grade;
		try {
			this.doGet(request, response);
			grade = readGrade(request);
			if (grade == null) {
				response.getWriter().append(output);
			} else {
				response.getWriter().append(output + grade.getGradeValue());
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, e.toString());
		}
		
	}

}
