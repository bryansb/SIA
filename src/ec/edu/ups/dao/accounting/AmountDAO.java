package ec.edu.ups.dao.accounting;

import java.util.Date;
import java.util.List;

import ec.edu.ups.dao.GenericDAO;
import ec.edu.ups.entities.accounting.Amount;

public interface AmountDAO extends GenericDAO<Amount, Integer>{

	public List<Amount> findByDateAndType(Date start, Date end, int accountId);
	
}
