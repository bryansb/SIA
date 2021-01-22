package ec.edu.ups.entities.accounting;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Account
 *
 */
@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acc_id")
	private int id;
	
	@Column(name = "acc_name")
	private String name;
	
	@Column(name = "acc_balance")
	private double balance;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account", 
			fetch = FetchType.LAZY)
	private List<Amount> amountList;
	
	@JoinColumn
	@ManyToOne(fetch = FetchType.EAGER)
	private AccountType accountType;
	
	public Account() {
		super();
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Amount> getAmountList() {
		return amountList;
	}

	public void setAmountList(List<Amount> amountList) {
		this.amountList = amountList;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", balance=" + balance + ", amountList=" + amountList
				+ ", accountType=" + accountType + "]";
	}
   
}
