package ec.edu.ups.controller.accounting;

import java.util.GregorianCalendar;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AmountDAO;
import ec.edu.ups.entities.accounting.Account;
import ec.edu.ups.entities.accounting.Amount;

public class AmountController {
	
	private AmountDAO amountDAO;
	
	public AmountController() {
		super();
		amountDAO = DAOFactory.getFactory().getAmountDAO();
	}
	
	public void createIncomeAmount(String description, double unitPrice, double total, 
			Account account) {
		try {
			Amount amount = new Amount(description, unitPrice, total, account);
			amount.setType('I');
			amount.setDate(new GregorianCalendar());
			amountDAO.create(amount);
		} catch (Exception e) {
			System.out.println(">>> Error >> AmountController:createIncomeAmount > " + e);
		}
	}
}
