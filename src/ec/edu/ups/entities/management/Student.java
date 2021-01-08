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
	
	@Column(name = "use_birthdate")
	private Date birthdate;
	
	@Column(name = "use_gender")
	private char gender;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student", 
			fetch = FetchType.LAZY)
	private List<Inscription> inscriptionList;

	public Student() {
		super();
	}

	public Student(Date birthdate, char gender, List<Inscription> inscriptionList) {
		super();
		this.birthdate = birthdate;
		this.gender = gender;
		this.inscriptionList = inscriptionList;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public List<Inscription> getInscriptionList() {
		return inscriptionList;
	}

	public void setInscriptionList(List<Inscription> inscriptionList) {
		this.inscriptionList = inscriptionList;
	}

	@Override
	public String toString() {
		return "Student [birthdate=" + birthdate + ", gender=" + gender + ", inscriptionList=" + inscriptionList + "]";
	}
   
	
}
