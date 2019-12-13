<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>POST A REVIEW</title>
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


<h1 style="color:#335B78;text-align:center;">POST A REVIEW</h1> 

		<div>

		  <ul>
		    <li><a href="StorePage.jsp">HOME</a></li>
		    <li><a href="#">FAVORITES</a>
		      <ul>
		        <li><a href="favoriteSellers.jsp">FAVORITE SELLERS</a></li>
		        <li><a href="favoriteItems.jsp">FAVORITE ITEMS</a></li>
		      </ul>
		    </li>
		    <li><a href="newItem.jsp">NEW ITEM</a></li>
		   	<li><a href="logoutUser.jsp">LOGOUT</a></li>
		  </ul>
		</div>



<form name="postReview" method="POST" action="ControlServlet?postReviewaction=postReview">
 <input type="hidden" name="itemID" value='<%= request.getParameter("itemID")%>'></input>
    <div >
		<label for="score">Score:</label>
		<select id="score" name="score">
			<option value = ""></option>
			<option value="Excellent">Excellent</option>
			<option value="Good">Good</option>
			<option value="Fair">Fair</option>
			<option value="Poor">Poor</option>
		</select>
	</div>
				
	<br/>
				
	<div>
		<label for="remark">Remark:</label>
		<textarea id="remark" name="remark"></textarea>
	</div>
	
	<br/>
	
	<div>
		<label for="submit"></label>
		<input type = "submit" name="submit" value="Submit"></input>
	</div>
   </form>
	
</body>
</html>