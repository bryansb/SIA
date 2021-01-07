package ec.edu.ups.controller.offer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.offer.Career;
import ec.edu.ups.entities.offer.Subject;
import ec.edu.ups.entities.registration.Inscription;

/**
 * Servlet implementation class CareerController
 */
@WebServlet("/CareerController")
public class CareerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CareerDAO careerDAO;
	private SubjectDAO subjectDAO;
	private InscriptionDAO inscriptionDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CareerController() {
        super();
        careerDAO =  DAOFactory.getFactory().getCareerDAO();
        subjectDAO =  DAOFactory.getFactory().getSubjectDAO();
        inscriptionDAO =  DAOFactory.getFactory().getInscriptionDAO();
        
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
		int subjectId;
		int inscriptionId;
		List<Subject> subjectList;
		List<Inscription> inscriptionList;
		Career career;
		
		try {
			name = request.getParameter("car_name");
			time = Integer.parseInt(request.getParameter("car_time"));
			subjectId = Integer.parseInt(request.getParameter("sub_id"));
			subjectList = subjectDAO.find("", subjectId, 0);
			inscriptionId = Integer.parseInt(request.getParameter("ins_id"));
			inscriptionList = inscriptionDAO.find("", inscriptionId, 0);
			career = new Career(name, time, subjectList, inscriptionList);
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
			careerId = Integer.parseInt(request.getParameter("car_id"));
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
			name = request.getParameter("car_name");
			time = Integer.parseInt(request.getParameter("car_time"));
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
