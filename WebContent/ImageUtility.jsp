<%@page import="java.io.FileOutputStream"%>
<%@page import="com.nagarro.ImageUtilityApp.entity.Users"%>
<%@page import="com.nagarro.ImageUtilityApp.entity.Images"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html >
<html>
<head>
<title>Image Utility</title>
<style type="text/css">
body {
	display: inline;
	margin: 0px;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}

img {
	margin: 5px;
}

#hiddenDiv {
	position: fixed;
	display: none;
	top: 33%;
	left: 45%;
	background-color: white;
}

#overlay {
	width: 100%;
	height: 100%;
	background-color: grey;
	opacity: 0.7;
	position: fixed;
	display: none;
}

legend {
	margin: auto;
}
</style>
</head>
<body>
	<div id="overlay"></div>
	<div id="hiddenDiv">
		<form id="changeImgNameForm" method="post" action="ImageEdit">
			<fieldset>
				<legend>Change Image Name</legend>
				<label>Enter Name : <Input name="name" required></label><br>
				<br> <input type="submit">
			</fieldset>
		</form>
	</div>


	<div>
		<form action="ImageSave" method="post" enctype="multipart/form-data">
			<input type="file" name="file" /> <input type="submit" />
		</form>
	</div>

	<div>
		<h2 style="margin-top: 40px">Uploaded Images</h2>
		<table style="width: 100%">
			<tr>
				<th>S.No</th>
				<th>Name</th>
				<th>Size</th>
				<th>Preview</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<%
			List<Images> imageList = (List) request.getAttribute("imageList");

			if (imageList == null) {
				out.println("<h2>No images found for this user</h2>");
			} else {
				String id, name, size, preview, action, imgPath;
				int total = 0;

				String SAVE_DIR, appPath, savePath, filePath;
				SAVE_DIR = "Images";

				FileOutputStream fos;
				for (Images i : imageList) {
					id = i.getId() + "";
					name = i.getName();
					size = i.getSize() + " kb";
					preview = i.getImagePath();
					total += i.getSize();
					pageContext.setAttribute("id", id);
					pageContext.setAttribute("name", name);
					pageContext.setAttribute("size", size);
					pageContext.setAttribute("preview", preview);
					pageContext.setAttribute("total", total);
			%>
			<tr>
				<td>${id}</td>
				<td class='ImgName'>${name}</td>
				<td>${size}</td>
				<td><img width='150px' height='150px' src=${preview}></td>
				<td><form action="ImageEdit" method="get">
						<input type="hidden" name="id" value=${id } /><input type="submit"
							value="Edit" />
					</form></td>
				<td><form action="ImageDelete" method="post">
						<input type="hidden" name="imageId" value=${id } /><input
							type="submit" value="Delete" />
					</form></td>
			</tr>
			<%
				}
			}
			%>

		</table>
	</div>
	<div>
	<h3>
	Total size of Images is ${total} kb.</h3></div>
</body>
</html>