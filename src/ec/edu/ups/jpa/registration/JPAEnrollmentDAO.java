package ec.edu.ups.jpa.registration;

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
	
	private ParameterDAO parameterDAO;
	
	public JPAEnrollmentDAO() {
		super(Enrollment.class);
		parameterDAO = DAOFactory.getFactory().getParameterDAO();
	}

	@Override
	public boolean isEnrolledByInscriptionId(int inscritionId) {
		try {
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

}
