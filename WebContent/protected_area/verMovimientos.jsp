<%@page import="controller.Config"%>
<%@page import="classes.Movimiento"%>
<%@page import="classes.ClaseIngreso"%>
<%@page import="classes.ClaseGasto"%>
<%@page import="classes.Cuenta"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
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
<title>Ver Movimientos</title>
</head>
<body>

	<jsp:include page="/common/userHeader.jsp" />

	<div class="contentWrapper">
	<div class="tableContainer">
<%-- 	<form method="POST" id="form" action="<%=Config.getInstance().getRoot()%>/protected_area/verUpdateMovimiento"> --%>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>id</th>
					<th>Tipo</th>
					<th>Fecha</th>
					<th>Clase</th>
					<th>Usuario</th>
					<th>Cuenta</th>
					<th>Importe</th>
					<th>Descripcion</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<% 
    				List <Movimiento> listaMovimientos = (List) request.getAttribute("listaMovimientos");
					List <ClaseIngreso> listaClaseIngreso = (List) request.getAttribute("listaClaseIngreso");
					List <ClaseGasto> listaClaseGasto = (List) request.getAttribute("listaClaseGasto");
					List <Cuenta> listaCuentas = (List) request.getAttribute("listaCuentas");
    				Movimiento movimiento;
    				String clase = null;
    				for(int i=0;i<listaMovimientos.size();i++){
    				movimiento = listaMovimientos.get(i); 
    				if(movimiento.getTipo().equals("Ingreso")){
    					clase = listaClaseIngreso.get(movimiento.getId_clase()-1).getDescripcion();
    				}
    				if(movimiento.getTipo().equals("Gasto")){
    					clase = listaClaseGasto.get(movimiento.getId_clase()-1).getDescripcion();
    				}
    				%>
				<tr>
					<td><%=movimiento.getId_movimiento()%></td>
					<td><%=movimiento.getTipo()%></td>
					<%
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String string  = dateFormat.format(movimiento.getFecha());
					%>
					<td><%=string%></td>
<%-- 					<td><%=movimiento.getFecha()%></td> --%>
					<td><%=clase%></td>
					<td><%=movimiento.getUsername()%></td>
					<td><%=listaCuentas.get(movimiento.getId_cuenta()-1).getDescripcion()%></td>
					<td><%=movimiento.getImporte()%></td>
					<td class ="tdDescripcion"><%=movimiento.getDescripcion()%></td>
					<td>
						<form method="POST" id="form" action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteMovimiento">
							<input type="hidden" name="id_movimiento" value="<%=movimiento.getId_movimiento()%>">
							<button type="submit" class="btn btn-default" name="update">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</button>			
							<button type="submit" class="btn btn-default" name="delete" onClick="return confirm('¿Desea eliminar este movimiento?');">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
							</button>		
						</form>
					</td>
				</tr>
				<%}%>
			</tbody>
		</table>
		<form method="POST" action="<%=Config.getInstance().getRoot()%>/protected_area/exportExcel">
<%-- 							<input type="hidden" name="listaMovimientos" value="<%=listaMovimientos%>"> --%>
							<%request.getSession().setAttribute("listaMovimientos", listaMovimientos); %>
							<button type="submit" class="btn btn-default" name="exportar">
								<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> Descargar Excel
							</button>			
						</form>
<!-- 		</form> -->
	</div>
	
</div>

</body>
</html>