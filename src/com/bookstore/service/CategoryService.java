package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Category;
import com.bookstore.entity.Users;

public class CategoryService {
	

	private HttpServletRequest request;
	private CategoryDAO categoryDao;
	private HttpServletResponse response;

	public CategoryService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		
		categoryDao=new CategoryDAO();
	}
	
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}


	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listcategory=categoryDao.listAll();
		request.setAttribute("listcategory", listcategory);
		request.setAttribute("message", message);
		RequestDispatcher dis=request.getRequestDispatcher("list_category.jsp");
		dis.forward(request, response);
	}

	public void createCategory() throws ServletException, IOException {
		String name=request.getParameter("name");
		
		Category exist=categoryDao.findByName(name);
		
		if(exist!=null){
			String message="Could not create a category with name "+name+"Already exist.";
			
			request.setAttribute("message", message);
			
			RequestDispatcher dis=request.getRequestDispatcher("message.jsp");
			dis.forward(request, response);
		}else{
			
			Category cat=new Category();
			cat.setName(name);
		
			categoryDao.create(cat);
			listCategory("A new category created successfully");

		}
	}

	public void editCategory() throws ServletException, IOException {
		Integer catId=Integer.parseInt(request.getParameter("id"));
		Category cat=categoryDao.get(catId);
		
		request.setAttribute("category", cat);
		RequestDispatcher dis=request.getRequestDispatcher("create_category.jsp");
		dis.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		Integer categoryId=Integer.parseInt(request.getParameter("categoryId"));
		Category category=categoryDao.get(categoryId);
		
		String name=request.getParameter("name");
	
		Category exist=categoryDao.findByName(name);
		
		if(exist!=null && exist.getCategoryId()!=categoryId){
			String message="Could not a category with name "+name+" is already exist.";
			request.setAttribute("message", message);
			RequestDispatcher dis=request.getRequestDispatcher("message.jsp");
			dis.forward(request, response);
		}else{
			Category updated=new Category(categoryId,name);
			categoryDao.update(updated);
			listCategory("Category has been updated successfully.");
		}
	}

	public void deleteCategory() throws ServletException, IOException {
		Integer catId=Integer.parseInt(request.getParameter("id"));
		BookDAO bookDAO=new BookDAO();
		long noOfBooks=bookDAO.countBookByCategory(catId);
		String name=categoryDao.get(catId).getName();
		String message ;
		if(noOfBooks>0){
			message="Cound not delete a category with "+name+" because it contains some books.";
		}else{
			categoryDao.delete(catId);
			message = "Category "+name +" has been deleted successfully";
		}
		listCategory(message);
	}

}
