<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>New Item</title>
</head>
<body >	
<h1 style= "background-color:#335B78; color:#C4D8E6; text-align: center">Add New Item</h1> 
	<form name="newItem" class="form-controls" method="POST" action="ControlServlet?action=newItem&userID=${userID}&userName=${userName}">
    <table style = "color:#335B78; margin-left:auto; margin-right:auto;" width = "50%" >
    	<tr>
        	<th width = "50%"> Title: </th>
        	<td width = "100%"><input type = "text" name = "title"> </td>
		</tr>
      <tr>
	     <th width = "50%">Description:</th>
		 <td width = "100%"><input type = "text" name = "itemDescription"></td>
	  </tr>
	  <tr>
	     <th width = "50%">Category:</th>
		 <td width = "100%"><input type = "text" name = "categoryName"></td>
	  </tr>
	  <tr>
	     <th width = "50%">Price:</th>
		 <td width = "100%"><input type = "text" name = "price"></td>
	  </tr>
	  <tr>
			<th> </th>
		    <td width = "100%"><input type = "submit" value = "submit" /> </td>
	  </tr>	
	 </table>
	
   </form>
<!-- <h1>LOGIN</h1>
        <form action="login" method="POST">
        	Email: <input type = "text" name = "email"> <br>
        	Password: <input type = "password" name = "password" /> <input type = "submit" value = "submit" />
        </form> -->
</body>
</html>