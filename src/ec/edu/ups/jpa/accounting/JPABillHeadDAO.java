package ec.edu.ups.jpa.accounting;

import ec.edu.ups.dao.accounting.BillHeadDAO;
import ec.edu.ups.entities.accounting.BillHead;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPABillHeadDAO extends JPAGenericDAO<BillHead, Integer> implements BillHeadDAO {

	public JPABillHeadDAO() {
		super(BillHead.class);
	}

}
