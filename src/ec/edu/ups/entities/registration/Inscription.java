package ec.edu.ups.entities.registration;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import ec.edu.ups.entities.management.Student;
import ec.edu.ups.entities.offer.Career;

/**
 * Entity implementation class for Entity: Inscription
 *
 */
@Entity
@Table(name = "INSCRIPTIONS")
public class Inscription implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ins_id")
	private int id;
	
	@Column(name = "ins_date")
	private Date date;
	
	@JoinColumn
	@ManyToOne(fetch = FetchType.EAGER)
	private Student student;
	
	@JoinColumn
	@ManyToOne(fetch = FetchType.EAGER)
	private Career career;
	
	public Inscription() {
		super();
	}
   
}
