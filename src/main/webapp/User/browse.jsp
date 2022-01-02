<%@ page import="java.util.List, com.sdp.project.model.Services" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="User | Browse Services" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="browse.css" />
	<jsp:param name="script" value="false" />
	<jsp:param name="services" value="active" />
	<jsp:param name="cartCount" value="${cartCount}" />
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<jsp:include page="banner.jsp">
	<jsp:param name="banner" value="Browse Services" />
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
<br> <br>
<center>
<div class="container">
<div class="row">
<%
List<Services> services = (List<Services>) request.getAttribute("services");
for (Services s : services)
{
%>
  <div class="col">
	<div id="card-container">
       <div id="card">
         <div class="front face">
           <img src="<%= s.getServiceImageUrl() %>" height="300" width="270"/>
         </div>
         <div class="back face" onclick="window.location.href='/user/browse/<%= s.getServiceName() %>'">
           <h1><%= s.getServiceName() %></h1>
           <!-- <p class="artist">The Chainsmokers</p>
           <p class="date">2015</p> -->
         </div>
       </div>
    </div>
  </div>
<%
}
%>
</div>
</div>
</center>
<br> <br>
<jsp:include page="footer.jsp" />