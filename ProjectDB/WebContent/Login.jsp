<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
</head>
<body >	
<h1 style= "background-color:#335B78; color:#C4D8E6; text-align: center">Login</h1> 
  <form action="login" method="POST">
    <table style = "color:#335B78; margin-left:auto; margin-right:auto;" width = "50%" >
    	<tr>
        	<th width = "50%"> Email: </th>
        	<td width = "100%"><input type = "email" name = "email"> </td>
		</tr>
      <tr>
	     <th width = "50%">Password:</th>
		 <td width = "100%"><input type = "password" name = "password"></td>
	  </tr>
	  <tr>
			<th> </th>
		    <td width = "100%"><input type = "submit" value = "submit" /> </td>
	  </tr>	
	 </table>
	 <br>
   </form>
<!-- <h1>LOGIN</h1>
        <form action="login" method="POST">
        	Email: <input type = "text" name = "email"> <br>
        	Password: <input type = "password" name = "password" /> <input type = "submit" value = "submit" />
        </form> -->
</body>
</html>