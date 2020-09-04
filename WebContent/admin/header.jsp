<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<div align="center">
	<div>
		<a href="${pageContext.request.contextPath}/admin/">
		<img  src="../images/BookstoreAdminLogo.png"/>
		</a>
	</div>
	
	<div>
		Welcome,<c:out value="${sessionScope.loggedUser}"/> | <a href="logout">Logout</a>
	</div>
	<div>&nbsp;</div>
	<div id="headeritems">
		<b>
		<div><a href="list_user"><img src="../images/users.png"/><br/>Users</a> </div>
		<div><a href="list_category"><img src="../images/category.png"/><br/>Categories</a></div> 
		<div><a href="list_book"><img src="../images/bookstack.png"/><br/>Books</a></div> 
		<div><a href="list_customer"><img src="../images/customer.png"/><br/>Customers</a></div> 
		<div><a href="list_review"><img src="../images/review.png"/><br/>Reviews</a></div> 
		<div><a href="order"><img src="../images/order.png"/><br/>Orders</a></div>
		</b>
	</div>
</div>