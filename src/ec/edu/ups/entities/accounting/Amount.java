package ec.edu.ups.entities.accounting;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Amount
 *
 */
@Entity
@Table(name = "AMOUNTS")
public class Amount implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "amo_id")
	private int id;
	
	@Column(name = "amo_date")
	private Calendar date;
	
	@Column(name = "amo_description")
	private String description;
	
	@Column(name = "amo_unit_price")
	private double unitPrice;
	
	@Column(name = "amo_total")
	private double total;
	
	@Column(name = "amo_balance")
	private double balance;
	
	// i: income |  e: egress
	@Column(name = "amo_type")
	private char type;
	
	@JoinColumn
	@ManyToOne
	private Account account;
	
	public Amount() {
		super();
	}

	public Amount(String description, double unitPrice, double total, Account account) {
		super();
		this.description = description;
		this.unitPrice = unitPrice;
		this.total = total;
		this.account = account;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Amount [id=" + id + ", date=" + date + ", description=" + description + ", unitPrice=" + unitPrice
				+ ", total=" + total + ", type=" + type + ", account=" + account + "]";
	}
}
