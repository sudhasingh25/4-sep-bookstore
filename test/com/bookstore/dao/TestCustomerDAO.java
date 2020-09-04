package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class TestCustomerDAO {

	private static CustomerDAO customerDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao=new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test
	public void testGet() {
		Integer custId=2;
		Customer customer=customerDao.get(custId);
		assertNotNull(customer);
	}

	@Test
	public void testDeleteObject() {
		customerDao.delete(2);
	}

	@Test
	public void testListAll() {
		List<Customer> customers=customerDao.listAll();
		for(Customer c:customers){
			System.out.println(c.getFullname());
		}
		assertNotNull(customers);
	}

	@Test
	public void testCreateCustomer() {
		Customer customer=new Customer();
		customer.setFullname("geeta sahu");
		customer.setPassword("geeta123");
		customer.setAddress("stree 107 rampur");
		customer.setCity("rampur");
		customer.setCountry("india");
		customer.setZipCode("898989");
		customer.setEmail("geeta123@gmail.com");
		customer.setPhoneNumber("9898989898");
		
		Customer newCust=customerDao.create(customer);
		
		assertTrue(newCust.getCustomerId()>0);
		
	}

	@Test
	public void testUpdateCustomer() {
		Integer custId=2;
		Customer customer=customerDao.get(custId);
		customer.setFullname("geeta kapoor");
		
		Customer updated=customerDao.update(customer);
		assertTrue(updated.getFullname().equals("geeta kapoor"));
	}
	
	@Test
	public void testFindByEmail(){
		Customer customer=customerDao.findByEmail("ruhi1234@gmail.com");
		assertNotNull(customer);
	}

}
