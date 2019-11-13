<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Welcome</title>
	<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #335B78;
  text-align: center;
  width:100%
}

li {
	display:inline-block;
        *display:inline; /*IE7*/
        *zoom:1; /*IE7*/
        color:white;
        margin-right:10px;
}

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
<body >	
<h1 style="color:#335B78;text-align:center;">WELCOME PAGE</h3> 

	<table>
		<ul style = "text-align: center">
			<li><a href="index.jsp">HOME</a></li>
			<li><a href="initialize"> INITIALIZE DATABASE</a></li>
			<li><a href="Registration.jsp">REGISTER</a></li>
			<li><a href="login">LOGIN</a></li>
		</ul>
	</table>
</body>
</html>