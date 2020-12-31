package ec.edu.ups.jpa.registration;

import ec.edu.ups.dao.registration.EnrollmentDAO;
import ec.edu.ups.entities.registration.Enrollment;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAEnrollmentDAO extends JPAGenericDAO<Enrollment, Integer> implements EnrollmentDAO {

	public JPAEnrollmentDAO() {
		super(Enrollment.class);
	}

}
