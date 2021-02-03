package ec.edu.ups.test.controller.registration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.controller.registration.EnrollmentController;
import ec.edu.ups.controller.registration.InscriptionController;
import ec.edu.ups.controller.utils.ParameterBasicCreation;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AccountDAO;
import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.entities.accounting.Account;
import ec.edu.ups.entities.offer.Group;
import ec.edu.ups.entities.offer.Subject;

class EnrollmentControllerTest {

	private static final Logger LOGGER = Logger.getLogger(EnrollmentControllerTest.class.getName());
	
	private EnrollmentController enrollmentController;
	private InscriptionController inscriptionController;
	private ParameterBasicCreation parameterBasicCreation;
	private AccountDAO accountDAO;
	private GroupDAO groupDAO;
	private SubjectDAO subjectDAO;
	private HttpServletRequest request;
    private HttpServletResponse response;
    private String[] groupIds;
	
	@BeforeEach
	void setUp() {
		enrollmentController = new EnrollmentController();
		inscriptionController = new InscriptionController();
		parameterBasicCreation = new ParameterBasicCreation();
		accountDAO = DAOFactory.getFactory().getAccountDAO();
		groupDAO = DAOFactory.getFactory().getGroupDAO();
		subjectDAO = DAOFactory.getFactory().getSubjectDAO();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class);
	}

	@Test
	void test() {
		String output;
		createInscription();
		createGroup();
		createAccount();
		createGroupIds();
		try {
			parameterBasicCreation.init();
			request = mock(HttpServletRequest.class);       
	        response = mock(HttpServletResponse.class);
			when(request.getParameter("option")).thenReturn("create");
			when(request.getParameter("inscriptionId")).thenReturn("1");
			when(request.getParameter("enrollmentId")).thenReturn("1");
			when(request.getParameterValues("groupId")).thenReturn(groupIds);
			
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
        
			when(response.getWriter()).thenReturn(writer);
			enrollmentController.doTest(request, response);
			verify(request, atLeast(1)).getParameter("inscriptionId");
	        writer.flush();
	        output = stringWriter.toString();
	        String mss = " >> Response: " + output;
	        LOGGER.log(Level.INFO, mss);
			assertTrue(output.contains("Success"));
		} catch (IOException | ServletException e) {
			LOGGER.log(Level.INFO, e.toString());
		}
	}
	
	void createInscription() {
		String output;
		try {
			when(request.getParameter("option")).thenReturn("create");
	        when(request.getParameter("studentId")).thenReturn("1");
	        when(request.getParameter("careerId")).thenReturn("1");
	        when(request.getParameter("inscriptionId")).thenReturn("1");
			
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);
	        
	        inscriptionController.doTest(request, response);
	        
	        verify(request, atLeast(1)).getParameter("inscriptionId");
	        writer.flush();
	        output = stringWriter.toString();
	        String mss = " >> Response: " + output;
	        LOGGER.log(Level.INFO, mss);
	        assertTrue(output.contains("Success"));
		} catch (IOException e) {
			LOGGER.log(Level.INFO, e.toString());
		}
	}
	
	void createGroup() {
		Group group;
		for (int i = 0; i < 3; i++) {
			group = new Group();
			try {
				Subject subject = new Subject();
				subject.setCost(1.0);
				subject.setCredits(150);
				group.setSubject(subject);
				subjectDAO.create(subject);
				subject = subjectDAO.read(i + 1);
				groupDAO.create(group);
			} catch (Exception e) {
				LOGGER.log(Level.INFO, e.toString());
			}
		}
	}

	void createAccount() {
		String name = "CAJA CONTABLE";
		Account account = new Account();
		account.setName(name);
		account.setBalance(0.0);
		try {
			accountDAO.create(account);
		} catch (Exception e) {
			LOGGER.log(Level.INFO, e.toString());
		}
	}
	
	void createGroupIds() {
		List<Group> groupList;
		try {
			groupList = groupDAO.find(null, 0, 0);
			groupList = groupList.subList(groupList.size() - 3, groupList.size() - 1);
			groupIds = new String[groupList.size()];
			for (int i = 0; i < groupList.size(); i++) {
				groupIds[i] = groupList.get(i).getId() + "";
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, e.toString());
		}
	}
	
}
