package ec.edu.ups.jpa.offer;

import ec.edu.ups.dao.offer.GroupDAO;
import ec.edu.ups.entities.offer.Group;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAGroupDAO extends JPAGenericDAO<Group, Integer> implements GroupDAO {

	public JPAGroupDAO() {
		super(Group.class);
	}

	@Override
	public void refresh(Group group) {
		em.refresh(group);
	}

}
