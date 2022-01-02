<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="header.jsp">
	<jsp:param name="title" value="Sign Up" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="signup.css" />
	<jsp:param name="script" value="true" />
	<jsp:param name="scriptpath" value="signup.js" />
	<jsp:param name="signup" value="active" />
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<jsp:include page="banner.jsp">
	<jsp:param name="banner" value="Sign Up" />
</jsp:include>

<%
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
%>
<center>
<div class="container">
<br>
<div class="card bg-light" style="width: 50%;">
<article class="card-body mx-auto" style="max-width: 500px;">
	<h4 class="card-title mt-3 text-center">Create Account</h4>
	<p class="text-center">Get started with your free account</p>
	<br>
	<form action="/signup" method="post" onsubmit="return validate();">
		<div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-user fa-2x"></i> </span>
			 </div>
			<input type="text" name="name" maxlength="100" size="100" class="form-control" placeholder="Full Name" required="required">
		</div>
		<br>
		<div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-envelope fa-2x"></i> </span>
			 </div>
			<input type="email" name="email" class="form-control" placeholder="Email Address" required="required">
		</div>
		<br>
		<div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-phone fa-2x"></i> </span>
			</div>
			<input type="text" value="+91" class="form-control" style="max-width: 60px; background-color: white;" readonly>
			<input type="number" name="contact" min="6000000000", max="9999999999" class="form-control" placeholder="Contact Number" required="required">
		</div>
		<br>
		<div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-lock fa-2x"></i> </span>
			</div>
			<input type="password" name="password" id="id_password" maxlength="10" minlength="8" size="10" class="form-control" placeholder="Password" required="required">
		</div>
		<br>
		<div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-lock fa-2x"></i> </span>
			</div>
			<input type="password" name="repassword" id="id_re_password" maxlength="10" minlength="8" size="10" class="form-control" placeholder="Password" required="required">
		</div>
		<br>
		<center>
		<div class="form-group">
			<button type="submit" class="btn btn-primary btn-block"> Create Account </button>
		</div>
		</center>
		<br>
    	<p class="text-center">Have an account? <a href="/login">Login</a> </p>
	</form>
</article>
</div>
</div>
</center>
<br>

<jsp:include page="footer.jsp" />