package ec.edu.ups.test.entities.registration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.entities.offer.Group;
import ec.edu.ups.entities.registration.Grade;

class GradeTest {

	private Grade grade;
	private Group group;
	
	@BeforeEach
	void setUp() throws Exception {
		this.group = null;
		grade = new Grade("", 90.0, group, null);
	}

	@Test
	void test() {
		grade.setDescription("test");
		grade.setGradeValue(95.0);
		assertEquals(95.0, grade.getGradeValue());
	}

}
