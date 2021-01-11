package ec.edu.ups.test.controller.management;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import ec.edu.ups.controller.management.StudentController;

public class StudentControllerTest {

	private StudentController studentController;
	private HttpServletRequest request;
    private HttpServletResponse response;
    
    @BeforeEach
    void setUp() throws Exception {
    	studentController = new StudentController();
    	request = mock(HttpServletRequest.class);
    	response = mock(HttpServletResponse.class);
    }
    
    @Test
    void test() throws ServletException, IOException, Exception {
    	when(request.getParameter("option")).thenReturn("create");
    	when(request.getParameter("use_id")).thenReturn("2");
    	when(request.getParameter("use_address")).thenReturn("Euclides");
    	when(request.getParameter("use_dni")).thenReturn("0105100606");
    	when(request.getParameter("use_email")).thenReturn("calvarezz@est.ups.edu.ec");
    	when(request.getParameter("use_full_name")).thenReturn("Carlos Alvarez");
    	when(request.getParameter("use_password")).thenReturn("12345");
    	when(request.getParameter("use_phone")).thenReturn("0983232969");
    	when(request.getParameter("use_type")).thenReturn("S");
    	when(request.getParameter("use_birthdate")).thenReturn("4/10/1997");
    	when(request.getParameter("use_gender")).thenReturn("M");
    	
    	StringWriter stringWriter = new StringWriter();
    	PrintWriter writer = new PrintWriter(stringWriter);
    	when(response.getWriter()).thenReturn(writer);
    	
    	studentController.doTest(request, response);
    	
    	verify(request, atLeast(1)).getParameter("option");
    	writer.flush();
    	System.out.println(" >> Response: "+stringWriter.toString());
    	assertEquals("Success", stringWriter.toString());
    }
    
    @Test
    void test2() throws ServletException, IOException {
    	when(request.getParameter("option")).thenReturn("update");
    	when(request.getParameter("use_id")).thenReturn("2");
    	when(request.getParameter("use_address")).thenReturn("Euclides");
    	when(request.getParameter("use_dni")).thenReturn("0105100606");
    	when(request.getParameter("use_email")).thenReturn("calvarezz@est.ups.edu.ec");
    	when(request.getParameter("use_full_name")).thenReturn("Carlos Alvarez");
    	when(request.getParameter("use_password")).thenReturn("12345");
    	when(request.getParameter("use_phone")).thenReturn("0983232969");
    	when(request.getParameter("use_type")).thenReturn("S");
    	when(request.getParameter("use_birthdate")).thenReturn("04/10/1997");
    	when(request.getParameter("use_gender")).thenReturn("M");
    	
    	StringWriter stringWriter = new StringWriter();
    	PrintWriter writer = new PrintWriter(stringWriter);
    	when(response.getWriter()).thenReturn(writer);
    	
    	studentController.doTest(request, response);
    	
    	verify(request, atLeast(1)).getParameter("option");
    	writer.flush();
    	System.out.println(" >> Response: "+stringWriter.toString());
    	assertEquals("Success", stringWriter.toString());
    }
    @Test
    void test3() throws ServletException, IOException {
    	
    	when(request.getParameter("option")).thenReturn("read");
    	when(request.getParameter("use_id")).thenReturn("2");
    	
    	StringWriter stringWriter = new StringWriter();
    	PrintWriter writer = new PrintWriter(stringWriter);
    	when(response.getWriter()).thenReturn(writer);
    	
    	studentController.doTest(request, response);
    	
    	verify(request, atLeast(1)).getParameter("option");
    	writer.flush();
    	System.out.println(" >> Response: "+stringWriter.toString());
    	assertEquals("Success", stringWriter.toString());
    }
}
