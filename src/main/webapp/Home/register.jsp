<%@ page language="java" import="java.util.*, com.sdp.project.model.Services, com.sdp.project.model.Category" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Register As A Professional" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="register.css" />
	<jsp:param name="script" value="true" />
	<jsp:param name="scriptpath" value="register.js" />
	<jsp:param name="register" value="active" />
</jsp:include>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link rel="stylesheet" type="text/css" href="/fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
<link rel="stylesheet" type="text/css" href="/css/Home/montserrat-font.css">

<%
List<Services> s = (List<Services>) request.getAttribute("services");
%>

<script>
	var categories = new Object();
	var temp = [];
	<%
	for (Services i : s)
	{
	%>
			temp = [];
		<%
		for (Category c : i.getCategory())
		{
		%>
			temp.push("<%= c.getCategoryName() %>");
		<%
		}
		%>
		categories["<%= i.getServiceName() %>"] = temp;
	<%
	}
	%>
	function updateCategory(service)
	{
		if (service.length == 0)
			document.getElementById("category").innerHTML = "<option></option>";
		else
		{
			var categoryOptions = "";
			for (category in categories[service])
			{
				categoryOptions += "<option disabled style=\"color: white;\"> &#10004; " + categories[service][category] + "</option>";
			}
			document.getElementById("category").innerHTML = categoryOptions;
		}
	}
</script>

<jsp:include page="banner.jsp">
	<jsp:param name="banner" value="Register As A Professional" />
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

	<div class="page-content">
		<div class="form-v10-content">
			<form class="form-detail" action="/register" method="post" enctype="multipart/form-data" id="myform" onsubmit="return validate();">
				<div class="form-left">
					<h2 align="center">Personal Details</h2>
					<div class="form-row">
						<input type="text" name="name" maxlength="100" size="100" class="form-control", placeholder="Name" required="required">
					</div>
					<div class="form-row">
						<input type="text" name="address" maxlength="100" size="100" class="input-text", placeholder="Address / Locality" required="required">
					</div>
					<div class="form-group">
						<div class="form-row form-row-1">
							<input type="text" name="city" maxlength="20" size="20" class="input-text", placeholder="City" required="required">	
						</div>
						<div class="form-row form-row-2">
							<input type="number" name="zip" class="input-text", placeholder="Zip Code" required="required">
						</div>
					</div>
					<div class="form-row">
						<input type="email" name="email" class="input-text", placeholder="Email Address" required="required">
					</div>
					<div class="form-row">
						<input type="number" name="contact" class="input-text", placeholder="Contact Number" required="required">
					</div>
					<div class="form-row">
						<input type="password" name="password" maxlength="10" size="10" class="input-text", placeholder="Password" required="required" id="id_password">
					</div>
					<div class="form-row">
						<h4 style="color: #4835d4;">Profile Pic</h4>
						<input type="file" name="profilePic" class="form-control" accept="image/*" required="required">
					</div>
				</div>
				<div class="form-right">
					<h2 align="center">Professional Information</h2>
					<div class="form-row">
						<select name="service" id="service" required onChange="updateCategory(this.value)">
							<option value="" selected disabled hidden>Select Service</option>
							<%
							for (Services i : s)
							{
							%>
							<option value="<%= i.getServiceName() %>"><%= i.getServiceName() %></option>
							<%
							}
							%>
						</select>
						<span class="select-btn">
						  	<i class="zmdi zmdi-chevron-down"></i>
						</span>
					</div>
					<div class="form-row">
						<h4 style="color: white;">Category</h4>
						<select name="category" id="category" required multiple="multiple">
						</select>
						<font color="white" size="2">
						</font>
					</div>
					<div class="form-row">
						<select name="experience" required>
							<option value="" selected disabled hidden>Experience</option>
							<option value="< 1 Year">< 1 Year</option>
							<option value="1 Year">1 Year</option>
							<option value="2 Years">2 Years</option>
							<option value="3 Years">3 Years</option>
							<option value="4 Years">4 Years</option>
							<option value="5 Years">5 Years</option>
							<option value="6 Years">6 Years</option>
							<option value="7 Years">7 Years</option>
							<option value="8 Years">8 Years</option>
							<option value="9 Years">9 Years</option>
							<option value="10 Years">10 Years</option>
							<option value="> 10 Years">> 10 Years</option>
						</select>
						<span class="select-btn">
						  	<i class="zmdi zmdi-chevron-down"></i>
						</span>
					</div>
					<div class="form-row-last" align="center">
						<input type="submit" name="register" class="register" value="Submit">
					</div>
				</div>
			</form>
		</div>
	</div>

<jsp:include page="footer.jsp" />