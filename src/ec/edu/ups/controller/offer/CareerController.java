package ec.edu.ups.controller.offer;

import java.io.IOException;

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
		String option;
		String output = "";
		try {
			option = request.getParameter("option");
			switch (option) {
			case "create":
				output = createCareer(request);
				break;
			case "read":
				request.setAttribute("career", readCareer(request));
				break;
			case "update":
				updateCareer(request);
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
			System.out.println(">>> Error >> Servlet:CareerController:"
					+ "createCareer: > " + e);
		}
		
		return "Error";
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
			System.out.println(">>> Error >> Servlet:CareerController:"
					+ "updateCareer: > " + e);
		}
		
		return "Error";
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Career career;
		this.doGet(request, response);
		career = readCareer(request);
		if (career == null) {
			response.getWriter().append("Error");
		} else {
			response.getWriter().append("Success");
		}
	}

}
