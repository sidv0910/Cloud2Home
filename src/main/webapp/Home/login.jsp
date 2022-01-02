<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="header.jsp">
	<jsp:param name="title" value="Login" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="login.css" />
	<jsp:param name="script" value="true" />
	<jsp:param name="scriptpath" value="login.js" />
	<jsp:param name="login" value="active" />
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<jsp:include page="banner.jsp">
	<jsp:param name="banner" value="Login" />
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
    <form action="/login" method="post" onsubmit="return validate();">
		<div class="avatar"><i class="material-icons">&#xE7FF;</i></div>
    	<h4 class="modal-title"><b>Sign In</b></h4>
        <div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-envelope fa-2x"></i> </span>
			</div>
			<input type="email" name="email" class="form-control" placeholder="Email Address" required="required">
		</div>
        <div class="form-group input-group">
			<div class="input-group-prepend">
				<span class="input-group-text"> <i class="fa fa-lock fa-2x"></i> </span>
			</div>
			<input type="password" name="password" id="id_password" maxlength="10" minlength="8" size="10" class="form-control" placeholder="Password" required="required">
		</div>
        <div class="form-group small clearfix">
            <a href="/forgot" class="forgot-link">Forgot Password?</a>
        </div>
        <input type="submit" style="width: 100%;" class="btn btn-primary btn-block btn-lg" value="Login">
    </form>
    <div class="text-center small">Don't have an account? <a href="/signup">Sign up</a></div>
</div>

<jsp:include page="footer.jsp" />