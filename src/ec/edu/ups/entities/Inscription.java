package ec.edu.ups.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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
	
	public Inscription() {
		super();
	}
   
}
