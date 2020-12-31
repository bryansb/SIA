package ec.edu.ups.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ec.edu.ups.dao.DAOFactory;

public class CreateTable {

	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("SIA").createEntityManager();
	}

}
