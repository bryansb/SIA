package ec.edu.ups.test.entities.accounting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.controller.utils.ParameterBasicCreation;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.BillHeadDAO;
import ec.edu.ups.entities.accounting.BillHead;

class BillHeadTest {

	private BillHead billHead;
	private ParameterBasicCreation parameterBasicCreation;
	private BillHeadDAO billHeadDAO;
	private Logger logger;
	
	@BeforeEach
	void setUp() throws Exception {
		this.billHead = new BillHead();
		this.parameterBasicCreation = new ParameterBasicCreation();
		this.billHeadDAO = DAOFactory.getFactory().getBillHeadDAO();
		this.parameterBasicCreation.init();
		this.billHead.createBillDetail("", 10, 1.0);
		this.billHead.createBillDetail("", 10, 2.0);
		this.billHead.createBillDetail("", 10, 3.0);
	}

	@Test
	void test() {
		double calculatedTotal;
		double expectedTotal = 70.2;
		try {
			this.billHead.calculateTotal();
			billHeadDAO.create(this.billHead);
			calculatedTotal = this.billHead.getTotal();
			System.out.println(" >>>>>>> " + calculatedTotal);
			assertEquals(expectedTotal, calculatedTotal);
		} catch (Exception e) {
			this.logger.log(Level.INFO, e.toString());
		}
		
	}

}
