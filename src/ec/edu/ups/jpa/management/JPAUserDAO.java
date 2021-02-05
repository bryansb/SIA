package ec.edu.ups.jpa.management;

import javax.persistence.Query;

import ec.edu.ups.controller.utils.SiaTool;
import ec.edu.ups.dao.management.UserDAO;
import ec.edu.ups.entities.management.User;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAUserDAO extends JPAGenericDAO<User, Integer> implements UserDAO {

	public JPAUserDAO() {
		super(User.class);
	}

	@Override
	public Object login(String key, String password) {
		password = SiaTool.getSha256(password);
		String jpql = "SELECT u FROM User u "
				+ " WHERE u.email LIKE '" + key + "' "
				+ " AND u.password LIKE '" + password + "' ";
		em.clear();
		try {
			Query query = em.createQuery(jpql);
			Object user = query.getSingleResult();
			em.refresh(user);
			return user;
		} catch (Exception e) {
			return null;
		}
	}

}
