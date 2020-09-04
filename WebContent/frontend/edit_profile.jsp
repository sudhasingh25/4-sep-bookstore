<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Customer Profile</title>
<link rel="stylesheet" href="css/style.css">




<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<script type="text/javascript" src="js/jquery-ui.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		
		<h2 class="pageheading">Edit Customer</h2>
		
	</div>
	
	
	<div align="center">
		<form action="update_profile" method="post" id="customerForm" >
		
		<table class="form">
			
			<tr>
				<td align="right">E-mail:</td>
				<td align="left"><b>${loggedCustomer.email}</b>(Can not be changed)</td>
			</tr>
			
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" size="40" id="fullname" name="fullname" value="${loggedCustomer.fullname}" ></td>
			</tr>
			
			
			<tr>
				<td align="right">Phone Number:</td>
				<td align="left"><input type="text" size="40" id="phone" name="phone" value="${loggedCustomer.phoneNumber}" ></td>
			</tr>
			
			<tr>
				<td align="right">Address:</td>
				<td align="left"><input type="text" size="40" id="address" name="address" value="${loggedCustomer.address}"></td>
			</tr>
			
			<tr>
				<td align="right">City:</td>
				<td align="left"><input type="text" size="40" id="city" name="city" value="${loggedCustomer.city}" ></td>
			</tr>
			
			<tr>
				<td align="right">Zip Code:</td>
				<td align="left"><input type="text" size="40" id="zipcode" name="zipcode" value="${loggedCustomer.zipCode}"></td>
			</tr>
			
			<tr>
				<td align="right">Country:</td>
				<td align="left"><input type="text" size="40" id="country" name="country" value="${loggedCustomer.country}"></td>
			</tr>
			
			<tr><td colspan="2" align="center">(Leave password field blank if you dont want to change.)</td></tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="text" size="40" id="password" name="password" ></td>
			</tr>
			
			<tr>
				<td align="right">Confirm-Password:</td>
				<td align="left"><input type="text" size="40" id="cnfpassword" name="cnfpassword" ></td>
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
			$("#customerForm").validate({
				rules:{
					email:{
						email:true,
						required:true
					},	
				
			fullname:"required",
			
			cnfpassword:{
				
				equalTo:"#password"
			},
			phone:"required",
			address:"required",
			city:"required",
			zipcode:"required",
			country:"required"
				},
				messages:{
					email:{
						required:"email is required",
						email:"Please enter valid email"
					},
				
					fullname:"fullname is required",
				
					cnfpassword:{
						
						equalTo:"Confirm password does not match Password"
					},
					phone:"phone number is required",
					address:"author name is required",
					city:" city name required",
					zipcode:"zipcode is required",
					country:"country is required"
				}
					
			});
			
			$("#buttonCancel").click(function(){
				history.go(-1);	
			});
			
		});
		
		
	</script>
</body>
</html>