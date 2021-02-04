package ec.edu.ups.controller.accounting;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.accounting.AccountDAO;
import ec.edu.ups.dao.accounting.AmountDAO;
import ec.edu.ups.entities.accounting.Account;
import ec.edu.ups.entities.accounting.Amount;

/**
 * Servlet implementation class DiaryBook
 */
@WebServlet("/DiaryBook")
public class DiaryBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_ROOT = ">>> Error >> DiaryBook > ";
	private static final String URL = "/JSP/private/accounting/diaryBook.jsp";
	private static final Logger LOGGER = Logger.getLogger(DiaryBook.class.getName());
	private final  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	private final AmountDAO amountDAO;
	private final AccountDAO accountDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryBook() {
        super();
        amountDAO = DAOFactory.getFactory().getAmountDAO();
        accountDAO = DAOFactory.getFactory().getAccountDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try { 
	    	request.setCharacterEncoding("UTF-8");
	    	response.setCharacterEncoding("UTF-8");
	    	setAccountList(request);
			setAmountList(request);
			getServletContext().getRequestDispatcher(URL).forward(request, response);
    	} catch (Exception e) {
    		String errorMessage = ERROR_ROOT + e.getMessage();
			LOGGER.log(Level.INFO, errorMessage);
		}
	}
	
	private void setAmountList(HttpServletRequest request) {
		try {
			int accountId = Integer.parseInt(request.getParameter("accountId"));
			Date start = formatter.parse(request.getParameter("start"));
			Date end = formatter.parse(request.getParameter("end"));
			List<Amount> amountList = amountDAO.findByDateAndType(start, end, accountId);
			Account account = accountDAO.read(accountId);
			account.setAmountList(amountList);
			account.calculateTotal();
			request.setAttribute("account", account);
		} catch (Exception e) {
			request.setAttribute("account", null);
		}
		
	}
	
	private void setAccountList(HttpServletRequest request) {
		request.setAttribute("accountList", accountDAO.find("name", 0, 0));
	}
}
