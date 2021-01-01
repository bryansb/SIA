package ec.edu.ups.entities.registration;

import java.io.Serializable;
import java.util.Date;

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
	private Date date;
	
	@Column(name = "ins_deleted", nullable = false,  
			columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean deleted;
	
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Student student;
	
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Career career;
	
	public Inscription() {
		super();
	}

	public Inscription(Date date, Student student, Career career) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
		return "Inscription [id=" + id + ", date=" + date + ", deleted=" + deleted + ", student=" + student
				+ ", career=" + career + "]";
	}
}
