<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post Review</title>
</head>
<body>

<h1 style= "background-color:#335B78; color:#C4D8E6; text-align: center">Review Item</h1> 
  <form action="postReview" method="POST">
 <input type="hidden" name="itemID" value="<%= request.getParameter("itemID")%>"></input>
    <div class="form-group">
		<label class="col-md-2" for="score">Score:</label>
		<select class="form" id="score" name="score">
			<option value = ""></option>
			<option value="Excellent">Excellent</option>
			<option value="Good">Good</option>
			<option value="Fair">Fair</option>
			<option value="Poor">Poor</option>
		</select>
	</div>
				
	<br/>
				
	<div class="form-group">
		<label class="col-md-2" for="comment">Remark:</label>
		<textarea class="form control" rows="" id="remark" name="remark"></textarea>
	</div>
	
	<br/>
	
	<div class="form-group">
		<label class="col-md-2" for="submit"></label>
		<input type = "submit" name="submit" value="Submit"></input>
	</div>
   </form>
	
</body>
</html>