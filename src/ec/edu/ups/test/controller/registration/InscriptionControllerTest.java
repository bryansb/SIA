package ec.edu.ups.test.controller.registration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.PrintWriter;
import java.io.StringWriter;

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
	
	@BeforeEach
	void setUp() throws Exception {
		inscriptionController = new InscriptionController();
		studentDAO = DAOFactory.getFactory().getStudentDAO();
		careerDAO = DAOFactory.getFactory().getCareerDAO();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class); 
	}

	@Test
	void test() throws Exception {
		String output;
		createStudent();
		createCareer();
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
	}
	
	void createStudent() throws Exception {
		Student student = new Student();
		studentDAO.create(student);
	}
	
	void createCareer() throws Exception {
		Career career = new Career();
		career.setName("Computación");
		careerDAO.create(career);
	}

}
