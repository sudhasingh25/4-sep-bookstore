package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerService {
	
	private HttpServletRequest request;
	private CustomerDAO customerDao;
	private HttpServletResponse response;
	
	public CustomerService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		customerDao=new CustomerDAO();
	}
	
	public void listCustomer() throws ServletException, IOException{
		listCustomer(null);
	}
	
	public void listCustomer(String message) throws ServletException, IOException{
		System.out.println("********************************************inside listCustomer");
		List<Customer> listCustomers=customerDao.listAll();
		
		if(listCustomers!=null){
			System.out.println("i m not null");
		}
		request.setAttribute("listCustomer",  listCustomers);
		request.setAttribute("message", message);
		RequestDispatcher dis=request.getRequestDispatcher("list_customer.jsp");
		dis.forward(request, response);
	}

	public void createCustomer() throws ServletException, IOException {
		Customer customer=new Customer();
		String email=request.getParameter("email");
		
		Customer exist=customerDao.findByEmail(email);
		if(exist!=null){
			listCustomer("Could not create a customer with email "+email+" already exist.");
			return;
		}
		String fullname=request.getParameter("fullname");
		String password=request.getParameter("password");
		String phone=request.getParameter("phone");
		String address =request.getParameter("address");
		String city=request.getParameter("city");
		String zipcode=request.getParameter("zipcode");
		String country=request.getParameter("country");
		
		customer.setEmail(email);
		customer.setFullname(fullname);
		customer.setPassword(password);
		customer.setPhoneNumber(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipCode(zipcode);
		customer.setCountry(country);
		Customer newCustomer=customerDao.create(customer);
		
		if(newCustomer.getCustomerId()>0){
			listCustomer("New Customer created successfully.");
		}
		
	}

	public void editCustomer() throws ServletException, IOException {
		Integer customerId=Integer.parseInt(request.getParameter("id"));
		Customer customer=customerDao.get(customerId);
		request.setAttribute("customer", customer);
		RequestDispatcher dis=request.getRequestDispatcher("create_customer.jsp");
		dis.forward(request, response);
	}

	public void updateCustomer() throws ServletException, IOException {
		Integer customerId=Integer.parseInt(request.getParameter("customerId"));
		Customer customer=customerDao.get(customerId);
		String email=request.getParameter("email");
		Customer exist=customerDao.findByEmail(email);
		
		if(exist!=null && exist.getCustomerId()!=customerId){
			listCustomer("Could not update a user with mail "+email+" is already exist.");
			return;
		}else{
			String fullname=request.getParameter("fullname");
			String password=request.getParameter("password");
			String phone=request.getParameter("phone");
			String address =request.getParameter("address");
			String city=request.getParameter("city");
			String zipcode=request.getParameter("zipcode");
			String country=request.getParameter("country");
			
			
			customer.setEmail(email);
			customer.setFullname(fullname);
			customer.setPassword(password);
			customer.setPhoneNumber(phone);
			customer.setAddress(address);
			customer.setCity(city);
			customer.setZipCode(zipcode);
			customer.setCountry(country);
			customerDao.update(customer);
			listCustomer("Customer updated successfully.");
			
		}
		
	}

	public void deleteCustomer() throws ServletException, IOException {
		Integer customerId=Integer.parseInt(request.getParameter("id"));
		customerDao.delete(customerId);
		listCustomer("Customer has been deleted successfully.");
	}

	public void registerCustomer() throws ServletException, IOException {
		Customer customer=new Customer();
		String email=request.getParameter("email");
		String message;
		Customer exist=customerDao.findByEmail(email);
		if(exist!=null){
			message="Could not Register a customer with email "+email+" already exist.";
		}else{
		String fullname=request.getParameter("fullname");
		String password=request.getParameter("password");
		String phone=request.getParameter("phone");
		String address =request.getParameter("address");
		String city=request.getParameter("city");
		String zipcode=request.getParameter("zipcode");
		String country=request.getParameter("country");
		
		customer.setEmail(email);
		customer.setFullname(fullname);
		customer.setPassword(password);
		customer.setPhoneNumber(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipCode(zipcode);
		customer.setCountry(country);
		customerDao.create(customer);
		message="You are Registered successfully.Thank You!<br/>"
				+ "<a href='login'>Click here</a> to Login";
		}
		
		request.setAttribute("message", message);
		RequestDispatcher dis=request.getRequestDispatcher("/frontend/message.jsp");
		dis.forward(request, response);
	}

	public void loginPage() throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("/frontend/login.jsp");
		dis.forward(request, response);
		
	}

	public void doLogin() throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String message;
		String page;
		Customer customer=customerDao.checkLogin(email,password);
		
		if(customer==null){
			message="Login fail please check email and password";
			request.setAttribute("message", message);
			loginPage();
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("loggedCustomer", customer);
			
			Object objRedirectURL=session.getAttribute("redirectURL");
			if(objRedirectURL !=null){
				String redirectURL=(String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			}else{
				showCustomerProfile();
			}
			
			
		}
		
		
	}

	public void showCustomerProfile() throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("/frontend/customer_profile.jsp");
		dis.forward(request, response);
		
	}

	public void showEditCustomerForm() throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("/frontend/edit_profile.jsp");
		dis.forward(request, response);
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer=(Customer)request.getSession().getAttribute("loggedCustomer");
		String fullname=request.getParameter("fullname");
		String password=request.getParameter("password");
		String phone=request.getParameter("phone");
		String address =request.getParameter("address");
		String city=request.getParameter("city");
		String zipcode=request.getParameter("zipcode");
		String country=request.getParameter("country");
		
		
		customer.setFullname(fullname);
		if(password!=null && !password.equals("")){
		customer.setPassword(password);}
		customer.setPhoneNumber(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipCode(zipcode);
		customer.setCountry(country);
		customerDao.update(customer);
		showCustomerProfile();
	}
	
}
