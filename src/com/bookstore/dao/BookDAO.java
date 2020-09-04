package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO() {
	}

	@Override
	public Book create(Book entity) {
		entity.setLastUpdateTime(new Date());
		return super.create(entity);
	}

	@Override
	public Book update(Book entity) {
		entity.setLastUpdateTime(new Date());
		return super.update(entity);
	}

	@Override
	public Book get(Object id) {
		return super.find(Book.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Book.class, id);
	}

	@Override
	public List<Book> listAll() {
		System.out.println("inside listAll bookDao");
		return super.findWithNamedQuery("Book.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Book.count");
	}
	
	public Book findByTitle(String title){
		List<Book> books=super.findWithNamedQuery("Book.findByTitle", "title", title);
		
		if(!books.isEmpty()){
			System.out.println("***************inside bookdao*********************");
			for(Book b:books){
				System.out.println(b.getTitle());
			}
			return books.get(0);
		}
		System.out.println("***************inside bookdao findbytitle*********************");

		return null;
	}
	
	public List<Book> listNewBook(){
		return super.findWithNamedQuery("Book.listbook",0,5);
	}
	
	public List<Book> listByCategory(int categoryId){
		List<Book> booklist=super.findWithNamedQuery("Book.bookByCategory", "catID", categoryId);
		return booklist;
	}
	
	public List<Book> search(String keyword){
		List<Book> books=super.findWithNamedQuery("Book.search", "keyword", keyword);
		return books;
	}
	
	public long countBookByCategory(Integer categoryId){
		return super.countWithNamedQuery("Book.countByCategory","catId",categoryId);
	}

}
