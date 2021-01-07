package ec.edu.ups.test.entities.offer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.entities.offer.Subject;

class SubjectTest {

	private Subject subject;
	
	@BeforeEach
	void setUp() throws Exception {		
		subject = new Subject("subjectTest", 10, 1000.111, 160, 1, null);
	}

	@Test
	void test() {
		subject.setName("subjectTest");
		subject.setCredits(10);
		subject.setCost(1000.111);
		subject.setHours(160);
		subject.setLevel(1);
		assertEquals("subjectTest", subject.getName());
		assertEquals(10, subject.getCredits());
		assertEquals(1000.111, subject.getCost());
		assertEquals(160, subject.getHours());
		assertEquals(1, subject.getLevel());
	}

}
