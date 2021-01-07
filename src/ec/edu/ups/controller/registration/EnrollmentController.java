package ec.edu.ups.controller.registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.registration.EnrollmentDAO;
import ec.edu.ups.entities.accounting.BillHead;
import ec.edu.ups.entities.registration.Enrollment;
import ec.edu.ups.entities.registration.Grade;
import ec.edu.ups.entities.registration.Inscription;

/**
 * Servlet implementation class EnrollmentController
 */
@WebServlet("/EnrollmentController")
public class EnrollmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private EnrollmentDAO enrollmentDAO;
	private InscriptionController inscriptionController;
	private GradeController gradeController;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollmentController() {
        super();
        enrollmentDAO = DAOFactory.getFactory().getEnrollmentDAO();
        inscriptionController = new InscriptionController();
        gradeController = new GradeController();
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
		Inscription inscription;
		Enrollment enrollment;
		BillHead billHead;
		List<Grade> gradeList;
		try {
			inscription = inscriptionController.readInscription(request);
			gradeList = getGradeList(getGroupIdListByRequest(request));
			billHead = getBillHead(gradeList);
			if (inscription == null) {
				throw new NullPointerException("No existe inscripción");
			}
			if (billHead == null) {
				throw new NullPointerException("No se pudo concretar el Detalle");
			}
			enrollment = new Enrollment(new Date());
			enrollment.setInscription(inscription);
			enrollment.setBillHead(billHead);
			enrollment.setGradeList(gradeList);
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
			enrollmentId = Integer.parseInt(request.getParameter("enrollmentId"));
			enrollment = enrollmentDAO.read(enrollmentId);
		} catch (Exception e) {
			enrollment = null;
		}
		return enrollment;
	}
	
	private List<Grade>  getGradeList(List<Integer> groupIdList) {
		return gradeController.createGradeListByGroupIdList(groupIdList);
	}
	
	private BillHead getBillHead(List<Grade> gradeList) {
		BillHead billHead = new BillHead();
		for (Grade grade : gradeList) {
			billHead.createBillDetail("", 100, 0.4);
		}
		if (!billHead.calculateTotal()) {
			return null;
		}
		return billHead;
	}
	
	private List<Integer> getGroupIdListByRequest(HttpServletRequest request) throws Exception {
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
			response.getWriter().append("Error");
		} else {
			response.getWriter().append("Success");
		}
	}
}
