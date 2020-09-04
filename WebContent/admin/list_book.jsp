<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>book_list</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>


</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h2 class="pageheading">Book Management</h2>
	</div>
	
	<div align="center">
		<a href="new_book">Create New Book</a>
	</div>
	
	<div>&nbsp;&nbsp;</div>
	
	<div align="center">
		<c:if test="${message!=null }">
			<h4>${message}</h4>
		</c:if>
	</div>
	
	<div align="center">
		<table class="tabledesign" border="1">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="book" items="${listbook}" varStatus="status">
				<tr><td>${status.index+1}</td>
				<td>${book.bookId}</td>
				
				<td>
					<img src="data:image/jpg;base64,${book.base64Image}" width="84" height="110" />
				</td>
				
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.category.name}</td>
				<td>${book.price}</td>
				<td>${book.lastUpdateTime}</td>
				<td>
					<a href="edit_book?id=${book.bookId}">Edit</a>
					<a href="javascript:void(0);" class="deleteLink" id="${book.bookId}">Delete</a>
				</td>
				</tr>
			</c:forEach>
			
		</table>
	</div>
	<jsp:directive.include file="footer.jsp"/>
	
	<script>
		$(document).ready(function(){
			$(".deleteLink").each(function(){
				$(this).on("click",function(){
					bookId=$(this).attr("id");
					if(confirm('Are you sure you want to delete book with Id '+bookId)){
						window.location='delete_book?id='+bookId;
					}
				});
			});
		});
	
	</script>
	
</body>
</html>