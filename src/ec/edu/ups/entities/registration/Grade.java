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
	private double gradeValue;
	
	@Column(name = "gra_status", nullable = false,  
			columnDefinition = "VARCHAR(1) DEFAULT 'N'")
	private char status;
	
	@Column(name = "gra_deleted", nullable = false,  
			columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean deleted;
	
	@JoinColumn
	@ManyToOne(cascade = CascadeType.ALL)
	private Enrollment enrollment;
	
	@JoinColumn
	@ManyToOne
	private Group group;
	
	public Grade() {
		super();
	}

	public Grade(String description, double gradeValue, Group group, char status, Enrollment enrollment) {
		super();
		this.description = description;
		this.gradeValue = gradeValue;
		this.group = group;
		this.status = status;
		this.enrollment = enrollment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getGradeValue() {
		return gradeValue;
	}

	public void setGradeValue(double gradeValue) {
		this.gradeValue = gradeValue;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
