package ec.edu.ups.test.entities.accounting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.BillDetailDAO;
import ec.edu.ups.dao.accounting.BillHeadDAO;
import ec.edu.ups.entities.accounting.BillDetail;
import ec.edu.ups.entities.accounting.BillHead;

class BillDetailTest {

	private BillDetail billDetail;
	private BillHead billHead;
	private BillDetailDAO billDetailDAO;
	private BillHeadDAO billHeadDAO;
	
	@BeforeEach
	void setUp() throws Exception {
		this.billDetail = new BillDetail();
		this.billHead = new BillHead();
		this.billHeadDAO = DAOFactory.getFactory().getBillHeadDAO();
		this.billDetailDAO = DAOFactory.getFactory().getBillDetailDAO();
		this.billDetail.setQuantity(5);
	}

	@Test
	void test() throws Exception {
		BillDetail billDetail;
		createBillHead();
		this.billDetail.setUnitPrice(2.5);
		this.billDetail.calculateTotal();
		this.billDetail.setBillHead(this.billHead);
		this.billDetailDAO.create(this.billDetail);
		billDetail = billDetailDAO.read(1);
		double expectedTotal = 12.5;
		double calculatedTotal = billDetail.getTotal();
		assertEquals(expectedTotal, calculatedTotal);
	}
	
	void createBillHead() throws Exception {
		this.billHead = new BillHead();
		this.billHeadDAO.create(billHead);
		this.billHead = this.billHeadDAO.read(1);
	}

}
