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

class EnrollmentControllerTest {

	private EnrollmentController enrollmentController;
	private HttpServletRequest request;
    private HttpServletResponse response;
	
	@BeforeEach
	void setUp() throws Exception {
		enrollmentController = new EnrollmentController();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class); 
	}

	@Test
	void test() throws ServletException, IOException {
		String output;
		when(request.getParameter("option")).thenReturn("create");
		when(request.getParameter("ins_id")).thenReturn("1");
		when(request.getParameter("enr_id")).thenReturn("1");
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        enrollmentController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("ins_id");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertEquals("Success", output);
	}

}
