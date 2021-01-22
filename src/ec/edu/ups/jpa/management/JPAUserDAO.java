package ec.edu.ups.jpa.management;

import ec.edu.ups.dao.management.UserDAO;
import ec.edu.ups.entities.management.User;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAUserDAO extends JPAGenericDAO<User, Integer> implements UserDAO {

	public JPAUserDAO() {
		super(User.class);
	}

}
