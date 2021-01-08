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

import ec.edu.ups.controller.offer.ScheduleController;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.entities.offer.Group;

class ScheduleControllerTest {
	
	private ScheduleController scheduleController;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private GroupDAO groupDAO;

	@BeforeEach
	void setUp() throws Exception {
		scheduleController = new ScheduleController();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class);
        groupDAO = DAOFactory.getFactory().getGroupDAO();
	}

	@Test
	void test() throws ServletException, IOException {
		String output;
		
		createGroup();
		
		when(request.getParameter("option")).thenReturn("create");
		when(request.getParameter("day")).thenReturn("dayTest");
		when(request.getParameter("startTime")).thenReturn("7:00");
		when(request.getParameter("endTime")).thenReturn("9:00");
		when(request.getParameter("groupId")).thenReturn("1");
		
        when(request.getParameter("scheduleId")).thenReturn("1");
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        scheduleController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("scheduleId");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertEquals("Success", output);
	}
	
	void createGroup() {
		Group group = new Group();
		try {
			groupDAO.create(group);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
