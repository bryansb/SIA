package ec.edu.ups.jpa.management;

import ec.edu.ups.dao.management.EmployeeDAO;
import ec.edu.ups.entities.management.Employee;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAEmployeeDAO extends JPAGenericDAO<Employee, Integer> implements EmployeeDAO {

	public JPAEmployeeDAO() {
		super(Employee.class);
	}

}
