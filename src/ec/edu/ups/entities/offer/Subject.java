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
	
	@Transient
	private boolean editable;
	
	public Subject() {
		super();
	}

	public Subject(String name, int credits, double cost, int hours, int level, Career career) {
		super();
		this.name = name;
		this.credits = credits;
		this.cost = cost;
		this.hours = hours;
		this.level = level;
		this.career = career;
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

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", credits=" + credits + ", cost=" + cost + ", hours=" + hours
				+ ", level=" + level + ", career=" + career + ", groupList=" + groupList + "]";
	}
   
}
