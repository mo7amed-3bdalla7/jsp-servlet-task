<%@page import="model.userModel"%>
<%
    //request.getSession(false);
    userModel user = (userModel) session.getAttribute("user");
    if (user != null) {
        response.sendRedirect("profile");
    }
%>
<% Object display = request.getAttribute("display"); %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="UTF-8" />

        <title><%if (display != null) {
                out.print("Sign Up");
            } else {
                out.print("Log In");
            }
            %></title>
        <link rel="stylesheet" href="css/reset.css" type="text/css" />
        <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons' />
        <link rel="stylesheet" href="css/style.css" type="text/css" />
    </head>

    <body>
        <!-- Form-->
        <div class="form" style="<%if (display != null) {
                out.print("height:852px;");
            }%>">


            <div class="form-toggle <%if (display != null) {
                    out.print("visible");
                }%>">


            </div>

            <div class="form-panel one <%if (display != null) {
                    out.print("hidden");
                }%>">
                <div class="form-header">
                    <h1>Account Login</h1>
                     <p style="color: red"><%if (request.getAttribute("loginError") != null) {
                    out.print((String) request.getAttribute("loginError"));
                }%></p>
                </div>

                <div class="form-content">
                    <form action="login" method="post">
                        <div class="form-group">
                            <label for="email">Email</label> <input type="email" id="username" name="email" required="required" />
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label> <input type="password" id="password" name="password" required="required" />
                        </div>

                        <div class="form-group">
                            <label class="form-remember"><input type="checkbox" />Remember Me</label> <a href="#" class="form-recovery">Forgot Password?</a>
                        </div>

                        <div class="form-group">
                            <button type="submit">Log In</button>
                        </div>
                    </form>
                </div>
            </div>

            <div class="form-panel two <%if (display != null) {
                    out.print("active");
                }%>">
                <div class="form-header">
                    <h1>Register Account</h1>
                    <p style="color: red;">
                        <% if (request.getAttribute("error") != null) {
                                out.print(request.getAttribute("error"));

                            }%>
                    </p>
                </div>

                <div class="form-content">
                    <form action="signup" method="post">
                        <div class="form-group">
                            <label for="frist_name">Frist Name</label> <input type="text" id="username" name="frist_name" required="required" />
                        </div>
                        <div class="form-group">
                            <label for="last_name">Last Name</label> <input type="text" id="username" name="last_name" required="required" />
                        </div>
                        <div class="form-group">
                            <label for="age">Age</label> <input type="text" id="username" name="age" required="required" />
                        </div>
                        <div class="form-group">
                            <label for="age">Phone</label> <input type="text" id="username" name="phone" required="required" />
                        </div>
                        <div class="form-group">
                            <label for="email">Email Address</label> <input type="email" id="email" name="email" required="required" />
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label> <input type="password" id="password" name="password" required="required" />
                        </div>

                        <div class="form-group">
                            <label for="cpassword">Confirm Password</label> <input type="password" id="cpassword" name="cpassword" required="required" />
                        </div>


                        <div class="form-group">
                            <button type="submit">Register</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="pen-footer">
        </div><script src="js/jquery.min.js">
        </script><script src="js/index.js" type="text/javascript">
        </script>
    </body>
</html>
