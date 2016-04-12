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
		float totalIngresosYear = (Float)request.getAttribute("totalIngresosYear");
		float totalGastosYear = (Float)request.getAttribute("totalGastosYear");
		float beneficioYear = (Float)request.getAttribute("beneficioYear");
		
		List <Movimiento> listaIngresosMonth = (List) request.getAttribute("listaIngresosMonth");
		List <Movimiento> listaGastosMonth = (List) request.getAttribute("listaGastosMonth");
		float totalIngresosMonth = (Float)request.getAttribute("totalIngresosMonth");
		float totalGastosMonth = (Float)request.getAttribute("totalGastosMonth");
		float beneficioMonth = (Float)request.getAttribute("beneficioMonth");
		
		
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
	        
	          
	     //Ingresos Year
	     
	      var dataIngresosYear = new google.visualization.DataTable();
	      dataIngresosYear.addColumn('string', 'ingresosYear');
	      dataIngresosYear.addColumn('number');
	      dataIngresosYear.addRows(<%=listaClaseIngreso.size()%>);
	  		
	  		<%
		      for(int i=0;i<listaClaseIngreso.size();i++){ 
			%>
				dataIngresosYear.setCell(<%=i%>, 0, '<%=listaClaseIngreso.get(i).getDescripcion()%>');
				dataIngresosYear.setCell(<%=i%>, 1, <%=listaClaseIngresoYear.get(i)%>);
			<%
			  }
		      
	  		%>
	  		
			var optionsIngresosYear = {
				title : 'Ingresos del año',
					is3D: true,
// 					width:'50%'
			};

			var chartIngresosYear = new google.visualization.PieChart(document
					.getElementById('chartIngresosYear'));

			chartIngresosYear.draw(dataIngresosYear,optionsIngresosYear);
			
			
			
			//Gastos Year

			var dataGastosYear = new google.visualization.DataTable();
			dataGastosYear.addColumn('string', 'gastosYear');
			dataGastosYear.addColumn('number');
			dataGastosYear.addRows(<%=listaClaseGasto.size()%>);
		  		
		  		<%
			      for(int i=0;i<listaClaseGasto.size();i++){ 
				%>
				dataGastosYear.setCell(<%=i%>, 0, '<%=listaClaseGasto.get(i).getDescripcion()%>');
				dataGastosYear.setCell(<%=i%>, 1, <%=listaClaseGastoYear.get(i)%>);
				<%
				  }
			      
		  		%>
		  		
				var optionsGastosYear = {
					title : 'Gastos del año',
						is3D: true,
//	 					width:'50%'
				};

				var chartGastosYear = new google.visualization.PieChart(document
						.getElementById('chartGastosYear'));

				chartGastosYear.draw(dataGastosYear,optionsGastosYear);
			
				
				//Ingresos Month
			     
			      var dataIngresosMonth = new google.visualization.DataTable();
			      dataIngresosMonth.addColumn('string', 'ingresosMonth');
			      dataIngresosMonth.addColumn('number');
			      dataIngresosMonth.addRows(<%=listaClaseIngreso.size()%>);
			  		
			  		<%
				      for(int i=0;i<listaClaseIngreso.size();i++){ 
					%>
					dataIngresosMonth.setCell(<%=i%>, 0, '<%=listaClaseIngreso.get(i).getDescripcion()%>');
					dataIngresosMonth.setCell(<%=i%>, 1, <%=listaClaseIngresoMonth.get(i)%>);
					<%
					  }
				      
			  		%>
			  		
					var optionsIngresosMonth = {
						title : 'Ingresos del mes',
							is3D: true,
//		 					width:'50%'
					};

					var chartIngresosMonth = new google.visualization.PieChart(document
							.getElementById('chartIngresosMonth'));

					chartIngresosMonth.draw(dataIngresosMonth,optionsIngresosMonth);
			
					
					//Gastos Month

					var dataGastosMonth = new google.visualization.DataTable();
					dataGastosMonth.addColumn('string', 'gastosMonth');
					dataGastosMonth.addColumn('number');
					dataGastosMonth.addRows(<%=listaClaseGasto.size()%>);
				  		
				  		<%
					      for(int i=0;i<listaClaseGasto.size();i++){ 
						%>
						dataGastosMonth.setCell(<%=i%>, 0, '<%=listaClaseGasto.get(i).getDescripcion()%>');
						dataGastosMonth.setCell(<%=i%>, 1, <%=listaClaseGastoMonth.get(i)%>);
						<%
						  }
					      
				  		%>
				  		
						var optionsGastosMonth = {
							title : 'Gastos del mes',
								is3D: true,
//			 					width:'50%'
						};

						var chartGastosMonth = new google.visualization.PieChart(document
								.getElementById('chartGastosMonth'));

						chartGastosMonth.draw(dataGastosMonth,optionsGastosMonth);
			
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


	
		<div id="chartIngresosYear"></div>
		<div id="chartGastosYear"></div>
		<div>
			<span class="label label-pill label-success"><%=totalIngresosYear%></span>
			<span class="label label-pill label-danger"><%=totalGastosYear%></span>
			<span class="label label-pill label-info"><%=beneficioYear%></span>
		</div>
		
		
		<div id="chartIngresosMonth"></div>
		<div id="chartGastosMonth"></div>
		<div>
			<span class="label label-pill label-success"><%=totalIngresosMonth%></span>
			<span class="label label-pill label-danger"><%=totalGastosMonth%></span>
			<span class="label label-pill label-info"><%=beneficioMonth%></span>
		</div>
		
</body>
</html>