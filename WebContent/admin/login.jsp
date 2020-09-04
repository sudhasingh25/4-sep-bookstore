<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login form</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	
	
	<h1 align="center">Book Store Administration</h1>
	
	
	<div align="center">
	
		<h2 class="pageheading">Admin Login</h2>
		
		<div align="center">
		<c:if test="${message!=null }">
			<h4>${message}</h4>
		</c:if>
		</div>
		
		<form action="login" method="post" id="loginForm">
		
		<table class="form">
			<tr>
				<td align="right">Email:</td>
				<td align="left"><input type="text" size="20" id="email" name="email" ></td>
			</tr>
			
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" size="20" id="password" name="password" ></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Login</button>&nbsp;
				</td>
			</tr>
		</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp"/>
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#loginForm").validate({
				rules:{
					email:{
						required:true,
						email:true
					},
					password:"required"
				},
				messages:{
					email:{
						required:"Please enter email",
						email:"Please enter a valid email"
					},
					password:"Please enter password"
				}
			});
			
		
		});
	</script>
</body>
</html>