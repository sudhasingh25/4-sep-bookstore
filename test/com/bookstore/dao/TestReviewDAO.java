package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class TestReviewDAO {

	private static ReviewDAO reviewDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao=new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}

	@Test
	public void testCreateReview() {
		Review review=new Review();
		
		Book book=new Book();
		book.setBookId(34);
		
		Customer customer=new Customer();
		customer.setCustomerId(10);
		
		review.setBook(book);
		review.setCustomer(customer);
		review.setHeadline("This is very good Book");
		review.setRating(3);
		review.setComment("nice book must buy");
		
		Review saved=reviewDao.create(review);
		assertTrue(saved.getReviewId()>0);

		
	}

	@Test
	public void testUpdateReview() {
		Integer reviewId=1;
		Review review=reviewDao.get(reviewId);
		review.setHeadline("Excellent book");
		Review updated=reviewDao.update(review);
		assertEquals(review.getHeadline(), updated.getHeadline());
	}

	@Test
	public void testGet() {
		Integer reviewId=1;
		Review review=reviewDao.get(reviewId);
		assertNotNull(review);
	}

	@Test
	public void testDeleteObject() {
		Integer reviewId=1;
		reviewDao.delete(reviewId);
		
	}
	
	@Test
	public void testFindByCustomerAndBook(){
		Review review=reviewDao.findByCustomerAndBook(11, 12);
		assertNotNull(review);
	}

	@Test
	public void testListAll() {
		List<Review> lisReview=reviewDao.listAll();
		for(Review r:lisReview){
			System.out.println(r.getBook()+" "+r.getReviewId());
		}
		assertNotNull(lisReview);
	}

}
