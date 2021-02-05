package ec.edu.ups.entities.management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.entities.offer.Group;

/**
 * Entity implementation class for Entity: Teacher
 *
 */
@Entity
@Table(name = "TEACHERS")
public class Teacher extends Employee implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@ManyToMany( 
			mappedBy = "teacherList", 
			fetch = FetchType.EAGER)
	private List<Degree> degreeList;
	
	@ManyToMany(mappedBy = "teacherList")
	@JoinColumn
	private List<Group> groupList;

	public Teacher() {
		super();
	}
	
	public void addGroup(Group group) {

		if (this.groupList == null) {
			this.groupList = new ArrayList<>();
		}
		groupList.add(group);
	}

	public List<Degree> getDegreeList() {
		return degreeList;
	}

	public void setDegreeList(List<Degree> degreeList) {
		this.degreeList = degreeList;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	@Override
	public String toString() {
		return "Teacher [degreeList=" + degreeList + ", groupList=" + groupList + "]";
	}
   
	public void addDegree(Degree degree) {
		if(this.degreeList == null) {
			this.degreeList = new ArrayList<>();
		}
		degreeList.add(degree);
	}
}
