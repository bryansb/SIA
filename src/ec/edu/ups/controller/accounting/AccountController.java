package ec.edu.ups.controller.accounting;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AccountDAO;
import ec.edu.ups.dao.accounting.AccountTypeDAO;
import ec.edu.ups.entities.accounting.Account;
import ec.edu.ups.entities.accounting.AccountType;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/AccountController")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> AccountController > ";
	private static final String URL = "/JSP/private/accounting/accountCrud.jsp";
	private static final Logger LOGGER = Logger.getLogger(AccountController.class.getName());
	private final AccountDAO accountDAO;
	private final AccountTypeDAO accountTypeDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountController() {
    	super();
    	accountDAO = DAOFactory.getFactory().getAccountDAO();
    	accountTypeDAO = DAOFactory.getFactory().getAccountTypeDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
	    	response.setCharacterEncoding("UTF-8");
	    	
	    	option(request);
	    	listAll(request);
	    	
			getServletContext().getRequestDispatcher(URL).forward(request, response);
		} catch (Exception e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
    
    private void option(HttpServletRequest request) {
    	try {
			String option = request.getParameter("option");
			if (option != null && option.equals("create")) {
				create(request);
			}
			
			if (option != null && option.equals("read")) {
				read(request);
			}
			
			if (option != null && option.equals("update")) {
				update(request);
			}
		} catch (Exception e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
    	
    }
	
	protected void create(HttpServletRequest request) {
		try {
			AccountType accountType = accountTypeDAO.read(Integer.parseInt(request.getParameter("accountTypeId")));
			if (accountType == null) {
				return;
			}
			Account account = new Account();
			account.setAccountType(accountType);
			account.setName(request.getParameter("name").toUpperCase());
			account.setBalance(Double.parseDouble(request.getParameter("balance")));
			accountDAO.create(account);
		} catch (Exception e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
	
	protected void read(HttpServletRequest request) {
		try {
			request.setAttribute("accountRead", accountDAO.read(Integer.parseInt(request.getParameter("accountId"))));
		} catch (Exception e) {
			request.setAttribute("accountRead", null);
		}
	}
	
	protected void update(HttpServletRequest request) {
		try {
			AccountType accountType = accountTypeDAO.read(Integer.parseInt(request.getParameter("accountTypeId")));
			if (accountType == null) {
				return;
			}
			Account account = accountDAO.read(Integer.parseInt(request.getParameter("accountId")));
			account.setAccountType(accountType);
			account.setName(request.getParameter("name").toUpperCase());
			account.setBalance(Double.parseDouble(request.getParameter("balance")));
			accountDAO.update(account);
		} catch (Exception e) {
			String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
	
	protected void listAll(HttpServletRequest request) {
		request.setAttribute("accountList", accountDAO.find("name", 0, 0));
		request.setAttribute("accountTypeList", accountTypeDAO.find("name", 0, 0));
	}

}
