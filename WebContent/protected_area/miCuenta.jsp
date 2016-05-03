<%@page import="controller.Config"%>
<%@page import="model.classes.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/css/bootstrap.min.css" type="text/css">
<script src="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/css/custom.css" type="text/css">
<title>Mi Cuenta</title>
</head>
<body>

<jsp:include page="/common/userHeader.jsp" />

		<div class="contentWrapper">
		<div id="login-form"class="miCuenta-form">
				<h3>Mi Cuenta</h3>
				<fieldset>
				<% Usuario usuario = (Usuario) request.getAttribute("usuario");
				%>
					<form method="POST" action="${pageContext.request.contextPath}/protected_area/updateDeleteUsuario">
						<label>Username:<span class="required"> *</span></label><input class="field" type="text" name="username" placeholder="Usuario" value="<%=usuario.getUsername()%>" readonly> 
						<label>E-mail:<span class="required"> *</span></label><input class="field" type="text" name="mail" placeholder="E-mail" value="<%=usuario.getMail()%>"> 
						<label>Contraseña:<span class="required"> *</span></label><input class="field" type="password" name="password" placeholder="Contraseña" value="<%=usuario.getPassword()%>">
						<label>Repetir contraseña:<span class="required"> *</span></label><input class="field" type="password" name="password2" placeholder="Repetir contraseña" value="<%=usuario.getPassword()%>">
						<input class="button blue" name="delete" type="submit" value="Darse de baja" onClick="return confirm('¿Desea darse de baja?\nEsta operación NO podra deshacerse');">
						<input class="button blue" name="update" type="submit" value="Actualizar mi cuenta" onClick="return confirm('¿Desea actualizar su información personal?');">
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
	

</body>
</html>