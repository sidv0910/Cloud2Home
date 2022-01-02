<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script>
	$(document).ready(function(){
		$("#staticBackdrop").modal('show');
	});
</script>
<% 
String styling = request.getParameter("style");
String scripting = request.getParameter("script");
if(styling.equals("true"))
{
%>
<link rel="stylesheet" type="text/css" href="/css/User/${param.stylepath}">
<%
}
if(scripting.equals("true"))
{
%>
<script type="text/javascript" src="/js/User/${param.scriptpath}"> </script>
<%
}
%>

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

<title>${param.title}</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-warning">
    <div class="container-fluid">
      <a class="navbar-brand" href="/user">
        <img src="https://urban2cloud.s3.ap-south-1.amazonaws.com/Header.png" alt="" width="40" height="30" class="d-inline-block align-text-top">
        &nbsp; <b>Urban 2 Cloud</b>
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link ${param.home}" aria-current="page" href="/user/home"><b>Home</b></a>
          </li>
          <li class="nav-item">
            <a class="nav-link ${param.about}" href="/user/about"><b>About</b></a>
          </li>
          <li class="nav-item">
            <a class="nav-link ${param.contact}" href="/user/contact"><b>Contact</b></a>
          </li>
        </ul>
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle ${param.profile}" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false"> <b>${name}</b> </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="/user/profile">Profile</a></li>
              <li><a class="dropdown-item" href="/user/logout">Logout</a></li>
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle ${param.services}" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false"> <b>Services</b> </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="/user/browse">Browse Services</a></li>
              <li><a class="dropdown-item" href="/user/history">Booking History</a></li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link ${param.cart}" href="/user/cart"><b>Cart <span class="badge bg-secondary">${param.cartCount}</span></b></a>
          </li>
        </ul>
      </div>
    </div>
  </nav>