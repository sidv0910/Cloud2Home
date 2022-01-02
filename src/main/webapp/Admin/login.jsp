<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Admin Login</title>
	<link rel="stylesheet" href="css/Admin/adminLogin.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="apple-touch-icon" sizes="57x57" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/favicon-16x16.png">
	<link rel="manifest" href="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="https://urban2cloud.s3.ap-south-1.amazonaws.com/Icons/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#staticBackdrop").modal('show');
		});
	</script>
  </head>
  <body>
<%
if (request.getAttribute("status") != null)
{
String status = (String) request.getAttribute("status");
if (status.equals("true") || status.equals("false"))
{
%>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" style="color: black;">Urban 2 Cloud</h5>
        <a href="${url}"><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></a>
      </div>
      <div class="modal-body" style="color: black;">
        <p>${message}</p>
      </div>
      <div class="modal-footer" style="color: black;">
        <a href="${url}"><button type="button" class="btn btn-primary" data-bs-dismiss="modal">${button}</button></a>
      </div>
    </div>
  </div>
</div>
<%
}
}
%>
  	<br> <br> <br> <br> <br> <br>
	<div class="login-form">
	    <form action="/admin" method="post">
			<div class="avatar"><i class="material-icons">&#xE7FF;</i></div>
	    	<h4 class="modal-title"><b>Admin Login</b></h4>
	        <div class="form-group input-group">
				<div class="input-group-prepend">
					<span class="input-group-text"> <i class="fa fa-user fa-2x"></i> </span>
				</div>
				<input type="text" name="username" class="form-control" placeholder="Username" required="required">
			</div>
	        <div class="form-group input-group">
				<div class="input-group-prepend">
					<span class="input-group-text"> <i class="fa fa-lock fa-2x"></i> </span>
				</div>
				<input type="password" name="password" id="id_password" class="form-control" placeholder="Password" required="required">
			</div>
	        <input type="submit" style="width: 100%;" class="btn btn-primary btn-block btn-lg" value="Login">
	    </form>
	</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>