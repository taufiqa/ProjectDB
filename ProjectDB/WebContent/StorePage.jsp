<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<!-- icon button -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<title>Store Page</title>
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

button {
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

<script>
function myItemFunction() {
  document.getElementById("myDropdown").classList.toggle("show");}
function myUserFunction() {
	  document.getElementById("myDropdown1").classList.toggle("show");}
  
window.onclick = function(event) {
	  if (!event.target.matches('.dropbtn')) {
	    var dropdowns = document.getElementsByClassName("dropdown-content");
	    var i;
	    for (i = 0; i < dropdowns.length; i++) {
	      var openDropdown = dropdowns[i];
	      if (openDropdown.classList.contains('show')) {
	        openDropdown.classList.remove('show');
	      }
	    }
	  }
	}
</script>


<h1 style="color:#335B78;text-align:center;">WELCOME TO STORE</h1> 

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
	
	<!-- 
	<table>
	<col width="600">
	<col width="600">
	<col width="600">
	<col width="600">
	<col width="600">
		<ul>
			<li><a href="StorePage.jsp">HOME</a></li>
			<li><a href="listItems.jsp">ITEMS</a></li>
			<li>
				<ul class="dropdown">
			        <li><a href="favoriteSellers.jsp">FAVORITE SELLERS</a></li>
			        <li><a href="favoriteItems.jsp">FAVORITE ITEMS</a></li>
			      </ul>
			    </li>

			<li><a href="newItem.jsp">NEW ITEM</a></li>
			<li><a href="logoutUser">LOGOUT</a></li>
		</ul>
		</table>
		-->
<br/>
<br/>
<br/>
			<!-- Search Bar -->
			<td align="left">
				<form action="search" method="post">
					<table align = "center">
					    <tr>
					    	<td><input type="search" placeholder="find an item by category ..." title="enter a category" name="searchCategory" size="50" value="<c:out value='${searchCategory}'/>"/></td>
							<td><input type="submit" title="search" value="search" /></td>
						</tr>
					</table>
				</form>
			</td>
			<!--End Search and Category-->
			
			
			
			<!-- Item Table -->	
			
	<h2 style="color:#335B78;text-align:center;">Search Results</h2>
	<table align = "center" style = "border :1px solid black; border-collapse : collapse;">	
        <tr> 
            <th style = "border :1px solid black; padding: 15px;">Title</th>
            <th style = "border :1px solid black; padding: 15px;">User Email</th>
            <th style = "border :1px solid black; padding: 15px;">Item Description</th>
            <th style = "border :1px solid black; padding: 15px;">Post Date</th>
            <th style = "border :1px solid black; padding: 15px;">Price ($)</th>
            <th style = "border :1px solid black; padding: 15px;">Categories</th>
            <th style = "border :1px solid black; padding: 15px;">Add a Review</th>
        </tr>
        
        <c:forEach items="${rows}" var="row">
        <tr>     
        <td style = "border :1px solid black; padding: 15px;">
      <!--   
        <div class="dropdown">
           <button onclick="myItemFunction()" class="dropbtn"> ${row.title}</button>
  				<div id="myDropdown" class="dropdown-content">
    				 <a href="postReview.jsp">Post Review of Item</a>
    				 <a href="favoriteItems.jsp">Add to Favorite Items List</a> 				 
  				</div>
			</div>
			
			-->
			 <button title= "Add to favorite items" onclick="window.location.href='ControlServlet?favItemaction=addFavoriteItems&favItemUserEmail=${row.userEmail}&title=${row.title}'">${row.title}</button>
			
			
			</td>
			
			<td style = "border :1px solid black; padding: 15px;">
		<!-- 	<div class="dropdown">
           <button onclick="myUserFunction()" class="dropbtn">${row.userEmail}</button>
  				<div id="myDropdown1" class="dropdown-content">
    			<!-- <a href = "favoriteSellers.jsp" >Add to Favorite Sellers List</a> -->
    			<button title= "Add to favorite sellers" onclick="window.location.href= 'ControlServlet?favSelleraction=addFavoriteSellers&favUserEmail=${row.userEmail}'">${row.userEmail}</button>
    		<!-- 	</div>
			</div>   -->
			</td> 
            <td style = "border :1px solid black; padding: 15px;">${row.itemDescription}</td>
            <td style = "border :1px solid black; padding: 15px;">${row.postDate}</td>
            <td style = "border :1px solid black; padding: 15px;">${row.price}</td>
            <td style = "border :1px solid black; padding: 15px;">${row.categoryName}</td>
            <td style = "border :1px solid black; padding: 15px;">
	            <button title= "Add a review" onclick="window.location.href='postReview.jsp?itemID=${row.itemID}'">
	            	<i class="fa fa-edit"></i>
	            </button>
            </td>
        </tr>
    </c:forEach>
    </table>
	<!-- 	
		<tr>
			<td/>
			<td align="left">
				<form action="searchCategory" method="post">
					<table>
					    <tr>
					    	<td><input type="search" placeholder="input a category name..." title="enter a category" name="searchCategory" size="50" value="<c:out value='${searchCategory}'/>"/></td>
							<td><input type="submit" title="search" value="search" /></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
	
	
	-->
</body>
</html>