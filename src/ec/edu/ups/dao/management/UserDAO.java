package ec.edu.ups.dao.management;

import ec.edu.ups.dao.GenericDAO;
import ec.edu.ups.entities.management.User;

public interface UserDAO extends GenericDAO<User, Integer>{

	public Object login(String key, String password);
}
