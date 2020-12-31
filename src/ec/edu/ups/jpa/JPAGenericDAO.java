package ec.edu.ups.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.edu.ups.dao.GenericDAO;

public class JPAGenericDAO<T, ID> implements GenericDAO<T, ID>{

	private Class<T> persistentClass;
	protected EntityManager em;
	
	public JPAGenericDAO(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
		this.em = Persistence.createEntityManagerFactory("SIA").createEntityManager();
	}
	
	@Override
	public void create(T entity) {
		em.getTransaction().begin();
		try {
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR>JPAGenericDAO:create" + e);
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public T read(ID id) {
		em.clear();
		return em.find(this.persistentClass, id);
	}

	@Override
	public void update(T entity) {
		em.getTransaction().begin();
		try {
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR>JPAGenericDAO:update" + e);
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		
	}

	@Override
	public void delete(T entity) {
		em.getTransaction().begin();
		try {
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR>JPAGenericDAO:delete" + e);
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		
	}

	@Override
	public void deleteByID(ID id) {
		T entity = this.read(id);
		if (entity != null) {
			this.delete(entity);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> find(String order, int index, int size) {
		em.clear();
		// Se crea un criterio de consulta
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		
		// FROM
		Root<T> root = criteriaQuery.from(this.persistentClass);
		
		// SELECT
		criteriaQuery.select(root);
		
		// ORDER
		if (order != null) criteriaQuery.orderBy(criteriaBuilder.asc(root.get(order)));
		
		// Resultado
		if (index >= 0 && size > 0) {
			TypedQuery<T> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(index);
			tq.setMaxResults(size);
			return (List<T>) tq.getResultList();
		} else {
			// Se realiza la Query
			Query query = em.createQuery(criteriaQuery);
			return (List<T>) query.getResultList();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> findByPath(String[][] attributes, String[] values, String order, int index, int size,
			boolean isDistinct) {
		em.clear();
		// Se crea un criterio de consulta
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		
		// FROM
		Root<T> root = criteriaQuery.from(this.persistentClass);
		
		// SELECT
		criteriaQuery.select(root);
		
		// Predicados, combinados con AND
		Predicate predicate = criteriaBuilder.conjunction();
		for (int i = 0; i < attributes.length; i++) {
			Path path = root.get(attributes[i][0]);
			for (int j = 1; j < attributes[i].length; j++) {
				path = path.get(attributes[i][j]);
			}
			predicate = criteriaBuilder.and(predicate, getSig(criteriaBuilder, path.as(String.class), values[i]));
		}
		
		// WHERE 
		criteriaQuery.where(predicate);
		
		// ORDER
		if (order != null) criteriaQuery.orderBy(criteriaBuilder.asc(root.get(order)));
		
		criteriaQuery.distinct(isDistinct);
		
		// Resultado
		if (index >= 0 && size > 0) {
			TypedQuery<T> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(index);
			tq.setMaxResults(size);
			return (List<T>) tq.getResultList();
		} else {
			// Se realiza la Query
			Query query = em.createQuery(criteriaQuery);
			return (List<T>) query.getResultList();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> findByJoin(String[] entities, String[][] attributes, String[] values, String order, int index,
			int size, boolean isDistinct) {
		em.clear();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		Root<T> root = criteriaQuery.from(this.persistentClass);
		criteriaQuery.select(root);
		Predicate predicate = criteriaBuilder.conjunction();
		Predicate sig;
		int k = 0;
		if(entities[k].isEmpty()) {
			sig = getSig(criteriaBuilder, root.get(attributes[k][0]).as(String.class), values[k]);
			predicate = criteriaBuilder.and(predicate, sig);
			k++;
		}
		Join join = root.join(entities[k]);
		if(!attributes[k][0].isEmpty()) {
			sig = getSig(criteriaBuilder, join.get(attributes[k][0]).as(String.class), values[k]);
			predicate = criteriaBuilder.and(predicate, sig);
		}
		k++;
		for (int i = k; i < entities.length; i++) {
			join = join.join(entities[i]);
			for (int j = 0; j < attributes[i].length; j++) {
				if(!attributes[i][j].isEmpty()) {
					sig = getSig(criteriaBuilder, join.get(attributes[i][j]).as(String.class), values[i]);
					predicate = criteriaBuilder.and(predicate, sig);
				}
			}
		}
		criteriaQuery.where(predicate);
		if (order != null) criteriaQuery.orderBy(criteriaBuilder.asc(root.get(order)));
		criteriaQuery.distinct(isDistinct);
		if (index >= 0 && size > 0) {
			TypedQuery<T> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(index);
			tq.setMaxResults(size);
			return (List<T>) tq.getResultList();
		} else {
			Query query = em.createQuery(criteriaQuery);
			return (List<T>) query.getResultList();
		}
	}

	public Predicate getSig(CriteriaBuilder criteriaBuilder, 
			javax.persistence.criteria.Expression<String> exp, String value) {
		Predicate sig = null;
		String[] keys = value.split("&");
		switch (keys[0]) {
		case "equal":
			sig = criteriaBuilder.equal(exp, keys[1]);
			break;
		case "like":
			sig = criteriaBuilder.like(exp, keys[1]);
			break;
		case "notLike":
			sig = criteriaBuilder.notLike(exp, keys[1]);
			break;
		case ">":
			sig = criteriaBuilder.greaterThan(exp, keys[1]);
			break;
		case "<":
			sig = criteriaBuilder.lessThan(exp, keys[1]);
			break;
		default:
			System.out.println(">>>> ERROR > JPAGenericDAO:getSig:Option not found >> " + keys[0]);
			break;
		}
		return sig;
	}
	
}
