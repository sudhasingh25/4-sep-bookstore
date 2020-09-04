package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO() {
	}

	@Override
	public Category create(Category entity) {
		return super.create(entity);
	}

	@Override
	public Category update(Category entity) {
		return super.update(entity);
	}

	@Override
	public Category get(Object id) {
		return super.find(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Category.class, id);
	}

	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.listAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Category.count");
	}
	
	public Category findByName(String name){
		List<Category> listcat= super.findWithNamedQuery("Category.findByName", "name", name);
		if(listcat!=null && listcat.size()>0){
			return listcat.get(0);
		}
		return null;
	}

}
