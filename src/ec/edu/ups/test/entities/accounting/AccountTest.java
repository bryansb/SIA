package ec.edu.ups.test.entities.accounting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AccountDAO;
import ec.edu.ups.entities.accounting.Account;

class AccountTest {

	private AccountDAO accountDAO;
	private Account account;
	private Logger logger;
	
	@BeforeEach
	void setUp() {
		this.accountDAO = DAOFactory.getFactory().getAccountDAO();
		this.account = new Account();
		this.account.setName("Prueba de Cuenta");
		this.account.setBalance(10.0);
		this.account.setAmountList(null);
	}

	@Test
	void test() {
		try {
			accountDAO.create(account);
			List<Account> accountList = accountDAO.find(null, 0, 0);
			Account account = accountList.get(accountList.size() - 1);
			assertEquals(this.account.getName(), account.getName());
			assertEquals(this.account.getBalance(), account.getBalance());
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.toString());
		}
	}

}
