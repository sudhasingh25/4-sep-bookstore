<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Bookstore Website</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<div align="center">
		<jsp:directive.include file="header.jsp"/>
		
		<div>
			<h3>Administrative Dashboard</h3>
		</div>
		
		<div>
			<h2>Quick Actions:</h2>
			<b>
			<a href="create_book">New Book</a>&nbsp;
			<a href="create_customer">New Customer</a>&nbsp;
			<a href="create_category">New Category</a>&nbsp;
			<a href="create_user">New User</a>&nbsp;
			</b>
		</div>
		
		<div>
			<h2>Recent Sales:</h2>
		</div>
		
		<div>
			<h2>Recent Reviews:</h2>
		</div>
		
		<div>
			<h2>Statistics:</h2>
		</div>
		<jsp:directive.include file="footer.jsp"/>
	</div>
</body>
</html>