package ec.edu.ups.test.entities.offer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.entities.offer.Career;

class CareerTest {
	
	private Career career;

	@BeforeEach
	void setUp() throws Exception {
		career = new  Career("CarrerTest", 1, null, null);
	}

	@Test
	void test() {
		career.setName("CarrerTest");
		career.setTime(1);
		assertEquals("CarrerTest", career.getName());
		assertEquals(1, career.getTime());
	}

}
