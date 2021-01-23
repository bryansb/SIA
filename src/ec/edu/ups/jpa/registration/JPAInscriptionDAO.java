package ec.edu.ups.jpa.registration;

import java.util.List;

import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.registration.Inscription;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAInscriptionDAO extends JPAGenericDAO<Inscription, Integer> 
	implements InscriptionDAO {

	public JPAInscriptionDAO() {
		super(Inscription.class);
	}

	@Override
	public Inscription getInscriptionByStudentId(int studentId) {
		String[][] attributes = {{"student", "id"}, {"status"}, {"deleted"}};
		String[] values = {"equal&" + studentId, "equal&A", "equal&0"};
		List<Inscription> inscriptionList = super.findByPath(attributes, values, 
				null, 0, 0, true, true);
		if (inscriptionList != null && inscriptionList.size() == 1) {
			return inscriptionList.get(0);
		}
		return null;
	}

}
