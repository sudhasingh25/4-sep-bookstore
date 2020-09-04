package com.bookstore.controller.frontend;

import com.bookstore.service.BookService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class KeywordSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public KeywordSearchServlet() {}
        // TODO Auto-generated constructor stub
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookService=new BookService(request, response);
		bookService.searchKeyword();
	}

}
