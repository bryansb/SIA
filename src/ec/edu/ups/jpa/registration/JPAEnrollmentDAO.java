package ec.edu.ups.jpa.registration;

import java.util.List;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.registration.EnrollmentDAO;
import ec.edu.ups.dao.utils.ParameterDAO;
import ec.edu.ups.entities.registration.Enrollment;
import ec.edu.ups.entities.utils.Parameter;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAEnrollmentDAO extends JPAGenericDAO<Enrollment, Integer> implements EnrollmentDAO {

	private static final String IS_ENROLLED_BY_INSCRIPTION_ID_QRY = 
			" SELECT COUNT(e.id) FROM Enrollment e "
			+ " WHERE (e.status = 'C' "
			+ " AND e.academicPeriod = :academicPeriod "
			+ " AND e.inscription.id = :inscritionId )";
	
	private static final String ACADEMIC_RECORD_BY_STUDENT_ID_QRY = 
			" SELECT e FROM Enrollment e "
			+ " WHERE e.status = 'E' "
			+ " AND e.inscription.student.id = :studentId"
			+ " ORDER BY e.date DESC ";
	
	private static final String ENROLLMENT_BY_STUDENT_ID_QRY = 
			" SELECT e FROM Enrollment e "
			+ " WHERE e.deleted = 0 "
			+ " AND e.inscription.student.id = :studentId "
			+ " ORDER BY e.date DESC ";
	
	private static final String ENROLLMENT_APPROVED_BY_INSCRIPTION_ID_QRY = 
			" SELECT DISTINCT e FROM Enrollment e "
			+ " LEFT JOIN FETCH e.gradeList gr "
			+ " WHERE e.deleted = 0 "
			+ " AND e.inscription.id = :inscriptionId "
			+ " AND gr.status = 'A' "
			+ " ORDER BY gr.group.subject.name ASC "
			;
	private static final String ENROLLMENT_BY_STUDENT_DNI_QRY = 
			" SELECT e FROM Enrollment e "
			+ " WHERE e.deleted = 0 "
			+ " AND e.inscription.student.dni = :dni "
			+ " ORDER BY e.date DESC ";
	
	private static final String ENROLLMENT_SCHEDULE_QRY =
			" SELECT e FROM Enrollment e "
			+ " WHERE e.deleted = 0 "
			+ " AND e.inscription.student.id = :id "
			+ " AND e.status = 'C' "
			+ " ORDER BY e.date DESC ";
	
	
	private ParameterDAO parameterDAO;
	
	public JPAEnrollmentDAO() {
		super(Enrollment.class);
		parameterDAO = DAOFactory.getFactory().getParameterDAO();
	}

	@Override
	public boolean isEnrolledByInscriptionId(int inscritionId) {
		try {
			em.clear();
			Long count = (Long) super.em.createQuery(IS_ENROLLED_BY_INSCRIPTION_ID_QRY)
					.setParameter("academicPeriod", getCurrentAcademicPeriod())
					.setParameter("inscritionId", inscritionId)
					.getSingleResult();
			return count.intValue() == 0 ? false : true;
		} catch (Exception e) {
			return true;
		}
		
	}
	
	@Override
	public String getCurrentAcademicPeriod() {
		Parameter parameter = parameterDAO.findByKey("CURRENT_ACADEMIC_PERIOD");
		return parameter.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enrollment> getAcademicRecordByStudentId(int studentId) {
		em.clear();
		return super.em.createQuery(ACADEMIC_RECORD_BY_STUDENT_ID_QRY)
			.setParameter("studentId", studentId)
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enrollment> getEnrollmentByStudentId(int studentId) {
		em.clear();
		return super.em.createQuery(ENROLLMENT_BY_STUDENT_ID_QRY)
				.setParameter("studentId", studentId)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enrollment> getEnrollmentApprovedLevelByInscriptionId(int inscriptionId) {
		em.clear();
		return super.em.createQuery(ENROLLMENT_APPROVED_BY_INSCRIPTION_ID_QRY)
				.setParameter("inscriptionId", inscriptionId)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enrollment> getEnrollmentByStudentDni(String dni) {
		em.clear();
		return super.em.createQuery(ENROLLMENT_BY_STUDENT_DNI_QRY)
			.setParameter("dni", dni)
			.getResultList();
	}

	@Override
	public Enrollment getEnrollmentCurrentSchedule(int studentId) {
		try {
			em.clear();
			return (Enrollment) super.em.createQuery(ENROLLMENT_SCHEDULE_QRY)
				.setParameter("id", studentId)
				.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
