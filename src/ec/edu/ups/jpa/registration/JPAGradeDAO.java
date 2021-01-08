package ec.edu.ups.jpa.registration;

import java.util.List;

import ec.edu.ups.dao.registration.GradeDAO;
import ec.edu.ups.entities.registration.Grade;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAGradeDAO extends JPAGenericDAO<Grade, Integer> implements GradeDAO {

	public JPAGradeDAO() {
		super(Grade.class);
	}

	@Override
	public List<Grade> findByEnrollmentId(int enrollmentId) {
		List<Grade> gradeList;
		String[][] attributes = {{"enrollment"}, {"id"}};
		String[] values = {"equal&" + enrollmentId};
		gradeList = super.findByPath(attributes, values, "id", 0, 0, false);
		return gradeList;
	}

}
