package ec.edu.ups.test.entities.accounting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AmountDAO;
import ec.edu.ups.entities.accounting.Amount;

class AmountTest {

	private AmountDAO amountDAO;
	private Amount amount;
	private Logger logger;
	
	@BeforeEach
	void setUp() {
		amountDAO = DAOFactory.getFactory().getAmountDAO();
		amount = new Amount();
		amount.setDate(new GregorianCalendar());
		amount.setDescription("Test Amount");
		amount.setType('I');
		amount.setUnitPrice(3.4);
		amount.setTotal(10.0);
	}

	@Test
	void test() {
		try{
			amountDAO.create(amount);
			List<Amount> amountList = amountDAO.find(null, 0, 0);
			Amount amount = amountList.get(amountList.size() - 1);
			assertEquals(this.amount.getDate(), amount.getDate());
			assertEquals(this.amount.getDescription(), amount.getDescription());
			assertEquals(this.amount.getType(), amount.getType());
			assertEquals(this.amount.getUnitPrice(), amount.getUnitPrice());
			assertEquals(this.amount.getTotal(), amount.getTotal());
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.toString());
		}
	}

}
