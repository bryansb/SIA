package ec.edu.ups.entities.accounting;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.controller.utils.SiaTool;

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
	
	@Transient
	private double total;
	
	public Account() {
		super();
	}
	
	public void calculateTotal() {
		if (amountList == null) {
			return;
		}
		total = 0.0;
		for (Amount amount : amountList) {
			if (amount.getType() == 'I') {
				total += amount.getTotal();
			} else if (amount.getType() == 'E') {
				total -= amount.getTotal();
			}
		}
		total = SiaTool.getTrunkDecimal(total);
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", balance=" + balance + ", amountList=" + amountList
				+ ", accountType=" + accountType + "]";
	}
   
}
