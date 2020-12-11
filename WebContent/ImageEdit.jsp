<%@page import="java.io.FileOutputStream"%>
<%@page import="com.nagarro.ImageUtilityApp.entity.Users"%>
<%@page import="com.nagarro.ImageUtilityApp.entity.Images"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html >
<html>
<head>
<title>Image Utility</title>
<style type="text/css">
body{
display: inline;
margin: 0px ;
}
table,th,td{
  border:1px solid black ;
  border-collapse:collapse ;
  text-align: center ;
}
img{
margin:5px;
}
#hiddenDiv{
position: fixed;
display:none ;
top:33% ;
left:45% ;
background-color: white ;
}
#overlay{
width: 100% ;
height: 100% ;
background-color: grey ;
opacity:0.7 ;
position: fixed;
display:none ;
}
legend {
     margin: auto;
}
<%
				int imageId = (int) request.getAttribute("imageId");
pageContext.setAttribute("imageId", imageId);
			%>

</style>
</head>
<body>
<div id ="overlay"></div>
<form  method="post" action="ImageEdit" >
<fieldset>
<legend >Change Image Name</legend>
<input type="hidden" name="imageId" value=${imageId} />
<label>Enter Name :  <Input name="imageName" required></label><br><br>

<input type="submit">
</fieldset>
</form>
</body>
</html>