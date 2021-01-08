package ec.edu.ups.dao.accounting;

import ec.edu.ups.dao.GenericDAO;
import ec.edu.ups.entities.accounting.Account;

public interface AccountDAO extends GenericDAO<Account, Integer>{
	public Account findByName(String name);
}
