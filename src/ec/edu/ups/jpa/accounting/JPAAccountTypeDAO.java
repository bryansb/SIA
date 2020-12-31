package ec.edu.ups.jpa.accounting;

import ec.edu.ups.dao.accounting.AccountTypeDAO;
import ec.edu.ups.entities.accounting.AccountType;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAAccountTypeDAO extends JPAGenericDAO<AccountType, Integer> implements AccountTypeDAO{

	public JPAAccountTypeDAO() {
		super(AccountType.class);
	}
	
}
