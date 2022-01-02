<%@ page import="java.util.List, java.util.LinkedHashMap, com.sdp.project.model.SubCategory" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="User | Browse Services" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="service.css" />
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
<h1 align="center" style="color: red; font-size: 65px; font-weight: bolder;">${service}</h1>
<br> <br>


<div class="accordion" id="accordionExample" style="width: 80%; margin: 0 auto;">
<%
LinkedHashMap<String, List<SubCategory>> category = (LinkedHashMap<String, List<SubCategory>>) request.getAttribute("category");
LinkedHashMap<String, List<String>> selected = (LinkedHashMap<String, List<String>>) request.getAttribute("selected"); 
int categoryCount = 1;
for (String c : category.keySet())
{
%>
	<div class="accordion-item">
      <h2 class="accordion-header" id="heading<%= categoryCount %>">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse<%= categoryCount %>" aria-expanded="false" aria-controls="collapse<%= categoryCount %>">
          <%= c %>
        </button>
      </h2>
      <div id="collapse<%= categoryCount %>" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
        <div class="accordion-body">
          <table class="table">
          	<tbody>
          <%
          for (SubCategory s : category.get(c))
          {
        	  boolean found = false;
        	  for (String cat : selected.keySet())
        	  {
        		  if (cat.equals(c) && selected.get(cat).contains(s.getSubCategoryName()))
        		  {
        			  found = true;
        			  break;
        		  }
        	  }
        	  if (found)
        	  {
          %>
          	
          	<tr>
          	  <form method="post" action="/user/service/delete">
          	  	<input type="hidden" name="service" value="${service}">
                <td style="padding-left: 25px"><%= s.getSubCategoryName() %></td>
                <input type="hidden" name="category" value="<%= c %>">
                <td align="center">Rs. <%= s.getPrice() %>0</td>
                <input type="hidden" name="subcategory" value="<%= s.getSubCategoryName() %>">
                <td align="center"><button type="submit" class="btn btn-danger">Delete</button></td>
              </form>
            </tr>
          <%
        	  }
        	  else
        	  {
          %>
            <tr>
          	  <form method="post" action="/user/service/add">
          	  	<input type="hidden" name="service" value="${service}">
                <td style="padding-left: 25px"><%= s.getSubCategoryName() %></td>
                <input type="hidden" name="category" value="<%= c %>">
                <td align="center">Rs. <%= s.getPrice() %>0</td>
                <input type="hidden" name="subcategory" value="<%= s.getSubCategoryName() %>">
                <td align="center"><button type="submit" class="btn btn-primary">Add</button></td>
              </form>
            </tr>
          <%
        	  }
          }
          %>
            </tbody>
          </table>
        </div>
      </div>
    </div>
<%
	categoryCount++;
}
%>
</div>


<br> <br> <br>

<jsp:include page="footer.jsp" />