package ec.edu.ups.entities.management;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.entities.registration.Inscription;

/**
 * Entity implementation class for Entity: Student
 *
 */
@Entity
@Table(name = "STUDENTS")
public class Student extends User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "usu_birthdate")
	private Date birthdate;
	
	@Column(name = "usu_gender")
	private char gender;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student", 
			fetch = FetchType.LAZY)
	private List<Inscription> inscriptionList;

	public Student() {
		super();
	}
   
}
