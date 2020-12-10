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

</style>
</head>
<body>
<div id ="overlay"></div>
<div id="hiddenDiv" >
<form id= "changeImgNameForm" method="post" action="ImageEdit" >
<fieldset>
<legend >Change Image Name</legend>
<label>Enter Name :  <Input name="name" required></label><br><br>
<input type="submit">
</fieldset>
</form>
</div>


<div>
<form method="post" action="ImageSave" enctype="multipart/form-data" id ="imgSaveForm">
<input type = "file" name="imgFile" style="margin-right: 400px" accept="image/*" required/>
<input type="submit" style="margin-right: 5px">
<input type="reset" >
</form>
</div>

<div>
<h2 style="margin-top: 40px">Uploaded Images</h2>
<table style="width: 100% ">
<tr>
<th>S.NO</th>
<th>Name</th>
<th>Size</th>
<th>Preview</th>
<th>Action</th>
</tr>
<% 
List<Images> li =(List) request.getAttribute("li");
String username = (String) request.getAttribute("username");
if(li==null)
{
	out.println("<h2>No images found for this user</h2>");
}
else{
String id, name, size, preview , action ,imgPath ;

String SAVE_DIR,appPath,savePath,filePath ;
SAVE_DIR = "Images";

FileOutputStream fos ;
action = "<a href='http://localhost:8080/Demo/ImageDelete'><img src= 'Images/cross_48.png '></a> <a href='http://localhost:8080/Demo/ImageEdit'> <img src= 'Images/pen-checkbox-64.png' > </a>" ;
for(Images i : li){
	id = i.getId()+"";
	name = i.getName();
	size = i.getSize()+" kb";
	preview = i.getImagePath(); %>
<tr>
<td>${id}</td>
<td class='ImgName'>name</td>
	out.println("<td>"+size+"</td>");
	out.println("<td><img width='150px' height = '150px' src = '" + preview + "'></td>");
	out.println("<td>"+action+"</td>");
	out.println("</tr>");	}
}<%

%>

</table>
</div>
</body>
</html>