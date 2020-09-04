<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div class="center">
		<c:if test="${fn:length(result)==0}">
			<h2>No Results for "${keyword}"</h2>
		</c:if>

		<c:if test="${fn:length(result)>0}">
			<h2 align="center">Results for "${keyword}"</h2>
			<div align="left" style="width: 80%; margin: 0 auto;">

				<c:forEach items="${result}" var="book">
					<div
						style="float: left; display: inline-block; height: 250px; margin-left: 30px;  height:300px;">
						<div>
							<a href="view_book?id=${book.bookId}"> <img
								src="data:image/jpg;base64,${book.base64Image}" width="80"
								height="110" />
							</a>
						</div>
						<div style="width: 170px;">
							<a href="view_book?id=${book.bookId}"> <b>${book.title}</b>
							</a>
						</div>
						<div>Rating*****</div>
						<div style="width: 140px;">${book.author}</div>
						<div>
							<b>$${book.price}</b><br/>
							<a href="">Add To Cart</a>
							
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>