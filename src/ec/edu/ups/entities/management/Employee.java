package ec.edu.ups.entities.management;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Employee
 *
 */
@Entity
@Table(name = "EMPLOYEES")
public class Employee extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "use_salary")
	private double salary;
	
	public Employee() {
		super();
	}

	public Employee(double salary) {
		super();
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [salary=" + salary + "]";
	}
   
}
