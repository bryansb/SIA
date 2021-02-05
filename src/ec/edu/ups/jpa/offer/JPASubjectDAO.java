package ec.edu.ups.jpa.offer;

import java.util.List;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.dao.utils.ParameterDAO;
import ec.edu.ups.entities.offer.Subject;
import ec.edu.ups.entities.registration.Enrollment;
import ec.edu.ups.entities.utils.Parameter;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPASubjectDAO extends JPAGenericDAO<Subject, Integer> implements SubjectDAO {
	
	private static final String FIND_BY_INSCRIPTION_ID_TO_ENROLLMENT_QRY = 
			"SELECT DISTINCT s FROM Subject s "
			+ " INNER JOIN Inscription i "
			+ " INNER JOIN Group g "
			+ " WHERE (s.career.id = i.career.id "
			+ " AND g.subject.id = s.id "
			+ " AND s.level = :maxLevel "
			+ " AND g.academicPeriod = :academicPeriod"
			+ " AND i.id = :inscritionId)"
			+ " OR s.id IN ("
				+ " SELECT DISTINCT s.id FROM Subject s "
				+ " INNER JOIN Inscription i ON i.id = :inscritionId"
				+ " INNER JOIN Grade gr "
				+ " WHERE (s.career.id = i.career.id "
				+ " AND gr.enrollment.inscription.id = i.id "
				+ " AND gr.group.subject.id = s.id "
				+ " AND gr.status = 'N'"
				+ " )"
			+ " )"
			+ " OR s.id IN ("
				+ " SELECT DISTINCT s.id FROM Subject s "
				+ " INNER JOIN Inscription i ON i.id = :inscritionId"
				+ " WHERE s.career.id = i.career.id "
				+ " AND s.level < :maxLevel "
				+ " AND s.id NOT IN ("
				+ " SELECT DISTINCT s.id FROM Subject s "
				+ " INNER JOIN Grade gr "
				+ " WHERE gr.enrollment.inscription.id = :inscritionId "
				+ " AND gr.group.subject.id = s.id "
				+ ")"
			+ ")"
			
			+ " ORDER BY s.level ASC, s.name ASC";
	
	private static final String GET_MAX_LEVEL_BY_INSCRIPTION_ID_QRY = 
			" SELECT MAX(g.group.subject.level) FROM Grade g "
			+ " WHERE g.enrollment.inscription.id = :inscritionId ";
	
	private static final String SUBJECT_CAREERID_QRY =
			" SELECT s FROM Subject s "
			+ " WHERE s.career.id = :id "
			+ " ORDER BY s.level ASC, s.name ASC";
	
	
	private ParameterDAO parameterDAO;
	
	public JPASubjectDAO() {
		super(Subject.class);
		parameterDAO = DAOFactory.getFactory().getParameterDAO();
	}
	
	@SuppressWarnings("unchecked")
	public List<Subject> findByInscriptionIdToEnrollment(int inscritionId) {
		List<Subject> subjectList = null;
		try {
			String academicPeriod = getCurrentAcademicPeriod();
			int maxLevel = getMaxEnrollmentLevelByInscriptionId(inscritionId);
			maxLevel++;
			System.out.println(maxLevel);
			subjectList = super.em.createQuery(FIND_BY_INSCRIPTION_ID_TO_ENROLLMENT_QRY)
					.setParameter("academicPeriod", academicPeriod)
					.setParameter("inscritionId", inscritionId)
					.setParameter("maxLevel", maxLevel)
					.getResultList();
			
		} catch (Exception e) {
			System.out.println("ERROR " + e);
			subjectList = null;
		}
		return subjectList;
	}
	
	private int getMaxEnrollmentLevelByInscriptionId(int inscritionId) {
		try {
			return (int) super.em.createQuery(GET_MAX_LEVEL_BY_INSCRIPTION_ID_QRY)
					.setParameter("inscritionId", inscritionId).getSingleResult();
		} catch (Exception e) {
			return 0;
		}
	}
	
	private String getCurrentAcademicPeriod() {
		Parameter parameter = parameterDAO.findByKey("CURRENT_ACADEMIC_PERIOD");
		return parameter.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> findByCareerID(int careerId) {
		try {
			em.clear();
			return super.em.createQuery(SUBJECT_CAREERID_QRY)
				.setParameter("id", careerId)
				.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
