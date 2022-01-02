<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Admin | Home" />
	<jsp:param name="style" value="false" />
	<jsp:param name="script" value="false" />
	<jsp:param name="home" value="active" />
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

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

<br> <br> <br>

<center>
	<div class="container">
	  <div class="row">
	    <div class="col">
	      <a href="/admin/addServices"><button type="button" class="btn btn-success">Add Service</button></a>
	    </div>
	    <div class="col">
	      <a href="/admin/addCategory"><button type="button" class="btn btn-success">Add Category</button></a>
	    </div>
	    <div class="col">
	      <a href="/admin/addSubCategory"><button type="button" class="btn btn-success">Add Sub-Category</button></a>
	    </div>
	  </div>
	</div>
</center>

<br> <br> <br>

<center>
	<div class="container">
	  <div class="row">
	    <div class="col">
	      <a href="/admin/viewServices"><button type="button" class="btn btn-primary">View All Services</button></a>
	    </div>
	  </div>
	</div>
</center>

<br> <br> <br>

<jsp:include page="footer.jsp" />