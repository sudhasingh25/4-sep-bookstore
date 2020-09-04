<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>create category</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<c:if test="${category==null}">
			<h2 class="pageheading">Create New Category</h2>
		</c:if>
		
		<c:if test="${category!=null}">
			<h2 class="pageheading">Edit Category</h2>
		</c:if>
	</div>
	
	
	<div align="center">
		<c:if test="${category==null}">
		<form action="create_category" method="post" id="categoryForm">
		</c:if>
		
		<c:if test="${category!=null}">
		<form action="update_category" method="post" id="categoryForm">
		<input type="hidden" name="categoryId" value="${category.categoryId}">
		</c:if>
		
		<table class="form">
			
			<tr>
				<td align="right">Name:</td>
				<td align="left"><input type="text" size="20" id="name" name="name" value="${category.name}"></td>
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
			$("#categoryForm").validate({
				rules:{
					name:"required",
				},
					
				messages:{
				
					name:"Please enter category name",
					
				}
					
			});
			
			$("#buttonCancel").click(function(){
				history.go(-1);	
			});
		});
	</script>
	
</body>
</html>