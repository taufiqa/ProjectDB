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

</style>
</head>
<body>
<h1 style="color:#335B78;text-align:center;">WELCOME TO STORE</h1> 
	<table>
	<col width="600">
	<col width="600">
	<col width="600">
	<col width="600">
	<col width="600">
		<ul>
			<li><a href="StorePage.jsp">HOME</a></li>
			<li><a href="listItems.jsp">ITEMS</a></li>
			<li><a href="listFavoriteSellers">FAVORITE SELLERS</a></li>
			<li><a href="newItem.jsp">NEW ITEM</a></li>
			<li><a href="logoutUser">LOGOUT</a></li>
		</ul>
		</table>
		

			<!-- Search Bar and Category Button -->
			<div>
				<a class="dropdown-item" href="ControlServlet?action=sortByPrice&sortBy=price">Price</a>
			</div>
			
			<div>
				<input id="searchText" type="text" placeholder="Search.." style="/* width: 85% */">
				<input type="submit" title="search" value="search" />
			</div>
			<br/>
			<!--End Search and Category-->
			
			
			
			<!-- Item Table -->
			<table>
				<thead>
					<tr>
						<td></td>
						<td>Category</td>
						<td>Name</td>
						<td>Description</td>
						<td>Price</td>
						<td>Add Review</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listOfItems}" var="items">
						<tr>
							<td style='white-space: nowrap; display: inline-block;'>
							<!-- 	<button onclick="window.location.href= 'Controller?action=addFave&itemID=${item.itemID}&itemName=${item.name}'" class="btn fa fa-thumbs-up"></button>
								<button onclick="window.location.href= 'Controller?action=removeFave&itemID=${item.itemID}&itemName=${item.name}'" class="btn fa fa-thumbs-down"></button>
							-->
							</td>
							<td>${items.categoryName}</td>
							<td>${items.title}</td>
							<td>${items.itemDescription}</td>
							<td>$ ${items.price}</td>
							<td>
								<button class="dropdown-item" onclick = "window.location.href= 'postReview.jsp?itemID=${items.itemID}'">Add a Review!</button>
							</td>
						</tr>
					</c:forEach>
	
				</tbody>
			</table>
			
			<!-- BEGIN JAVASCRIPT -->
			<script>
				/* Search Functionality */
				$(document).ready(function(){
					$("#searchText").on("keyup", function() {
	  					var value = $(this).val().toLowerCase();
	  					$("#itemData tr").filter(function() {
	    				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	  					});
					});
				});
				/* End Search Functionality */
				
				/* Highlight Selected Item */
				function changeLike(x) {
  					x.classList.toggle("fa-thumbs-down");
				}
				/* END Heart Functionality */
				
			</script>
			<!-- END OF JAVASCRIPT -->

		
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