<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Admin | Add Services" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="addServices.css" />
	<jsp:param name="script" value="false" />
	<jsp:param name="services" value="active" />
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
    <div class="form">
      <div class="title">Add Services</div>
      <form method="post" action="/admin/addServices" enctype="multipart/form-data">
      	  <div class="input-container ic1">
	        <input name="serviceimage" class="input" type="file" placeholder="Service Image" accept="image/*" required style="vertical-align: middle;"/>
	      </div>
	      <div class="input-container ic2">
	        <input name="service" class="input" type="text" placeholder="Service" required />
	      </div>
	      <div class="input-container ic2">
	        <input name="category" class="input" type="text" placeholder="Category" required />
	      </div>
	      <div class="input-container ic2">
	        <input name="subcategory" class="input" type="text" placeholder="Sub-Category" requireds />
	      </div>
	      <div class="input-container ic2">
	        <input name="price" class="input" type="number" placeholder="Price" requireds />
	      </div>
	      <button type="text" class="submit">Submit</button>
      </form>
    </div>
</center>
<br> <br> <br>
<jsp:include page="footer.jsp" />  