package ec.edu.ups.test.controller.accounting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.controller.accounting.AmountController;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AccountDAO;
import ec.edu.ups.dao.accounting.AmountDAO;
import ec.edu.ups.entities.accounting.Account;
import ec.edu.ups.entities.accounting.Amount;

class AmountControllerTest {

	private AmountController amountController;
	private AccountDAO accountDAO;
	private AmountDAO amountDAO;

	@BeforeEach
	void setUp() throws Exception {
		amountController = new AmountController();
		accountDAO = DAOFactory.getFactory().getAccountDAO();
		amountDAO = DAOFactory.getFactory().getAmountDAO();
	}

	@Test
	void test() throws Exception {
		String name = "CAJA CONTABLE";
		Account account = new Account();
		account.setName(name);
		account.setBalance(0.0);
		accountDAO.create(account);
		account = accountDAO.findByName(name);
		amountController.createIncomeAmount("Test", 10.0, 20.0, account);
		Amount amount = amountDAO.read(1);
		assertTrue(amount.getDescription().contains("Test"));
	}

}
