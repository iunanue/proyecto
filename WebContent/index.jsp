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
	<div id="container">
		<div id="header">
			<jsp:include page="/common/userHeader.jsp" />
		</div>
		<div id="body">
			<div class="contentWrapper">
				
					<h2>Bienvenido, para acceder al sistema pulse el botón
						inferior</h2>
					<h2><%=request.getAttribute("saludo")%></h2>	

			</div>
			<div id="footer">
			<jsp:include page="/common/footer.jsp" />
		</div>
		</div>
		
	</div>
</body>
</html>
