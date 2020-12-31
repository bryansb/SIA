package ec.edu.ups.entities.accounting;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AccountType
 *
 */
@Entity
@Table(name = "ACCOUNT_TYPES")
public class AccountType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acc_typ_id")
	private int id;
	
	@Column(name = "acc_typ_name")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountType", 
			fetch = FetchType.LAZY)
	private List<Account> accountList;
	
	public AccountType() {
		super();
	}
   
}
