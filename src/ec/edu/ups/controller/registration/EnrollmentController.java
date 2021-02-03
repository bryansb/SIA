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
import ec.edu.ups.entities.management.Student;
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
	private static final String ERROR_ROOT = ">>> Error >> EnrollmentController  > ";
	private static final String URL = "/JSP/private/registration/student/studentEnrollment.jsp";
	private static final Logger LOGGER = Logger.getLogger(EnrollmentController.class.getName());
	
	private final EnrollmentDAO enrollmentDAO;
	private final SubjectDAO subjectDAO;
	private final InscriptionDAO inscriptionDAO;
	private final GradeController gradeController;
	
	private String output;
	private String noticeClass;
	private int level;
	private HttpSession session;
	
	private static final String GROUP_ID_LIST_ID = "groupIdList";
	private static final String INSCRIPTION_ID = "inscription";
	private static final String ENROLLMENT_ID = "enrollment";
	private static final String SUBJECT_ID_ARRAY_ID = "subjectIdArray";
	private static final String SUBJECT_LIST_ID = "subjectList";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollmentController() {
        super();
        this.enrollmentDAO = DAOFactory.getFactory().getEnrollmentDAO();
        this.subjectDAO = DAOFactory.getFactory().getSubjectDAO();
        this.inscriptionDAO = DAOFactory.getFactory().getInscriptionDAO();
        this.gradeController = new GradeController();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	noticeClass = "none";
        output = "";
        level = 0;
        session = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
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
				request.setAttribute(ENROLLMENT_ID, readEnrollment(request));
				break;
			case ENROLLMENT_ID:
				studentEnrollment(request, response);
				break;
			default:
				output = "Error al buscar una opción";
				break;
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, e.getMessage());
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
		} catch (ServletException | IOException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
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
		session.setAttribute(GROUP_ID_LIST_ID, null);
		session.setAttribute(ENROLLMENT_ID, null);
		String[] subjectIdArray = (String[]) session.getAttribute(SUBJECT_ID_ARRAY_ID);
		if (subjectIdArray == null || subjectIdArray.length == 0) {
			subjectIdArray = request.getParameterValues("subjectId");
			session.setAttribute(SUBJECT_ID_ARRAY_ID, subjectIdArray);
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
		session.setAttribute(SUBJECT_LIST_ID, subjectList);
	}


	private void enrollmentSummary(HttpServletRequest request) {
		if (((Enrollment) session.getAttribute(ENROLLMENT_ID)) == null) {
			createEnrollment(request);
		}
	}
	
	private void enrollmentPrefecture() {
		output = "";
		noticeClass = "none";
	}

	public void saveEnrollment() {
		Enrollment enrollment = (Enrollment) session.getAttribute(ENROLLMENT_ID);
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
			LOGGER.log(Level.INFO, message);
		}
	}
	
	private boolean setInscriptionToRequest(HttpServletRequest request) {
		int studentId = ((Student) request.getSession().getAttribute("user")).getId();
		Inscription inscription;
		if(session != null) {
			session.setAttribute(INSCRIPTION_ID, null);
			inscription = inscriptionDAO.getInscriptionByStudentId(studentId);
			if (enrollmentDAO.isEnrolledByInscriptionId(inscription.getId())) {
				level = 4;
				output = "Ya se encuentra Matriculado en el sistema";
				noticeClass = "bg-info";
				return false;
			}
			session.setAttribute(INSCRIPTION_ID, inscription);
		}
		return true;
	}

	private void setSubjectListToRequest(HttpServletRequest request) {
		List<Subject>  subjectList = null;
		try {
			if (session != null) {
				session.setAttribute(SUBJECT_LIST_ID, null);
				session.setAttribute(SUBJECT_ID_ARRAY_ID, null);
				Inscription inscription = (Inscription) session.getAttribute(INSCRIPTION_ID);
				if (inscription != null) {
					subjectList = subjectDAO.findByInscriptionIdToEnrollment(inscription.getId());
					session.setAttribute(SUBJECT_LIST_ID, subjectList);
				} else {
					level = 0;
					output = "Sesión caducada, vuelva a iniciar sesión";
					noticeClass = "bg-info";
					return;
				}
			}
			request.setAttribute(SUBJECT_LIST_ID, subjectList);
		} catch (Exception e) {
			String message = ERROR_ROOT + ":setSubjectListToRequest > " + e.toString();
			LOGGER.log(Level.INFO, message);
		}
	}

	public void createEnrollment(HttpServletRequest request) {
		Enrollment enrollment;
		BillHead billHead;
		session.setAttribute(GROUP_ID_LIST_ID, null);
		Inscription inscription = (Inscription) session.getAttribute(INSCRIPTION_ID);
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
			session.setAttribute(ENROLLMENT_ID, enrollment);
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
		if (groupIdList.isEmpty()) {
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
		List<Integer> groupIdList = (List<Integer>) session.getAttribute(GROUP_ID_LIST_ID);
		if (groupIdList != null && !groupIdList.isEmpty()) {
			return groupIdList;
		}
		String[] subjectIdArray = (String[]) session.getAttribute(SUBJECT_ID_ARRAY_ID);
		if (subjectIdArray == null || subjectIdArray.length == 0) {
			return new ArrayList<>();
		}
		groupIdList = new ArrayList<>();
		for (String subjectId : subjectIdArray) {
			try{
				String groupId = request.getParameter("groupId-" + subjectId);
				groupIdList.add(Integer.parseInt(groupId));
			} catch (Exception e) {
				return groupIdList;
			}
		}
		session.setAttribute(GROUP_ID_LIST_ID, groupIdList);
		return groupIdList;
	}
	
	public void endProcess() {
		session.setAttribute(SUBJECT_LIST_ID, null);
		session.setAttribute(GROUP_ID_LIST_ID, null);
		session.setAttribute(SUBJECT_ID_ARRAY_ID, null);
		session.setAttribute(ENROLLMENT_ID, null);
	}
	
	public void doTest(HttpServletRequest request, HttpServletResponse response) {
		Enrollment enrollment;
		try {
			this.doGet(request, response);
			enrollment = readEnrollment(request);
			if (enrollment == null) {
				response.getWriter().append(this.output + "Error");
			} else {
				response.getWriter().append(this.output);
			}
		} catch (ServletException | IOException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
		
	}
}
