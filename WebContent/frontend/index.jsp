<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Bookstore Website</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	
	<jsp:directive.include file="header.jsp"/>
	
	<div class="center">
		<div>
			<h2>New books</h2>
				<div  style="width:80%; margin-left:80px">
			<c:forEach items="${newBooks}" var="book">
			<div style="float:left; display:inline-block; height:250px; margin-left:20px;  ">
			<div >
				<a href="view_book?id=${book.bookId}">
				<img src="data:image/jpg;base64,${book.base64Image}" width="80" height="110" />
				</a>
			</div>
			<div  style="width:140px;">
				<a href="view_book?id=${book.bookId}">
				<b>${book.title}</b>
				</a>	
			</div>
			
			<div>
				<jsp:directive.include file="book_rating.jsp"/>
			</div>
			
			<div style="width:140px;">${book.author}</div>
			<div><b>$${book.price}</b></div>
			</div>
			</c:forEach>
		</div>
	</div>
	<div align="center" style="clear:both">
		<h2>Best selling Books</h2>
	</div>
	<div div align="center" style="clear:both">
		<h2>Most favored Books</h2>
	</div>
	
	<jsp:directive.include file="footer.jsp"/>
	
	
</body>
</html>