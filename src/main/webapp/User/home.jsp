<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="User | Home" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="home.css" />
	<jsp:param name="script" value="false" />
	<jsp:param name="home" value="active" />
	<jsp:param name="cartCount" value="${cartCount}" />
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<div class="header">
	<img src="https://urban2cloud.s3.ap-south-1.amazonaws.com/Banner.jpg" class="banner">
</div>

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
<div id="content" role="main">
    <div class="section">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <h1 align="center">Who We Are</h1>
                    <p>
                        <p style="font-size: 20px;" align="center">Urban 2 Cloud helps customers book reliable and high quality services - beauty treatments, massages, haircuts, home cleaning, 
                        appliance repair, painting, pest control and more - delivered by trained professionals conveniently at home.</p>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<br> <br>
<div id="content" role="main">
    <div class="featurebg-container section">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <h1 align="center">How We Do It</h1>
                    <p>
                        <p style="font-size: 20px;" align="center">Urban 2 Cloud provides a platform that allows skilled and experienced professionals to connect with users looking for specific services
                        Our match-making algorithm identifies professionals who are closest to the user's requirements and available at the requested time and date.</p>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />