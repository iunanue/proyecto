<%@page import="model.classes.Movimiento"%>
<%@page import="model.classes.Usuario"%>
<%@page import="model.classes.ClaseIngreso"%>
<%@page import="model.classes.ClaseGasto"%>
<%@page import="model.classes.Cuenta"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
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
<title>Modificar Movimiento</title>
</head>
<body>
	<script>
		<%
		Movimiento movimiento = (Movimiento) request.getAttribute("movimiento");
		System.out.println("ID llega:" + movimiento.getId_movimiento());
		System.out.println("fecha llega:" + movimiento.getFecha());
		%>
		$(document).ready(function() {
			var tipo="<%=movimiento.getTipo()%>";
			<%
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date  = dateFormat.format(movimiento.getFecha());
			%>
			document.getElementById("date").value = "<%=date %>";
			
	
			
			if(tipo == 1){
				$("#claseIngreso").show();
				$("#claseGasto").hide();
				$("#ingreso").prop("checked", true);
				$("#gasto").prop("checked", false);
				$('#dropdownIngreso').prop('selectedIndex', <%=movimiento.getId_clase()-1%>);
		
			}
			if(tipo == 2){
				$("#claseIngreso").hide();
				$("#claseGasto").show();
				$("#ingreso").prop("checked", false);
				$("#gasto").prop("checked", true);
				$('#dropdownGasto').prop('selectedIndex', <%=movimiento.getId_clase()-1%>); 
			}
			$('#username').val("<%=movimiento.getUsername()%>");
			$('#cuenta').prop('selectedIndex', <%=movimiento.getId_cuenta()-1%>);
			$('#importe').val("<%=movimiento.getImporte()%>");
			$('#descripcion').val("<%=movimiento.getDescripcion()%>");
		});
																			
		function check() {
			if ((document.getElementById("ingreso").checked) == true) {
				$("#claseIngreso").show();
				$("#claseGasto").hide();
			} else {
				$("#claseIngreso").hide();
				$("#claseGasto").show();
			}
		}
	</script>
		
	<jsp:include page="/common/userHeader.jsp" />
	<div class="contentWrapper">
				<div id="login-form" class="registro-form">
					<h3>Modificar movimiento (Ingreso/Gasto)</h3>
					<h2></h2>
					<fieldset>
						<form method="POST" id="form" action="${pageContext.request.contextPath}/protected_area/updateMovimiento">
							<label>Fecha:</label> <input id="date" type="date" name="fecha"> <label>Tipo de movimiento:</label>
							<div id="tipo">
								<input type="radio" id="ingreso" name="id_tipoMovimiento" value="1" checked onclick="check()">Ingreso<br> 
								<input type="radio" id="gasto" name="id_tipoMovimiento" value="2" onclick="check()">Gasto<br>
							</div>
							<div id="claseIngreso" style="display: none">
								<label>Clase de Ingreso:</label>
								<div class="dropdown">
									<select id="dropdownIngreso" name="claseIngreso" class="dropdown-select">
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
									<select id="dropdownGasto" name="claseGasto" class="dropdown-select">
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

							
							
							<label>Usuario asociado al movimiento:</label>
							<div class="dropdown">
								<select id="username" name="username" class="dropdown-select">
									<%
									List<Usuario> listaUsuarios = (List) request.getAttribute("listaUsuarios");
										for (Usuario usuario : listaUsuarios) {
									%><option value=<%=usuario.getUsername()%>><%=usuario.getUsername()%></option>
									<%
										}
									%>
								</select>
							</div>
							<label>Cuenta asociada al movimiento:</label>
							<div class="dropdown">
								<select id="cuenta" name="cuenta" class="dropdown-select">
									<%
									List<Cuenta> listaCuentas = (List) request.getAttribute("listaCuentas");
										for (Cuenta cuenta : listaCuentas) {
									%><option value=<%=cuenta.getId_cuenta()%>><%=cuenta.getDescripcion()%></option>
									<%
										}
									%>
								</select>
							</div>
							<label>Importe:</label> 
							<input id="importe" type="number" name="importe"
								min="0" step="0.01"> 
								<label>Descripción:</label>
							<textarea id="descripcion" class="descripcion" name="descripcion"></textarea>
							<input type="hidden" name="id_movimiento" value="<%=movimiento.getId_movimiento()%>" />
							<input class="button blue" name="update" type="submit"
								value="Modificar"
								onClick="return confirm('¿Desea modificar este movimiento?');">
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
				<!-- 	<div class="marginEnd"></div> -->
				</div>
	</div>
</body>
</html>