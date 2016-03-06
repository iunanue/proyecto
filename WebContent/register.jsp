<%@page import="controller.Config"%>
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
					<form method="POST"
						action="<%=Config.getInstance().getRoot()%>Register">
						<input class="field" type="text" name="username"
							placeholder="Usuario"> <input class="field" type="text"
							name="mail" placeholder="E-mail"> <input class="field"
							type="password" name="password" placeholder="Contraseña">
						<input class="field" type="password" name="password2"
							placeholder="Repetir contraseña"> 
							<input
							class="button blue" type="submit" value="Registrarse">
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
</body>
</html>