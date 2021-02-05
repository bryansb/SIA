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
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.entities.offer.Career;
import ec.edu.ups.entities.offer.Subject;

/**
 * Servlet implementation class StudyPlan
 */
@WebServlet("/StudyPlan")
public class StudyPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ERROR_ROOT = ">>> Error >> StudentSchedule:";
	private Logger logger;
	
	private SubjectDAO subjectDAO;
	private CareerDAO careerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudyPlan() {
        super();
        logger = Logger.getLogger(StudentSchedule.class.getName());
        subjectDAO = DAOFactory.getFactory().getSubjectDAO();
        careerDAO = DAOFactory.getFactory().getCareerDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String option = request.getParameter("option");
		
		if (option != null && option.equals("find")) {
			System.out.println("entra");
			subjects(request);
		}
		updateRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void updateRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			List<Career> careers = careerDAO.find("name", 0, 0);
			request.setAttribute("careers", careers);
			RequestDispatcher view;
			view = request.getRequestDispatcher("/JSP/private/offer/studyPlan.jsp");
			view.forward(request, response);
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.toString());
		}
	}
	
	public void subjects(HttpServletRequest request) {
		try {
			int careerId = Integer.parseInt(request.getParameter("careerId"));
			List<Subject> subjects = subjectDAO.findByCareerID(careerId);
			request.setAttribute("subjects", subjects);
		} catch (Exception e) {
			return;
		}
	}


}
