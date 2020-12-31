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
   
}
