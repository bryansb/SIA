package ec.edu.ups.controller.registration;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.registration.EnrollmentDAO;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.accounting.BillHead;
import ec.edu.ups.entities.registration.Enrollment;
import ec.edu.ups.entities.registration.Inscription;

/**
 * Servlet implementation class EnrollmentController
 */
@WebServlet("/EnrollmentController")
public class EnrollmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private EnrollmentDAO enrollmentDAO;
	private InscriptionDAO inscriptionDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollmentController() {
        super();
        enrollmentDAO = DAOFactory.getFactory().getEnrollmentDAO();
        inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
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
				output = createEnrollment(request);
				break;
			case "read":
				request.setAttribute("enrollment", readEnrollment(request));
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

	public String createEnrollment(HttpServletRequest request) {
		int inscriptionId;
		Inscription inscription;
		Enrollment enrollment;
		BillHead billHead;
		try {
			inscriptionId = Integer.parseInt(request.getParameter("ins_id"));
			inscription = inscriptionDAO.read(inscriptionId);
			if (inscription == null) {
//				return "Error";
			}
			/* BillHeadController and GradeController */
			billHead = new BillHead();
			enrollment = new Enrollment(new Date());
			enrollment.setInscription(inscription);
			enrollment.setBillHead(billHead);
			enrollmentDAO.create(enrollment);
			return "Success";
		} catch (Exception e) {
			System.out.println(">>> Error >> Servlet:EnrollmentController:"
					+ "createEnrollment: > " + e.getMessage());
		}
		
		return "Error";
	}
	
	public Enrollment readEnrollment(HttpServletRequest request) {
		Enrollment enrollment;
		int enrollmentId;
		try {
			enrollmentId = Integer.parseInt(request.getParameter("enr_id"));
			System.out.println(enrollmentId);
			enrollment = enrollmentDAO.read(enrollmentId);
		} catch (Exception e) {
			enrollment = null;
		}
		return enrollment;
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Enrollment enrollment;
		this.doGet(request, response);
		enrollment = readEnrollment(request);
		if (enrollment == null) {
			response.getWriter().append("Error");
		} else {
			response.getWriter().append("Success");
		}
	}
}
