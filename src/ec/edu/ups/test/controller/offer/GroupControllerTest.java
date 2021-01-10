package ec.edu.ups.test.controller.offer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

import ec.edu.ups.controller.offer.GroupController;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.management.TeacherDAO;
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.entities.management.Teacher;
import ec.edu.ups.entities.offer.Subject;

class GroupControllerTest {
	private Logger logger;
	private GroupController groupController;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private SubjectDAO subjectDAO;
	private TeacherDAO teacherDAO;

	@BeforeEach
	void setUp() throws Exception {
		groupController = new GroupController();
		request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class);
        subjectDAO = DAOFactory.getFactory().getSubjectDAO();
        teacherDAO = DAOFactory.getFactory().getTeacherDAO();
	}

	@Test
	void test() throws ServletException, IOException {
		String output;
		String[] days = {"lunes", "martes"};
		String[] startTimes = {"8:00", "10:00"};
		String[] endTimes = {"10:00", "12:00"};
		String[] teacherIds = {"1", "2", "3"};
		
		createSubject();
		createTeacher();
		
		when(request.getParameter("option")).thenReturn("create");
		when(request.getParameter("academicPeriod")).thenReturn("academicPeriodTest");
		when(request.getParameter("physicalSpace")).thenReturn("physicalSpaceTest");
		when(request.getParameter("quota")).thenReturn("1");
		when(request.getParameterValues("day")).thenReturn(days);
		when(request.getParameterValues("startTime")).thenReturn(startTimes);
		when(request.getParameterValues("endTime")).thenReturn(endTimes);
		when(request.getParameterValues("teacherId")).thenReturn(teacherIds);
		when(request.getParameter("subjectId")).thenReturn("1");
        
        when(request.getParameter("groupId")).thenReturn("1");
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        groupController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("groupId");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertEquals("Success", output);
		updateTest();
	}
	
	void createSubject() {
		Subject subject = new Subject();
		try {
			subjectDAO.create(subject);
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
	}
	
	void createTeacher() {
		try {
			teacherDAO.create(new Teacher());
			teacherDAO.create(new Teacher());
			teacherDAO.create(new Teacher());
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.getMessage());
		}
	}
	
	void updateTest() throws ServletException, IOException {
		String output;
		
		when(request.getParameter("option")).thenReturn("update");
		when(request.getParameter("academicPeriod")).thenReturn("academicPeriodTest1");
		when(request.getParameter("physicalSpace")).thenReturn("physicalSpaceTest1");
		when(request.getParameter("quota")).thenReturn("2");
		
        when(request.getParameter("groupId")).thenReturn("1");
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        groupController.doTest(request, response);
        
        verify(request, atLeast(1)).getParameter("groupId");
        writer.flush();
        output = stringWriter.toString();
        System.out.println(" >> Response: " + output);
		assertEquals("Success", output);
	}

}
