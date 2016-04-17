<%@page import="controller.Config"%>
<%@page import="classes.Usuario"%>
<%@page import="classes.ClaseIngreso"%>
<%@page import="classes.ClaseGasto"%>
<%@page import="classes.Cuenta"%>
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
<title>Análisis Personalizado</title>
</head>
<body>
<script>
	$(document).ready(function() {				
		
	});
	function check() {
		if ((document.getElementById("fecha_si").checked) == true) {
			$("#fechas").show();
		} else {
			$("#fechas").hide();
		}
		if ((document.getElementById("usuario_si").checked) == true) {
			$("#usuario").show();
		} else {
			$("#usuario").hide();
		}
		if ((document.getElementById("cuenta_si").checked) == true) {
			$("#cuenta").show();
		} else {
			$("#cuenta").hide();
		}
	}
	function submitForm(){
		 $("#form").resetForm();
	}
</script>

<jsp:include page="/common/userHeader.jsp" />

			<div class="contentWrapper">
				<div id="login-form" class="registro-form">
					<h2 class="paddingIzquierda">Generar Análisis Personalizado</h2>
					<h2></h2>
					<fieldset>
						<form method="POST" id="form" action="${pageContext.request.contextPath}/protected_area/analisisPersonalizado">									
							<h3>Filtro de fecha:</h3>
							<div id="filtroFecha">
								<input type="radio" id="fecha_si"  name="filtroFecha" value="fecha_si" onclick="check()">Sí<br> 
								<input type="radio" id="fecha_no"  name="filtroFecha" value="fecha_no" checked onclick="check()">No<br>
							</div>
							<div id="fechas" style="display: none">
								<label>Fecha Inicio:</label> <input type="date" name="fecha_inicio">
								<label>Fecha Fin:</label> <input type="date" name="fecha_fin"> 
							</div>
							<h3>Filtro de usuario asociado al Movimiento:</h3>
							<div id="filtroUsuario">
								<input type="radio" id="usuario_si"  name="filtroUsuario" value="usuario_si" onclick="check()">Sí<br> 
								<input type="radio" id="usuario_no"  name="filtroUsuario" value="usuario_no" checked onclick="check()">No<br>
							</div>
							<div id="usuario" style="display: none">
								<label>Usuario asociado al movimiento:</label>
								<div class="dropdown">
									<select name="username" class="dropdown-select">
										<%
										List<Usuario> listaUsuarios = (List) request.getAttribute("listaUsuarios");
											for (Usuario usuario : listaUsuarios) {
										%><option value=<%=usuario.getUsername()%>><%=usuario.getUsername()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							
							<h3>Filtro de cuenta asociada al Movimiento:</h3>
							<div id="filtroCuenta">
								<input type="radio" id="cuenta_si"  name="filtroCuenta" value="cuenta_si" onclick="check()">Sí<br> 
								<input type="radio" id="cuenta_no"  name="filtroCuenta" value="cuenta_no" checked onclick="check()">No<br>
							</div>
							<div id="cuenta" style="display: none">
								<label>Cuenta asociada al movimiento:</label>
								<div class="dropdown">
									<select name="cuenta" class="dropdown-select">
										<%
										List<Cuenta> listaCuentas = (List) request.getAttribute("listaCuentas");
											for (Cuenta cuenta : listaCuentas) {
										%><option value=<%=cuenta.getId_cuenta()%>><%=cuenta.getDescripcion()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>						
							<input class="button blue" name="generate" type="submit"
								value="Generar Análisis"
								onClick="submitForm()">
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