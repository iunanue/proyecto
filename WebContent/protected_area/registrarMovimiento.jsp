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
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/css/custom.css" type="text/css">
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<title>Registrar Movimiento</title>
</head>
<body>
<jsp:include page="/common/userHeader.jsp" />
			<div class="contentWrapper">
				<div id="login-form" class="registro-form">
					<h3>Registrar movimiento (Ingreso/Gasto)</h3>
					<h2></h2>
					<fieldset>
						<form method="POST" id="form" action="${pageContext.request.contextPath}/protected_area/addMovimiento">
							<label>Fecha:</label> <input type="date" name="fecha"> <label>Tipo de movimiento:</label>
							<div id="tipo">
								<input type="radio" id="ingreso" name="tipo" value="Ingreso"
									checked onclick="check()">Ingreso<br> <input
									type="radio" id="gasto" name="tipo" value="Gasto"
									onclick="check()">Gasto<br>
							</div>

							<!-- 						<label>Tipo de movimiento:</label> -->
							<!-- 						<div class="dropdown"> -->
							<!-- 							<select id="tipoMovimiento" name="tipoMovimiento" class="dropdown-select" -->
							<!-- 								onclick='test()'> -->
							<!-- 								<option value="ingreso">Ingreso</option> -->
							<!-- 								<option value="gasto">Gasto</option> -->
							<!-- 							</select> -->
							<!-- 						</div> -->

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

							<script
								src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
							<script>
								$(document).ready(function() {
									$("#claseIngreso").show();
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

							<!-- 						<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script> -->
							<!-- 						<script> -->
							<!-- // 							$(document).ready(function() { -->
							<!-- // 								$("#claseIngreso").show(); -->
							<!-- // 								$("#tipoMovimiento").change(function() { -->
							<!-- // 									if ($(this).val() == "ingreso") { -->
							<!-- // 										$("#claseIngreso").show(); -->
							<!-- // 										$("#claseGasto").hide(); -->
							<!-- // 									} else { -->
							<!-- // 										$("#claseIngreso").hide(); -->
							<!-- // 										$("#claseGasto").show(); -->
							<!-- // 									} -->
							<!-- // 								}); -->
							<!-- // 							}); -->
							<!-- 						</script> -->


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
							<label>Importe:</label> 
							<input type="number" name="importe"
								min="0" step="0.01"> 
								<label>Descripción:</label>
							<textarea class="descripcion" name="descripcion"></textarea>

							<input class="button blue" name="update" type="submit"
								value="Registrar"
								onClick="return confirm('¿Desea registrar este movimiento?');">
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
				</div>
			</div>

</body>
</html>