package ec.edu.ups.controller.utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.utils.ParameterDAO;
import ec.edu.ups.entities.utils.Parameter;

/**
 * Servlet implementation class ParameterController
 */
@WebServlet("/ParameterController")
public class ParameterBasicCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ParameterDAO parameterDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParameterBasicCreation() {
        super();
        parameterDAO = DAOFactory.getFactory().getParameterDAO();
    }

    public void init() throws ServletException {
    	createParameter("IVA", "0.12", "IVA - Ecuador 2021");
    	createParameter("BASIC_TAX", "0.05", 
    			"Impuesto básico de la institución");
    	System.out.println("----------");
        System.out.println("---------- ParameterBasicCreation Initialized successfully ----------");
        System.out.println("----------");
    }

	public String createParameter(String key, String value, String description) {
		Parameter parameter;
		try {
			parameter = new Parameter(key, value, description);
			parameterDAO.create(parameter);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "Error";
	}
	
}
