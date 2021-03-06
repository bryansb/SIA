package ec.edu.ups.entities.offer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.entities.registration.Inscription;

/**
 * Entity implementation class for Entity: Career
 *
 */
@Entity
@Table(name = "CAREERS")
public class Career implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int id;
	
	@Column(name = "car_name", nullable = false)
	private String name;
	
	@Column(name = "car_time", nullable = true)
	private int time;
	
	@Column(name = "car_deleted", nullable = false,  
			columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean deleted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "career", fetch = FetchType.LAZY)
	private List<Subject> subjectList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "career", fetch = FetchType.LAZY)
	private List<Inscription> inscriptionList;
	
	@Transient
	private boolean editable;
	
	public Career() {
		super();
	}

	public Career(String name, int time) {
		super();
		this.name = name;
		this.time = time;
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Inscription> getInscriptionList() {
		return inscriptionList;
	}

	public void setInscriptionList(List<Inscription> inscriptionList) {
		this.inscriptionList = inscriptionList;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Career [id=" + id + ", name=" + name + ", time=" + time + ", subjectList=" + subjectList
				+ ", inscriptionList=" + inscriptionList + "]";
	}
   
}
