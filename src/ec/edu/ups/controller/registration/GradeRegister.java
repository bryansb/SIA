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
import ec.edu.ups.entities.registration.Grade;

/**
 * Servlet implementation class GradeRegister
 */
@WebServlet("/GradeRegister")
public class GradeRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> GradeRegister";
	private static final String URL = "/JSP/private/registration/teacher/gradeRegister.jsp";
	private GradeDAO gradeDAO;
	private List<Grade> gradeList;
	private String output;
	private String noticeClass;
	private Logger logger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeRegister() {
        super();
        gradeDAO = DAOFactory.getFactory().getGradeDAO();
        logger = Logger.getLogger(GradeRegister.class.getName());
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
			switch (option) {
			case "update":
				updateGrade(request);
			default:
				setGradesToRequest(request, response);
				break;
			}
		} catch (Exception e) {
			logger.log(Level.INFO, ERROR_ROOT + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void setGradesToRequest(HttpServletRequest request, HttpServletResponse response) {
		int teacherId = 2;
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
			logger.log(Level.INFO, ERROR_ROOT + e.getMessage());
		}
	}
	
	private void updateGrade(HttpServletRequest request) {
		if(gradeList == null || gradeList.size() == 0) {
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
