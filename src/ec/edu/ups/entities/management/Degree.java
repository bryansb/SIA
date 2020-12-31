package ec.edu.ups.entities.management;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Degree
 *
 */
@Entity
@Table(name = "DEGREES")
public class Degree implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "deg_id")
	private int id;
	
	@Column(name = "deg_name", nullable = false)
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "REL_TEA_DEG", 
			joinColumns = @JoinColumn(name = "deg_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "tea_id", nullable = false))
	private List<Teacher> teacherList;
	
	public Degree() {
		super();
	}
   
}
