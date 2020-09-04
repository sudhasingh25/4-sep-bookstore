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
		<c:if test="${user==null}">
			<h2 class="pageheading">Create New User</h2>
		</c:if>
		
		<c:if test="${user!=null}">
			<h2 class="pageheading">Edit User</h2>
		</c:if>
	</div>
	
	
	<div align="center">
		<c:if test="${user==null}">
		<form action="create_user" method="post" id="userForm">
		</c:if>
		
		<c:if test="${user!=null}">
		<form action="update_user" method="post" id="userForm">
		<input type="hidden" name="userId" value="${user.userId}">
		</c:if>
		
		<table class="form">
			<tr>
				<td align="right">Email:</td>
				<td align="left"><input type="text" size="40" id="email" name="email" value="${user.email}"></td>
			</tr>
			<tr>
				<td align="right">Fullname:</td>
				<td align="left"><input type="text" size="40" id="fullname" name="fullname" value="${user.fullName }"></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" size="40" id="password" name="password" value="${user.password }"></td>
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
			$("#userForm").validate({
				rules:{
					email:{
						required:true,
						email:true
					},
					fullname:"required",
					password:"required"
				},
				messages:{
					email:{
						required:"Please enter email",
						email:"Please enter a valid email"
					},
					fullname:"Please enter fullname",
					password:"Please enter password"
				}
					
			});
			
			$("#buttonCancel").click(function(){
				history.go(-1);	
			});
			
		});
	</script>
</body>
</html>