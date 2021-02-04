package ec.edu.ups.controller.accounting;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import ec.edu.ups.controller.utils.SiaTool;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AccountDAO;
import ec.edu.ups.dao.accounting.AmountDAO;
import ec.edu.ups.entities.accounting.Account;
import ec.edu.ups.entities.accounting.Amount;

public class AmountController {
	
	private static final String ERROR_ROOT = ">>> Error >> AmountController";
	private AmountDAO amountDAO;
	private AccountDAO accountDAO;
	private Logger logger;
	
	public AmountController() {
		super();
		amountDAO = DAOFactory.getFactory().getAmountDAO();
		accountDAO = DAOFactory.getFactory().getAccountDAO();
	}
	
	public void createIncomeAmount(String description, double unitPrice, double total, 
			Account account) {
		Amount amount;
		try {
			Account accountant = accountDAO.findByName("CAJA CONTABLE");
			amount = new Amount(description, unitPrice, total, account);
			amount.setType('I');
			amount.setDate(new GregorianCalendar());
			
			amount.setBalance(SiaTool.getTrunkDecimal(account.getBalance() + total));
			account.setBalance(SiaTool.getTrunkDecimal(account.getBalance() + total));
			accountant.setBalance(SiaTool.getTrunkDecimal(accountant.getBalance() + total));
			
			amountDAO.create(amount);
			
			accountDAO.update(account);
			accountDAO.update(accountant);
			
		} catch (Exception e) {
			String message = ERROR_ROOT + ":createIncomeAmount > " + e.toString();
			this.logger.log(Level.INFO, message);
		}
	}
}
