package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewService {
	private HttpServletRequest request;
	private ReviewDAO reviewDao;
	private HttpServletResponse response;
	
	public ReviewService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		reviewDao=new ReviewDAO();
	}
	
	public void listReview() throws ServletException, IOException {
		listReview(null);
	}

	public void listReview(String message) throws ServletException, IOException {
		//System.out.println("i m called*******************************");
		
		List<Review> listreview=reviewDao.listAll();
		
		/*for(Review r:listreview){
			System.out.println(r.getReviewTime());
		}*/
		request.setAttribute("listreview", listreview);
		request.setAttribute("message", message);
		RequestDispatcher dispatcher=request.getRequestDispatcher("list_review.jsp");
		dispatcher.forward(request, response);
	}

	public void editReview() throws ServletException, IOException {
		Integer reviewId=Integer.parseInt(request.getParameter("id"));
		Review review=reviewDao.get(reviewId);
		
		request.setAttribute("review", review);
		RequestDispatcher dispatcher=request.getRequestDispatcher("edit_review.jsp");
		dispatcher.forward(request, response);
	}

	public void updateReview() throws ServletException, IOException {
		Integer reviewId=Integer.parseInt(request.getParameter("reviewId"));
		String headline=request.getParameter("headline");
		String comment=request.getParameter("comment");
		Review review=reviewDao.get(reviewId);
		
		review.setHeadline(headline);
		review.setComment(comment);
		
		reviewDao.update(review);
		listReview("Review has been updated successfully");
	}

	public void deleteReview() throws ServletException, IOException {
		Integer reviewId=Integer.parseInt(request.getParameter("id"));
		reviewDao.delete(reviewId);
		listReview("Review has been deleted successfully");
	}

	public void showReviewForm() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("book_id"));
		BookDAO bookDAO=new BookDAO();
		Book book=bookDAO.get(bookId);
		Customer customer=(Customer)request.getSession().getAttribute("loggedCustomer");

		request.getSession().setAttribute("book",book);
		
		Review existReview=reviewDao.findByCustomerAndBook(customer.getCustomerId(), bookId);
		String targetPage="frontend/review_form.jsp";
		if(existReview!=null){
			request.setAttribute("review", existReview);
			targetPage="frontend/review_info.jsp";
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(targetPage);
		dispatcher.forward(request, response);
	}

	public void submitReview() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("bookId"));
		Integer rating=Integer.parseInt(request.getParameter("rating"));
		String comment=request.getParameter("comment");
		String headline=request.getParameter("headline");
		
		Review review=new Review();
		review.setHeadline(headline);
		review.setComment(comment);
		review.setRating(rating);
		
		Book book=new Book();
		book.setBookId(bookId);
		review.setBook(book);
		
		Customer customer=(Customer)request.getSession().getAttribute("loggedCustomer");
		review.setCustomer(customer);
		
		reviewDao.create(review);
		
		RequestDispatcher dis=request.getRequestDispatcher("frontend/review_done.jsp");
		dis.forward(request, response);
	}
	
	
}
