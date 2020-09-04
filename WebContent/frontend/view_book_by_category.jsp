<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Books in ${category.name }</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	
	<jsp:directive.include file="header.jsp"/>
	
	<div class="center">
		<h3>${category.name}</h3>
	</div>
	
	<div  style="width:80%; margin-left:80px">
		<c:forEach items="${booklist}" var="book">
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
	
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>