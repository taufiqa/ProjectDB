<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<!-- icon button -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<title>Favorite Sellers</title>
	<style>
	
a {
  text-decoration: none;
}

ul {
  background: #335B78;
    list-style-type: none;
    margin: 0;
    padding: 0;
    text-align:center;
}

li {
display:inline-block;
    color: #fff;
  background: #335B78;
    display: inline-block;
    padding: 1rem;
    position: relative;
    text-decoration: none;
  transition-duration: 0.5s;
}
  
li a {
  color: #fff;
}

li:hover {
    background: #C4D8E6;
    cursor: pointer;
}

ul li ul {
    visibility: hidden;
  opacity: 0;
  min-width: 5rem;
    position: absolute;
  transition: all 0.5s ease;
  margin-top: 1rem;
    left: 0;
  display: none;
}

ul li:hover > ul,
ul li:focus-within > ul,
ul li ul:hover {
  visibility: visible;
  opacity: 1;
  display: block;
}

ul li ul li {
    clear: both;
  width: 100%;
}

.btn {
  background-color: #C4D8E6;
  border: none;
  color: white;
  padding: 12px 16px;
  font-size: 16px;
  cursor: pointer;
}
	
.btn:hover {
  background-color: #335B78;
}

.dropbtn {
  background-color: #335B78;
  color: white;
  padding: 8px;
  font-size: 16px;
  border: none;
  cursor: pointer;
  border-radius: 4px;
  font-family:"Times New Roman", Times, serif;
}
.dropbtn:hover, .dropbtn:focus {
  background-color: #C4D8E6;
    transition: all 0.5s ease;
  
}
.dropdown {
  position: relative;
  display: inline-block;
}
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}
.dropdown a:hover {background-color: #ddd;}
.show {display:block;}
</style>

</head>
<body>


<h1 style="color:#335B78;text-align:center;">FAVORITE SELLERS</h1> 

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
		
		<br/>
		
	<%@page import="java.sql.DriverManager"%>
	<%@page import="java.sql.ResultSet"%>
	<%@page import="java.sql.Statement"%>
	<%@page import="java.sql.Connection"%>

	<%
	HttpSession sess = request.getSession(false);
	String userEmail = sess.getAttribute("currentUser").toString();
	System.out.println("Current user:"+userEmail);
	String favUserEmail = request.getParameter("userEmail");

	String driverName = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost:3306/";
	String dbName = "projectdb";
	String userId = "john";
	String password = "pass1234";

	try {
	Class.forName(driverName);
	} catch (ClassNotFoundException e) {
	e.printStackTrace();
	}

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	%>
		<table align = "center" style = "border :1px solid black; border-collapse : collapse;">	

	<tr>

	</tr>
	<tr>
	<th style = "border :1px solid black; padding: 15px;">User Email</th>
	<th style = "border :1px solid black; padding: 15px;">Fav User Email</th>
	</tr>
	<%
	try{ 
	connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
	statement=connection.createStatement();
	String sql ="SELECT * FROM favUsers WHERE userEmail='" + userEmail + "'";

	resultSet = statement.executeQuery(sql);
	while(resultSet.next()){
	%>
	<tr>

	<td style = "border :1px solid black; padding: 15px;"><%=resultSet.getString("userEmail") %></td>
	<td style = "border :1px solid black; padding: 15px;"><%=resultSet.getString("favUserEmail") %></td>
	<td style = "border :1px solid black; padding: 15px;">
		<button class="btn" title= "Delete" onclick="window.location.href='ControlServlet?delFavSelleraction=deleteFavoriteSeller&favUserEmail=${userEmail}'">
			<i class="fa fa-trash"></i>
		</button>
	</td>
	<% 
	}

	} catch (Exception e) {
	e.printStackTrace();
	}
	%>

	</tr>

	</table>
	
	
	
</body>
</html>