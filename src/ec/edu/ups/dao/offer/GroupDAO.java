package ec.edu.ups.dao.offer;

import ec.edu.ups.dao.GenericDAO;
import ec.edu.ups.entities.offer.Group;

public interface GroupDAO extends GenericDAO<Group, Integer> {
	
	public void refresh(Group group);

}
