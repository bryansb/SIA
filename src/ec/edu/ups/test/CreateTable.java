package ec.edu.ups.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ec.edu.ups.dao.DAOFactory;

public class CreateTable {

	public static void main(String[] args) {
		
		try {
            Class.forName("org.eclipse.persistence.jpa.PersistenceProvider");
            System.out.println("called class");
        } catch (Exception e){
            e.printStackTrace();
        }
		
		EntityManager em = Persistence.createEntityManagerFactory("SIA").createEntityManager();
	}

}
