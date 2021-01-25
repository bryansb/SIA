package ec.edu.ups.jpa.registration;

import java.util.List;

import ec.edu.ups.dao.registration.InscriptionDAO;
import ec.edu.ups.entities.management.Student;
import ec.edu.ups.entities.registration.Inscription;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAInscriptionDAO extends JPAGenericDAO<Inscription, Integer> 
	implements InscriptionDAO {

	private static final String GET_STUDENT_BY_DNI_QRY = 
			" SELECT s FROM Student s "
			+ " WHERE s.dni LIKE :dni";
	private static final String IS_CURRENT_INSCRITED_QRY = 
			" SELECT i FROM Inscription i "
			+ " WHERE i.status = 'A' "
			+ " AND i.student.id = :studentId";
	
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

	@Override
	public Student getStudentByDni(String dni) {
		return (Student) super.em.createQuery(GET_STUDENT_BY_DNI_QRY)
				.setParameter("dni", dni).getSingleResult();
	}

	@Override
	public Inscription getCurrentInscrited(int studentId) {
		try {
			Inscription inscription = (Inscription) super.em.createQuery(IS_CURRENT_INSCRITED_QRY)
					.setParameter("studentId", studentId)
					.getSingleResult();
			return inscription;
		} catch (Exception e) {
			return null;
		}
	}

}
