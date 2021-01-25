package ec.edu.ups.jpa.offer;

import javax.persistence.Query;

import ec.edu.ups.dao.offer.CareerDAO;
import ec.edu.ups.entities.offer.Career;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPACareerDAO extends JPAGenericDAO<Career, Integer> implements CareerDAO{

	public JPACareerDAO() {
		super(Career.class);
	}

	@Override
	public Career findByCareerName(String name) {
		String spql = "SELECT c FROM Careers c "
				+ "WHERE c.name LIKE '" + name + "'";
		Query query = super.em.createQuery(spql, Career.class);
		
		return (Career) query.getSingleResult();
	}

}
