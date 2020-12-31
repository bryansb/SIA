package ec.edu.ups.entities.offer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.entities.registration.Inscription;

/**
 * Entity implementation class for Entity: Career
 *
 */
@Entity
@Table(name = "CAREERS")
public class Career implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int id;
	
	@Column(name = "car_name", nullable = false)
	private String name;
	
	@Column(name = "car_time", nullable = true)
	private int time;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "career", fetch = FetchType.LAZY)
	private List<Subject> subjectList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "career", fetch = FetchType.LAZY)
	private List<Inscription> inscriptionList;
	
	public Career() {
		super();
	}
   
}
