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
<title>Registrar Movimiento</title>
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
		if ((document.getElementById("tipo_si").checked) == true) {
			$("#tipo").show();
		} else {
			$("#tipo").hide();
		}
		if ((document.getElementById("clase_si").checked) == true) {
			if ((document.getElementById("ingreso").checked) == true) {
				$("#claseIngreso").show();
				$("#claseGasto").hide();
			} else {
				$("#claseIngreso").hide();
				$("#claseGasto").show();
			}
		} else {
			$("#claseIngreso").hide();
			$("#claseGasto").hide();
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
</script>
<jsp:include page="/common/userHeader.jsp" />
			
			<div class="contentWrapper">
				<div id="login-form" class="registro-form">
					<h1>GENERAR CONSULTA SOBRE MOVIMIENTOS</h1>
					<h2></h2>
					<fieldset>
						<form method="POST" id="form" action="${pageContext.request.contextPath}/protected_area/generarConsultaMovimientos">									
							<h3>Filtro de fecha:</h3>
							<div id="filtroFecha">
								<input type="radio" id="fecha_si"  name="filtroFecha" value="fecha_si" onclick="check()">Sí<br> 
								<input type="radio" id="fecha_no"  name="filtroFecha" value="fecha_no" checked onclick="check()">No<br>
							</div>
							<div id="fechas" style="display: none">
								<label>Fecha Inicio:</label> <input type="date" name="fecha_inicio">
								<label>Fecha Fin:</label> <input type="date" name="fecha_fin"> 
							</div>
							
							
							<h3>Filtro de tipo de movimiento:</h3>
							<div id="filtroTipo">
								<input type="radio" id="tipo_si"  name="filtroTipo" value="tipo_si" onclick="check()">Sí<br> 
								<input type="radio" id="tipo_no"  name="filtroTipo" value="tipo_no" checked onclick="check()">No<br>
							</div>
							<div id="tipo" style="display: none">
							<label>Tipo de movimiento:</label>
								<input type="radio" id="ingreso" name="tipo" value="Ingreso" onclick="check()">Ingreso<br> 
								<input type="radio" id="gasto" name="tipo" value="Gasto" onclick="check()">Gasto<br>
							</div>
							

							<h3>Filtro de clase de movimiento:</h3>
							<div id="filtroClase">
								<input type="radio" id="clase_si"  name="filtroClase" value="clase_si" onclick="check()">Sí<br> 
								<input type="radio" id="clase_no"  name="filtroClase" value="clase_no" checked onclick="check()">No<br>
							</div>
							
							<div id="claseIngreso" style="display: none">
								<label>Clase de Ingreso:</label>
								<div class="dropdown">
									<select name="claseIngreso" class="dropdown-select">
										<%
										List<ClaseIngreso> listaClaseIngreso = (List) request.getAttribute("listaClaseIngreso");
											for (ClaseIngreso claseingreso : listaClaseIngreso) {
										%><option value=<%=claseingreso.getId_claseIngreso()%>><%=claseingreso.getDescripcion()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>

							<div id="claseGasto" style="display: none">
								<label>Clase de Gasto:</label>
								<div class="dropdown">
									<select name="claseGasto" class="dropdown-select">
										<%
										List<ClaseGasto> listaClaseGasto = (List) request.getAttribute("listaClaseGasto");
											for (ClaseGasto clasegasto : listaClaseGasto) {
										%><option value=<%=clasegasto.getId_claseGasto()%>><%=clasegasto.getDescripcion()%></option>
										<%
											}
										%>
									</select>
								</div>

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
								value="Generar consulta"
								onClick="return confirm('¿Desea registrar este movimiento?');">
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