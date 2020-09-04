<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp"/>
		
		<div class="center">
			<table style="border:0px">
				<tr>
					<td colspan="3" valign="top" align="left">
						<h4>${book.title }</h4> by ${book.author}
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						<img src="data:image/jpg;base64,${book.base64Image}" width="120" height="150" />
					</td>
					<td valign="top" align="left">
						<jsp:directive.include file="book_rating.jsp"/>
						<a href="#reviews">${fn:length(book.reviews) }  Reviews</a>
					</td>
					<td rowspan="2" valign="top" width="15%" >
						<b>$${book.price}</b></br>						<br/>
						<button id="buttonAddToCart"><b>Add to Cart</b></button>
					</td>
				</tr>
				<tr>
					<td valign="top" style="text-align:justify;">
						${book.description}
					</td>
				</tr>
				
				<tr colspan="2">
					<td style="width:130px;"><h4 ><a id="reviews">Customer Reviews</a></h4></td>
					
					<td>
						<button id="buttonWriteReview">Write a Customer Review</button>
					</td>
				</tr>
				
				<tr>
					<td colspan="3">
						<table style="border:0px">
							<c:forEach items="${book.reviews}" var="review">
								<tr>
									<td>
										<c:forTokens items="${review.stars}" delims="," var="star">
											<c:if test="${star eq 'on' }">
												<img src="images/rating_on.png"/>
											</c:if>
					
											<c:if test="${star eq 'off' }">
												<img src="images/rating_off.png"/>
											</c:if>
										</c:forTokens>
										- <b>${review.headline}</b>
									</td>
								</tr>
								
								<tr>
									<td>
										by ${review.customer.fullname} on ${review.reviewTime}
									</td>
								</tr>
								<tr><td><i>${review.comment}</i></td></tr>
								<tr><td></td></tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
		</div>
			
		<jsp:directive.include file="footer.jsp"/>
		
			
	<script type="text/javascript">
		$(document).ready(function(){
			$("#buttonWriteReview").click(function(){
				window.location='write_review?book_id='+ ${book.bookId};
			});
			
			$("#buttonAddToCart").click(function(){
				window.location='add_to_cart?book_id='+ ${book.bookId};
			});
		});
		
		
	</script>
</body>
</html>