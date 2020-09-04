<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Review loaded</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	
	
	<jsp:directive.include file="header.jsp"/>
	
	
	<div align="center">
	
	<form >	
		
		<table  style="width:60% ;border:0px">
			<tr>
				<td align="left"><h2>Your Reviews</h2></td>
				<td align="right"><h2>${loggedCustomer.fullname}</h2></td>
			</tr>
			
			<tr>
				<td colspan="3"><b><hr/></b></td>
			</tr>
			<tr>
				<td >
					<b>${book.title }</b><br/>
					<img src="data:image/jpg;base64,${book.base64Image}" width="150" height="180" />
				</td>
				<td>Your Review has been loaded successfully.Thank you!</td>
			</tr>
		
			
		</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp"/>
	
	
	
</body>
</html>