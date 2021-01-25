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
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.dao.registration.EnrollmentDAO;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.accounting.BillHead;
import ec.edu.ups.entities.offer.Group;
import ec.edu.ups.entities.offer.Subject;
import ec.edu.ups.entities.registration.Enrollment;
import ec.edu.ups.entities.registration.Grade;
import ec.edu.ups.entities.registration.Inscription;

/**
 * Servlet implementation class EnrollmentController
 */
@WebServlet("/EnrollStudent")
public class EnrollmentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> EnrollmentController";
	private static final String URL = "/JSP/private/registration/student/studentEnrollment.jsp";
	private EnrollmentDAO enrollmentDAO;
	private SubjectDAO subjectDAO;
	private InscriptionDAO inscriptionDAO;
	private GradeController gradeController;
	private String output;
	private String noticeClass;
	private int level;
	private Logger logger;
	private HttpSession session;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollmentController() {
        super();
        this.enrollmentDAO = DAOFactory.getFactory().getEnrollmentDAO();
        this.subjectDAO = DAOFactory.getFactory().getSubjectDAO();
        this.inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
        this.gradeController = new GradeController();
        noticeClass = "none";
        output = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String option;
		session = request.getSession(true);
		try {
			option = request.getParameter("option");
			switch (option) {
			case "create":
				createEnrollment(request);
				break;
			case "read":
				request.setAttribute("enrollment", readEnrollment(request));
				break;
			case "enrollment":
				studentEnrollment(request, response);
				break;
			default:
				output = "Error al buscar una opción";
				break;
			}
		} catch (Exception e) {
			logger.log(Level.INFO, e.getMessage());
		}
	}

	private void studentEnrollment(HttpServletRequest request, HttpServletResponse response) {
		try {
			noticeClass = "none";
	        output = "";
			String levelString = request.getParameter("level");
			level = Integer.parseInt(levelString == null ? "0" : levelString);
			setLevelValues(request);
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("output", output);
			request.setAttribute("noticeClass", noticeClass);
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
			subjectSelection(request);
			break;
		case 1:
			groupSelection(request);
			break;
		case 2:
			enrollmentSummary(request);
			break;
		case 3:
			enrollmentPrefecture();
			break;
		case 4:
			saveEnrollment();
			break;
		default:
			level = 0;
			break;
		}
		request.setAttribute("level", level);
	}

	private void subjectSelection(HttpServletRequest request) {
		if (!setInscriptionToRequest(request)) {
			return;
		}
		setSubjectListToRequest(request);
	}
	
	private void groupSelection(HttpServletRequest request) {
		session.setAttribute("groupIdList", null);
		session.setAttribute("enrollment", null);
		String[] subjectIdArray = (String[]) session.getAttribute("subjectIdArray");
		if (subjectIdArray == null || subjectIdArray.length == 0) {
			subjectIdArray = request.getParameterValues("subjectId");
			session.setAttribute("subjectIdArray", subjectIdArray);
		}
		if (subjectIdArray == null || subjectIdArray.length == 0) {
			level = 0;
			noticeClass = "bg-danger";
			output = "Seleccione una o más Asignaturas disponibles";
			return;
		}
		List<Subject> subjectList = new ArrayList<>();
		for (String subjectId : subjectIdArray) {
			Subject subject = subjectDAO.read(Integer.parseInt(subjectId));
			subjectList.add(subject);
		}
		session.setAttribute("subjectList", subjectList);
	}


	private void enrollmentSummary(HttpServletRequest request) {
		if (((Enrollment) session.getAttribute("enrollment")) == null) {
			createEnrollment(request);
		}
	}
	
	private void enrollmentPrefecture() {
		output = "";
		noticeClass = "none";
	}

	public void saveEnrollment() {
		Enrollment enrollment = (Enrollment) session.getAttribute("enrollment");
		level = 4;
		if (enrollment == null) {
			noticeClass = "bg-danger";
			output = "No se pudo crear la matrícula, inténtelo de nuevo";
			return;
		}
		try {
			if (enrollment.validGroupQuota()) {
				this.enrollmentDAO.create(enrollment);
				output = "Su matrícula fue creada con éxito";
				noticeClass = "bg-success";
				endProcess();
			} else {
				output = "No se pudo concretar su matrícula, debido a que no quedan suficientes cupos. "
						+ "Inténtelo de nuevo o póngase en contacto con la institución.";
				noticeClass = "bg-danger";
			}
			
		} catch (Exception e) {
			String message = ERROR_ROOT + ":createEnrollment > " + e.toString();
			this.logger.log(Level.INFO, message);
		}
	}
	
	private boolean setInscriptionToRequest(HttpServletRequest request) {
		int studentId = 12;
		Inscription inscription;
		session.setAttribute("inscription", null);
		if(session != null) {
			inscription = inscriptionDAO.getInscriptionByStudentId(studentId);
			if (enrollmentDAO.isEnrolledByInscriptionId(inscription.getId())) {
				level = 4;
				output = "Ya se encuentra Matriculado en el sistema";
				noticeClass = "bg-info";
				return false;
			}
			session.setAttribute("inscription", inscription);
		}
		return true;
	}

	private void setSubjectListToRequest(HttpServletRequest request) {
		List<Subject>  subjectList = null;
		session.setAttribute("subjectList", null);
		session.setAttribute("subjectIdArray", null);
		Inscription inscription = (Inscription) session.getAttribute("inscription");
		try {
			if (session != null) {
				if (inscription != null) {
					subjectList = subjectDAO.findByInscriptionIdToEnrollment(inscription.getId());
					session.setAttribute("subjectList", subjectList);
				} else {
					level = 0;
					output = "Sesión caducada, vuelva a iniciar sesión";
					noticeClass = "bg-info";
					return;
				}
			}
			request.setAttribute("subjectList", subjectList);
		} catch (Exception e) {
			String message = ERROR_ROOT + ":setSubjectListToRequest > " + e.toString();
			this.logger.log(Level.INFO, message);
		}
	}

	public void createEnrollment(HttpServletRequest request) {
		Enrollment enrollment;
		BillHead billHead;
		session.setAttribute("groupIdList", null);
		Inscription inscription = (Inscription) session.getAttribute("inscription");
		enrollment = new Enrollment(new GregorianCalendar(), 
				enrollmentDAO.getCurrentAcademicPeriod(), 'C');
		if (!setGradeList(request, enrollment)) {
			level = 1;
			noticeClass = "bg-danger";
			output = "Seleccione un Grupo por Asignatura";
			return;
		}
		billHead = getBillHead(enrollment.getGradeList());
		level = 1;
		noticeClass = "bg-danger";
		if (inscription == null) {
			output = "No existe una inscripción";
			return;
		}
		if (billHead == null) {
			output = "No se pudo concretar el Detalle";
			return;
		}
		enrollment.setInscription(inscription);
		enrollment.setBillHead(billHead);
		if (enrollment.validGroupQuota()) {
			level = 2;
			output = "";
			noticeClass = "none";
			session.setAttribute("enrollment", enrollment);
		} else {
			output = "No quedan suficientes cupos. ";
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
	
	private boolean setGradeList(HttpServletRequest request, Enrollment enrollment) {
		List<Integer> groupIdList;
		groupIdList = getGroupIdListByRequest(request);
		if (groupIdList == null || groupIdList.size() == 0) {
			return false;
		}
		List<Group> groupList = gradeController.getGroupListByIdList(groupIdList);
		for (Group group : groupList) {
			enrollment.createGrade("Cursando...", 0.0, 'C', group);
		}
		return true;
	}
	
	private BillHead getBillHead(List<Grade> gradeList) {
		BillHead billHead = new BillHead();
		for (Grade grade : gradeList) {
			billHead.createBillDetail(grade.getGroup().getAcademicPeriod() + " "
					+ grade.getGroup().getPhysicalSpace()  + " ID " 
					+ grade.getGroup().getId(), 
					grade.getGroup().getSubject().getCredits(), 
					grade.getGroup().getSubject().getCost());
		}
		if (!billHead.calculateTotal()) {
			return null;
		}
		billHead.setDate(new GregorianCalendar());
		return billHead;
	}
	
	@SuppressWarnings("unchecked")
	private List<Integer> getGroupIdListByRequest(HttpServletRequest request) {
		List<Integer> groupIdList = (List<Integer>) session.getAttribute("groupIdList");
		if (groupIdList != null && groupIdList.size() > 0) {
			return groupIdList;
		}
		String[] subjectIdArray = (String[]) session.getAttribute("subjectIdArray");
		if (subjectIdArray == null || subjectIdArray.length == 0) {
			return null;
		}
		groupIdList = new ArrayList<>();
		for (String subjectId : subjectIdArray) {
			try{
				String groupId = request.getParameter("groupId-" + subjectId);
				groupIdList.add(Integer.parseInt(groupId));
			} catch (Exception e) {
				return null;
			}
		}
		session.setAttribute("groupIdList", groupIdList);
		return groupIdList;
	}
	
	public void endProcess() {
		session.setAttribute("subjectList", null);
		session.setAttribute("groupIdList", null);
		session.setAttribute("subjectIdArray", null);
		session.setAttribute("enrollment", null);
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
