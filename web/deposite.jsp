<%@page import="java.sql.ResultSet"%>
<%@page import="model.userModel"%>
<%
    request.getSession(false);
    ResultSet account = (ResultSet) session.getAttribute("account");
    if (account == null) {
        response.sendRedirect("login");
    }
    // ResultSet account = (ResultSet) request.getAttribute("account");  

%>
<%@include file="header.jsp" %>
<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <p style="color: red"><%if (request.getAttribute("error") != null) {
                    out.print((String) request.getAttribute("error"));
                }%></p>
            <form name="sentMessage" id="contactForm" action="deposite" method="post">
                <div class="row">
                    <div class="col-xs-12  col-sm-offset-5 col-sm-5">
                        <label>Balance Value</label>
                        <p class="custom_p color"><%if (account != null) {
                                out.print(account.getInt(2));
                            }%></p>
                    </div>
                </div>
                <div class="row control-group">
                    <div class="col-xs-12 floating-label-form-group controls">
                        <label>Deposite Value</label>
                        <input type="number" class="form-control" name="deposite" placeholder="Deposite Value" required>
                    </div>

                </div>
                <br>

                <div class="row">
                    <div class="form-group col-xs-offset-4 col-xs-4">
                        <button type="submit" class="btn btn-default center-block">Add</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>

<hr>

<%@include file="footer.jsp" %>