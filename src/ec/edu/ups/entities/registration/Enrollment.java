package ec.edu.ups.entities.registration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.controller.accounting.AmountController;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AccountDAO;
import ec.edu.ups.dao.offer.GroupDAO;
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
	
	@Column(name = "enr_academic_period")
	private String academicPeriod;
	
	/* 
	 * Default Values: 
	 * C: Current
	 * E: End
	 * 
	 * */
	@Column(name = "enr_status", nullable = false,  
			columnDefinition = "VARCHAR(1) DEFAULT 'C'")
	private char status;
	
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
	private GroupDAO groupDAO;
	
	@Transient
	private AmountController amountController;
	
	public Enrollment() {
		super();
		init();
	}

	public Enrollment(Calendar date, String academicPeriod, char status) {
		super();
		this.date = date;
		this.academicPeriod = academicPeriod;
		this.status = status;
		init();
	}
	
	public Enrollment(Calendar date, String academicPeriod, char status, Inscription inscription, BillHead billHead) {
		super();
		this.date = date;
		this.academicPeriod = academicPeriod;
		this.status = status;
		this.inscription = inscription;
		this.billHead = billHead;
		init();
	}
	
	private void init() {
		accountDAO = DAOFactory.getFactory().getAccountDAO();
		groupDAO = DAOFactory.getFactory().getGroupDAO();
		amountController = new AmountController();
	}
	
	public void createGrade(String description, double gradeValue, char status, Group group) {
		if (gradeList == null) {
			gradeList = new ArrayList<>();
		}
		Grade grade = new Grade(description, gradeValue, group, status, this);
		this.gradeList.add(grade);
	}
	
	public boolean validGroupQuota() {
		for (Grade grade : gradeList) {
			Group group = groupDAO.read(grade.getGroup().getId());
			if (group.getQuota() < 1) {
				return false;
			}
		}
		return true;
	}
	
	@PostPersist
	private void createIncomeAmount() {
		Account account = this.accountDAO.findByName("CAJA CONTABLE");
		this.amountController.createIncomeAmount("Enrollment", this.billHead.getSubtotal(), 
				this.billHead.getTotal(), account);
		updateGroupQuota();
	}
	
	private void updateGroupQuota() {
		for (Grade grade : gradeList) {
			Group group = grade.getGroup();
			group.setQuota(group.getQuota() - 1);
			groupDAO.update(group);
		}
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

	public String getAcademicPeriod() {
		return academicPeriod;
	}

	public void setAcademicPeriod(String academicPeriod) {
		this.academicPeriod = academicPeriod;
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
