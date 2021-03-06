<%@page import="model.userModel"%>
<%request.getSession(true);
userModel currentuser = (userModel) session.getAttribute("user");
//out.print(session.getAttribute("deposite"));




%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Online Banking</title>
    <link rel="shortcut icon" href="img/icon.jpg">

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/clean-blog.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index">Online Banking</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="index">Home</a>
                    </li>
<!--                    <li>
                        <a href="about">About</a>
                    </li>-->
<!--                    <li>
                        <a href="contact">Contact</a>
                    </li>-->
                    <%if(currentuser!=null){%>
                     <li class="dropdown">
                      <a href="#" class="dropdown-toggle" style="background:rgba(0,0,0,0)" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Services <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                        <li ><a href="deposite" style="color:#333">Deposit</a></li>
                        <li><a href="withdraw" style="color:#333">Withdraw</a></li>
                        <li><a href="transfer" style="color:#333">Transfering</a></li>                       
                      </ul>
                    </li>
                    <li>
                        <a href="profile">Profile</a>
                    </li>
                    <%}%>
                    <%if(currentuser==null){%>
                    <li>
                        <a href="login">Login / sign Up</a>
                    </li>
                    <%}else{%>
                    <li>
                        <a href="logout">Logout</a>
                    </li>
                    <%}%>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Header -->
    <!-- Set your background image for this header on the line below.
    <header class="intro-header" style="background-image: url('img/bank_card.jpg')">bank_card  -->
        <header class="intro-header" style="background-image: url('img/home-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="site-heading">
                        <h1>Online Banking</h1>
                        <hr class="small">
                        <!--<span class="subheading">A Clean Blog Theme by Start Bootstrap</span> -->
                    </div>
                </div>
            </div>
        </div>
    </header>
