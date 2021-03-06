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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import ec.edu.ups.controller.registration.GradeController;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.registration.GradeDAO;
import ec.edu.ups.entities.registration.Grade;

@TestMethodOrder(OrderAnnotation.class)
class GradeControllerTest {

	private GradeController gradeController;
	private HttpServletRequest request;
    private HttpServletResponse response;
    private GradeDAO gradeDAO;
    private int gradeId;
    private Logger logger;
	
	@BeforeEach
	void setUp() {
		this.gradeController = new GradeController();
		this.gradeDAO = DAOFactory.getFactory().getGradeDAO();
		this.request = mock(HttpServletRequest.class);     
		this.response = mock(HttpServletResponse.class);
	}
	
	void createGrade() {
		Grade grade = new Grade("TEST", 0.0, null, 'N', null);
		try {
			gradeDAO.create(grade);
			List<Grade> gradeList = gradeDAO.find(null, 0, 0);
			gradeId = gradeList.get(gradeList.size() - 1).getId();
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.toString());
		}
	}
	
	@Test
	@Order(1)
	void updateTest() {
		String output;
		createGrade();
		try {
			when(request.getParameter("option")).thenReturn("update");
			when(request.getParameter("description")).thenReturn("Test JUnit");
	        when(request.getParameter("gradeValue")).thenReturn("95.0");
	        when(request.getParameter("gradeId")).thenReturn("" + gradeId);
			
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);
	        
	        gradeController.doTest(request, response);
	        
	        verify(request, atLeast(1)).getParameter("gradeId");
	        writer.flush();
	        output = stringWriter.toString();
	        System.out.println(" >> Response: " + output);
			assertTrue(output.contains("95.0"));
		} catch (IOException e) {
			this.logger.log(Level.INFO, e.toString());
		}
	}

}
