package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.bookstore.entity.Book;

public class TestShoppingCart {

	
	ShoppingCart cart=new ShoppingCart();
	@Test
	public void testAddItem() {
		Book book=new Book(17);
		cart.addItem(book);
		cart.addItem(book);
		Map<Book,Integer> item=cart.getItems();
		int quantity=item.get(book);
		assertEquals(2,quantity);
	}
	
	@Test
	public void testRemoveItem() {
		Book book=new Book(17);
		cart.addItem(book);
		cart.addItem(book);
		Map<Book,Integer> item=cart.getItems();
		Integer quantity=item.get(book);
		cart.removeItem(book);
		assertTrue(cart.getItems().isEmpty());
	}
	
	@Test
	public void testGetTotalQuantity(){
		Book book=new Book(17);
		cart.addItem(book);
		cart.addItem(book);
		
		Book book1=new Book(13);
		cart.addItem(book1);
		
		assertEquals(3, cart.getTotalQuantity());
	}
	
	@Test
	public void testGetTotalAmount(){
		Book book=new Book(36);
		cart.addItem(book);
		cart.addItem(book);
		Book book1=new Book(17);
		cart.addItem(book1);
		
		double t=cart.getTotalAmount();
		System.out.print("t========="+t);
		assertEquals(200.0f,t ,0.0f);
	}
	@Test
	public void testClear(){
		Book book1=new Book(17);
		cart.addItem(book1);
		cart.clear();
		assertEquals(0, cart.getTotalQuantity());
	}
	
	@Test
	public void testUpdateCart(){
		Book book=new Book(36);
		cart.addItem(book);
		cart.addItem(book);
		Book book1=new Book(17);
		cart.addItem(book1);
		cart.addItem(book1);
		
		int[] bookId={17,36};
		int[] quantities={1,2};
		
		cart.updateCart(bookId, quantities);
		assertEquals(3, cart.getTotalQuantity());
	}
}
