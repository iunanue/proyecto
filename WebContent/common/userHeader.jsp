<%@page import="controller.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=Config.getInstance().getRoot()%>bootstrap-3.3.6-dist/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=Config.getInstance().getRoot()%>bootstrap-3.3.6-dist/css/custom.css"
	type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="<%=Config.getInstance().getRoot()%>bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<%=Config.getInstance().getRoot()%>index.jsp">Proyecto</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Gallery</a></li>
				<li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Page 1-1</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li> 
        </ul>
      </li>
				<li><a href="#">Contact</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=Config.getInstance().getRoot()%>/protected_area/miCuenta"><span class="glyphicon glyphicon-user"></span>
						Mi Cuenta</a></li>

				<li><a
					href="<%=Config.getInstance().getRoot()%>Logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>

			</ul>
		</div>
	</div>
	</nav>	
</body>
</html>