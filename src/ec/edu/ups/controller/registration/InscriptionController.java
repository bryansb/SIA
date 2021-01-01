package ec.edu.ups.controller.registration;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.registration.Inscription;

/**
 * Servlet implementation class InscriptionController
 */
@WebServlet("/InscriptionController")
public class InscriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InscriptionDAO inscriptionDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionController() {
        super();
        inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
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
				output = createInscription(request);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("output", output);
		response.getWriter().append(output);
	}
	
	private String createInscription(HttpServletRequest request) {
		int studentId;
		int careerId;
		Inscription inscription;
		
		try {
			studentId = Integer.parseInt(request.getParameter("stu_id"));
			careerId = Integer.parseInt(request.getParameter("car_id"));
			inscription = new Inscription();
			inscription.setDate(new Date());
			inscriptionDAO.create(inscription);
			return "Success";
		} catch (Exception e) {
			return "Error";
		}
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}
