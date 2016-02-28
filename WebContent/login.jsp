<%@page import="controller.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=Config.getInstance().getRoot()%>css/style.css" type="text/css">
<title>Login</title>
</head>
<body>
	<jsp:include page="/common/header.jsp" />

	<div class="marginNavbar"></div>
	<div class="contentWrapper">
		<div class="container">
			<div id="login-form">
				<fieldset>
					<form method="POST"
						action="<%=Config.getInstance().getRoot()%>j_security_check">
						<input class="field" type="text" name="j_username"
							placeholder="User"> <input class="field" type="password"
							name="j_password" placeholder="Password"> <input
							class="button blue" type="submit" value="Login">
						<footer class="clearfix">

						<p>
							<span class="info">?</span><a
								href="<%=Config.getInstance().getRoot()%>forgotPassword.jsp">Olvidé
								la contraseña</a>
						</p>
						</footer>
					</form>
				</fieldset>
			</div>
		</div>
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
	<div>
		<jsp:include page="/common/footer.jsp" />
	</div>
</body>
</html>