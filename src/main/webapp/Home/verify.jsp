<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Forgot Password" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="verify.css" />
	<jsp:param name="script" value="false" />
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<jsp:include page="banner.jsp">
	<jsp:param name="banner" value="Forgot Password" />
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

<div class="container height-100 d-flex justify-content-center" style="margin-top: 50px; margin-bottom: 50px;">
      <div class="card p-2 text-center">
          <h6>Please enter the one time password <br> to verify your account</h6>
          <div> <span>A code has been sent to your email</span> </div>
          <br>
          <form action="/verify" method="post">
              <div id="otp" class="inputs d-flex flex-row justify-content-center mt-2">
                  <input class="m-2 text-center form-control rounded" type="text" name="first" id="first" maxlength="1" required="required" />
                  <input class="m-2 text-center form-control rounded" type="text" name="second" id="second" maxlength="1" required="required" />
                  <input class="m-2 text-center form-control rounded" type="text" name="third" id="third" maxlength="1" required="required" />
                  <input class="m-2 text-center form-control rounded" type="text" name="fourth" id="fourth" maxlength="1" required="required" />
                  <input class="m-2 text-center form-control rounded" type="text" name="fifth" id="fifth" maxlength="1" required="required" />
                  <input class="m-2 text-center form-control rounded" type="text" name="sixth" id="sixth" maxlength="1" required="required" />
              </div>
              <font size="2">
				  * The verification code is valid till 5 minutes.
			  </font>
              <div class="mt-4"> <button class="btn btn-danger px-4 validate">Validate</button> </div>
          </form>
      </div>
</div>

<jsp:include page="footer.jsp" />