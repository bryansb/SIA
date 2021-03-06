package ec.edu.ups.dao.registration;

import java.util.List;

import ec.edu.ups.dao.GenericDAO;
import ec.edu.ups.entities.registration.Enrollment;

public interface EnrollmentDAO extends GenericDAO<Enrollment, Integer> {

	public boolean isEnrolledByInscriptionId(int inscritionId);
	
	public String getCurrentAcademicPeriod();
	
	public List<Enrollment> getAcademicRecordByStudentId(int studentId);
	
	public List<Enrollment> getEnrollmentByStudentDni(String dni);
	
	public List<Enrollment> getEnrollmentByStudentId(int studentId);
	
	public List<Enrollment> getEnrollmentApprovedLevelByInscriptionId(int inscriptionId);
	
	public Enrollment getEnrollmentCurrentSchedule(int studentId);
	
}
