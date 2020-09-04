package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;


public class OrderDAOTest {

	private static OrderDAO orderDao; 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDao=new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDao.close();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order=new BookOrder();
	
		Customer customer=new Customer();
		customer.setCustomerId(3);
		order.setCustomer(customer);
		
		order.setRecipientName("sudha");
		order.setRecipientPhone("7777755555");
		order.setShippingAddress("rajnandgaon");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail=new OrderDetail();
		
		Book book=new Book(36);
		orderDetail.setBook(book);
		
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(200f);
		orderDetail.setBookOrder(order);
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		
		orderDao.create(order);
		assertTrue(order.getOrderId()>0);
	}

	@Test
	public void testUpdateBookOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

}
