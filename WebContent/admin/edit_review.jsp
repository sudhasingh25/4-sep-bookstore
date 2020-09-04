<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edit_review</title>
<link rel="stylesheet" href="../css/style.css">




<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<script type="text/javascript" src="../js/jquery-ui.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		
		
		<c:if test="${review!=null}">
			<h2 class="pageheading">Edit review</h2>
		</c:if>
	</div>
	
	
	<div align="center">
		
		
		<c:if test="${review!=null}">
		<form action="update_review" method="post" id="reviewForm">
		<input type="hidden" name="reviewId" value="${review.reviewId}">
		</c:if>
		
		<table  class="form">
			
			
			<tr>
				<td align="right">Book:</td>
				<td align="left"><b>${review.book.title}</b></td>
			</tr>
			
			<tr>
				<td align="right">Rating:</td>
				<td align="left"><b>${review.rating}</b></td>
			</tr>
			
			<tr>
				<td align="right">Name:</td>
				<td align="left"><b>${review.customer.fullname}</b></td>
			</tr>
			
			<tr>
				<td align="right">Headline:</td>
				<td align="left"><input type="text" size="40" id="headline" name="headline" value="${review.headline}"></td>
			</tr>
			
			<tr>
				<td align="right">Comment:</td>
				<td align="left"><input type="text" size="70" id="comment" name="comment" value="${review.comment}"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;
					<button id="buttonCancel">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp"/>
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#reviewForm").validate({
				rules:{
					headline:"required",
					comment:"required"
				},
				messages:{
					headline:"Please enter headline",
					comment:"Please enter comment"
				}
			});
			
			$("#buttonCancel").click(function(){
				history.go(-1);	
			});
			
		});
		
		
	</script>
</body>
</html>