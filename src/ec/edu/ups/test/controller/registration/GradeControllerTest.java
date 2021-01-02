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
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import ec.edu.ups.controller.registration.GradeController;

@TestMethodOrder(OrderAnnotation.class)
class GradeControllerTest {

	private GradeController gradeController;
	private HttpServletRequest request;
    private HttpServletResponse response;
	
	@BeforeEach
	void setUp() throws Exception {
		gradeController = new GradeController();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class); 
	}

	@Test
	@Order(1)
	void test() throws ServletException, IOException {
		String output;
		
		when(request.getParameter("option")).thenReturn("create");
        when(request.getParameter("enr_id")).thenReturn("1");
        when(request.getParameter("gro_id")).thenReturn("1");
        when(request.getParameter("gra_description")).thenReturn("Test JUnit");
        when(request.getParameter("gra_grade")).thenReturn("90.0");
        when(request.getParameter("gra_id")).thenReturn("1");
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        gradeController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("enr_id");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
        assertTrue(output.contains("Success"));
	}
	
	@Test
	@Order(2)
	void updateTest() throws ServletException, IOException {
		String output;
		
		when(request.getParameter("option")).thenReturn("update");
		when(request.getParameter("gra_description")).thenReturn("Test JUnit");
        when(request.getParameter("gra_grade")).thenReturn("95.0");
        when(request.getParameter("gra_id")).thenReturn("1");
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        gradeController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("gra_id");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertTrue(output.contains("95.0"));
	}

}
