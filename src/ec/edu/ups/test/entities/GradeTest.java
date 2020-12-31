package ec.edu.ups.test.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.entities.Grade;

class GradeTest {

	private Grade grade;
	
	@BeforeEach
	void setUp() throws Exception {
		this.grade = new Grade();
	}

	@Test
	void test() {
		assertEquals("", "");
	}

}
