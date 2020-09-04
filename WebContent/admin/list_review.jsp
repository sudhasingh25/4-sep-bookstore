<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>review_list</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h2 class="pageheading">Review Management</h2>
	</div>
	
	<div align="center">
		<a href="create_review.jsp">Create New Review</a>
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
				<th>Book</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Review On</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="review" items="${listreview}" varStatus="status">
				<tr><td>${status.index+1}</td>
				<td>${review.reviewId}</td>
				<td>${review.book.title}</td>
				<td>${review.rating}</td>
				<td>${review.headline}</td>
				<td>${review.customer.fullname}</td>
				<td>${review.reviewTime}</td>
				
				
				<td>
					<a href="edit_review?id=${review.reviewId}">Edit</a>
					<a href="javascript:void(0); "  class="deleteLink" id="${review.reviewId}">Delete</a>
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
					reviewId=$(this).attr("id");
					if(confirm('Are you sure you want to delete review with Id '+reviewId)){
						window.location='delete_review?id='+reviewId;
					}
				});
			});
		});
	
	</script>
	
</body>
</html>