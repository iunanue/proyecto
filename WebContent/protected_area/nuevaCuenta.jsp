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
<title>Añadir Cuenta</title>
</head>
<body>
	<jsp:include page="/common/header.jsp" />
	
	<div class="contentWrapper">
		<div id="login-form" class="registro-form">
			<h3>Nueva Cuenta</h3>
			<fieldset>
				<form method="POST" action="${pageContext.request.contextPath}/protected_area/addCuenta">
					<label>Saldo:<span class="required"> *</span></label> 
					<input type="number" name="saldo" step="0.01"> 
					<label>Descripción<span class="required"> *</span></label> 
					<input class="field" type="text" name="descripcion" placeholder="Descripción">
					<input class="button blue" type="submit" value="Añadir">
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