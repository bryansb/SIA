package ec.edu.ups.dao.utils;

import ec.edu.ups.dao.GenericDAO;
import ec.edu.ups.entities.utils.Parameter;

public interface ParameterDAO extends GenericDAO<Parameter, Integer>{
	public Parameter findByKey(String key);
}
