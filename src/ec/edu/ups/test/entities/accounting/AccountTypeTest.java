package ec.edu.ups.test.entities.accounting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AccountTypeDAO;
import ec.edu.ups.entities.accounting.AccountType;

class AccountTypeTest {

	private AccountTypeDAO accountTypeDAO;
	private AccountType accountType;
	
	@BeforeEach
	void setUp() throws Exception {
		accountTypeDAO = DAOFactory.getFactory().getAccountTypeDAO();
		accountType = new AccountType();
		accountType.setName("Tipo de Cuenta");
	}

	@Test
	void test() throws Exception {
		accountTypeDAO.create(accountType);
		AccountType accountType = accountTypeDAO.read(1);
		String expected = this.accountType.getName();
		String actual = accountType.getName();
		assertEquals(expected, actual);
	}

}
