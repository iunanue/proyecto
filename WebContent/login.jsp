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
<title>Login</title>
</head>
<body>
<jsp:include page="/common/header.jsp" />
	<div class="contentWrapper">
			<div id="login-form" class="login-form">
			<h3>Login</h3>
				<fieldset>
					<form method="POST"
						action="<%=Config.getInstance().getRoot()%>j_security_check">
						<input class="field" type="text" name="j_username"
							placeholder="User"> <input class="field" type="password"
							name="j_password" placeholder="Password"> <input
							class="button blue" type="submit" value="Login">
						<div class="footer">

						<p>
							<span class="info">?</span><a
								href="<%=Config.getInstance().getRoot()%>forgotPassword.jsp">Olvidé
								la contraseña</a>
						</p>
						</div>
					</form>
				</fieldset>
				<%
			if (((String) request.getAttribute("javax.servlet.forward.request_uri"))
					.indexOf("j_security_check") != -1) {
		%>
		<div class="errormsg">
			<p class="errorfont">La combinación de Usuario y Contraseña no es
				correcta. Inténtelo de nuevo.</p>
		</div>
		<%
			}
		%>
			</div>
	</div>
<jsp:include page="/common/footer.jsp" />
</body>
</html>