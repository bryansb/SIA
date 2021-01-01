package ec.edu.ups.test.controller.registration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.controller.registration.InscriptionController;

class InscriptionControllerTest {

	private InscriptionController inscriptionController;
	private HttpServletRequest request;
    private HttpServletResponse response;
	
	@BeforeEach
	void setUp() throws Exception {
		inscriptionController = new InscriptionController();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class); 
	}

	@Test
	void test() throws ServletException, IOException {
		String output;
		
		when(request.getParameter("option")).thenReturn("create");
        when(request.getParameter("stu_id")).thenReturn("1");
        when(request.getParameter("car_id")).thenReturn("1");
        when(request.getParameter("ins_id")).thenReturn("0");
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        inscriptionController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("ins_id");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertEquals("Success", output);
	}

}
