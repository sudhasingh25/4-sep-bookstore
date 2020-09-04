package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] arrayBookId=request.getParameterValues("book_id");
		String[] aquantity=new String[arrayBookId.length];
		
		for(int i=1;i<=aquantity.length;i++){
			aquantity[i-1]=request.getParameter("quantity"+i);
		}
		int[] bookId=Arrays.stream(arrayBookId).mapToInt(Integer::parseInt).toArray();
		int[] quantity=Arrays.stream(aquantity).mapToInt(Integer::parseInt).toArray();
       
		ShoppingCart cart=(ShoppingCart)request.getSession().getAttribute("cart");
		cart.updateCart(bookId, quantity);
		
		String cartPage=request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
