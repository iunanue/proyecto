<%@page import="controller.Config"%>
<%@page import="model.classes.Cuenta"%>
<%@ page import="java.util.List"%>

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
<title>Modificar Cuenta</title>
</head>
<body>
	<script>
		<%
		Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");
		%>
		$(document).ready(function() {
			$('#id_cuenta').val("<%=cuenta.getId_cuenta()%>");
			$('#descripcion').val("<%=cuenta.getDescripcion()%>");
			$('#saldo').val("<%=cuenta.getSaldo()%>");
		});
																			
	</script>
		
	<jsp:include page="/common/userHeader.jsp" />
	<div class="contentWrapper">
				<div id="login-form" class="registro-form">
					<h3>Modificar cuenta</h3>
					<h2></h2>
					<fieldset>
						<form method="POST" id="form" action="${pageContext.request.contextPath}/protected_area/updateCuenta">
							
							<input id="id_cuenta" class="field" type="text" name="id_cuenta" placeholder="id_cuenta" value="<%=cuenta.getId_cuenta()%>" readonly> 
							<label>Descripción:</label>
							<textarea id="descripcion" class="descripcion" name="descripcion"></textarea>
							<label>Saldo:</label> 
							<input id="saldo" type="number" name="saldo"
								 step="0.01"> 
								
<%-- 							<input type="hidden" name="id_movimiento" value="<%=movimiento.getId_movimiento()%>" /> --%>
							<input class="button blue" name="update" type="submit"
								value="Actualizar"
								onClick="return confirm('¿Desea actualizar esta cuenta?');">
						</form>
					</fieldset>
					<%-- 			<a href="<%=Config.getInstance().getRoot()%>protected_area/updateUsuario.jsp">Acceder</a> --%>

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
				<!-- 	<div class="marginEnd"></div> -->
				</div>
	</div>
</body>
</html>>