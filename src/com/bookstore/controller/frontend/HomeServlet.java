package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;

import com.bookstore.entity.Book;



@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO bookDAO=new BookDAO();
		List<Book> newBooks=bookDAO.listNewBook();
		request.setAttribute("newBooks", newBooks);
		
		RequestDispatcher dis=request.getRequestDispatcher("frontend/index.jsp");
		dis.forward(request, response);
	}

}
