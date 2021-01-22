package ec.edu.ups.entities.management;

import java.io.Serializable;
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
	
	@Column(name = "use_birthdate")
	private String birthdate;
	
	@Column(name = "use_gender")
	private char gender;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student", 
			fetch = FetchType.LAZY)
	private List<Inscription> inscriptionList;

	public Student() {
		super();
	}

	public Student(String birthdate, char gender) {
		super();
		this.birthdate = birthdate;
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Student [birthdate=" + birthdate + ", gender=" + gender + "]";
	}
   
	
}
