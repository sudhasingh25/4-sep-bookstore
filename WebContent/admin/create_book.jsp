<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>create_book</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="..//css/richtext.min.css">

<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<c:if test="${book==null}">
			<h2 class="pageheading">Create New Book</h2>
		</c:if>
		
		<c:if test="${book!=null}">
			<h2 class="pageheading">Edit Book</h2>
		</c:if>
	</div>
	
	
	<div align="center">
		<c:if test="${book==null}">
		<form action="create_book" method="post" id="bookForm" enctype="multipart/form-data">
		</c:if>
		
		<c:if test="${book!=null}">
		<form action="update_book" method="post" id="bookForm" enctype="multipart/form-data">
		<input type="hidden" name="bookId" value="${book.bookId}">
		</c:if>
		
		<table class="form">
			
			<tr>
				<td>Category:</td>
				<td>
					<select name="category">
						<c:forEach items="${listCategory}" var="category">
							<c:if test="${category.categoryId eq book.category.categoryId}">
								<option value="${category.categoryId}" selected>
							</c:if>
							
							<c:if test="${category.categoryId ne book.category.categoryId}">
								<option value="${category.categoryId}">
							</c:if>
								${category.name}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">Title:</td>
				<td align="left"><input type="text" size="20" id="title" name="title" value="${book.title}"></td>
			</tr>
			
			<tr>
				<td align="right">Author:</td>
				<td align="left"><input type="text" size="20" id="author" name="author" value="${book.author}"></td>
			</tr>
			
			<tr>
				<td align="right">Isbn:</td>
				<td align="left"><input type="text" size="20" id="isbn" name="isbn" value="${book.isbn}"></td>
			</tr>
			
			<tr>
				<td align="right">Publish Date:</td>
				<td align="left"><input type="text" size="20" id="publishDate" name="publishDate" 
				value="<fmt:formatDate pattern='MM/dd/yyyy' value='${book.publishDate}'/>" /></td>
				
			</tr>
			
			<tr>
				<td align="right">Book Image:</td>
				<td align="left"><input type="file" size="20" id="bookImage" name="bookImage" /></br>
					<img id="thumbnail" alt="Image Preview" style="width:30%; margin-top:8px "
						src="data:image/jpg;base64,${book.base64Image}"/>
				</td>
			
			</tr>
			
			<tr>
				<td align="right">Price:</td>
				<td align="left"><input type="text" size="20" id="price" name="price" value="${book.isbn}"></td>
			</tr>
			
			<tr>
				<td align="right">Description:</td>
				<td align="left"><textarea  rows="4" cols="30" id="description" name="description">${book.description}</textarea></td>
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
			$("#publishDate").datepicker();
			
			$("#description").richText();

			$("#bookImage").change(function(){
				showImageThumbnail(this);
			});
			
			$("#bookForm").validate({
				rules:{
					category:"required",
					title:"required",
					author:"required",
					isbn:"required",
					publishDate:"required",
					
					<c:if test="${book==null}">
						bookImage:"required",
					</c:if>
					
					price:"required",
					description:"required"
				},
				messages:{
					category:"Please select book category",
					title:"Please enter title for book",
					author:"Please enter author name",
					isbn:"Please enter isbn",
					publishDate:"Please enter publish Date",
					bookImage:"Please select book image",
					price:"Please enter price",
					description:"Please enter description"
				}
					
			});
			
			$("#buttonCancel").click(function(){
				history.go(-1);	
			});
			
		});
		
		function showImageThumbnail(fileInput){
			var file=fileInput.files[0];
			var reader=new FileReader();
			
			reader.onload=function(e){
				$("#thumbnail").attr('src',e.target.result);
			};
			reader.readAsDataURL(file);
		}
	</script>
</body>
</html>