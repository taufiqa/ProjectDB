<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.sql.*,java.util.*"%>
<html>
<head>
	<title>Registration</title>
</head>
<body>	
	<h1 style= "background-color:#335B78; color:#C4D8E6; text-align: center">Registration</h1> 
	
	<form action="register" method="POST">
    <table style = "color:#335B78; margin-left:auto; margin-right:auto;" width = "40%" >
	    	<tr>
	        	<th width="50%"> First Name: </th>
	        	<td width="100%"> <input type = "text" name = "firstName"> </td>
			</tr>
			<tr>
	        	<th width="50%"> Last Name: </th>
	        	<td width="100%"> <input type = "text" name = "lastName"> </td>
			</tr>
			<tr>
	        	<th width = "50%"> Email: </th>
	        	<td width = "100%"><input type = "email" name = "email"> </td>
			</tr>
	        <tr>
			    <th width="50%"> Password:</th>
				<td width="100%"><input type = "password" name = "password"></td>
		    </tr>
		    <tr>
	        	<th width = "50%"> Age: </th>
	        	<td width = "100%"><input type = "text" name = "age"> </td>
			</tr>
			<tr>
	        	<th width = "50%"> Gender (M/F): </th>
	        	<td width = "100%"><input type = "text" name = "gender"> </td>
			</tr>	
			<tr>
				<th> </th>
	        	<td width = "100%"><input type = "submit" value = "submit" /> </td>
			</tr>	
			
		 </table>		 
	</form>	
</body>
</html>