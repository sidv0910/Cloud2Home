<%@ page import="java.util.List, java.util.LinkedHashMap, com.sdp.project.model.Category, com.sdp.project.model.SubCategory" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="User | Cart" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="cart.css" />
	<jsp:param name="script" value="false" />
	<jsp:param name="cart" value="active" />
	<jsp:param name="cartCount" value="${cartCount}" />
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<jsp:include page="banner.jsp">
	<jsp:param name="banner" value="Cart" />
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

<%
int cartCount = (int) request.getAttribute("cartCount");
double total = 0;
LinkedHashMap<String, List<String>> selected = (LinkedHashMap<String, List<String>>) request.getAttribute("selected");
List<Category> category = (List<Category>) request.getAttribute("category");
if (cartCount == 0)
{
%>
	<h1> Cart Empty </h1>
<%
}
else
{
%>
	<h1>Services</h1>
    <br> <br>
	<div class="caption" align="left">
        <h4>Service - ${service}</h4>
    </div>
    <table class="table table-striped" style="width: 80%;">
        <thead>
            <tr>
                <th class="table-dark" scope="col" style="width: 37%; padding-left: 20px;">Category</th>
                <th class="table-dark" scope="col" style="width: 37%;">Sub-Category</th>
                <th class="table-dark" scope="col" style="width: 10%;">Price</th>
                <th class="table-dark text-center" scope="col" style="width: 16%;">Remove</th>
            </tr>
        </thead>
        <tbody>
<%
for (String c : selected.keySet())
{
	if (selected.get(c).size() == 1)
	{
		double price = 0.0;
		for (Category cat : category)
		{
			if (cat.getCategoryName().equals(c))
			{
				for (SubCategory subcat : cat.getSubCategory())
				{
					if (subcat.getSubCategoryName().equals(selected.get(c).get(0)))
					{
						price = subcat.getPrice();
						total += price;
					}
				}
			}
		}
%>
	<tr>
	  <form method="post" action="/user/cart/delete">
		<td style="padding-left: 20px;"><%= c %></td>
		<input type="hidden" name="service" value="${service}">
		<td><%= selected.get(c).get(0) %></td>
		<input type="hidden" name="category" value="<%= c %>">
		<td>Rs. <%= price %>0</td>
		<input type="hidden" name="subcategory" value="<%= selected.get(c).get(0) %>">
		<td align="center"><button type="submit" class="btn btn-danger">Delete</button></td>
	  </form>
	</tr>
<%
	}
	else
	{
		double price = 0.0;
		for (Category cat : category)
		{
			if (cat.getCategoryName().equals(c))
			{
				for (SubCategory subcat : cat.getSubCategory())
				{
					if (subcat.getSubCategoryName().equals(selected.get(c).get(0)))
					{
						price = subcat.getPrice();
						total += price;
					}
				}
			}
		}
%>
	<tr>
	  <form method="post" action="/user/cart/delete">
		<td rowspan="<%= selected.get(c).size() %>" style="padding-left: 20px;"><%= c %></td>
		<input type="hidden" name="service" value="${service}">
		<td><%= selected.get(c).get(0) %></td>
		<input type="hidden" name="category" value="<%= c %>">
		<td>Rs. <%= price %>0</td>
		<input type="hidden" name="subcategory" value="<%= selected.get(c).get(0) %>">
		<td align="center"><button type="submit" class="btn btn-danger">Delete</button></td>
	  </form>
	</tr>
<%
		for (int i = 1; i < selected.get(c).size(); i++)
		{
			price = 0.0;
			for (Category cat : category)
			{
				if (cat.getCategoryName().equals(c))
				{
					for (SubCategory subcat : cat.getSubCategory())
					{
						if (subcat.getSubCategoryName().equals(selected.get(c).get(i)))
						{
							price = subcat.getPrice();
							total += price;
						}
					}
				}
			}
%>
	<tr>
	  <form method="post" action="/user/cart/delete">
	    <input type="hidden" name="service" value="${service}">
		<td><%= selected.get(c).get(i) %></td>
		<input type="hidden" name="category" value="<%= c %>">
		<td>Rs. <%= price %>0</td>
		<input type="hidden" name="subcategory" value="<%= selected.get(c).get(i) %>">
		<td align="center"><button type="submit" class="btn btn-danger">Delete</button></td>
	  </form>
	</tr>
<%
		}
	}
}
%>
			<tr>
				<th class="table-dark" scope="col" style="width: 37%; padding-left: 20px;"></th>
                <th class="table-dark" scope="col" style="width: 37%; text-align: right;">Total &nbsp;-</th>
                <th class="table-dark" scope="col" style="width: 10%;">Rs. <%= total %>0</th>
                <th class="table-dark text-center" scope="col" style="width: 16%;"></th>
            </tr>
            <tr>
				<th class="table-dark" scope="col" style="width: 37%; padding-left: 20px;"></th>
                <th class="table-dark" scope="col" style="width: 37%; text-align: right;">Service Charges &nbsp;-</th>
                <th class="table-dark" scope="col" style="width: 10%;">Rs. 100.00</th>
                <th class="table-dark text-center" scope="col" style="width: 16%;"></th>
            </tr>
<%
	total += 100;
%>
            <tr>
				<th class="table-dark" scope="col" style="width: 37%; padding-left: 20px;"></th>
                <th class="table-dark" scope="col" style="width: 37%; text-align: right;">Grand Total &nbsp;-</th>
                <th class="table-dark" scope="col" style="width: 10%;">Rs. <%= total %>0</th>
                <th class="table-dark text-center" scope="col" style="width: 16%;"></th>
            </tr>
        </tbody>
	</table>
<%
}
%>

</center>
<br> <br>

<jsp:include page="footer.jsp" />