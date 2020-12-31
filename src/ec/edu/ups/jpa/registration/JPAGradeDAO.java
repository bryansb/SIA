package ec.edu.ups.jpa.registration;

import ec.edu.ups.dao.registration.GradeDAO;
import ec.edu.ups.entities.registration.Grade;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAGradeDAO extends JPAGenericDAO<Grade, Integer> implements GradeDAO {

	public JPAGradeDAO() {
		super(Grade.class);
	}

}
