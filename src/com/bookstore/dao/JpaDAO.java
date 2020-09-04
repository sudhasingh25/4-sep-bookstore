package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bookstore.entity.Book;



public class JpaDAO<T> {
	private static EntityManagerFactory entityManagerFactory;


	static{
		System.out.println("inside static");
		entityManagerFactory=Persistence.createEntityManagerFactory("BooksStoreWebsite");
		System.out.println("inside static2");
	}
	public JpaDAO() {
	}
	
	public T create(T entity){
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
		return entity;
	}
	
	public T update(T entity){
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		//entityManager.close();
		return entity;
	}



	public T find(Class<T> type, Object id) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		T entity=entityManager.find(type,id);
		if(entity!=null){
			entityManager.refresh(entity);
		}
		//entityManager.close();
		return entity;
	}
	
	public void delete(Class<T> type,Object id){
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Object reference=entityManager.getReference(type, id);
		entityManager.remove(reference);
		entityManager.getTransaction().commit();
	//	entityManager.close();
	}

	public long countWithNamedQuery(String string) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createNamedQuery(string);
		long total=query.getFirstResult();
		//entityManager.close();
		return total;
	}
	
	public List<T> findWithNamedQuery(String string){
		System.out.println("i m findwithnamedquery");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createNamedQuery(string);
		//entityManager.close();
		return query.getResultList();
	}

	public List<T> findWithNamedQuery(String query1, String paramName, Object paramValue) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createNamedQuery(query1);
		query.setParameter(paramName, paramValue);
		//entityManager.close();
		return query.getResultList();
	}
	
	public List<T> findWithNamedQuery(String query1, int firstResult, int maxResult) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createNamedQuery(query1);
		
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		//entityManager.close();
		return query.getResultList();
		
	}
	
	public List<T> findWithNamedQuery(String queryName,Map<String,Object>parameters){
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createNamedQuery(queryName);
		
		Set<Entry<String,Object>> setParametes=parameters.entrySet();
		
		for(Entry<String,Object> entry:setParametes){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		//entityManager.close();
		return query.getResultList();
	}

	public void close(){
		if(entityManagerFactory!=null){
			entityManagerFactory.close();
		}
	}

	public long countWithNamedQuery(String string, String paramName, Object paramValue) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createNamedQuery(string);
		query.setParameter(paramName, paramValue);
		long total=(long) query.getSingleResult();
		//entityManager.close();
		return total;
		
	}
	
}
