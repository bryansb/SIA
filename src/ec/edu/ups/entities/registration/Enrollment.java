package ec.edu.ups.entities.registration;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.entities.accounting.BillHead;

/**
 * Entity implementation class for Entity: Enrollment
 *
 */
@Entity
@Table(name = "ENROLLMENTS")
public class Enrollment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enr_id")
	private int id;
	
	@Column(name = "enr_date")
	private Date date;
	
	@Column(name = "enr_deleted", nullable = false,  
			columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean deleted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "enrollment")
	private List<Grade> gradeList;
	
	@JoinColumn
	@ManyToOne
	private Inscription inscription;
	
	@JoinColumn
	@OneToOne(cascade = CascadeType.ALL)
	private BillHead billHead;
	
	public Enrollment() {
		super();
	}

	public Enrollment(Date date) {
		super();
		this.date = date;
	}

	public Enrollment(Date date, Inscription inscription, BillHead billHead) {
		super();
		this.date = date;
		this.inscription = inscription;
		this.billHead = billHead;
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

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public BillHead getBillHead() {
		return billHead;
	}

	public void setBillHead(BillHead billHead) {
		this.billHead = billHead;
	}

	@Override
	public String toString() {
		return "Enrollment [id=" + id + ", date=" + date + ", deleted=" + deleted + ", gradeList=" + gradeList
				+ ", inscription=" + inscription + ", billHead=" + billHead + "]";
	}
}
