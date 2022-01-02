<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Professional | Reset Password" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="reset.css" />
	<jsp:param name="script" value="true" />
	<jsp:param name="scriptpath" value="reset.js" />
	<jsp:param name="profile" value="active" />
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<jsp:include page="banner.jsp">
	<jsp:param name="banner" value="Reset Password" />
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

<div class="login-form">
    <form action="/professional/reset" method="post" onsubmit="return validate();">
		<h3 align="center"><i class="fa fa-lock fa-4x"></i></h3>
        <h2 class="text-center">Reset Password</h2>
        <br>
        <div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-lock fa-2x"></i> </span>
			</div>
			<input type="password" name="old_password" id="id_old_password" maxlength="10" minlength="8" size="10" class="form-control" placeholder="Old Password" required="required">
		</div>
        <div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-lock fa-2x"></i> </span>
			</div>
			<input type="password" name="password" id="id_password" maxlength="10" minlength="8" size="10" class="form-control" placeholder="Password" required="required">
		</div>
		<div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-lock fa-2x"></i> </span>
			</div>
			<input type="password" name="re_password" id="id_re_password" maxlength="10" minlength="8" size="10" class="form-control" placeholder="Repeat New Password" required="required">
		</div>
        <input type="submit" style="width: 100%;" class="btn btn-primary btn-block btn-lg" value="Submit">
    </form>
</div>

<jsp:include page="footer.jsp" />