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
			name = "rel_tea_deg", 
			joinColumns = @JoinColumn(name = "deg_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "tea_id", nullable = false))
	private List<Teacher> teacherList;
	
	@Column(name = "deg_deleted", nullable = false,  
			columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean deleted;
	
	public Degree() {
		super();
	}

	
	
	public Degree(int id, String name, List<Teacher> teacherList, boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.teacherList = teacherList;
		this.deleted = deleted;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}



	@Override
	public String toString() {
		return "Degree [id=" + id + ", name=" + name + ", teacherList=" + teacherList + ", deleted=" + deleted + "]";
	}
   
}
