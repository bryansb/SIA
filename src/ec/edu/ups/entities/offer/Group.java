package ec.edu.ups.entities.offer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.entities.management.Teacher;
import ec.edu.ups.entities.registration.Grade;

/**
 * Entity implementation class for Entity: Group
 *
 */
@Entity
@Table(name = "GROUPS")
public class Group implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gro_id")
	private int id;
	
	@Column(name = "gro_academic_period")
	private String academicPeriod;
	
	@Column(name = "gro_physical_space")
	private String physicalSpace;
	
	@Column(name = "gro_quota")
	private int quota;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	private List<Schedule> scheduleList;
	
	@JoinColumn
	@ManyToOne(fetch = FetchType.EAGER)
	private Subject subject;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	private List<Grade> gradeList;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "groupList")
	private List<Teacher> teacherList;
	
	public Group() {
		super();
	}
   
}
