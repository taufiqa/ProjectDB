<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>
</head>
<body>	
<h1>LOGIN</h1>
        <form action="login" method="GET">
        	Email: <input type = "text" name = "email"> <br>
        	Password: <input type = "password" name = "password" /> <input type = "submit" value = "submit" />
        </form>
</body>
</html>