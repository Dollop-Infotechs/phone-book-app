<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
	<p>${NOTIFICATION}</p>
</div>
	<form action="./ContactController" method="post">
	<div class = "form-group">
						<input type = "text" class = "form-control" name = "name" placeholder = "Enter Name" value = "${contact.name}"/>
					</div>
				
					<div class = "form-group">
						<input type = "email" class = "form-control" name = "email" placeholder = "Enter Email" value = "${contact.email}"/>
					</div>
				
					<div class="form-group">
					  	<input type = "text" class = "form-control" name = "phone" placeholder = "Enter Mobile Number" value = "${contact.phone}"/>
					</div>
				
					<input type = "hidden" name = "id" value = "${contact.id}"/>
					<input type = "hidden" name = "action" value = "ADD"/>
				
					<button type = "submit" class = "btn btn-primary">Save</button>
	</form>
	<div>
	<p>
	<a href="${pageContext.request.contextPath}/ContactController">Contact List</a>
	</p></div>
</body>
</html>