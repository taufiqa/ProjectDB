<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Store Page</title>
</head>
<body>
    <center>
        <h1>WELCOME TO STORE!</h1>
        </center>
  <!-- 
        <h2>
            <a href="new">Add New People</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All People</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of People</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Address</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="people" items="${listPeople}">
                <tr>
                    <td><c:out value="${people.id}" /></td>
                    <td><c:out value="${people.name}" /></td>
                    <td><c:out value="${people.address}" /></td>
                    <td><c:out value="${people.status}" /></td>
                    <td>
                        <a href="edit?id=<c:out value='${people.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${people.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
    
    -->
</body>
</html>