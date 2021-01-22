package ec.edu.ups.jpa.offer;

import ec.edu.ups.dao.offer.SubjectDAO;
import ec.edu.ups.entities.offer.Subject;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPASubjectDAO extends JPAGenericDAO<Subject, Integer> implements SubjectDAO {

	public JPASubjectDAO() {
		super(Subject.class);
	}

}
