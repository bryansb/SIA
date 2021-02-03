package ec.edu.ups.test.controller.registration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.controller.registration.InscriptionController;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.StudentDAO;
import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.entities.management.Student;
import ec.edu.ups.entities.offer.Career;

class InscriptionControllerTest {

	private InscriptionController inscriptionController;
	private StudentDAO studentDAO;
	private CareerDAO careerDAO;
	private HttpServletRequest request;
    private HttpServletResponse response;
    private Logger logger;
	
	@BeforeEach
	void setUp() {
		inscriptionController = new InscriptionController();
		studentDAO = DAOFactory.getFactory().getStudentDAO();
		careerDAO = DAOFactory.getFactory().getCareerDAO();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class); 
	}

	@Test
	void test() {
		String output;
		createStudent();
		createCareer();
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
	        System.out.println(" >> Response: " + output);
	        assertTrue(output.contains("Success"));
		} catch (IOException e) {
			this.logger.log(Level.INFO, e.toString());
		}
	}
	
	void createStudent() {
		Student student = new Student();
		try {
			studentDAO.create(student);
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.toString());
		}
	}
	
	void createCareer() {
		Career career = new Career();
		career.setName("Computaci�n");
		try {
			careerDAO.create(career);
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.toString());
		}
	}

}
