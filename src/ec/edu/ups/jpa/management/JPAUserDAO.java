package ec.edu.ups.jpa.management;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Query;

import ec.edu.ups.dao.management.UserDAO;
import ec.edu.ups.entities.management.User;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAUserDAO extends JPAGenericDAO<User, Integer> implements UserDAO {

	public JPAUserDAO() {
		super(User.class);
	}

	@Override
	public User login(String key, String password) {
		password = getMd5(password);
		String jpql = "SELECT u FROM User u "
				+ "WHERE u.email LIKE '" + key + "'"
				+ "AND u.password LIKE '" + password + "'";
		em.clear();
		Query query = em.createQuery(jpql);
		User user = (User)query.getSingleResult();
		em.refresh(user);
		return user;
	}
	
	private static String getMd5(String input) { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }

}
