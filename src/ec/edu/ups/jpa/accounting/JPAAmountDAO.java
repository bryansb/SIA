package ec.edu.ups.jpa.accounting;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import ec.edu.ups.dao.accounting.AmountDAO;
import ec.edu.ups.entities.accounting.Amount;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAAmountDAO extends JPAGenericDAO<Amount, Integer> implements AmountDAO {

	private static final String FIND_BY_DATE_AND_TYPE_ID_QRY = 
			" SELECT a FROM Amount a "
			+ " WHERE a.date BETWEEN :start AND :end "
			+ " AND a.account.id = :accoundId "
			+ " ORDER BY a.date ASC ";
	
	private static final String FIND_BY_DATE_FOR_ACCOUNTING_BOX_QRY = 
			" SELECT a FROM Amount a "
			+ " WHERE a.date BETWEEN :start AND :end "
			+ " AND (a.account.name LIKE :box_enrollment "
			+ " OR a.account.name LIKE :box_salaries) "
			+ " ORDER BY a.date ASC ";
	
	private static final String GET_AMOUNT_VALUE_FROM_DATE = 
			" SELECT SUM(a.total) FROM Amount a "
			+ " WHERE a.date >= :start "
			+ " AND (a.account.name LIKE :box_enrollment "
			+ " OR a.account.name LIKE :box_salaries) ";
	
	public JPAAmountDAO() {
		super(Amount.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Amount> findByDateAndType(Date start, Date end, int accountId) {
		em.clear();
		return em.createQuery(FIND_BY_DATE_AND_TYPE_ID_QRY)
				.setParameter("start", start, TemporalType.DATE)
				.setParameter("end", end, TemporalType.DATE)
				.setParameter("accoundId", accountId)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Amount> findByDateForAccountingBox(Date start, Date end) {
		em.clear();
		return em.createQuery(FIND_BY_DATE_FOR_ACCOUNTING_BOX_QRY)
				.setParameter("start", start, TemporalType.DATE)
				.setParameter("end", end, TemporalType.DATE)
				.setParameter("box_enrollment", "MATRICULAS")
				.setParameter("box_salaries", "SUELDOS")
				.getResultList();
	}

	@Override
	public double getAmountValueFromAccountingBoxDate(Date start) {
		em.clear();
		try {
		return ((double) em.createQuery(GET_AMOUNT_VALUE_FROM_DATE)
				.setParameter("start", start, TemporalType.DATE)
				.setParameter("box_enrollment", "MATRICULAS")
				.setParameter("box_salaries", "SUELDOS")
				.getSingleResult());
		} catch (Exception e) {
			return 0;
		}
	}
	
	
}
