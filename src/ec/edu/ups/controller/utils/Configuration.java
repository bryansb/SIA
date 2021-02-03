package ec.edu.ups.controller.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Configuration {
	
	private static final String[] LOGIN_STUDENT =
		{
			// Servlet:
				//Registration
			"/AcademicRecord", "/EnrollStudent", "/EnrollmentReceipt",
			
			//JPS Folder:
				//Registration
			"/JSP/private/registration/student/*"
		};
	
	private static final String[] LOGIN_SECRETARY = 
		{
			// Servlet:
				//Registration
			"/InscriptionController",
			
			//JPS Folder:
				//Registration
			"/JSP/private/registration/secretary/*"
		};
	
	private static final String[] LOGIN_TEACHER = 
		{
			// Servlet:
				//Registration
			"/GradeController", "/GradeRegister",
			
			//JPS Folder:
				//Registration
			"/JSP/private/registration/teacher/*"
				
		};
	
	private static final String[] LOGIN_ADMIN = {""};
	
	private List<String> loginServlet;
	
	public Configuration() {
		createLoginServlet();
	}
	
	private void createLoginServlet() {
		loginServlet = new ArrayList<>();
		loginServlet.addAll(Arrays.asList(LOGIN_STUDENT));
		loginServlet.addAll(Arrays.asList(LOGIN_SECRETARY));
		loginServlet.addAll(Arrays.asList(LOGIN_TEACHER));
		loginServlet.addAll(Arrays.asList(LOGIN_ADMIN));
	}
	
	public List<String> getLoginServlet(){
		return loginServlet;
	}
	
	public static List<String> getLoginStudent(){
		return Arrays.asList(LOGIN_STUDENT);
	}
	
	public static List<String> getLoginSecretary(){
		return Arrays.asList(LOGIN_SECRETARY);
	}
	
	public static List<String> getLoginTeacher(){
		return Arrays.asList(LOGIN_TEACHER);
	}
}
