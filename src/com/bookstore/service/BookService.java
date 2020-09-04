package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookService {

	private HttpServletRequest request;
	
	private CategoryDAO categoryDao;
	private HttpServletResponse response;
	private BookDAO bookDao;
	
	public BookService( HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		
		this.response = response;
		bookDao=new BookDAO();
		categoryDao=new CategoryDAO();
	}

	public void listBook() throws ServletException, IOException {
		List<Book> listbook=bookDao.listAll();
		
		if(listbook==null){
			System.out.println("listbook is null");
		}
		
		System.out.println("list book************");
		for(Book book:listbook){
			System.out.println(book.getTitle());
		}
		
		request.setAttribute("listbook", listbook);
		RequestDispatcher dis=request.getRequestDispatcher("list_book.jsp");
		dis.forward(request, response);
	}

	public void showBookForm() throws ServletException, IOException {
		List<Category> listCategory=categoryDao.listAll();
		request.setAttribute("listCategory", listCategory);
		RequestDispatcher dis=request.getRequestDispatcher("create_book.jsp");
		dis.forward(request, response);
	}

	public void createBook() throws ServletException, IOException {
		Integer categoryId=Integer.parseInt(request.getParameter("category"));
		String title=request.getParameter("title");
		
		Book existBook=bookDao.findByTitle(title);
		
		if(existBook!=null){
			String message="Could not create a book with title "+title+" already exist.";
			request.setAttribute("message", message);
			listBook();
			return;
		}
		String author=request.getParameter("author");
		String description=request.getParameter("description");
		String isbn=request.getParameter("isbn");
		float price=Float.parseFloat(request.getParameter("price"));
		
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=null;
		
		try{
			publishDate=dateFormat.parse(request.getParameter("publishDate"));
		}catch(ParseException ex){
			ex.printStackTrace();
			throw new ServletException("error parsing publish date (format is MM/dd/yyyy)");
		}
		
		Book newbook=new Book();
		newbook.setTitle(title);
		newbook.setAuthor(author);
		newbook.setDescription(description);
		newbook.setIsbn(isbn);
		newbook.setPrice(price);
		newbook.setPublishDate(publishDate);
		
		Category category=categoryDao.get(categoryId);
		if(category.getCategoryId()>0){
			System.out.println("category name:"+category.getName());
		}else{
			System.out.println("nill cal id");
		}
		newbook.setCategory(category);
		
		Part part=request.getPart("bookImage");
		
		if(part!=null && part.getSize()>0){
			long size=part.getSize();
			byte[] imageBytes= new byte[(int)size];
			
			InputStream inputStream=part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			newbook.setImage(imageBytes);
			System.out.println("******* Book *************");
		}
		
		Book createdBook=bookDao.create(newbook);
		if(createdBook.getBookId()>0){
			String message="A new book has been created successfully.";
			request.setAttribute("message", message);
			listBook();
		}
	}

	public void editBook() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("id"));
		Book book=bookDao.get(bookId);
		
		List<Category> listCategory=categoryDao.listAll();
		
		request.setAttribute("book", book);
		request.setAttribute("listCategory", listCategory);
		
		RequestDispatcher dis=request.getRequestDispatcher("create_book.jsp");
		dis.forward(request, response);
	}
	
	public void updateBook() throws ServletException, IOException{
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("++++++++++++++++++++++++++++updatebook");

		Integer bookId=Integer.parseInt(request.getParameter("bookId"));
		System.out.println("++++++++++++++++++++++++++++bookId"+bookId);
		Book bookById=bookDao.get(bookId);
		
		
		
		String title=request.getParameter("title");
		
		Book bookByTitle=bookDao.findByTitle(title);
		
		if(bookByTitle!=null && !bookById.equals(bookByTitle)){
			String message="Could not update a book with title "+title+" is already exist.";
			request.setAttribute("message", message);
			listBook();
			return;
		}
		String author=request.getParameter("author");
		String description=request.getParameter("description");
		String isbn=request.getParameter("isbn");
		float price=Float.parseFloat(request.getParameter("price"));
		Integer categoryId=Integer.parseInt(request.getParameter("category"));

		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=null;
		
		try{
			publishDate=dateFormat.parse(request.getParameter("publishDate"));
		}catch(ParseException ex){
			ex.printStackTrace();
			throw new ServletException("error parsing publish date (format is MM/dd/yyyy)");
		}
		Part part=request.getPart("bookImage");
		
		Book newbook=new Book();
		newbook.setTitle(title);
		newbook.setAuthor(author);
		newbook.setDescription(description);
		newbook.setIsbn(isbn);
		newbook.setPrice(price);
		newbook.setPublishDate(publishDate);
		
		
		
		if(part!=null && part.getSize()>0){
			long size=part.getSize();
			byte[] imageBytes= new byte[(int)size];
			
			InputStream inputStream=part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			newbook.setImage(imageBytes);
		}
		Category category=categoryDao.get(categoryId);
		
		newbook.setCategory(category);
		
		
		
		Book updatedBook=bookDao.update(newbook);
		if(updatedBook.getBookId()>0){
			String message="A book updated successfully.";
			request.setAttribute("message", message);
			listBook();
		}
		
	}

	public void deleteBook() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("id"));
		bookDao.delete(bookId);
		String message="A book has been deleted successfully.";
		request.setAttribute("message", message);
		listBook();
	}

	public void listBookByCategory() throws ServletException, IOException {
		Integer catId=Integer.parseInt(request.getParameter("id"));
		List<Book> booklist=bookDao.listByCategory(catId);
		request.setAttribute("booklist", booklist);
		
		Category category=categoryDao.get(catId);
		request.setAttribute("category", category);
	
		
		RequestDispatcher dis=request.getRequestDispatcher("frontend/view_book_by_category.jsp");
		dis.forward(request, response);
	}

	public void viewBookDetails() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("id"));
		Book book=bookDao.get(bookId);
	
		request.setAttribute("book",book);
		

		RequestDispatcher dis=request.getRequestDispatcher("frontend/book_detail.jsp");
		dis.forward(request, response);
	}

	public void searchKeyword() throws ServletException, IOException {
		String keyword=request.getParameter("keyword");
		List<Book> result=null;
		if(keyword.equals("")){
			result=bookDao.listAll();
		}else{
			result=bookDao.search(keyword);
		}
		System.out.println("********************inside");
		if(result!=null){
			for(Book b:result){
				System.out.println(b.getTitle());
			}
		}
		request.setAttribute("result",result);
		request.setAttribute("keyword",keyword);


		RequestDispatcher dis=request.getRequestDispatcher("frontend/keyword_result.jsp");
		dis.forward(request, response);
	}
	
}
