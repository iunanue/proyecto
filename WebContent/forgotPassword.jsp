<%@page import="classes.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recuperación de Contraseña</title>
</head>
<body>

	<jsp:include page="/common/header.jsp" />

	<div class="contentWrapper">
		<div id="login-form" class="login-form">
			<h3>Recuperación de Contraseña</h3>
			<fieldset>
				<form method="POST" action="${pageContext.request.contextPath}/ForgotPassword">
					<p>Se enviará una nueva contraseña a su e-mail.</p>
					<p>Posteriormente podrá cambiarla accediendo a su cuenta.</p>
					<label>Username:<span class="required"> *</span></label> 
					<input class="field" type="text" name="username" placeholder="Username"> 
					<label>E-mail:<span class="required"> *</span></label> 
					<input class="field" type="text" name="mail" placeholder="E-mail"> 
					<input class="button blue" type="submit" value="Recuperar">
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