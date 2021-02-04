package ec.edu.ups.dao.registration;

import java.util.List;

import ec.edu.ups.dao.GenericDAO;
import ec.edu.ups.entities.management.Student;
import ec.edu.ups.entities.registration.Inscription;

public interface InscriptionDAO extends GenericDAO<Inscription, Integer> {
	public Inscription getInscriptionByStudentId(int studentId);
	public Student getStudentByDni(String dni);
	public Inscription getCurrentInscrited(int studentId);
	public boolean isStudentCreated(String dni);
	public boolean isEmailCreated(String email);
	
	public List<Inscription> getInscriptionByStudentDni(String dni);
}
