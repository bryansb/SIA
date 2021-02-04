package ec.edu.ups.controller.accounting;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AccountTypeDAO;
import ec.edu.ups.entities.accounting.AccountType;

/**
 * Servlet implementation class AccountTypeController
 */
public class AccountTypeController {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> AccountController > ";
	private static final String URL = "/JSP/private/accounting/accountCrud.jsp";
	private static final Logger LOGGER = Logger.getLogger(AccountTypeController.class.getName());
	private final AccountTypeDAO accountTypeDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountTypeController() {
        super();
        accountTypeDAO = DAOFactory.getFactory().getAccountTypeDAO();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void create(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	protected AccountType read(HttpServletRequest request, HttpServletResponse response) {
		return null;
		
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	protected List<AccountType> listAll(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
