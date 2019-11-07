<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.sql.*,java.util.*"%>
<html>
<head>
	<title>Registration</title>
</head>
<body>

<h1>REGISTER</h1>
<form action="register" method="GET">
			First Name: <input type = "text" name = "firstName"> <br>
			Last Name: <input type = "text" name = "lastName"> <br>
        	Email: <input type = "text" name = "email"> <br>
        	Password: <input type = "password" name = "password" > <br>
        	Age: <input type = "text" name = "age"> <br>
        	Gender (M/F): <input type = "text" name = "gender" /> <input type = "submit" value = "submit" />
        </form>
	
</body>
</html>