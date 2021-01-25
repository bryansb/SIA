package ec.edu.ups.dao.registration;

import java.util.List;

import ec.edu.ups.dao.GenericDAO;
import ec.edu.ups.entities.registration.Grade;

public interface GradeDAO extends GenericDAO<Grade, Integer> {
	public List<Grade> findByEnrollmentId(int enrollmentId);
	public List<Grade> findCurrentDregreListByTeacherId(int teacherId);
}
