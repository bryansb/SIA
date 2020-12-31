package ec.edu.ups.jpa.management;

import ec.edu.ups.dao.management.StudentDAO;
import ec.edu.ups.entities.management.Student;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAStudentDAO extends JPAGenericDAO<Student, Integer> implements StudentDAO {

	public JPAStudentDAO() {
		super(Student.class);
	}

}
