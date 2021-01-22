package ec.edu.ups.test.entities.accounting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AccountTypeDAO;
import ec.edu.ups.entities.accounting.AccountType;

class AccountTypeTest {

	private AccountTypeDAO accountTypeDAO;
	private AccountType accountType;
	private Logger logger;
	
	@BeforeEach
	void setUp() {
		accountTypeDAO = DAOFactory.getFactory().getAccountTypeDAO();
		accountType = new AccountType();
		accountType.setName("Tipo de Cuenta");
	}

	@Test
	void test() {
		try {
			accountTypeDAO.create(accountType);
			AccountType accountType = accountTypeDAO.read(1);
			String expected = this.accountType.getName();
			String actual = accountType.getName();
			assertEquals(expected, actual);
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.toString());
		}
	}

}
