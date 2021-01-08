package ec.edu.ups.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;


class CreateTableTest {
	
	@Test
	void test() {
		try {
            Class.forName("org.eclipse.persistence.jpa.PersistenceProvider");
            EntityManager em = Persistence.createEntityManagerFactory("SIA").createEntityManager();
            assertTrue(em.isOpen());
        } catch (Exception e){
            fail("No se puede crear las tablas");
        }
	}

}
