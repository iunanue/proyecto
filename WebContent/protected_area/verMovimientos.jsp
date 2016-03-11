<%@page import="controller.Config"%>
<%@page import="classes.Movimiento"%>
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
<link rel="stylesheet"
	href="<%=Config.getInstance().getRoot()%>bootstrap-3.3.6-dist/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=Config.getInstance().getRoot()%>bootstrap-3.3.6-dist/css/custom.css" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="<%=Config.getInstance().getRoot()%>bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<title>Ver Movimientos</title>
</head>
<body>

	<jsp:include page="/common/userHeader.jsp" />

	<div class="contentWrapper">
	<div class="tableContainer">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>id</th>
					<th>tipo</th>
					<th>fecha</th>
					<th>id_clase</th>
					<th>username</th>
					<th>id_cuenta</th>
					<th>importe</th>
					<th>descripcion</th>
				</tr>
			</thead>
			<tbody>
				<% 
    				List <Movimiento> listaMovimientos = (List) request.getAttribute("listaMovimientos");
					List <ClaseIngreso> listaClaseIngreso = (List) request.getAttribute("listaClaseIngreso");
					List <ClaseGasto> listaClaseGasto = (List) request.getAttribute("listaClaseGasto");
					List <Cuenta> listaCuentas = (List) request.getAttribute("listaCuentas");
    				Movimiento movimiento;
    				String clase;
    				for(int i=0;i<listaMovimientos.size();i++){
    				movimiento = listaMovimientos.get(i); 
    				if(movimiento.getTipo().equals("ingreso")){
    					clase = listaClaseIngreso.get(movimiento.getId_clase()-1).getDescripcion();
    				}
    				else
    				{
    					clase = listaClaseGasto.get(movimiento.getId_clase()-1).getDescripcion();
    				}
    				%>
				<tr>
					<td><%=movimiento.getId_movimiento()%></td>
					<td><%=movimiento.getTipo()%></td>
					<td><%=movimiento.getFecha()%></td>
					<td><%=clase%></td>
					<td><%=movimiento.getUsername()%></td>
					<td><%=listaCuentas.get(movimiento.getId_cuenta()-1).getDescripcion()%></td>
					<td><%=movimiento.getImporte()%></td>
					<td><%=movimiento.getDescripcion()%></td>
				</tr>
				<%}%>
			</tbody>
		</table>
	</div>
</div>
	<jsp:include page="/common/footer.jsp" />

</body>
</html>