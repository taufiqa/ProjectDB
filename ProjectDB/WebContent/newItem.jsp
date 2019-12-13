<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>New Item</title>
</head>
<body >	
<h1 style="color:#335B78;text-align:center;">ADD A NEW ITEM</h1> 

		<div>

		  <ul>
		    <li><a href="StorePage.jsp">HOME</a></li>
		    <li><a href="#">FAVORITES</a>
		      <ul>
		        <li><a href="favoriteSellers.jsp">FAVORITE SELLERS</a></li>
		        <li><a href="favoriteItems.jsp">FAVORITE ITEMS</a></li>
		      </ul>
		    </li>
		    <li><a href="#">QUERIES</a>
		      <ul>
		        <li><a href="favoriteSellers.jsp">QUERY 1</a></li>
		        <li><a href="favoriteItems.jsp">QUERY 2</a></li>
		        <li><a href="favoriteSellers.jsp">QUERY 3</a></li>
		        <li><a href="favoriteItems.jsp">QUERY 4</a></li>
		        <li><a href="favoriteSellers.jsp">QUERY 5</a></li>
		        <li><a href="favoriteItems.jsp">QUERY 6</a></li>
		        <li><a href="favoriteSellers.jsp">QUERY 7</a></li>
		        <li><a href="favoriteItems.jsp">QUERY 8</a></li>
		        <li><a href="favoriteSellers.jsp">QUERY 9</a></li>
		      </ul>
		    </li>
		    <li><a href="newItem.jsp">NEW ITEM</a></li>
		   	<li><a href="logout">LOGOUT</a></li>
		  </ul>
		</div>
		
  <form action="newItem" method="POST">
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