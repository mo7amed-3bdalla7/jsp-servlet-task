<%@page import="model.userModel"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.depositeModel"%>
<%@include file="header.jsp" %>
<%
    ResultSet account = (ResultSet) session.getAttribute("account");
    ResultSet refreshedaccount = null;
    if (currentuser != null) {
        refreshedaccount = depositeModel.getAccount(currentuser.getAccountNum());
        refreshedaccount.next();
    } else {
        response.sendRedirect("login");
    }
   


%>



<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <!--<p class="h1">Review your Profile!</p><br>-->

            <div class="row"><img class=" col-xs-offset-4 col-xs-4 img-responsive profile_img" src="img/post-bg.jpg" alt=""></div>

            <div class="row">
                <div class="col-xs-12 col-sm-5">
                    <label>Name</label>

                    <p class="custom_p color"><%if (currentuser != null) {
                            out.print(currentuser.getFristName() + " " + currentuser.getLastName());
                        }%></p>
                </div>

                <div class="col-xs-12 col-sm-offset-2 col-sm-5">
                    <label>Phone Number</label>

                    <p class="custom_p color"><%
                        if (currentuser != null) {
                            out.print(currentuser.getPhone());
                        }%></p>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12 col-sm-5">
                    <label>Email</label>

                    <p class="custom_p color"><% if (currentuser != null) {
                            out.print(currentuser.getEmail());
                        }%></p>
                </div>

                <div class="col-xs-12 col-sm-offset-2 col-sm-5">
                    <label>Age</label>

                    <p class="custom_p color"><% if (currentuser != null) {
                            out.print(currentuser.getAge());
                        }%></p>
                </div>
                <div class="col-xs-12 col-sm-5">
                    <label>Balance</label>

                    <p class="custom_p color"><% if (refreshedaccount != null) {
                            out.print(refreshedaccount.getInt(2));
                        }%></p>
                </div>
                <div class="col-xs-12 col-sm-offset-2 col-sm-5">
                    <label>Account Number</label>

                    <p class="custom_p color"><% if (refreshedaccount != null) {
                            out.print(refreshedaccount.getInt(1));
                        }%></p>
                </div>
            </div><br>

            <!--<div class="panel panel-default">-->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <b>Deposit History</b>
                </div>

                <table class="table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Value</th>
                            <th>Date</th>
                        </tr>
                    </thead>

                    <tbody>
                        <% if(request.getAttribute("deposite")!=null){
                            ResultSet deposite=(ResultSet)request.getAttribute("deposite");
                            int count = 0;
                            while(deposite.next() ){
                        %>
                        <tr>
                            <th scope="row"><%=++count%></th>

                            <td><%=deposite.getInt(3)%></td>
                            <td><%=deposite.getTimestamp(4)%></td>                 
                        </tr>
                        <%}if(count==0) out.print("<tr><td>There Is No Data !!</td></tr>");};%>
                    </tbody>
                </table>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <b>WithDraw History</b>
                </div>

                <table class="table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Value</th>
                            <th>Date</th>
                        </tr>
                    </thead>

                    <tbody>
                        <% if(request.getAttribute("withdraw")!=null){
                            ResultSet withdraw=(ResultSet)request.getAttribute("withdraw");
                            int count = 0;
                            while(withdraw.next() ){
                        %>
                        <tr>
                            <th scope="row"><%=++count%></th>

                            <td><%=withdraw.getInt(3)%></td>
                            <td><%=withdraw.getTimestamp(4)%></td>                 
                        </tr>
                        <%}if(count==0) out.print("<tr><td>There Is No Data !!</td></tr>");};%>
                    </tbody>
                </table>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <b>transfer History</b>
                </div>

                <table class="table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Sender</th>
                            <th>Receiver</th>
                            <th>Value</th>
                            <th>Date</th>
                        </tr>
                    </thead>

                    <tbody>
                        <% if(request.getAttribute("transfer")!=null){
                            ResultSet transfer=(ResultSet)request.getAttribute("transfer");
                            int count = 0;
                            while(transfer.next() ){
                        %>
                        <tr>
                            <th scope="row"><%=++count%></th>

                            <td><%=transfer.getInt(2)%></td>
                            <td><%=transfer.getInt(3)%></td>
                            <td><%=transfer.getInt(4)%></td>
                            <td><%=transfer.getTimestamp(5)%></td>                 
                        </tr>
                        <%}if(count==0) out.print("<tr><td>There Is No Data !!</td></tr>");};%>
                    </tbody>
                </table>
            </div>
            <div class="row">
                <div class=" col-xs-offset-4 col-xs-4">
                    <a href="edit_profile.html" class="btn btn-default center-block">Edit</a>
                </div>
            <!--</div>-->
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>