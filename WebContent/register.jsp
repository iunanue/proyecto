<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrarse</title>
</head>
<body>
	<jsp:include page="/common/header.jsp" />
	
	<div class="contentWrapper">
		<div id="login-form" class="login-form">
			<h3>Registro</h3>
			<fieldset>
				<form method="POST" action="${pageContext.request.contextPath}/register">
					<label>Username:<span class="required"> *</span></label> 
					<input class="field" type="text" name="username" placeholder="Username"> 
					<label>E-mail:<span class="required"> *</span></label> 
					<input class="field" type="text" name="mail" placeholder="E-mail">
					<label>Contraseña:<span class="required"> *</span></label>  
					<input class="field" type="password" name="password" placeholder="Contraseña">
					<label>Repetir contraseña:<span class="required"> *</span></label> 
					<input class="field" type="password" name="password2" placeholder="Repetir contraseña"> 
					<input class="button blue" type="submit" value="Registrarse">
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
	</div>
	<div>
	</div>
</body>
</html>