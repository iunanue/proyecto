<%@page import="controller.Config"%>
<%@page import="classes.Usuario"%>
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
<title>Mi Cuenta</title>
</head>
<body>

<jsp:include page="/common/userHeader.jsp" />

		<div class="contentWrapper">
		<div id="login-form"class="miCuenta-form">
				<h3>Mi Cuenta</h3>
				<fieldset>
				<% Usuario usuario = (Usuario) request.getAttribute("usuario");
				Config.getInstance().setUsername(usuario.getUsername());
				%>
					<form method="POST" action="<%=Config.getInstance().getRoot()%>/protected_area/updateUsuario">
						<input class="field" type="text" name="username" placeholder="Usuario" value="<%=usuario.getUsername()%>" readonly> 
						<input class="field" type="text" name="mail" placeholder="E-mail" value="<%=usuario.getMail()%>"> 
						<input class="field" type="password" name="password" placeholder="Contraseña" value="<%=usuario.getPassword()%>">
						<input class="field" type="password" name="password2" placeholder="Repetir contraseña" value="<%=usuario.getPassword()%>">
						<input class="button blue" name="delete" type="submit" value="Darse de baja" onClick="return confirm('¿Desea darse de baja?\nEsta operación NO podra deshacerse');">
						<input class="button blue" name="update" type="submit" value="Actualizar" onClick="return confirm('¿Desea actualizar su información personal?');">
					</form>
				</fieldset>
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
	</div>
	
<jsp:include page="/common/footer.jsp" />

</body>
</html>