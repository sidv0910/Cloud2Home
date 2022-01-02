<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Professional | Contact" />
	<jsp:param name="style" value="true" />
	<jsp:param name="stylepath" value="contact.css" />
	<jsp:param name="script" value="false" />
	<jsp:param name="contact" value="active" />
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<link rel="stylesheet" type="text/css" href="/css/Home/icon.css">

<jsp:include page="banner.jsp">
	<jsp:param name="banner" value="Contact Us" />
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
<section class="contact-page-area section-gap">
    <br> <br>
    <div class="container">
        <div class="row">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4903.858025177046!2d80.62260869088087!3d16.44247693926482!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3a35f0a2a7d81943%3A0x8ba5d78f65df94b8!2sK%20L%20University!5e0!3m2!1sen!2sin!4v1640000520177!5m2!1sen!2sin" width="1200" height="600" style="border:0;" allowfullscreen="true" loading="lazy"></iframe>
            <div class="col-lg-4 d-flex flex-column address-wrap">
                <br> <br>
                <div class="single-contact-address d-flex flex-row">
                    <div class="icon">
                        <span class="lnr lnr-home"></span>
                    </div>
                    <div class="contact-details">
                        <h5>Andhra Pradesh, India</h5>
                        <p>
                            K L University, Guntur
                        </p>
                    </div>
                </div>
                <div class="single-contact-address d-flex flex-row">
                    <div class="icon">
                        <span class="lnr lnr-phone-handset"></span>
                    </div>
                    <div class="contact-details">
                        <h5>+91 9771653478, +91 9060119019</h5>
                        <p>Timings: 9 a.m. to 5 p.m.</p>
                    </div>
                </div>
                <div class="single-contact-address d-flex flex-row">
                    <div class="icon">
                        <span class="lnr lnr-envelope"></span>
                    </div>
                    <div class="contact-details">
                        <h5>urban2cloud@gmail.com</h5>
                        <p>Send us your query anytime!</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <br> <br>
                <form class="form-area contact-form text-right" action="/professional/contact" method="post">
                    <div class="row">
                        <div class="col-lg-6 form-group">
                            <input type="text" name="name" maxlength="100" size="100" class="common-input mb-20 form-control" value="${name}" readonly="readonly"> <br>
                            <input type="email" name="email" class="common-input mb-20 form-control" value="${email}" readonly="readonly"> <br>
                            <input type="text" name="subject" maxlength="50" size="50" class="common-input mb-20 form-control" placeholder="Enter Subject" required="required"> <br>
                        </div>
                        <div class="col-lg-6 form-group">
                            <textarea name="message" maxlength="1000", minlength="50" size="10" class="common-textarea form-control" placeholder="Enter Query" required="required" style="height: 202px;"></textarea>
                        </div>
                        <div class="col-lg-12">
                            <br>
                            <div class="alert-msg" style="text-align: left;"></div>
                            <button class="btn btn-danger" style="float: right;">Send Message</button>
                        </div>
                    </div>
                </form>
                <br> <br>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp" />