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
import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.entities.offer.Career;

/**
 * Servlet implementation class CareerController
 */
@WebServlet("/CareerController")
public class CareerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ERROR_ROOT = ">>> Error >> CareerController:";
	private Logger logger;
	private String output;
	
	private CareerDAO careerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CareerController() {
        super();
        careerDAO =  DAOFactory.getFactory().getCareerDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String option;
//
//		try {
//			RequestDispatcher view;
//			option = request.getParameter("option");
//			switch (option) {
//			case "create":
//				output = createCareer(request);
//				view = request.getRequestDispatcher("/JSP/private/offer/career.jsp");
//				view.forward(request, response);
//				break;
//			case "read":
//				request.setAttribute("career", readCareer(request));
//				view = request.getRequestDispatcher("/JSP/private/offer/career.jsp");
//				view.forward(request, response);
//				break;
//			case "search":
//				request.setAttribute("careers", readCareer(request));
//				view = request.getRequestDispatcher("/JSP/private/offer/career.jsp");
//				view.forward(request, response);
//				break;
//			case "update":
//				updateCareer(request);
//				break;
//			default:
//				break;
//			}
//		} catch (Exception e) {
//			this.logger.log(Level.INFO, e.getMessage());
//			this.output = "Error al buscar una opción";
//		}
//		request.setAttribute("output", output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option;

		try {
			RequestDispatcher view;
			option = request.getParameter("option");
			switch (option) {
			case "create":
				output = createCareer(request);
				
				break;
			case "read":
				request.setAttribute("career", readCareer(request));
				break;
			case "search":
				request.setAttribute("careers", readCareer(request));
				break;
			case "update":
				updateCareer(request);
				break;
			default:
				break;
			}
			view = request.getRequestDispatcher("/JSP/private/offer/career.jsp");
			view.forward(request, response);
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
			this.output = "Error al buscar una opción";
		}
		request.setAttribute("output", output);
	}
	
	public String createCareer(HttpServletRequest request) {
		
		String name;
		int time;
		Career career;
		
		try {
			name = request.getParameter("name");
			time = Integer.parseInt(request.getParameter("time"));
			career = new Career(name, time);
			careerDAO.create(career);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":createCareer > " + e.toString();
			this.logger.log(Level.INFO, message);
			return "Error " + e.getMessage();
		}		
	}
	
	public Career readCareer(HttpServletRequest request) {
		Career career;
		int careerId;
		try {
			careerId = Integer.parseInt(request.getParameter("careerId"));
			career = careerDAO.read(careerId);
		} catch (Exception e) {
			career = null;
		}
		return career;
	}
	
	public List<Career> searchCareer(HttpServletRequest request) {
		List<Career> careers;
		String careerName;
		try {
			careerName = request.getParameter("careerName");
			
			String order = "car_name like " + careerName;
			careers = careerDAO.find(order, 0, 0);
		} catch (Exception e) {
			careers = null;
		}
		return careers;
	}

	public String updateCareer(HttpServletRequest request) {
		
		String name;
		int time;
		Career career;
		
		try {
			name = request.getParameter("name");
			time = Integer.parseInt(request.getParameter("time"));
			career = readCareer(request);
			career.setName(name);
			career.setTime(time);
			careerDAO.update(career);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":updateCareer > " + e.toString();
			this.logger.log(Level.INFO, message);
			return "Error " + e.getMessage();
		}		
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Career career;
		this.doGet(request, response);
		career = readCareer(request);
		if (career == null) {
			response.getWriter().append(this.output);
		} else {
			response.getWriter().append(this.output);
		}
	}

}
