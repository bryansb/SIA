package ec.edu.ups.entities.registration;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

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
	
	@Column(name = "ins_date", nullable = false)
	private Calendar date;
	
	@Column(name = "ins_deleted", nullable = false,  
			columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean deleted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "inscription")
	private List<Enrollment> enrollmentList;
	
	@JoinColumn
	@ManyToOne(fetch = FetchType.EAGER)
	private Student student;
	
	@JoinColumn
	@ManyToOne(fetch = FetchType.EAGER)
	private Career career;
	
	public Inscription() {
		super();
	}

	public Inscription(Calendar date, Student student, Career career) {
		super();
		this.date = date;
		this.student = student;
		this.career = career;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<Enrollment> getEnrollmentList() {
		return enrollmentList;
	}

	public void setEnrollmentList(List<Enrollment> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	@Override
	public String toString() {
		return "Inscription [id=" + id + ", date=" + date + ", deleted=" + deleted + ", enrollmentList="
				+ enrollmentList + ", student=" + student + ", career=" + career + "]";
	}
}
