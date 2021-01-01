package ec.edu.ups.test.entities.registration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.entities.registration.Enrollment;

class EnrollmentTest {

	private Enrollment enrollment;
	
	@BeforeEach
	void setUp() throws Exception {
		enrollment = new Enrollment(new Date());
	}

	@Test
	void test() {
		enrollment.setDate(new Date());
		assertFalse(enrollment.getDate().toString().isEmpty());
	}

}
