package ec.edu.ups.test.controller.offer;

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

import ec.edu.ups.controller.offer.CareerController;

class CareerControllerTest {
	
	private CareerController careerController;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@BeforeEach
	void setUp() throws Exception {
		careerController = new CareerController();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class);
	}

	@Test
	void test() throws ServletException, IOException {
		String output;
		
		when(request.getParameter("option")).thenReturn("create");
		when(request.getParameter("name")).thenReturn("carTest");
		when(request.getParameter("time")).thenReturn("160");
        
        when(request.getParameter("careerId")).thenReturn("1");
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        careerController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("careerId");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertEquals("Success", output);
		updateTest();
	}
	
	void updateTest() throws ServletException, IOException {
		String output;
		
		when(request.getParameter("option")).thenReturn("update");
		when(request.getParameter("name")).thenReturn("carTest1");
		when(request.getParameter("time")).thenReturn("150");
        
        when(request.getParameter("careerId")).thenReturn("1");
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        careerController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("careerId");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertEquals("Success", output);
	}

}
