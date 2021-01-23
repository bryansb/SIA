package ec.edu.ups.jpa.accounting;

import ec.edu.ups.dao.accounting.AccountDAO;
import ec.edu.ups.entities.accounting.Account;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAAccountDAO extends JPAGenericDAO<Account, Integer>  implements AccountDAO{

	public JPAAccountDAO() {
		super(Account.class);
	}

	@Override
	public Account findByName(String name) {
		Account account;
		try {
			String[][] attributes = {{"name"}};
			String[] values = {"equal&" + name};
			account = super.findByPath(attributes, values, null, 0, 1, true, false).get(0);
		} catch (Exception e) {
			System.out.println(">>> Error >> JPAAccountDAO:findByName > " + e);
			account = null;
		}
		return account;
	}

}
