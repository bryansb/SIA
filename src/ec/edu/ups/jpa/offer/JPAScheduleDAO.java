package ec.edu.ups.jpa.offer;

import ec.edu.ups.dao.offer.ScheduleDAO;
import ec.edu.ups.entities.offer.Schedule;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAScheduleDAO extends JPAGenericDAO<Schedule, Integer> implements ScheduleDAO {

	public JPAScheduleDAO() {
		super(Schedule.class);
	}

}
