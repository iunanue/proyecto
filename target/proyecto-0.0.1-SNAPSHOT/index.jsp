<%@page import="controller.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=Config.getInstance().getRoot()%>css/style.css" type="text/css">
<title>Index</title>
</head>
<body>
	<jsp:include page="/common/header.jsp" />

	<div class="marginNavbar"></div>
	<div class="contentWrapper">
		<div id="body">
			<h2>Bienvenido, para acceder al sistema pulse el botón inferior</h2>
			<h2><%=request.getAttribute("saludo")%></h2>
			<p>
				<a class="button"
					href="<%=Config.getInstance().getRoot()%>protected_area/index.jsp">Acceder</a>
				<a class="button"
					href="<%=Config.getInstance().getRoot()%>register.jsp">Registrarse</a>
			</p>
		</div>
	</div>
	<div>
		<jsp:include page="/common/footer.jsp" />
	</div>
</body>
</html>
