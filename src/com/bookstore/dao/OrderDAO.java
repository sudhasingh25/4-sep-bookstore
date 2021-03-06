package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import com.bookstore.entity.BookOrder;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {

	public OrderDAO(){
		
	}
	
	public BookOrder create(BookOrder order){
		order.setOrderDate(new Date());
		order.setPaymentMethod("Cash On Deliery");
		order.setStatus("processing");
		return super.create(order);
	}
	
	public BookOrder update(BookOrder order){
		order.setOrderDate(new Date());
		return super.update(order);
	}
	
	
	@Override
	public BookOrder get(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookOrder> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
