package ec.edu.ups.dao.offer;

import java.util.List;

import ec.edu.ups.dao.GenericDAO;
import ec.edu.ups.entities.offer.Subject;

public interface SubjectDAO extends GenericDAO<Subject, Integer> {
	public List<Subject> findByInscriptionIdToEnrollment(int inscritionId);
}
