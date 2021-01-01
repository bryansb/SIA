package ec.edu.ups.controller.registration;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.StudentDAO;
import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.management.Student;
import ec.edu.ups.entities.offer.Career;
import ec.edu.ups.entities.registration.Inscription;

/**
 * Servlet implementation class InscriptionController
 */
@WebServlet("/InscriptionController")
public class InscriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InscriptionDAO inscriptionDAO;
	private StudentDAO studentDAO;
	private CareerDAO careerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionController() {
        super();
        inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
        studentDAO = DAOFactory.getFactory().getStudentDAO();
        careerDAO = DAOFactory.getFactory().getCareerDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String option;
		String output = "";
		try {
			option = request.getParameter("option");
			switch (option) {
			case "create":
				output = createInscription(request);
				break;
			case "read":
				request.setAttribute("inscription", readInscription(request));
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public String createInscription(HttpServletRequest request) {
		int studentId;
		int careerId;
		Student student;
		Career career;
		Inscription inscription;
		try {
			studentId = Integer.parseInt(request.getParameter("stu_id"));
			careerId = Integer.parseInt(request.getParameter("car_id"));
			student = studentDAO.read(studentId);
			career = careerDAO.read(careerId);
			inscription = new Inscription(new Date(), student, career);
			inscriptionDAO.create(inscription);
			return "Success";
		} catch (Exception e) {
			System.out.println(">>> Error >> Servlet:InscriptionController:"
					+ "createInscription: > " + e.getMessage());
		}
		return "Error";
	}
	
	public Inscription readInscription(HttpServletRequest request) {
		Inscription inscription;
		int inscriptionId;
		try {
			inscriptionId = Integer.parseInt(request.getParameter("ins_id"));
			inscription = inscriptionDAO.read(inscriptionId);
		} catch (Exception e) {
			inscription = null;
		}
		return inscription;
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Inscription inscription;
		this.doGet(request, response);
		inscription = readInscription(request);
		if (inscription == null) {
			response.getWriter().append("Error");
		} else {
			response.getWriter().append("Success");
		}
	}
	
}
