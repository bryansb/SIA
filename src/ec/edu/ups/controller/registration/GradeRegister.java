package ec.edu.ups.controller.registration;

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
import ec.edu.ups.dao.registration.GradeDAO;
import ec.edu.ups.entities.management.Teacher;
import ec.edu.ups.entities.registration.Grade;

/**
 * Servlet implementation class GradeRegister
 */
@WebServlet("/GradeRegister")
public class GradeRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> GradeRegister";
	private static final String URL = "/JSP/private/registration/teacher/gradeRegister.jsp";
	private static final Logger LOGGER = Logger.getLogger(GradeRegister.class.getName());
	
	private final GradeDAO gradeDAO;
	private List<Grade> gradeList;
	private String output;
	private String noticeClass;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeRegister() {
        super();
        gradeDAO = DAOFactory.getFactory().getGradeDAO();
        noticeClass = "none";
        output = "";
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	gradeList = null;
    	noticeClass = "none";
        output = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		noticeClass = "none";
        output = "";
		try {
			String option = request.getParameter("option") == null ? "none" :request.getParameter("option");
			if (option.equals("update")) {
				updateGrade(request);
			}
			setGradesToRequest(request, response);
		} catch (Exception e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    	try {
    		doGet(request, response);
		} catch (Exception e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
		
	}
	
	private void setGradesToRequest(HttpServletRequest request, HttpServletResponse response) {
		int teacherId = ((Teacher) request.getSession().getAttribute("user")).getId();
		gradeList = gradeDAO.findCurrentDregreListByTeacherId(teacherId);
		request.setAttribute("gradeList", gradeList);
		redirectProcess(request, response);
	}
	
	private void redirectProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("output", output);
			request.setAttribute("noticeClass", noticeClass);
			getServletContext().getRequestDispatcher(URL).forward(request, response);
		} catch (ServletException | IOException e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
	
	private void updateGrade(HttpServletRequest request) {
		if(gradeList == null || gradeList.isEmpty()) {
			noticeClass = "bg-danger";
			output = "No se encontraron registros para guardar";
			return;
		}
		try {
			for (Grade grade : gradeList) {
				double gradeValue = Double.parseDouble(request.getParameter("grade-" + grade.getId()));
				char status = request.getParameter("status-" + grade.getId()).charAt(0);
				String description = request.getParameter("description-" + grade.getId());
				grade.setGradeValue(gradeValue);
				grade.setStatus(status);
				grade.setDescription(description);
				gradeDAO.update(grade);
			}
			noticeClass = "bg-success";
			output = "Se actualizó correctamente";
		} catch (Exception e) {
			noticeClass = "bg-danger";
			output = "Ingrese sólo números";
		}
	}
}
