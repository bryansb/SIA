package ec.edu.ups.jpa.accounting;

import ec.edu.ups.dao.accounting.AccountDAO;
import ec.edu.ups.entities.accounting.Account;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAAccountDAO extends JPAGenericDAO<Account, Integer>  implements AccountDAO{

	public JPAAccountDAO() {
		super(Account.class);
	}

}
