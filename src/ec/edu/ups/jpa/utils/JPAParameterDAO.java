package ec.edu.ups.jpa.utils;

import java.util.List;

import ec.edu.ups.dao.utils.ParameterDAO;
import ec.edu.ups.entities.utils.Parameter;
import ec.edu.ups.jpa.JPAGenericDAO;

public class JPAParameterDAO extends JPAGenericDAO<Parameter, Integer> implements ParameterDAO {

	public JPAParameterDAO() {
		super(Parameter.class);
	}

	@Override
	public Parameter findByKey(String key) {
		List<Parameter> parameterList;
		String[][] attributes = {{"key"}};
		String[] values = {"equal&" + key};
		parameterList = super.findByPath(attributes, values, null, 0, 1, false, false);
		try {
			return parameterList.get(0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(">>> Error >> JPAParameterDAO:findByKey:"
					+ "IndexOutOfBoundsException > " + e);
		} catch (Exception e) {
			System.out.println(">>> Error >> JPAParameterDAO:findByKey " + e);
		}
		return null;
	}
	
}
