package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

	@Override
	public Customer get(Object id) {
		// TODO Auto-generated method stub
		return super.find(Customer.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Customer.class, id);
	}

	@Override
	public List<Customer> listAll() {
		// TODO Auto-generated method stub
		System.out.println("inside listAll");
		return super.findWithNamedQuery("Customer.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Customer.count");
	}
	
	public Customer create(Customer entity){
		entity.setRegisterDate(new Date());
		return super.create(entity);
	}
	
	public Customer update(Customer entity){
		entity.setRegisterDate(new Date());
		return super.update(entity);
	}
	
	public Customer findByEmail(String email){
		List<Customer> listCustomer=super.findWithNamedQuery("Customer.findByEmail","email",email);
		if(!listCustomer.isEmpty()){
			return listCustomer.get(0);
		}
		return null;
	}

	public Customer checkLogin(String email, String password) {
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);
		
		List<Customer> customers=super.findWithNamedQuery("Customer.checkLogin", parameters);
		if(!customers.isEmpty()){
			return customers.get(0);
		}
		return null;
	}

}
