package ec.edu.ups.jpa.offer;

import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.entities.offer.Career;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPACareerDAO extends JPAGenericDAO<Career, Integer> implements CareerDAO{

	public JPACareerDAO() {
		super(Career.class);
	}

}
