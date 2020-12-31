package ec.edu.ups.test.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.edu.ups.entities.Inscription;

class InscriptionTest {

	private Inscription inscription;
	
	@BeforeEach
	void setUp() throws Exception {
		this.inscription = new Inscription();
	}

	@Test
	void test() {
		assertEquals("", "");
	}

}
