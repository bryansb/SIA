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
   
}
