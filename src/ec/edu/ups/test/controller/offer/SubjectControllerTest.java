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

import ec.edu.ups.controller.offer.SubjectController;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.entities.offer.Career;

class SubjectControllerTest {
	
	private SubjectController subjectController;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CareerDAO careerDAO;

	@BeforeEach
	void setUp() throws Exception {
		subjectController = new SubjectController();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class);
        careerDAO = DAOFactory.getFactory().getCareerDAO();
	}

	@Test
	void test() throws ServletException, IOException {
		String output;
		
		createCareer();
		
		when(request.getParameter("option")).thenReturn("create");
		when(request.getParameter("name")).thenReturn("nameTest");
		when(request.getParameter("credits")).thenReturn("4");
		when(request.getParameter("cost")).thenReturn("1600");
		when(request.getParameter("hours")).thenReturn("160");
		when(request.getParameter("level")).thenReturn("1");
		when(request.getParameter("careerId")).thenReturn("1");
        
        when(request.getParameter("subjectId")).thenReturn("1");
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        subjectController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("subjectId");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertEquals("Success", output);
	}

	void createCareer() {
		Career career = new Career();
		career.setName("careerTest");
		career.setTime(160);
		try {
			careerDAO.create(career);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
