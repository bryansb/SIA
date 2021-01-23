package ec.edu.ups.dao;

import java.util.List;

public interface GenericDAO<T, ID> {

	public void create(T entity) throws Exception;
		
	public T read(ID id);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public void deleteByID(ID id);
	
	public List<T> find(String order, int index, int size);
	
	public List<T> findByPath(String[][] attributes, String[] values, String order, int index, int size,
			boolean isDistinct);
	
	public List<T> findByPath(String[][] attributes, String[] values, String[] order, int index, 
			int size, boolean isAsc, boolean isDistinct);
	
	public List<T> findByJoin(String[] entities, String[][] attributes, 
			String[] values, String order, int index, int size, boolean isDistinct);
}
