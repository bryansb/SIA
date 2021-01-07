package ec.edu.ups.dao;

import ec.edu.ups.dao.accounting.AccountDAO;
import ec.edu.ups.dao.accounting.AccountTypeDAO;
import ec.edu.ups.dao.accounting.AmountDAO;
import ec.edu.ups.dao.accounting.BillDetailDAO;
import ec.edu.ups.dao.accounting.BillHeadDAO;
import ec.edu.ups.dao.management.DegreeDAO;
import ec.edu.ups.dao.management.EmployeeDAO;
import ec.edu.ups.dao.management.StudentDAO;
import ec.edu.ups.dao.management.TeacherDAO;
import ec.edu.ups.dao.management.UserDAO;
import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.dao.offer.ScheduleDAO;
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.dao.registration.EnrollmentDAO;
import ec.edu.ups.dao.registration.GradeDAO;
import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.dao.utils.ParameterDAO;
import ec.edu.ups.jpa.JPADAOFactory;

public abstract class DAOFactory {

	protected static final DAOFactory factory = new JPADAOFactory();
	
	public static DAOFactory getFactory() {
		return factory;
	}
	
	public abstract AccountDAO getAccountDAO();
	
	public abstract AccountTypeDAO getAccountTypeDAO();
	
	public abstract AmountDAO getAmountDAO();
	
	public abstract BillDetailDAO getBillDetailDAO();
	
	public abstract BillHeadDAO getBillHeadDAO();
	
	public abstract DegreeDAO getDegreeDAO();
	
	public abstract EmployeeDAO getEmployeeDAO();
	
	public abstract StudentDAO getStudentDAO();
	
	public abstract TeacherDAO getTeacherDAO();
	
	public abstract UserDAO getUserDAO();
	
	public abstract CareerDAO getCareerDAO();
	
	public abstract GroupDAO getGroupDAO();
	
	public abstract ScheduleDAO getScheduleDAO();
	
	public abstract SubjectDAO getSubjectDAO();
	
	public abstract EnrollmentDAO getEnrollmentDAO();
	
	public abstract GradeDAO getGradeDAO();
	
	public abstract InscriptionDAO getInscriptionDAO();
	
	public abstract ParameterDAO getParameterDAO();
	
}
