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
import ec.edu.ups.entities.management.Degree;

@WebServlet("/DegreeController")
public class DegreeController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static String ERROR_ROOT = ">>> Error >> DegreeController:";
	private Logger logger = Logger.getLogger(DegreeController.class.getName());
	private DegreeDAO degreeDAO;
	private String output;
	private static final String URL = "/JSP/private/management/degree.jsp";
	private String noticeClass;
	
	
	public DegreeController() {
		super();
		degreeDAO = DAOFactory.getFactory().getDegreeDAO();
	}
	
	public String getOutput() {
		return output;
	}


	public String getNoticeClass() {
		return noticeClass;
	}
	
	private void createDegree(HttpServletRequest request) {
		Degree degree;
		try {
			degree = new Degree();
			degree.setName(request.getParameter("name"));
			degreeDAO.create(degree);
		}catch(Exception e) {
			String message = ERROR_ROOT + ":createDegree > " +e.toString();
			this.logger.log(Level.INFO, message);
		}
	}
	
	private String updateDegree(HttpServletRequest request) {
		int degreeId;
		Degree degree;
		try {
			degreeId = Integer.parseInt(request.getParameter("degreeId"));
			degree = degreeDAO.read(degreeId);
			degree.setName(request.getParameter("name"));
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
			degreeId = Integer.parseInt(request.getParameter("degreeId"));
			degree = degreeDAO.read(degreeId);
			return degree;
		}catch(Exception e) {
			return null;
		}
	}
	
	private String deleteDegree(HttpServletRequest request) {
		int degreeId;
		Degree degree;
		try {
			degreeId = Integer.parseInt(request.getParameter("degreeId"));
			degree = degreeDAO.read(degreeId);
			if (degree.isDeleted()) {
				degree.setDeleted(false);
				degreeDAO.update(degree);
				return "No eliminado";
			}else {
				degree.setDeleted(true);
				degreeDAO.update(degree);
				return "Eliminado";
			}
		}catch(Exception e) {
			String message = ERROR_ROOT + ":deleteDegree > " +e.toString();
			this.logger.log(Level.INFO, message);
			return "Error "+e.getMessage();
		}
	}
	
	private List<Degree> listDegree(HttpServletRequest request) {
		List<Degree> degrees;
		try {
			degrees = degreeDAO.find(null, 0, 0);
		}catch(Exception e) {
			degrees = null;
		}
		return degrees;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
				createDegree(request);
				updateRequest(request, response);
				break;
			case "update":
				output = updateDegree(request);
				updateRequest(request, response);
				break;
			case "read":
				request.setAttribute("degree", readDegree(request));
				break;
			case "list":
				request.setAttribute("degrees", listDegree(request));
				updateRequest(request, response);
				break;
			case "delete":
				output = deleteDegree(request);
				updateRequest(request, response);
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
		request.setAttribute("degrees", listDegree(request));
		request.setAttribute("readDegree", null);
		getServletContext().getRequestDispatcher(URL).forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
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
