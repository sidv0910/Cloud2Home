<%@ page import="java.util.List" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="User | Profile" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="profile.css" />
	<jsp:param name="script" value="false" />
	<jsp:param name="profile" value="active" />
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<jsp:include page="banner.jsp">
	<jsp:param name="banner" value="Profile" />
</jsp:include>

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

<center>
<div class="container">
<br>
<div class="card bg-light" style="width: 50%;">
<article class="card-body mx-auto" style="max-width: 500px;">
	<br> <br>
		<div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-envelope fa-2x"></i> </span>
			 </div>
			<input type="email" name="email" class="form-control" style="background-color: white;" placeholder="Email Address" value="${obj.getEmail()}" readonly>
		</div>
		<br>
		<div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-user fa-2x"></i> </span>
			 </div>
			<input type="text" size="100" name="name" class="form-control" style="background-color: white;" placeholder="Full Name" value="${obj.getName()}" readonly>
		</div>
		<br>
		<div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-phone fa-2x"></i> </span>
			</div>
			<input type="text" value="+91" class="form-control" style="max-width: 60px; background-color: white;" readonly>
			<input type="number" name="contact" class="form-control" style="background-color: white;" placeholder="Contact Number" value="${obj.getContact()}" readonly>
		</div>
		<br> <br>
		<center>
		<div class="row mb-3 px-3">
			<div class="col-sm-6 col-12">
                <center><a href="/user/update"><button type="submit" class="btn btn-primary btn-block">Update Profile</button></a></center>
            </div>
            <div class="col-sm-6 col-12">
                <center><a href="/user/reset"><button type="submit" class="btn btn-primary btn-block">Reset Password</button></a></center>
            </div>
		</div>
        <br>
		</center>
</article>
</div>
</div>
</center>
<br>

<jsp:include page="footer.jsp" />