package ec.edu.ups.jpa.accounting;

import ec.edu.ups.dao.accounting.BillDetailDAO;
import ec.edu.ups.entities.accounting.BillDetail;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPABillDetailDAO extends JPAGenericDAO<BillDetail, Integer> implements BillDetailDAO {

	public JPABillDetailDAO() {
		super(BillDetail.class);
	}
	
}
