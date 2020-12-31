package ec.edu.ups.jpa.accounting;

import ec.edu.ups.dao.accounting.AmountDAO;
import ec.edu.ups.entities.accounting.Amount;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAAmountDAO extends JPAGenericDAO<Amount, Integer> implements AmountDAO {

	public JPAAmountDAO() {
		super(Amount.class);
	}
	
}
