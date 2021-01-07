package ec.edu.ups.test.entities.accounting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.controller.utils.ParameterBasicCreation;
import ec.edu.ups.entities.accounting.BillHead;

class BillHeadTest {

	private BillHead billHead;
	private ParameterBasicCreation parameterBasicCreation;
	
	@BeforeEach
	void setUp() throws Exception {
		billHead = new BillHead();
		parameterBasicCreation = new ParameterBasicCreation();
		parameterBasicCreation.init();
		billHead.createBillDetail("", 10, 1.0);
		billHead.createBillDetail("", 10, 2.0);
		billHead.createBillDetail("", 10, 3.0);
	}

	@Test
	void test() {
		billHead.calculateTotal();
		double expectedTotal = 70.2;
		double calculatedTotal = billHead.getTotal();
		assertEquals(expectedTotal, calculatedTotal);
	}

}
