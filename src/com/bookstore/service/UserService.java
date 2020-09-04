package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserService {
	
	private HttpServletRequest request;
	private UserDAO userDao;
	private HttpServletResponse response;
	public UserService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		userDao=new UserDAO();
	}
	
	public void listUser() throws ServletException, IOException {
		listUser(null);
	}
	
	public void listUser(String message) throws ServletException, IOException {
		List<Users> listuser=userDao.listAll();
		request.setAttribute("message", message);
		request.setAttribute("listuser", listuser);
		RequestDispatcher dis=request.getRequestDispatcher("list_user.jsp");
		dis.forward(request, response);
	}

	public void createUser() throws ServletException, IOException {
		String email=request.getParameter("email");
		String fullname=request.getParameter("fullname");
		String password=request.getParameter("password");
		
		Users exist=userDao.findByEmail(email);
		
		if(exist!=null){
			String message="Could not create a user with email "+email+"Already exist.";
			
			request.setAttribute("message", message);
			
			RequestDispatcher dis=request.getRequestDispatcher("message.jsp");
			dis.forward(request, response);
		}else{
			Users user=new Users();
			user.setEmail(email);
			user.setFullName(fullname);
			user.setPassword(password);
		
			userDao.create(user);
			listUser("A new user created successfully");
		}
	}

	public void updateUser() throws ServletException, IOException {
		Integer userId=Integer.parseInt(request.getParameter("userId"));
		Users user=userDao.get(userId);
		
		String email=request.getParameter("email");
		String fullname=request.getParameter("fullname");
		String password=request.getParameter("password");
		
		Users exist=userDao.findByEmail(email);
		
		if(exist!=null && exist.getUserId()!=userId){
			String message="Could not a user with email "+email+" is already taken by other user.";
			request.setAttribute("message", message);
			RequestDispatcher dis=request.getRequestDispatcher("message.jsp");
			dis.forward(request, response);
		}else{
			Users updated=new Users(userId,email,password,fullname);
			userDao.update(updated);
			listUser("User has been updated successfully.");
		}
	}

	public void editUser() throws ServletException, IOException {
		Integer userId=Integer.parseInt(request.getParameter("id"));
		Users user=userDao.get(userId);
		
		request.setAttribute("user", user);
		RequestDispatcher dis=request.getRequestDispatcher("create_user.jsp");
		dis.forward(request, response);
	}

	public void deleteUser() throws ServletException, IOException {
		Integer userId=Integer.parseInt(request.getParameter("id"));
		userDao.delete(userId);
		listUser("User has been deleted successfully");
	}

	public void checkLogin() throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		System.out.println("IIIIII");
		boolean existUser=userDao.checkLogin(email, password);
		
		if(existUser){
			System.out.println("inside exist");
			request.getSession().setAttribute("loggedUser", email);
			RequestDispatcher dis=request.getRequestDispatcher("/admin/");
			dis.forward(request, response);
		}else{
			System.out.println("outside exist");
			String message="Login Failed";
			RequestDispatcher dis=request.getRequestDispatcher("login.jsp");
			dis.forward(request, response);
		}
	}
	
	
	
}
