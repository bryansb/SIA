package ec.edu.ups.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Enrollment
 *
 */
@Entity
@Table(name = "ENROLLMENTS")
public class Enrollment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enr_id")
	private int id;
	
	@Column(name = "enr_date")
	private Date date;
	
	public Enrollment() {
		super();
	}
   
}
