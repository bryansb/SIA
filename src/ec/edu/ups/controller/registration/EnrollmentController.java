package ec.edu.ups.controller.registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.dao.registration.EnrollmentDAO;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.accounting.BillHead;
import ec.edu.ups.entities.offer.Career;
import ec.edu.ups.entities.offer.Group;
import ec.edu.ups.entities.offer.Subject;
import ec.edu.ups.entities.registration.Enrollment;
import ec.edu.ups.entities.registration.Grade;
import ec.edu.ups.entities.registration.Inscription;

/**
 * Servlet implementation class EnrollmentController
 */
@WebServlet("/EnrollmentController")
public class EnrollmentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> EnrollmentController";
	private static final String URL = "/JSP/private/registration/student/studentEnrollment.jsp";
	private EnrollmentDAO enrollmentDAO;
	private CareerDAO careerDAO;
	private SubjectDAO subjectDAO;
	private GroupDAO groupDAO;
	private InscriptionDAO inscriptionDAO;
	private InscriptionController inscriptionController;
	private GradeController gradeController;
	private String output;
	private int level;
	private Logger logger;
	
	private HttpSession session;
	
	private Inscription inscription;
	private List<Subject> subjectList;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollmentController() {
        super();
        this.enrollmentDAO = DAOFactory.getFactory().getEnrollmentDAO();
        this.subjectDAO = DAOFactory.getFactory().getSubjectDAO();
        this.groupDAO = DAOFactory.getFactory().getGroupDAO();
        this.inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
        this.inscriptionController = new InscriptionController();
        this.gradeController = new GradeController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String option;
		session = request.getSession(false);
		try {
			option = request.getParameter("option");
			switch (option) {
			case "create":
				this.output = createEnrollment(request);
				break;
			case "read":
				request.setAttribute("enrollment", readEnrollment(request));
				break;
			case "enrollment":
				studentEnrollment(request, response);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
			this.output = "Error al buscar una opción";
		}
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("output", this.output);
	}

	private void studentEnrollment(HttpServletRequest request, HttpServletResponse response) {
		try {
			level = 0;
			setLevelValues(request);
			getServletContext().getRequestDispatcher(URL).forward(request, response);
		} catch (ServletException e) {
			this.logger.log(Level.WARNING, e.getMessage());
		} catch (IOException e) {
			this.logger.log(Level.WARNING, e.getMessage());
		}
	}

	private void setLevelValues(HttpServletRequest request) {
		switch (level) {
		case 0:
			setInscriptionToRequest(request);
			setSubjectListToRequest(request);
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		default:
			break;
		}
		request.setAttribute("level", level);
	}

	private void setInscriptionToRequest(HttpServletRequest request) {
		int studentId = 1;
		if(session != null) {
			inscription = (Inscription) session.getAttribute("inscription");
			if (inscription == null) {
				inscription = inscriptionDAO.getInscriptionByStudentId(studentId);
				session.setAttribute("inscription", inscription);
			}
		}
		request.setAttribute("inscription", inscription);
	}

	@SuppressWarnings("unchecked")
	private void setSubjectListToRequest(HttpServletRequest request) {
		try {
			if (session != null) {
				subjectList = (List<Subject>) session.getAttribute("subjectList");
				if (subjectList == null) {
					String[][] attributes = {{"career", "id"}};
					String[] values = {"equal&" + inscription.getCareer().getId()};
					String[] order = {"name"};
					subjectList = subjectDAO.findByPath(attributes, values, order, 
							0, 0, true, true);
					session.setAttribute("subjectList", subjectList);
				}
			}
			request.setAttribute("subjectList", subjectList);
		} catch (Exception e) {
			String message = ERROR_ROOT + ":setSubjectListToRequest > " + e.toString();
			this.logger.log(Level.INFO, message);
		}
	}

	public String createEnrollment(HttpServletRequest request) {
		Inscription inscription;
		Enrollment enrollment;
		BillHead billHead;
		
		try {
			enrollment = new Enrollment(new GregorianCalendar());
			inscription = inscriptionController.readInscription(request);
			setGradeList(request, enrollment);
			billHead = getBillHead(enrollment.getGradeList());
			if (inscription == null) {
				throw new NullPointerException("No existe inscripción");
			}
			if (billHead == null) {
				throw new NullPointerException("No se pudo concretar el Detalle");
			}
			enrollment.setInscription(inscription);
			enrollment.setBillHead(billHead);
			this.enrollmentDAO.create(enrollment);
			return "Success";
		} catch (Exception e) {
			String message = ERROR_ROOT + ":createEnrollment > " + e.toString();
			this.logger.log(Level.INFO, message);
			return "Error " + e.getMessage(); 
		}
	}
	
	public Enrollment readEnrollment(HttpServletRequest request) {
		Enrollment enrollment;
		int enrollmentId;
		
		try {
			enrollmentId = Integer.parseInt(request.getParameter("enrollmentId"));
			enrollment = enrollmentDAO.read(enrollmentId);
		} catch (Exception e) {
			enrollment = null;
		}
		return enrollment;
	}
	
	private void  setGradeList(HttpServletRequest request, Enrollment enrollment) {
		List<Integer> groupIdList;
		
		try {
			groupIdList = getGroupIdListByRequest(request);
			List<Group> groupList = gradeController.getGroupListByIdList(groupIdList);
			for (Group group : groupList) {
				enrollment.createGrade("", 0.0, group);
			}
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
		
	}
	
	private BillHead getBillHead(List<Grade> gradeList) {
		BillHead billHead = new BillHead();
		for (Grade grade : gradeList) {
			billHead.createBillDetail(grade.getGroup().getAcademicPeriod() + " group Id " 
					+ grade.getGroup().getId(), grade.getGroup().getSubject().getCredits(), 
					grade.getGroup().getSubject().getCost());
		}
		if (!billHead.calculateTotal()) {
			return null;
		}
		billHead.setDate(new GregorianCalendar());
		return billHead;
	}
	
	private List<Integer> getGroupIdListByRequest(HttpServletRequest request) 
			throws NumberFormatException {
		List<Integer> groupIdList = new ArrayList<Integer>();
		String[] groupIds = request.getParameterValues("groupId");
		for (String groupId : groupIds) {
			groupIdList.add(Integer.parseInt(groupId));
		}
		return groupIdList;
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Enrollment enrollment;
		this.doGet(request, response);
		enrollment = readEnrollment(request);
		if (enrollment == null) {
			response.getWriter().append(this.output);
		} else {
			response.getWriter().append(this.output);
		}
	}
}
