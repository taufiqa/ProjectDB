<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Store Page</title>
	<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #335B78;
  text-align: center;
}

li {
display:inline-block;
        *display:inline; /*IE7*/
        *zoom:1; /*IE7*/
        color:white;
        margin-right:10px;}

li a {
  display: inline-block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover {
  background-color: #C4D8E6;
}

.dropbtn {
  background-color: #3498DB;
  color: white;
  padding: 8px;
  font-size: 16px;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}

.dropbtn:hover, .dropbtn:focus {
  background-color: #2980B9;
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

<h1 style="color:#335B78;text-align:center;">WELCOME TO STORE</h3> 
	<table>
	<col width="600">
	<col width="600">
	<col width="600">
	<col width="600">
	<col width="600">
		<ul>
			<li><a href="StorePage.jsp">HOME</a></li>
			<li><a href="listFriends">ITEMS</a></li>
			<li><a href="listFavoriteSellers">FAVORITE SELLERS</a></li>
			<li><a href="newItem.jsp">NEW ITEM</a></li>
			<li><a href="logoutUser">LOGOUT</a></li>
		</ul>
		<tr>
			<td/> <!--action="search"--> 
			
			<td align="left">
				<form action="search" method="post">
					<table>
					    <tr>
					    	<td><input type="search" placeholder="find an item by category ..." title="enter a category" name="searchCategory" size="50" value="<c:out value='${searchCategory}'/>"/></td>
							<td><input type="submit" title="search" value="search" /></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
	<!-- <label id="name1label"> </label>-->
	
	<table>	
        <th> 
            <td>Title</td>
            <td>User Email</td>
            <td>Item Description</td>
            <td>Post Date</td>
            <td>Price ($)</td>
            <td>Categories</td>
        </th>
        
    <c:forEach items="${rows}" var="row">
        <tr>     
        <td><div class="dropdown">
           <button onclick="myItemFunction()" class="dropbtn"> ${row.title}</button>
  				<div id="myDropdown" class="dropdown-content">
    				 <a href="postReview.jsp">Post Review of Item</a>
    				 <a href="favoriteItems.jsp">Add to Favorite Items List</a> 				 
  				</div>
			</div>
			</td>
			
			<td><div class="dropdown">
           <button onclick="myUserFunction()" class="dropbtn">${row.userEmail}</button>
  				<div id="myDropdown1" class="dropdown-content">
    				 <a href="favoriteSellers.jsp">Add to Favorite Sellers List</a>
  				</div>
			</div>   
			</td> 
            <td>${row.itemDescription}</td>
            <td>${row.postDate}</td>
            <td>${row.price}</td>
            <td>${row.categoryName}</td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>