package ec.edu.ups.test.entities.registration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.entities.management.Student;
import ec.edu.ups.entities.offer.Career;
import ec.edu.ups.entities.registration.Inscription;

class InscriptionTest {
	private Inscription inscription;
	
	@BeforeEach
	void setUp() throws Exception {
		Student student = new Student();
		Career career = new Career();
		this.inscription = new Inscription(new GregorianCalendar(), student, career);
	}

	@Test
	void test() {
		this.inscription.setDate(new GregorianCalendar());
		assertFalse(inscription.getDate().toString().isEmpty());
	}

}
