package ec.edu.ups.entities.registration;

import java.io.Serializable;
import javax.persistence.*;

import ec.edu.ups.entities.offer.Group;

/**
 * Entity implementation class for Entity: Grade
 *
 */
@Entity
@Table(name = "GRADES")
public class Grade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gra_id")
	private int id;
	
	@Column(name = "gra_description")
	private String description;
	
	@Column(name = "gra_grade")
	private double grade;
	
	@JoinColumn
	@ManyToOne
	private Enrollment enrollment;
	
	@JoinColumn
	@ManyToOne
	private Group group;
	
	public Grade() {
		super();
	}
   
}
