package ec.edu.ups.entities.offer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Subject
 *
 */
@Entity
@Table(name = "SUBJECTS")
public class Subject implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_id")
	private int id;
	
	@Column(name = "sub_name")
	private String name;
	
	@Column(name = "sub_credits")
	private int credits;
	
	@Column(name = "sub_cost")
	private double cost;
	
	@Column(name = "sub_hours")
	private int hours;
	
	@Column(name = "sub_level")
	private int level;
	
	@JoinColumn
	@ManyToOne(fetch = FetchType.EAGER)
	private Career career;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject", fetch = FetchType.LAZY)
	private List<Group> groupList;
	
	public Subject() {
		super();
	}
   
}
