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
	
	@Column(name = "use_deleted", nullable = false,  
			columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean deleted;
	
	public User() {
		super();
	}

	public User(int id, String fullName, String email, String password, String dni, String phone, String address,
			char type, boolean deleted) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.dni = dni;
		this.phone = phone;
		this.address = address;
		this.type = type;
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", email=" + email + ", password=" + password + ", dni="
				+ dni + ", phone=" + phone + ", address=" + address + ", type=" + type + ", deleted=" + deleted + "]";
	}
}
