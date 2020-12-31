package ec.edu.ups.test.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.entities.Enrollment;

class EnrollmentTest {
	
	private Enrollment enrollment;

	@BeforeEach
	void setUp() throws Exception {
		this.enrollment = new Enrollment();
	}

	@Test
	void test() {
		assertEquals(" ", " ");
	}

}
