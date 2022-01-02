<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="About" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="about.css" />
	<jsp:param name="script" value="false" />
	<jsp:param name="about" value="active" />
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link rel="stylesheet" type="text/css" href="/css/Home/icon.css">

<jsp:include page="banner.jsp">
	<jsp:param name="banner" value="About Us" />
</jsp:include>

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