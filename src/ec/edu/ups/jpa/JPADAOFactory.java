package ec.edu.ups.jpa;

import ec.edu.ups.dao.DAOFactory;
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
import ec.edu.ups.jpa.accounting.JPAAccountDAO;
import ec.edu.ups.jpa.accounting.JPAAccountTypeDAO;
import ec.edu.ups.jpa.accounting.JPAAmountDAO;
import ec.edu.ups.jpa.accounting.JPABillDetailDAO;
import ec.edu.ups.jpa.accounting.JPABillHeadDAO;
import ec.edu.ups.jpa.management.JPADegreeDAO;
import ec.edu.ups.jpa.management.JPAEmployeeDAO;
import ec.edu.ups.jpa.management.JPAStudentDAO;
import ec.edu.ups.jpa.management.JPATeacherDAO;
import ec.edu.ups.jpa.management.JPAUserDAO;
import ec.edu.ups.jpa.offer.JPACareerDAO;
import ec.edu.ups.jpa.offer.JPAGroupDAO;
import ec.edu.ups.jpa.offer.JPAScheduleDAO;
import ec.edu.ups.jpa.offer.JPASubjectDAO;
import ec.edu.ups.jpa.registration.JPAEnrollmentDAO;
import ec.edu.ups.jpa.registration.JPAGradeDAO;
import ec.edu.ups.jpa.registration.JPAInscriptionDAO;
import ec.edu.ups.jpa.utils.JPAParameterDAO;

public class JPADAOFactory extends DAOFactory{

	@Override
	public AccountDAO getAccountDAO() {
		return new JPAAccountDAO();
	}

	@Override
	public AccountTypeDAO getAccountTypeDAO() {
		return new JPAAccountTypeDAO();
	}

	@Override
	public AmountDAO getAmountDAO() {
		return new JPAAmountDAO();
	}

	@Override
	public BillDetailDAO getBillDetailDAO() {
		return new JPABillDetailDAO();
	}

	@Override
	public BillHeadDAO getBillHeadDAO() {
		return new JPABillHeadDAO();
	}

	@Override
	public DegreeDAO getDegreeDAO() {
		return new JPADegreeDAO();
	}

	@Override
	public EmployeeDAO getEmployeeDAO() {
		return new JPAEmployeeDAO();
	}

	@Override
	public StudentDAO getStudentDAO() {
		return new JPAStudentDAO();
	}

	@Override
	public TeacherDAO getTeacherDAO() {
		return new JPATeacherDAO();
	}

	@Override
	public UserDAO getUserDAO() {
		return new JPAUserDAO();
	}

	@Override
	public CareerDAO getCareerDAO() {
		return new JPACareerDAO();
	}

	@Override
	public GroupDAO getGroupDAO() {
		return new JPAGroupDAO();
	}

	@Override
	public ScheduleDAO getScheduleDAO() {
		return new JPAScheduleDAO();
	}

	@Override
	public SubjectDAO getSubjectDAO() {
		return new JPASubjectDAO();
	}

	@Override
	public EnrollmentDAO getEnrollmentDAO() {
		return new JPAEnrollmentDAO();
	}

	@Override
	public GradeDAO getGradeDAO() {
		return new JPAGradeDAO();
	}

	@Override
	public InscriptionDAO getInscriptionDAO() {
		return new JPAInscriptionDAO();
	}

	@Override
	public ParameterDAO getParameterDAO() {
		return new JPAParameterDAO();
	}
	
}
