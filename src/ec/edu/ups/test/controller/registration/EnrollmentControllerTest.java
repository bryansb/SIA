package ec.edu.ups.test.controller.registration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.controller.registration.EnrollmentController;
import ec.edu.ups.controller.registration.InscriptionController;

class EnrollmentControllerTest {

	private EnrollmentController enrollmentController;
	private InscriptionController inscriptionController;
	private HttpServletRequest request;
    private HttpServletResponse response;
	
	@BeforeEach
	void setUp() throws Exception {
		enrollmentController = new EnrollmentController();
		inscriptionController = new InscriptionController();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class); 
	}

	@Test
	void test() throws ServletException, IOException {
		String output;
		createInscription();
		when(request.getParameter("option")).thenReturn("create");
		when(request.getParameter("inscriptionId")).thenReturn("1");
		when(request.getParameter("enrollmentId")).thenReturn("1");
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        enrollmentController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("inscriptionId");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertEquals("Success", output);
	}
	
	void createInscription() throws ServletException, IOException {
		String output;
		
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
		assertEquals("Success", output);
	}

}
