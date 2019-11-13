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
			<td/>
			<td align="left">
				<form action="searchCategory" method="post">
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
</body>
</html>