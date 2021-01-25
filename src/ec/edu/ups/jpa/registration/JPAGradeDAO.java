package ec.edu.ups.jpa.registration;

import java.util.List;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.registration.GradeDAO;
import ec.edu.ups.dao.utils.ParameterDAO;
import ec.edu.ups.entities.registration.Grade;
import ec.edu.ups.entities.utils.Parameter;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAGradeDAO extends JPAGenericDAO<Grade, Integer> implements GradeDAO {

	private static final String CURRENT_DEGREE_QRY = 
			" SELECT gr FROM Grade gr "
			+ "	INNER JOIN gr.group.teacherList t"
			+ " INNER JOIN t.groupList g"
			+ " WHERE t.id = :teacherId "
			+ " AND g.academicPeriod = :academicPeriod";
	private ParameterDAO parameterDAO;
	
	public JPAGradeDAO() {
		super(Grade.class);
		parameterDAO = DAOFactory.getFactory().getParameterDAO();
	}

	@Override
	public List<Grade> findByEnrollmentId(int enrollmentId) {
		List<Grade> gradeList;
		String[][] attributes = {{"enrollment"}, {"id"}};
		String[] values = {"equal&" + enrollmentId};
		String[] order = {"id"};
		gradeList = super.findByPath(attributes, values, order, 0, 0, true, true);
		return gradeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grade> findCurrentDregreListByTeacherId(int teacherId) {
		return (List<Grade>  ) super.em.createQuery(CURRENT_DEGREE_QRY)
				.setParameter("teacherId", teacherId)
				.setParameter("academicPeriod", getCurrentAcademicPeriod())
				.getResultList();
	}
	
	private String getCurrentAcademicPeriod() {
		Parameter parameter = parameterDAO.findByKey("CURRENT_ACADEMIC_PERIOD");
		return parameter.getValue();
	}

}
