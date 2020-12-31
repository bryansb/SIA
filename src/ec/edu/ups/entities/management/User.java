package ec.edu.ups.entities.management;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name = "USERS")
public abstract class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "use_id")
	private int id;
	
	@Column(name = "use_full_name")
	private String fullName;
	
	@Column(name = "use_email")
	private String email;
	
	@Column(name = "use_password")
	private String password;
	
	@Column(name = "use_dni")
	private String dni;
	
	@Column(name = "use_phone")
	private String phone;
	
	@Column(name = "use_address")
	private String address;

	@Column(name = "use_type")
	private char type;
	
	public User() {
		super();
	}
   
}
