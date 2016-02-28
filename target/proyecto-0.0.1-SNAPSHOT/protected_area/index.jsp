<%@page import="controller.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=Config.getInstance().getRoot()%>css/style.css" type="text/css">
<title>Bienvenido</title>
</head>
<body>
	<jsp:include page="/common/userHeader.jsp" />
	<div class="marginNavbar"></div>
	<div class="contentWrapper">
		<div id="body">
			<p>
				Bienvenido:
				<%=request.getUserPrincipal().getName()%></p>
			<a class="button" href="<%=Config.getInstance().getRoot()%>Logout">Logout</a>
		</div>
	</div>

	<div>
		<jsp:include page="/common/footer.jsp" />
	</div>
</body>
</html>