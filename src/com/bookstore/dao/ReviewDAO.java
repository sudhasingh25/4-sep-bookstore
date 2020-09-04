package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

	@Override
	public Review create(Review entity) {
		entity.setReviewTime(new Date());
		return super.create(entity);
	}

	@Override
	public Review update(Review entity) {
		entity.setReviewTime(new Date());
		return super.update(entity);
	}

	@Override
	public Review get(Object id) {
		
		return super.find(Review.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Review.class, id);
	}

	@Override
	public List<Review> listAll() {
		return super.findWithNamedQuery("Review.listAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Review.count");
	}
	
	public Review findByCustomerAndBook(Integer customerId,Integer bookId){
		Map<String ,Object> parameters= new HashMap<>();
		parameters.put("bookId", bookId);
		parameters.put("customerId", customerId);
		List<Review> reviews=super.findWithNamedQuery("Review.findByCustomerAndBook", parameters);
		if(!reviews.isEmpty()){
			return reviews.get(0);
		}
		return null;
	}

}
