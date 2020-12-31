package ec.edu.ups.jpa.management;

import ec.edu.ups.dao.management.DegreeDAO;
import ec.edu.ups.entities.management.Degree;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPADegreeDAO extends JPAGenericDAO<Degree, Integer> implements DegreeDAO {

	public JPADegreeDAO() {
		super(Degree.class);
	}

}
