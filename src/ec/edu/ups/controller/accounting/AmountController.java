package ec.edu.ups.controller.accounting;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AmountDAO;
import ec.edu.ups.entities.accounting.Account;
import ec.edu.ups.entities.accounting.Amount;

public class AmountController {
	
	private static final String ERROR_ROOT = ">>> Error >> AmountController";
	private AmountDAO amountDAO;
	private Logger logger;
	
	public AmountController() {
		super();
		amountDAO = DAOFactory.getFactory().getAmountDAO();
	}
	
	public void createIncomeAmount(String description, double unitPrice, double total, 
			Account account) {
		Amount amount;
		try {
			amount = new Amount(description, unitPrice, total, account);
			amount.setType('I');
			amount.setDate(new GregorianCalendar());
			amountDAO.create(amount);
		} catch (Exception e) {
			String message = ERROR_ROOT + ":createIncomeAmount > " + e.toString();
			this.logger.log(Level.INFO, message);
			throw new NullPointerException(message);
		}
	}
}
