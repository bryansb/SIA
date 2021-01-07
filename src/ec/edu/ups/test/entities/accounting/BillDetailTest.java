package ec.edu.ups.test.entities.accounting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.entities.accounting.BillDetail;

class BillDetailTest {

	private BillDetail billDetail;
	
	@BeforeEach
	void setUp() throws Exception {
		billDetail = new BillDetail();
		billDetail.setQuantity(5);
	}

	@Test
	void test() {
		billDetail.setUnitPrice(2.5);
		billDetail.calculateTotal();
		double expectedTotal = 12.5;
		double calculatedTotal = billDetail.getTotal();
		assertEquals(expectedTotal, calculatedTotal);
	}

}
