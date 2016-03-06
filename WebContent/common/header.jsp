<%@page import="controller.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=Config.getInstance().getRoot()%>css/style.css" type="text/css">
<!-- 	href="css/style.css" type="text/css"> -->

<title>Insert title here</title>
</head>
<body>
	<div class="navbar">
		<div>
			<a id="logo" class="left"
				href="<%=Config.getInstance().getRoot()%>index.jsp">Proyecto</a>
		</div>
		<div>
			<div class="menu">
				<ul>
					<li><a
						href="<%=Config.getInstance().getRoot()%>protected_area/index.jsp">Acceder</a></li>
					<li><a href="<%=Config.getInstance().getRoot()%>register.jsp">Registrarse</a></li>
				</ul>
			</div>
			<div class="userbox">
				<ul>
					<li><a class="button"
						href="<%=Config.getInstance().getRoot()%>protected_area/index.jsp">Acceder</a></li>
					<li><a class="button"
						href="<%=Config.getInstance().getRoot()%>register.jsp">Registrarse</a></li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>