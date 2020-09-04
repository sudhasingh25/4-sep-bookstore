<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<script type="text/javascript" src="js/jquery-ui.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<h1 align="center">Your Shopping Cart Details.</h1>
	
	
	<div align="center">
	
		<div align="center">
			<c:if test="${message!=null }">
				<h4>${message}</h4>
			</c:if>
		</div>
		
		<c:set var="cart" value="${sessionScope['cart']}"/>
		
		<c:if test="${cart.totalItems == 0}">
			<h2>There is no item in the cart.</h2>
		</c:if>
		
		<c:if test="${cart.totalItems > 0}">
			
				<form action="update_cart" method="post" id="cartForm">
				<div>
					<table border="1">
						<tr>
							<th>No</th>
							<th colspan="2">Book</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
							<th>
								
							</th>
						</tr>
						
						<c:forEach var="item" items="${cart.items}" varStatus="status">
							<tr>
								<td>${status.index +1 }</td>
								<td>
									<img src="data:image/jpg;base64,${item.key.base64Image}" width="80" height="110" />
								</td>
								<td>
									<b>${item.key.title}</b>
								</td>
								<td>
								<input type="hidden" name="book_id" value="${item.key.bookId}"/>
								<input type="text" size="5"name="quantity${status.index +1 }" value="${item.value}"/></td>
								<td><fmt:formatNumber value="${item.key.price}" type="currency"/></td>
								<td><fmt:formatNumber value="${item.value*item.key.price}" type="currency"/></td>
								<td><a href="remove_from_cart?book_id=${item.key.bookId}">Remove</a></td>
							</tr>	
						</c:forEach>
						
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><b>${cart.totalQuantity} book(s)</b></td>
							<td>Total:</td>
							<td span="2"><fmt:formatNumber value="${cart.totalAmount}"/></td>
							<td></td>
						</tr>
					</table>
				</div>
				
				<div>
					<table style="border:0px">
						<tr>
							<td></td>
							<td><button type="submit">Update</button></td>
							<td><button id="clearCart">Clear Cart</button></td>
							
							<td><a href="${pageContext.request.contextPath}/">Continue Shopping</a></td>
							<td><a href="">Ckeckout</a></td>
							
						</tr>
					</table>
				</div>
				</form>
			
		</c:if>
	</div>
	<jsp:directive.include file="footer.jsp"/>
	
	<script type="text/javascript">
	$(document).ready(function(){
		
		
			
	
		$("#cartForm").validate({
			rules:{
				<c:forEach var="item" items="${cart.items}" varStatus="status">
					quantity${status.index +1 }:{required:true,number:true,min:1},
				</c:forEach>
			},
		
			messages:{
				<c:forEach var="item" items="${cart.items}" varStatus="status">
					quantity${status.index +1 }:{required:"Please enter quantity",number:"Quantity must be a number"
						min:"Quantity must be greater than 0"},
					}
				</c:forEach>
			}
		});
		
		$("#clearCart").click(function(){
			window.location='clear_cart';
		});
	});
	</script>
</body>
</html>