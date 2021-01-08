package ec.edu.ups.entities.registration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.controller.accounting.AmountController;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AccountDAO;
import ec.edu.ups.entities.accounting.Account;
import ec.edu.ups.entities.accounting.BillHead;
import ec.edu.ups.entities.offer.Group;

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
	private Calendar date;
	
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
	
	@Transient
	private AccountDAO accountDAO;
	
	@Transient
	private AmountController amountController;
	
	public Enrollment() {
		super();
		init();
	}

	public Enrollment(Calendar date) {
		super();
		this.date = date;
		init();
	}
	
	public Enrollment(Calendar date, Inscription inscription, BillHead billHead) {
		super();
		this.date = date;
		this.inscription = inscription;
		this.billHead = billHead;
		init();
	}
	
	private void init() {
		accountDAO = DAOFactory.getFactory().getAccountDAO();
		amountController = new AmountController();
	}
	
	public void createGrade(String description, double gradeValue, Group group) {
		if (gradeList == null) {
			gradeList = new ArrayList<Grade>();
		}
		Grade grade = new Grade(description, gradeValue, group, this);
		this.gradeList.add(grade);
	}
	
	@PostPersist
	private void createIncomeAmount() {
		Account account = this.accountDAO.findByName("CAJA CONTABLE");
		this.amountController.createIncomeAmount("", this.billHead.getSubtotal(), 
				this.billHead.getTotal(), account);
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
