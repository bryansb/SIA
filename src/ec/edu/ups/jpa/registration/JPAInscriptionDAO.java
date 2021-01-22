package ec.edu.ups.jpa.registration;

import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.registration.Inscription;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAInscriptionDAO extends JPAGenericDAO<Inscription, Integer> 
	implements InscriptionDAO {

	public JPAInscriptionDAO() {
		super(Inscription.class);
	}

}
