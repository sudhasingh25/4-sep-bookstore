<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Write Review form</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css"> 
</head>
<body>
	
	
	<jsp:directive.include file="header.jsp"/>
	
	
	<div align="center">
	
	<form id="reviewForm" action="submit_review" method="post">	
		
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
				<td align="left">
					<div style="width:10px;" id="rateYo"></div>
					<input type="hidden" id="rating" name="rating"/>
					<input type="hidden" name="bookId" value="${book.bookId }"/>
					<br/>
					<input type="text" name="headline" size="50" placeholder="Headline"/>
					<br/>
					<br/>
					<textarea name="comment" cols="50" rows="7" placeholder="Write your review details..."></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="3" align="center">
					<button type="submit">Submit</button>&nbsp;&nbsp;
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
					comment:"Please write comment"
				}
			});
			 $("#rateYo").rateYo({
				 starWidth: "40px",
				 fullStar:true,
				 onSet:function(rating,rateYoInstance){
					 $("#rating").val(rating);
				 }
			 });
			 
			 $("#buttonCancel").click(function(){
					history.go(-1);	
				});
		
		});
	</script>
</body>
</html>