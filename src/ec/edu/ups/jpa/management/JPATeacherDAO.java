package ec.edu.ups.jpa.management;

import ec.edu.ups.dao.management.TeacherDAO;
import ec.edu.ups.entities.management.Teacher;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPATeacherDAO extends JPAGenericDAO<Teacher, Integer> implements TeacherDAO {

	public JPATeacherDAO() {
		super(Teacher.class);
	}

}
