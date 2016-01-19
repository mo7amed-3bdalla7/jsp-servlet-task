<%@page import="model.depositeModel"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%
    ResultSet account = (ResultSet) session.getAttribute("account");
    if (account == null) {
        response.sendRedirect("login");
    }
    

%>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <p>This Service allows registered Customers to Online transfering Money between each Accounts</p>
            <!-- Contact Form - Enter your email address on line 19 of the mail/contact_me.php file to make this form work. -->
            <!-- WARNING: Some web hosts do not allow emails to be sent through forms to common mail hosts like Gmail or Yahoo. It's recommended that you use a private domain email address! -->
            <!-- NOTE: To use the contact form, your site must be on a live web host with PHP! The form will not work locally! -->
           <p style="color: red"><%if (request.getAttribute("error") != null) {
                    out.print((String) request.getAttribute("error"));
                }%></p>
            <h2>Transfering Information</h2>
            <form name="sentMessage" id="contactForm" method="post" novalidate action="transfer">

                <div class="row control-group">
                    <div class="form-group col-xs-12 floating-label-form-group controls">
                        <label>Reciver Account Number</label>
                        <input type="number" name="acnum"class="form-control" placeholder="Reciver Account Number" id="email" required data-validation-required-message="Please enter Reciver Account Number !.">
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
                <div class="row control-group">
                    <div class="form-group col-xs-12 floating-label-form-group controls">
                        <label>Transfer Value</label>
                        <input type="number" name="tansvalue" class="form-control" placeholder="Transfer Value" id="phone" required data-validation-required-message="Please enter your Transfer Value !.">
                        <p class="help-block text-danger"></p>
                    </div>
                </div>

<!--                <div class="row control-group">
                    <div class="form-group col-xs-12 floating-label-form-group controls">
                        <label>Message</label>
                        <textarea rows="5" class="form-control" placeholder="Message or Notes" id="message" required data-validation-required-message="Please enter a message."></textarea>
                        <p class="help-block text-danger"></p>
                    </div>
                </div>-->
                <br>
                <div id="success"></div>
                <div class="row">
                    <div class="form-group col-xs-12">
                        <button type="submit" class="btn btn-default">Send</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>

<hr>
<%@include file="footer.jsp" %>

