package com.bookstore.controller.frontend.shoppingcart;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bookstore.entity.Book;

public class ShoppingCart {
	private Map<Book,Integer> cart=new HashMap<>();
	
	public void addItem(Book book){
		Integer quantity;
		if(cart.containsKey(book)){
			quantity=cart.get(book)+1;
		}else{
			quantity=1;
		}
		cart.put(book, quantity);
		System.out.println("quantity : "+quantity);
	}
	
	public void updateCart(int[] bookIds,int[] quantities){
		for(int i=0;i<bookIds.length;i++){
			Book book=new Book(bookIds[i]);
			Integer value=quantities[i];
			cart.put(book, value);
		}
	}

	public Map<Book,Integer> isCartEmpty() {
		if(!cart.isEmpty()){
			return cart;
		}
		return null;
	}
	
	public Map<Book,Integer> getItems(){
		return this.cart;
	}
	
	public void removeItem(Book book){
		cart.remove(book);
	}
	
	public int getTotalQuantity(){
		int total=0;
		Iterator<Book> iterator=cart.keySet().iterator();
		
		while(iterator.hasNext()){
			Book nextBook=iterator.next();
			int quantity=cart.get(nextBook);
			total+=quantity;
		}
		return total;
	}
	
	public void clear(){
		cart.clear();
	}
	
	public int gettotalItems(){
		return cart.size();
	}
	
	public double getTotalAmount(){
		double total=0.0f;
		Iterator<Book> iterator=cart.keySet().iterator();
		
		while(iterator.hasNext()){
			Book nextBook=iterator.next();
			Integer quantity=cart.get(nextBook);
			
			
			double subtotal=quantity*nextBook.getPrice();
		
			total+=subtotal;
		}
		return total;
	}
}
