<%@page import="controller.Config"%>
<%@page import="classes.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=Config.getInstance().getRoot()%>css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/common/header.jsp" />

	<div class="marginNavbar"></div>
	<div class="contentWrapper">

		<div class="container">
			<div id="login-form">
				<fieldset>
				<% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
					<form method="POST" action="<%=Config.getInstance().getRoot()%>Micuenta">
						<input class="field" type="text" name="username" placeholder="Usuario" value=" <%=usuario.getUsername()%>" readonly> 
						<input class="field" type="text" name="mail" placeholder="E-mail" value=" <%=usuario.getMail()%>"> 
						<input class="field" type="password" name="password" placeholder="Contraseña" value=" <%=usuario.getPassword()%>">
						<input class="field" type="password" name="password2" placeholder="Repetir contraseña" value=" <%=usuario.getPassword()%>">
						<input class="button blue" type="submit" value="Actualizar">
						<footer class="clearfix"> </footer>
					</form>
				</fieldset>
			</div>
		</div>
		<%
			if (request.getSession().getAttribute("mensaje") != null) {
		%>
		<div class="errormsg">
			<p class="errorfont"><%=request.getSession().getAttribute("mensaje")%></p>
		</div>
		<%
			}
			request.getSession().setAttribute("mensaje", null);
		%>
	</div>
	<div>
		<jsp:include page="/common/footer.jsp" />
	</div>
	<!-- 	<div class="marginNavbar"></div> -->
	<!-- 	<div class="contentWrapper"> -->

	<!-- 		<div class="container"> -->
	<!-- 			<h2>Mis Datos</h2> -->
	<%-- 			<% --%>
<!-- 	// Usuario usuario = (Usuario) request.getAttribute("usuario"); -->
	<%-- 			%> --%>
	<%-- 			<p><%=usuario.getUsername()%></p> --%>
	<%-- 			<p><%=usuario.getMail()%></p> --%>
	<%-- 			<p><%=usuario.getPassword()%></p> --%>




	<!-- 		</div> -->
	<!-- 		<div> -->
	<%-- 			<jsp:include page="/common/footer.jsp" /> --%>
	<!-- 		</div> -->
	<!-- 	</div> -->
</body>
</html>