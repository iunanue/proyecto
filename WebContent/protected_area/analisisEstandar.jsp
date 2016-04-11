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
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/css/bootstrap.min.css" type="text/css">
<script src="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/css/custom.css" type="text/css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<title>Análisis Estándar</title>
</head>
<body>
	<%
		List <Movimiento> listaMovimientosYear = (List) request.getAttribute("listaMovimientosYear");
		List <Movimiento> listaMovimientosMonth = (List) request.getAttribute("listaMovimientosMonth");
		
		List <Movimiento> listaIngresosYear = (List) request.getAttribute("listaIngresosYear");
		List <Movimiento> listaGastosYear = (List) request.getAttribute("listaGastosYear");
		float totalIngresosYear = (float)request.getAttribute("totalIngresosYear");
		float totalGastosYear = (float)request.getAttribute("totalGastosYear");
		float beneficioYear = (float)request.getAttribute("beneficioYear");
		
		List <Movimiento> listaIngresosMonth = (List) request.getAttribute("listaIngresosMonth");
		List <Movimiento> listaGastosMonth = (List) request.getAttribute("listaGastosMonth");
		float totalIngresosMonth = (float)request.getAttribute("totalIngresosMonth");
		float totalGastosMonth = (float)request.getAttribute("totalGastosMonth");
		float beneficioMonth = (float)request.getAttribute("beneficioMonth");
		
		
		List <ClaseIngreso> listaClaseIngreso = (List) request.getAttribute("listaClaseIngreso");
		List <ClaseGasto> listaClaseGasto = (List) request.getAttribute("listaClaseGasto");
		
		List <Float> listaClaseIngresoYear = (List) request.getAttribute("listaClaseIngresoYear");
		List <Float> listaClaseGastoYear = (List) request.getAttribute("listaClaseGastoYear");
		List <Float> listaClaseIngresoMonth = (List) request.getAttribute("listaClaseIngresoMonth");
		List <Float> listaClaseGastoMonth = (List) request.getAttribute("listaClaseGastoMonth");
		
		
		List <Cuenta> listaCuentas = (List) request.getAttribute("listaCuentas");
		
	%>

	<script type="text/javascript">
	  google.charts.load('current', {packages: ['corechart']});
	  google.charts.setOnLoadCallback(drawChart);
	  
	  function drawChart() {
	      // Define the chart to be drawn.
	        
	          
	      
	     
	      var ingresosMonth = new google.visualization.DataTable();
	      ingresosMonth.addColumn('string', 'ingresosMonth');
	      ingresosMonth.addColumn('number');
	      ingresosMonth.addRows(<%=listaCuentas.size()%>);
	  		
	  		<%
		      for(int i=0;i<listaCuentas.size();i++){
		      
			      if(listaCuentas.get(i).getSaldo()>=0){
			      %>
			  		data2.setCell(<%=i%>, 0, '<%=listaCuentas.get(i).getDescripcion()%>');
			      data2.setCell(<%=i%>, 1, <%=listaCuentas.get(i).getSaldo()%>);
			      <%
			      }
		      }
	  		%>
	  		
		      

			var options2 = {
				title : 'Composición del total de cuentas en positivo',
					is3D: true,
// 					width:'50%'
			};

			var chart2 = new google.visualization.PieChart(document
					.getElementById('piechart'));

		      chart2.draw(data2,options2);

		}
	</script>
	
	
	
	<jsp:include page="/common/userHeader.jsp" />
	
	<div class="contentWrapper">
		<h2 class ="titulo2">Cuentas</h2>
		<h4 class="titulo4">Listado de Cuentas</h4>
		
		<div class="tableContainer">
		<table class="table table-hover tableCuentas">
			<thead>
				<tr>
					<th>id</th>
					<th>Descripción</th>
					<th>Saldo</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<%
			for(int i=0;i<listaCuentas.size();i++){
				Cuenta cuenta = listaCuentas.get(i);%>
				<tr> 
					<td><%=cuenta.getId_cuenta()%></td>
					<td><%=cuenta.getDescripcion()%></td>
					<%
					if(cuenta.getSaldo() > 0){
						%><td class="ingreso">+<%=cuenta.getSaldo()%></td>
					<% }
					if(cuenta.getSaldo() < 0){
						%><td class="gasto"><%=cuenta.getSaldo()%></td>
					<% }
					if(cuenta.getSaldo() == 0){
						%><td class="cero"><%=cuenta.getSaldo()%></td>
					<% }
					%>
					<td>
						<form method="POST" id="form" action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteCuenta">
							<input type="hidden" name="id_cuenta" value="<%=cuenta.getId_cuenta()%>">
							<button type="submit" class="btn btn-default" name="update">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</button>			
							<button type="submit" class="btn btn-default" name="delete" onClick="return confirm('¿Desea eliminar esta cuenta y todos los movimientos asociados a ella?');">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
							</button>		
						</form>
					</td>
				</tr>
			<%}		
			%>
			</tbody>
			</table>
			</div>


		<div class="row">
			<div class="col-sm-6">
				<h4 class="titulo4">Saldo de las cuentas</h4>
				<div id="visualization"></div>
			</div>
			<div class="col-sm-6">
				<h4 class="titulo4">Composición</h4>
				<div id="piechart" ></div>
			</div>

		</div>
		
</body>
</html>