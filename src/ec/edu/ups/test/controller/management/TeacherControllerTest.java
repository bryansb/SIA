package ec.edu.ups.test.controller.management;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ec.edu.ups.controller.management.TeacherController;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.DegreeDAO;
import ec.edu.ups.entities.management.Degree;

public class TeacherControllerTest {
/*
	private Logger logger;
	private TeacherController teacherController;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private DegreeDAO degreeDAO;
	
	@BeforeEach
	void setUp() throws Exception {
		teacherController = new TeacherController();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class);
        degreeDAO = DAOFactory.getFactory().getDegreeDAO();
	}
	
	@Test
	void test() throws ServletException, IOException {
		String output;
		String[] degreeIds = {"1" ,"2" , "3"};
		createDegree();
		when(request.getParameter("option")).thenReturn("create");
		when(request.getParameter("use_id")).thenReturn("8");
		when(request.getParameter("use_address")).thenReturn("Benigno Malo");
		when(request.getParameter("use_dni")).thenReturn("0105100845");
		when(request.getParameter("use_email")).thenReturn("dani@gmail.com");
		when(request.getParameter("use_full_name")).thenReturn("Pedro Garcia");
		when(request.getParameter("use_password")).thenReturn("1234");
		when(request.getParameter("use_phone")).thenReturn("0983265947");
		when(request.getParameter("use_type")).thenReturn("E");
		when(request.getParameter("use_salary")).thenReturn("350.50");
		when(request.getParameterValues("degreeId")).thenReturn(degreeIds);
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        teacherController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("teacherId");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertEquals("Success", output);
		updateTest();
	}
	
	void createDegree() {
		try {
			degreeDAO.create(new Degree());
			degreeDAO.create(new Degree());
			degreeDAO.create(new Degree());
		}catch(Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
	}
	
	void updateTest() throws ServletException, IOException {
		String output;
		
		when(request.getParameter("option")).thenReturn("update");
		when(request.getParameter("use_id")).thenReturn("8");
		when(request.getParameter("use_address")).thenReturn("Benigno Malo");
		when(request.getParameter("use_dni")).thenReturn("0105100845");
		when(request.getParameter("use_email")).thenReturn("dani@gmail.com");
		when(request.getParameter("use_full_name")).thenReturn("Pedro Garcia");
		when(request.getParameter("use_password")).thenReturn("1234");
		when(request.getParameter("use_phone")).thenReturn("0983265947");
		when(request.getParameter("use_type")).thenReturn("E");
		when(request.getParameter("use_salary")).thenReturn("350.50");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        teacherController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("teacherId");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertEquals("Success", output);
	}
	*/
}
