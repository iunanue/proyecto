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
<jsp:include page="/common/userHeader.jsp" />
<div class="marginNavbar"></div>
	<div class="contentWrapper">

		<div class="container">
			<div id="login-form" class="registro-form">
			<h3>Nuevo registro (Ingreso/Gasto)</h3>
			<h2></h2>
				<fieldset>
					<form method="POST" action="<%=Config.getInstance().getRoot()%>/protected_area/nuevoRegistro">
						<label>Usuario:</label>
						<input class="field" type="text" name="username" placeholder="Usuario" value="<%=request.getAttribute("username")%>" readonly> 
						<label>Tipo de registro:</label>
						<div class="dropdown">
						<select name="id_tiporegistro" class="dropdown-select">
						  <option value="ingreso">Ingreso</option>
						  <option value="gasto">Gasto</option>
						</select>
						</div>
						<label>Entidad asociada al registro:</label>
						<div class="dropdown">
						<select name="id_entidad" class="dropdown-select">
						  <option value="volvo">a</option>
						  <option value="saab">b</option>
						  <option value="mercedes">c</option>
						  <option value="audi">d</option>
						</select>
						</div>
						<label>Fecha:</label>
						<input type="date" name="fecha" >
						<label>Descripción:</label>
						<input class="descripcion" type="text" name="descripcion"> 
						<input class="button blue" name="delete" type="submit" value="Darse de baja" onClick="return confirm('¿Desea darse de baja?\nEsta operación NO podra deshacerse');">
						<input class="button blue" name="update" type="submit" value="Actualizar" onClick="return confirm('¿Desea actualizar su información personal?');">
						<footer class="clearfix"> </footer>
					</form>
				</fieldset>
			</div>
<%-- 			<a href="<%=Config.getInstance().getRoot()%>protected_area/updateUsuario.jsp">Acceder</a> --%>
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