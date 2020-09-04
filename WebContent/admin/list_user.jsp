<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>user_list</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h2 class="pageheading">User Management</h2>
	</div>
	
	<div align="center">
		<a href="create_user.jsp">Create New User</a>
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
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${listuser}" varStatus="status">
				<tr><td>${status.index+1}</td>
				<td>${user.userId}</td>
				<td>${user.email}</td>
				<td>${user.fullName}</td>
				<td>
					<a href="edit_user?id=${user.userId}">Edit</a>
					<a href="javascript:void(0);" class="deleteLink" id="${user.userId}">Delete</a>
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
					userId=$(this).attr("id");
					if(confirm('Are you sure you want to delete user with Id '+userId)){
						window.location='delete_user?id='+userId;
					}
				});
			});
		});
	
	</script>
	
</body>
</html>