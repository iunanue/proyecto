<%@page import="model.classes.Movimiento"%>
<%@page import="model.classes.ClaseIngreso"%>
<%@page import="model.classes.ClaseGasto"%>
<%@page import="model.classes.Cuenta"%>
<%@page import="model.classes.TipoMovimiento"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/css/bootstrap.min.css"
	type="text/css">
<script
	src="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/css/custom.css"
	type="text/css">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<title>Análisis Personalizado</title>
</head>

<body>
	<%
		List<Movimiento> listaMovimientosYear = (List) request.getAttribute("listaMovimientosYear");
		List<Movimiento> listaMovimientosMonth = (List) request.getAttribute("listaMovimientosMonth");

		List<String> listaMeses = (List) request.getAttribute("listaMeses");
		List<Float> listaMesesIngresos = (List) request.getAttribute("listaMesesIngresos");
		List<Float> listaMesesGastos = (List) request.getAttribute("listaMesesGastos");
		List<Float> listaMesesBeneficios = (List) request.getAttribute("listaMesesBeneficios");
		
		
		List<Movimiento> listaIngresosYear = (List) request.getAttribute("listaIngresosYear");
		List<Movimiento> listaGastosYear = (List) request.getAttribute("listaGastosYear");
		float totalIngresosYear = (Float) request.getAttribute("totalIngresosYear");
		float totalGastosYear = (Float) request.getAttribute("totalGastosYear");
		float beneficioYear = (Float) request.getAttribute("beneficioYear");

		List<Movimiento> listaIngresosMonth = (List) request.getAttribute("listaIngresosMonth");
		List<Movimiento> listaGastosMonth = (List) request.getAttribute("listaGastosMonth");
		float totalIngresosMonth = (Float) request.getAttribute("totalIngresosMonth");
		float totalGastosMonth = (Float) request.getAttribute("totalGastosMonth");
		float beneficioMonth = (Float) request.getAttribute("beneficioMonth");

		List<ClaseIngreso> listaClaseIngreso = (List) request.getAttribute("listaClaseIngreso");
		List<ClaseGasto> listaClaseGasto = (List) request.getAttribute("listaClaseGasto");

		List<Float> listaClaseIngresoYear = (List) request.getAttribute("listaClaseIngresoYear");
		List<Float> listaClaseGastoYear = (List) request.getAttribute("listaClaseGastoYear");
		List<Float> listaClaseIngresoMonth = (List) request.getAttribute("listaClaseIngresoMonth");
		List<Float> listaClaseGastoMonth = (List) request.getAttribute("listaClaseGastoMonth");

		List<Cuenta> listaCuentas = (List) request.getAttribute("listaCuentas");
		
		
		List<Movimiento> listaMovimientosPersonalizado = (List) request.getAttribute("listaMovimientosPersonalizado");
		List<Movimiento> listaIngresosPersonalizado = (List) request.getAttribute("listaIngresosPersonalizado");
		List<Movimiento> listaGastosPersonalizado = (List) request.getAttribute("listaGastosPersonalizado");
		float totalIngresosPersonalizado = (Float) request.getAttribute("totalIngresosPersonalizado");
		float totalGastosPersonalizado = (Float) request.getAttribute("totalGastosPersonalizado");
		float beneficioPersonalizado = (Float) request.getAttribute("beneficioPersonalizado");

		List<Float> listaClaseIngresoPersonalizado = (List) request.getAttribute("listaClaseIngresoPersonalizado");
		List<Float> listaClaseGastoPersonalizado = (List) request.getAttribute("listaClaseGastoPersonalizado");
	
		List <TipoMovimiento> listaTiposMovimiento = (List) request.getAttribute("listaTiposMovimiento");
	%>

	<script type="text/javascript">
	  google.charts.load('current', {packages: ['corechart']});
	  google.charts.setOnLoadCallback(drawChart);
	  
	  function drawChart() {
	      // Define the chart to be drawn.
	        
	          
	      //Evolucion Year
	      
	      var dataEvolucionYear = new google.visualization.DataTable();
	      dataEvolucionYear.addColumn('string', 'Mes');
	      dataEvolucionYear.addColumn('number', 'Ingresos');
	      dataEvolucionYear.addColumn('number', 'Gastos');
	      dataEvolucionYear.addColumn('number', 'Beneficio');
	      

	      dataEvolucionYear.addRows(<%=listaMeses.size()%>);
	      
	  		<%for (int i = 0; i < listaMeses.size(); i++) {%>
	  		dataEvolucionYear.setCell(<%=i%>, 0, '<%=listaMeses.get(i)%>');
	  		dataEvolucionYear.setCell(<%=i%>, 1, <%=listaMesesIngresos.get(i)%>);
	  		dataEvolucionYear.setCell(<%=i%>, 2, <%=listaMesesGastos.get(i)%>);
	  		dataEvolucionYear.setCell(<%=i%>, 3, <%=listaMesesBeneficios.get(i)%>);
			<%}%>
	  		
			var optionsEvolucionYear = {
// 				title : 'Evolucion del año',
					is3D: true,
					chartArea:{left:50},
// 					height: 400,
					width: 1000,
					min: 300,
					max: 1400
			};

			var chartEvolucionYear = new google.visualization.AreaChart(document
					.getElementById('chartEvolucionYear'));

			chartEvolucionYear.draw(dataEvolucionYear,optionsEvolucionYear);
       	     
			
			
	     //Ingresos Year
	     
	      var dataIngresosYear = new google.visualization.DataTable();
	      dataIngresosYear.addColumn('string', 'ingresosYear');
	      dataIngresosYear.addColumn('number');
	      dataIngresosYear.addRows(<%=listaClaseIngreso.size()%>);
	  		
	  		<%for (int i = 0; i < listaClaseIngreso.size(); i++) {%>
				dataIngresosYear.setCell(<%=i%>, 0, '<%=listaClaseIngreso.get(i).getDescripcion()%>');
				dataIngresosYear.setCell(<%=i%>, 1, <%=listaClaseIngresoYear.get(i)%>);
			<%}%>
	  		
			var optionsIngresosYear = {
				title : 'Ingresos del año',
					is3D: true,
					chartArea:{left:25,top:0,width:"100%",height:"100%"},
					height: 400,
					width: 450
			};

			var chartIngresosYear = new google.visualization.PieChart(document
					.getElementById('chartIngresosYear'));

			chartIngresosYear.draw(dataIngresosYear,optionsIngresosYear);
			
			var chartIngresosYear2 = new google.visualization.PieChart(document
					.getElementById('chartIngresosYear2'));

			chartIngresosYear2.draw(dataIngresosYear,optionsIngresosYear);
			
			
			//Gastos Year

			var dataGastosYear = new google.visualization.DataTable();
			dataGastosYear.addColumn('string', 'gastosYear');
			dataGastosYear.addColumn('number');
			dataGastosYear.addRows(<%=listaClaseGasto.size()%>);
		  		
		  		<%for (int i = 0; i < listaClaseGasto.size(); i++) {%>
				dataGastosYear.setCell(<%=i%>, 0, '<%=listaClaseGasto.get(i).getDescripcion()%>');
				dataGastosYear.setCell(<%=i%>, 1, <%=listaClaseGastoYear.get(i)%>);
				<%}%>
		  		
				var optionsGastosYear = {
					title : 'Gastos del año',
					is3D: true,
					chartArea:{left:25,top:0,width:"100%",height:"100%"},
					height: 400,
					width: 450
				};

				var chartGastosYear = new google.visualization.PieChart(document
						.getElementById('chartGastosYear'));

				chartGastosYear.draw(dataGastosYear,optionsGastosYear);
				
				var chartGastosYear2 = new google.visualization.PieChart(document
						.getElementById('chartGastosYear2'));

				chartGastosYear2.draw(dataGastosYear,optionsGastosYear);
			
				
				//Ingresos Month
			     
			      var dataIngresosMonth = new google.visualization.DataTable();
			      dataIngresosMonth.addColumn('string', 'ingresosMonth');
			      dataIngresosMonth.addColumn('number');
			      dataIngresosMonth.addRows(<%=listaClaseIngreso.size()%>);
			  		
			  		<%for (int i = 0; i < listaClaseIngreso.size(); i++) {%>
					dataIngresosMonth.setCell(<%=i%>, 0, '<%=listaClaseIngreso.get(i).getDescripcion()%>');
					dataIngresosMonth.setCell(<%=i%>, 1, <%=listaClaseIngresoMonth.get(i)%>);
					<%}%>
			  		
					var optionsIngresosMonth = {
						title : 'Ingresos del mes',
						is3D: true,
						chartArea:{left:0,top:0,width:"100%",height:"100%"},
						height: 400,
						width: 450
					};

					var chartIngresosMonth = new google.visualization.PieChart(document
							.getElementById('chartIngresosMonth'));

					chartIngresosMonth.draw(dataIngresosMonth,optionsIngresosMonth);
					
					var chartIngresosMonth2 = new google.visualization.PieChart(document
							.getElementById('chartIngresosMonth2'));

					chartIngresosMonth2.draw(dataIngresosMonth,optionsIngresosMonth);
			
					
					//Gastos Month

					var dataGastosMonth = new google.visualization.DataTable();
					dataGastosMonth.addColumn('string', 'gastosMonth');
					dataGastosMonth.addColumn('number');
					dataGastosMonth.addRows(<%=listaClaseGasto.size()%>);
				  		
				  		<%for (int i = 0; i < listaClaseGasto.size(); i++) {%>
						dataGastosMonth.setCell(<%=i%>, 0, '<%=listaClaseGasto.get(i).getDescripcion() %>');
						dataGastosMonth.setCell(<%=i%>, 1,<%=listaClaseGastoMonth.get(i)%>);
						<%}%>
					var optionsGastosMonth = {
							title : 'Gastos del mes',
							is3D : true,
							chartArea : {
								left : 0,
								top : 0,
								width : "100%",
								height : "100%"
							},
							height : 400,
							width : 450
						};

			var chartGastosMonth = new google.visualization.PieChart(document
					.getElementById('chartGastosMonth'));

			chartGastosMonth.draw(dataGastosMonth, optionsGastosMonth);
			
			var chartGastosMonth2 = new google.visualization.PieChart(document
					.getElementById('chartGastosMonth2'));

			chartGastosMonth2.draw(dataGastosMonth, optionsGastosMonth);
			
	//Personalizado
			 //Ingresos Personalizado
		     
		      var dataIngresosPersonalizado = new google.visualization.DataTable();
		      dataIngresosPersonalizado.addColumn('string', 'ingresosPersonalizado');
		      dataIngresosPersonalizado.addColumn('number');
		      dataIngresosPersonalizado.addRows(<%=listaClaseIngreso.size()%>);
		  		
		  		<%for (int i = 0; i < listaClaseIngreso.size(); i++) {%>
		  			dataIngresosPersonalizado.setCell(<%=i%>, 0, '<%=listaClaseIngreso.get(i).getDescripcion()%>');
		  			dataIngresosPersonalizado.setCell(<%=i%>, 1, <%=listaClaseIngresoPersonalizado.get(i)%>);
				<%}%>
		  		
				var optionsIngresosPersonalizado = {
					title : 'Ingresos',
						is3D: true,
						chartArea:{left:25,top:0,width:"100%",height:"100%"},
						height: 400,
						width: 450
				};

				var chartIngresosPersonalizado = new google.visualization.PieChart(document
						.getElementById('chartIngresosPersonalizado'));

				chartIngresosPersonalizado.draw(dataIngresosPersonalizado,optionsIngresosPersonalizado);
				
				var chartIngresosPersonalizado2 = new google.visualization.PieChart(document
						.getElementById('chartIngresosPersonalizado2'));

				chartIngresosPersonalizado2.draw(dataIngresosPersonalizado,optionsIngresosPersonalizado);
				
				
				//Gastos Personalizado

				var dataGastosPersonalizado = new google.visualization.DataTable();
				dataGastosPersonalizado.addColumn('string', 'gastosPersonalizado');
				dataGastosPersonalizado.addColumn('number');
				dataGastosPersonalizado.addRows(<%=listaClaseGasto.size()%>);
			  		
			  		<%for (int i = 0; i < listaClaseGasto.size(); i++) {%>
			  		dataGastosPersonalizado.setCell(<%=i%>, 0, '<%=listaClaseGasto.get(i).getDescripcion()%>');
			  		dataGastosPersonalizado.setCell(<%=i%>, 1, <%=listaClaseGastoPersonalizado.get(i)%>);
					<%}%>
			  		
					var optionsGastosPersonalizado = {
						title : 'Gastos',
						is3D: true,
						chartArea:{left:25,top:0,width:"100%",height:"100%"},
						height: 400,
						width: 450
					};

					var chartGastosPersonalizado = new google.visualization.PieChart(document
							.getElementById('chartGastosPersonalizado'));

					chartGastosPersonalizado.draw(dataGastosPersonalizado,optionsGastosPersonalizado);
					
					var chartGastosPersonalizado2 = new google.visualization.PieChart(document
							.getElementById('chartGastosPersonalizado2'));

					chartGastosPersonalizado2.draw(dataGastosPersonalizado,optionsGastosPersonalizado);
								
		}
	</script>

	<jsp:include page="/common/userHeader.jsp" />

	<jsp:include page="/common/userHeader.jsp" />

	<div class="contentWrapperAnalisis">
		<h1 class="titulo1">Análisis</h1>

		<ul class="nav nav-tabs navTiempo">
			<li class="active"><a data-toggle="tab" href="#personalizado">Análisis Personalizado</a></li>
			<li><a data-toggle="tab" href="#year">Análisis del Año</a></li>
			<li><a data-toggle="tab" href="#month">Análisis del Mes</a></li>
		</ul>

		<div>
			<div class="tab-content">

				<div id="personalizado" class="tab-pane fade in active recuadro">
					<ul class="nav nav-tabs navTipo">
						<li class="active"><a data-toggle="tab" href="#generalPersonalizado">General</a></li>
						<li><a data-toggle="tab" href="#ingresosPersonalizado">Ingresos</a></li>
						<li><a data-toggle="tab" href="#gastosPersonalizado">Gastos</a></li>
					</ul>
					<div class="tab-content">
						<div id="generalPersonalizado" class="tab-pane fade in active">
							<div class="row bordeAbajo">
								<div class="col-sm-6 bordeDerecha">
									<div class="paddingAnalisis ">
										<%
										if (request.getSession().getAttribute("consulta") != null) {
											String[] consultaLabel = {"Fechas: ","Usuario: ", "Cuenta: "};
											List <String> consulta =(List)request.getSession().getAttribute("consulta");
											%>
											<h2><span class="glyphicon glyphicon-filter"></span>Filtros</h2>
											<div class="list-group-analisis">
											<%	
											for(int i=0;i<consulta.size();i++){
												if(consulta.get(i)!=null){
													%>
													<li class="list-group-item itemFiltro"><span class="filterLabel"><%=consultaLabel[i]%></span><%=consulta.get(i)%></li>
												<% }
												else{
													%><li class="list-group-item itemFiltro"><span class="filterLabel"><%=consultaLabel[i]%></span>No se ha aplicado el filtro</li><%
												}
											}						
											%>
											</div>
										<%
											}
											request.getSession().setAttribute("consulta", null);
										%>
									</div>
								</div>
								<div class="col-sm-6 bordeIzquierda">
									<div class="paddingAnalisis ">
										<h3>Resultado</h3>
										<div class="paddingAnalisis">
											<p class="labelBalance">
												Ingresos = <span class="label label-pill label-success"><%=totalIngresosPersonalizado%></span>
											</p>
											<p class="labelBalance">
												Gastos = <span class="label label-pill label-danger"><%=totalGastosPersonalizado%>
											</p>
											<div class=totalBalance></div>
											<p class="labelBalance">
												TOTAL = <span class="label label-pill label-success"><%=totalIngresosPersonalizado%></span>
												- <span class="label label-pill label-danger"><%=totalGastosPersonalizado%></span>
												= <span class="label label-pill label-info"><%=beneficioPersonalizado%></span>
											</p>
										</div>
									</div>
								</div>
							</div>
							<div class="row bordeAbajo">
								<div class="col-sm-6 bordeDerecha">
									<div class="paddingAnalisis ">
										<h3>Ingresos</h3>
										<div id="chartIngresosPersonalizado2"></div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="paddingAnalisis ">
										<h3>Gastos</h3>
										<div id="chartGastosPersonalizado2"></div>
									</div>
								</div>
							</div>
							<div>
								<div class="paddingAnalisis">
									<h3>Listado de Movimientos</h3>
									<div class="tableContainer">
										<table class="table table-hover">
											<thead class="thead-propio">
												<tr class="totalCabecerasAnalisis">
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
														float total = beneficioPersonalizado;

														Movimiento movimiento;
														String clase = null;

														for (int i = 0; i < listaMovimientosPersonalizado.size(); i++) {
															movimiento = listaMovimientosPersonalizado.get(i);
															if (movimiento.getTipo()==1) {
																clase = listaClaseIngreso.get(movimiento.getId_clase() - 1).getDescripcion();
															}
															if (movimiento.getTipo()==2) {
																clase = listaClaseGasto.get(movimiento.getId_clase() - 1).getDescripcion();
															}
													%>
												<tr>
													<td><%=movimiento.getId_movimiento()%></td>
													<td><%=listaTiposMovimiento.get(movimiento.getTipo()-1).getDescripcion()%></td>
													<%
															SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
																String string = dateFormat.format(movimiento.getFecha());
														%>
													<td><%=string%></td>
													<td><%=clase%></td>
													<td><%=movimiento.getUsername()%></td>
													<td><%=listaCuentas.get(movimiento.getId_cuenta() - 1).getDescripcion()%></td>
													<%
															if (movimiento.getTipo()==1) {
														%><td class="ingreso">+<%=movimiento.getImporte()%></td>
													<%
															} else {
														%>
													<td class="gasto">-<%=movimiento.getImporte()%></td>
													<%
															}
														%>
													<td class="tdDescripcion"><%=movimiento.getDescripcion()%></td>
													<td>
														<form method="POST" id="form"
															action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteMovimiento">
															<input type="hidden" name="id_movimiento"
																value="<%=movimiento.getId_movimiento()%>">
															<button type="submit" class="btn btn-default"
																name="update">
																<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
															</button>
															<button type="submit" class="btn btn-default"
																name="delete"
																onClick="return confirm('¿Desea eliminar este movimiento?');">
																<span class="glyphicon glyphicon-trash"
																	aria-hidden="true"></span>
															</button>
														</form>
													</td>
												</tr>
												<%
														}
													%>
												<tr class="total">
													<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td class=total>TOTAL</td>
														<%
															if(total>0){
																%><td class="total ingreso"><%=+total%></td><%
															}
															if(total<0){
																%><td class="total gasto"><%=total%></td><%
															}
															if(total == 0){
																%><td class="total cero"><%=+total%></td><%
															}
														
														%>
														<td></td>
														<td></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

						<!-- Personalizado Ingresos -->

						<div id="ingresosPersonalizado" class="tab-pane fade">
							<div>
								<div class="bordeAbajo">
									<div class="row">
										<div class="col-sm-6 bordeDerecha">
											<div class="paddingAnalisis">
												<h3>Ingresos</h3>
												<div id="chartIngresosPersonalizado"></div>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="paddingAnalisis">
												<h3>Resultado</h3>
												<div class="paddingAnalisis">
													<div class="bordeAbajo">
														<p class="labelBalance">
															Ingresos = <span class="label label-pill label-success"><%=totalIngresosPersonalizado%></span>
														</p>
														<p class="labelBalance">
															Gastos = <span class="label label-pill label-danger"><%=totalGastosPersonalizado%>
														</p>
														<div class=totalBalance></div>
														<p class="labelBalance">
															TOTAL = <span class="label label-pill label-success"><%=totalIngresosPersonalizado%></span>
															- <span class="label label-pill label-danger"><%=totalGastosPersonalizado%></span>
															= <span class="label label-pill label-info"><%=beneficioPersonalizado%></span>
														</p>
														<div class="paddingTop"></div>
													</div>
													<div class="desglose">
														<div class="paddingTop"></div>
														<p class="labelBalance">Desglose:</p>

														<div class="paddingAnalisis">
															<ul style="list-style-type: disc">
																<%
																	for (int i = 0; i < listaClaseIngreso.size(); i++) {
																%>
																<li><p><span class="labelDesglose"><%=listaClaseIngreso.get(i).getDescripcion()%>:</span>
																		<%=listaClaseIngresoPersonalizado.get(i)%></p></li>
																<%
																	}
																%>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div>
									<div class="paddingAnalisis">
										<h3>Listado de Ingresos</h3>
										<div class="tableContainer">
											<table class="table table-hover">
												<thead class="thead-propio">
													<tr class="totalCabecerasAnalisis">
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
														 total = totalIngresosPersonalizado;

														
														 clase = null;
														for (int i = 0; i < listaIngresosPersonalizado.size(); i++) {
															movimiento = listaIngresosPersonalizado.get(i);
															if (movimiento.getTipo()==1) {
																clase = listaClaseIngreso.get(movimiento.getId_clase() - 1).getDescripcion();
															}
															if (movimiento.getTipo()==2) {
																clase = listaClaseGasto.get(movimiento.getId_clase() - 1).getDescripcion();
															}
													%>
													<tr>
														<td><%=movimiento.getId_movimiento()%></td>
														<td><%=listaTiposMovimiento.get(movimiento.getTipo()-1).getDescripcion()%></td>
														<%
															SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
																String string = dateFormat.format(movimiento.getFecha());
														%>
														<td><%=string%></td>
														<td><%=clase%></td>
														<td><%=movimiento.getUsername()%></td>
														<td><%=listaCuentas.get(movimiento.getId_cuenta() - 1).getDescripcion()%></td>
														<%
															if (movimiento.getTipo()==1) {
														%><td class="ingreso">+<%=movimiento.getImporte()%></td>
														<%
															} else {
														%>
														<td class="gasto">-<%=movimiento.getImporte()%></td>
														<%
															}
														%>
														<td class="tdDescripcion"><%=movimiento.getDescripcion()%></td>
														<td>
															<form method="POST" id="form"
																action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteMovimiento">
																<input type="hidden" name="id_movimiento"
																	value="<%=movimiento.getId_movimiento()%>">
																<button type="submit" class="btn btn-default"
																	name="update">
																	<span class="glyphicon glyphicon-pencil"
																		aria-hidden="true"></span>
																</button>
																<button type="submit" class="btn btn-default"
																	name="delete"
																	onClick="return confirm('¿Desea eliminar este movimiento?');">
																	<span class="glyphicon glyphicon-trash"
																		aria-hidden="true"></span>
																</button>
															</form>
														</td>
													</tr>
													<%
														}
													%>
													<tr class="total">
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td class=total>TOTAL</td>
														<td class="total ingreso">+<%=total%></td>
														<td></td>
														<td></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Personalizado Gastos -->

						<div id="gastosPersonalizado" class="tab-pane fade">
							<div>
								<div class="bordeAbajo">
									<div class="row">
										<div class="col-sm-6 bordeDerecha">
											<div class="paddingAnalisis">
												<h3>Gastos</h3>
												<div id="chartGastosPersonalizado"></div>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="paddingAnalisis">
												<h3>Resultado</h3>
												<div class="paddingAnalisis">
													<div class="bordeAbajo">
														<p class="labelBalance">
															Ingresos = <span class="label label-pill label-success"><%=totalIngresosPersonalizado%></span>
														</p>
														<p class="labelBalance">
															Gastos = <span class="label label-pill label-danger"><%=totalGastosPersonalizado%>
														</p>
														<div class=totalBalance></div>
														<p class="labelBalance">
															TOTAL = <span class="label label-pill label-success"><%=totalIngresosPersonalizado%></span>
															- <span class="label label-pill label-danger"><%=totalGastosPersonalizado%></span>
															= <span class="label label-pill label-info"><%=beneficioPersonalizado%></span>
														</p>
														<div class="paddingTop"></div>
													</div>
													<div class="desglose">
														<div class="paddingTop"></div>
														<p class="labelBalance">Desglose:</p>
														<div class="paddingAnalisis">
															<ul style="list-style-type: disc">
																<%
																	for (int i = 0; i < listaClaseGasto.size(); i++) {
																%>
																<li><p><span class="labelDesglose"><%=listaClaseGasto.get(i).getDescripcion()%>:</span>
																		<%=listaClaseGastoPersonalizado.get(i)%></p></li>
																<%
																	}
																%>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div>
									<div class="paddingAnalisis">
										<h3>Listado de Gastos</h3>
										<div class="tableContainer">
											<table class="table table-hover">
												<thead class="thead-propio">
													<tr class="totalCabecerasAnalisis">
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
														total = totalGastosPersonalizado;
														movimiento = null;
														clase = null;
														for (int i = 0; i < listaGastosPersonalizado.size(); i++) {
															movimiento = listaGastosPersonalizado.get(i);
															if (movimiento.getTipo()==1) {
																clase = listaClaseIngreso.get(movimiento.getId_clase() - 1).getDescripcion();
															}
															if (movimiento.getTipo()==2) {
																clase = listaClaseGasto.get(movimiento.getId_clase() - 1).getDescripcion();
															}
													%>
													<tr>
														<td><%=movimiento.getId_movimiento()%></td>
														<td><%=listaTiposMovimiento.get(movimiento.getTipo()-1).getDescripcion()%></td>
														<%
															SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
																String string = dateFormat.format(movimiento.getFecha());
														%>
														<td><%=string%></td>
														<td><%=clase%></td>
														<td><%=movimiento.getUsername()%></td>
														<td><%=listaCuentas.get(movimiento.getId_cuenta() - 1).getDescripcion()%></td>
														<%
															if (movimiento.getTipo()==1) {
														%><td class="ingreso">+<%=movimiento.getImporte()%></td>
														<%
															} else {
														%>
														<td class="gasto">-<%=movimiento.getImporte()%></td>
														<%
															}
														%>
														<td class="tdDescripcion"><%=movimiento.getDescripcion()%></td>
														<td>
															<form method="POST" id="form"
																action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteMovimiento">
																<input type="hidden" name="id_movimiento"
																	value="<%=movimiento.getId_movimiento()%>">
																<button type="submit" class="btn btn-default"
																	name="update">
																	<span class="glyphicon glyphicon-pencil"
																		aria-hidden="true"></span>
																</button>
																<button type="submit" class="btn btn-default"
																	name="delete"
																	onClick="return confirm('¿Desea eliminar este movimiento?');">
																	<span class="glyphicon glyphicon-trash"
																		aria-hidden="true"></span>
																</button>
															</form>
														</td>
													</tr>
													<%
														}
													%>
													<tr class="total">
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td class=total>TOTAL</td>
														<td class="total gasto">-<%=total%></td>
														<td></td>
														<td></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="year" class="tab-pane fade recuadro">
					<ul class="nav nav-tabs navTipo">
						<li class="active"><a data-toggle="tab" href="#generalYear">General</a></li>
						<li><a data-toggle="tab" href="#ingresosYear">Ingresos</a></li>
						<li><a data-toggle="tab" href="#gastosYear">Gastos</a></li>
					</ul>
					<div class="tab-content">
					
					<!--Evolucion año -->
					
						<div id="generalYear" class="tab-pane fade in active">
						<div class="bordeAbajo">
									<div class="row">
											<div class="paddingAnalisis paddingIzquierda">
												<h3>Evolución</h3>
												<div id="chartEvolucionYear" ></div>
											</div>
									</div>
								</div>
							<div class="bordeAbajo">
								<div class="paddingAnalisis">
									<h3>Resultado del año</h3>
									<div class="paddingAnalisis">
										<p class="labelBalance">
											Ingresos = <span class="label label-pill label-success"><%=totalIngresosYear%></span>
										</p>
										<p class="labelBalance">
											Gastos = <span class="label label-pill label-danger"><%=totalGastosYear%>
										</p>
										<div class=totalBalance></div>
										<p class="labelBalance">
											TOTAL = <span class="label label-pill label-success"><%=totalIngresosYear%></span>
											- <span class="label label-pill label-danger"><%=totalGastosYear%></span>
											= <span class="label label-pill label-info"><%=beneficioYear%></span>
										</p>
									</div>
								</div>
							</div>
							<div class="row bordeAbajo">
								<div class="col-sm-6 bordeDerecha">
									<div class="paddingAnalisis ">
										<h3>Ingresos</h3>
										<div id="chartIngresosYear2"></div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="paddingAnalisis ">
										<h3>Gastos</h3>
										<div id="chartGastosYear2"></div>
									</div>
								</div>
							</div>
								<div>
									<div class="paddingAnalisis">
									<h3>Listado de Movimientos del Año</h3>
										<div class="tableContainer">
											<table class="table table-hover">
												<thead class="thead-propio">
													<tr class="totalCabecerasAnalisis">
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
														total = beneficioYear;

														clase = null;
														for (int i = 0; i < listaMovimientosYear.size(); i++) {
															movimiento = listaMovimientosYear.get(i);
															if (movimiento.getTipo()==1) {
																clase = listaClaseIngreso.get(movimiento.getId_clase() - 1).getDescripcion();
															}
															if (movimiento.getTipo()==2) {
																clase = listaClaseGasto.get(movimiento.getId_clase() - 1).getDescripcion();
															}
													%>
													<tr>
														<td><%=movimiento.getId_movimiento()%></td>
														<td><%=listaTiposMovimiento.get(movimiento.getTipo()-1).getDescripcion()%></td>
														<%
															SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
																String string = dateFormat.format(movimiento.getFecha());
														%>
														<td><%=string%></td>
														<td><%=clase%></td>
														<td><%=movimiento.getUsername()%></td>
														<td><%=listaCuentas.get(movimiento.getId_cuenta() - 1).getDescripcion()%></td>
														<%
															if (movimiento.getTipo()==1) {
														%><td class="ingreso">+<%=movimiento.getImporte()%></td>
														<%
															} else {
														%>
														<td class="gasto">-<%=movimiento.getImporte()%></td>
														<%
															}
														%>
														<td class="tdDescripcion"><%=movimiento.getDescripcion()%></td>
														<td>
															<form method="POST" id="form"
																action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteMovimiento">
																<input type="hidden" name="id_movimiento"
																	value="<%=movimiento.getId_movimiento()%>">
																<button type="submit" class="btn btn-default"
																	name="update">
																	<span class="glyphicon glyphicon-pencil"
																		aria-hidden="true"></span>
																</button>
																<button type="submit" class="btn btn-default"
																	name="delete"
																	onClick="return confirm('¿Desea eliminar este movimiento?');">
																	<span class="glyphicon glyphicon-trash"
																		aria-hidden="true"></span>
																</button>
															</form>
														</td>
													</tr>
													<%
														}
													%>
													<tr class="total">
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td class=total>TOTAL</td>
														<%
															if(total>0){
																%><td class="total ingreso"><%=+total%></td><%
															}
															if(total<0){
																%><td class="total gasto"><%=total%></td><%
															}
															if(total == 0){
																%><td class="total cero"><%=+total%></td><%
															}														
														%>
														<td></td>
														<td></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
						</div>
												
						<!-- Año Ingresos -->
						
						<div id="ingresosYear" class="tab-pane fade">
							<div>
								<div class="bordeAbajo">
									<div class="row">
										<div class="col-sm-6 bordeDerecha">
											<div class="paddingAnalisis">
												<h3>Ingresos</h3>
												<div id="chartIngresosYear"></div>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="paddingAnalisis">
												<h3>Resultado del Año</h3>
												<div class="paddingAnalisis">
													<div class="bordeAbajo">
														<p class="labelBalance">
															Ingresos = <span class="label label-pill label-success"><%=totalIngresosYear%></span>
														</p>
														<p class="labelBalance">
															Gastos = <span class="label label-pill label-danger"><%=totalGastosYear%>
														</p>
														<div class=totalBalance></div>
														<p class="labelBalance">
															TOTAL = <span class="label label-pill label-success"><%=totalIngresosYear%></span>
															- <span class="label label-pill label-danger"><%=totalGastosYear%></span>
															= <span class="label label-pill label-info"><%=beneficioYear%></span>
														</p>
														<div class="paddingTop"></div>
													</div>
													<div class="desglose">
														<div class="paddingTop"></div>
														<p class="labelBalance">Desglose:</p>

														<div class="paddingAnalisis">
															<ul style="list-style-type: disc">
																<%
																	for (int i = 0; i < listaClaseIngreso.size(); i++) {
																%>
																<li><p><span class="labelDesglose"><%=listaClaseIngreso.get(i).getDescripcion()%>:</span>
																		<%=listaClaseIngresoYear.get(i)%></p></li>
																<%
																	}
																%>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div>
									<div class="paddingAnalisis">
									<h3>Listado de Ingresos del Año</h3>
										<div class="tableContainer">
											<table class="table table-hover">
												<thead class="thead-propio">
													<tr class="totalCabecerasAnalisis">
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
														 total = totalIngresosYear;
														 clase = null;
														for (int i = 0; i < listaIngresosYear.size(); i++) {
															movimiento = listaIngresosYear.get(i);
															if (movimiento.getTipo()==1) {
																clase = listaClaseIngreso.get(movimiento.getId_clase() - 1).getDescripcion();
															}
															if (movimiento.getTipo()==2) {
																clase = listaClaseGasto.get(movimiento.getId_clase() - 1).getDescripcion();
															}
													%>
													<tr>
														<td><%=movimiento.getId_movimiento()%></td>
														<td><%=listaTiposMovimiento.get(movimiento.getTipo()-1).getDescripcion()%></td>
														<%
															SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
																String string = dateFormat.format(movimiento.getFecha());
														%>
														<td><%=string%></td>
														<td><%=clase%></td>
														<td><%=movimiento.getUsername()%></td>
														<td><%=listaCuentas.get(movimiento.getId_cuenta() - 1).getDescripcion()%></td>
														<%
															if (movimiento.getTipo()==1) {
														%><td class="ingreso">+<%=movimiento.getImporte()%></td>
														<%
															} else {
														%>
														<td class="gasto">-<%=movimiento.getImporte()%></td>
														<%
															}
														%>
														<td class="tdDescripcion"><%=movimiento.getDescripcion()%></td>
														<td>
															<form method="POST" id="form"
																action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteMovimiento">
																<input type="hidden" name="id_movimiento"
																	value="<%=movimiento.getId_movimiento()%>">
																<button type="submit" class="btn btn-default"
																	name="update">
																	<span class="glyphicon glyphicon-pencil"
																		aria-hidden="true"></span>
																</button>
																<button type="submit" class="btn btn-default"
																	name="delete"
																	onClick="return confirm('¿Desea eliminar este movimiento?');">
																	<span class="glyphicon glyphicon-trash"
																		aria-hidden="true"></span>
																</button>
															</form>
														</td>
													</tr>
													<%
														}
													%>
													<tr class="total">
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td class=total>TOTAL</td>
														<td class="total ingreso">+<%=total%></td>
														<td></td>
														<td></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Año Gastos -->
						
						<div id="gastosYear" class="tab-pane fade">
							<div>
								<div class="bordeAbajo">
									<div class="row">
										<div class="col-sm-6 bordeDerecha">
											<div class="paddingAnalisis">
												<h3>Gastos</h3>
												<div id="chartGastosYear"></div>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="paddingAnalisis">
												<h3>Resultado del Año</h3>
												<div class="paddingAnalisis">
													<div class="bordeAbajo">
														<p class="labelBalance">
															Ingresos = <span class="label label-pill label-success"><%=totalIngresosYear%></span>
														</p>
														<p class="labelBalance">
															Gastos = <span class="label label-pill label-danger"><%=totalGastosYear%>
														</p>
														<div class=totalBalance></div>
														<p class="labelBalance">
															TOTAL = <span class="label label-pill label-success"><%=totalIngresosYear%></span>
															- <span class="label label-pill label-danger"><%=totalGastosYear%></span>
															= <span class="label label-pill label-info"><%=beneficioYear%></span>
														</p>
														<div class="paddingTop"></div>
													</div>
													<div class="desglose">
														<div class="paddingTop"></div>
														<p class="labelBalance">Desglose:</p>
														<div class="paddingAnalisis">
															<ul style="list-style-type: disc">
																<%
																	for (int i = 0; i < listaClaseGasto.size(); i++) {
																%>
																<li><p><span class="labelDesglose"><%=listaClaseGasto.get(i).getDescripcion()%>:</span>
																		<%=listaClaseGastoYear.get(i)%></p></li>
																<%
																	}
																%>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>

									</div>
								</div>
								<div>
									<div class="paddingAnalisis">
									<h3>Listado de Gastos del Año</h3>
										<div class="tableContainer">
											<table class="table table-hover">
												<thead class="thead-propio">
													<tr class="totalCabecerasAnalisis">
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
														total = totalGastosYear;
														movimiento = null;
														clase = null;
														for (int i = 0; i < listaGastosYear.size(); i++) {
															movimiento = listaGastosYear.get(i);
															if (movimiento.getTipo()==1) {
																clase = listaClaseIngreso.get(movimiento.getId_clase() - 1).getDescripcion();
															}
															if (movimiento.getTipo()==2) {
																clase = listaClaseGasto.get(movimiento.getId_clase() - 1).getDescripcion();
															}
													%>
													<tr>
														<td><%=movimiento.getId_movimiento()%></td>
														<td><%=listaTiposMovimiento.get(movimiento.getTipo()-1).getDescripcion()%></td>
														<%
															SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
																String string = dateFormat.format(movimiento.getFecha());
														%>
														<td><%=string%></td>
														<td><%=clase%></td>
														<td><%=movimiento.getUsername()%></td>
														<td><%=listaCuentas.get(movimiento.getId_cuenta() - 1).getDescripcion()%></td>
														<%
															if (movimiento.getTipo()==1) {
														%><td class="ingreso">+<%=movimiento.getImporte()%></td>
														<%
															} else {
														%>
														<td class="gasto">-<%=movimiento.getImporte()%></td>
														<%
															}
														%>
														<td class="tdDescripcion"><%=movimiento.getDescripcion()%></td>
														<td>
															<form method="POST" id="form"
																action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteMovimiento">
																<input type="hidden" name="id_movimiento"
																	value="<%=movimiento.getId_movimiento()%>">
																<button type="submit" class="btn btn-default"
																	name="update">
																	<span class="glyphicon glyphicon-pencil"
																		aria-hidden="true"></span>
																</button>
																<button type="submit" class="btn btn-default"
																	name="delete"
																	onClick="return confirm('¿Desea eliminar este movimiento?');">
																	<span class="glyphicon glyphicon-trash"
																		aria-hidden="true"></span>
																</button>
															</form>
														</td>
													</tr>
													<%
														}
													%>
													<tr class="total">
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td class=total>TOTAL</td>
														<td class="total gasto">-<%=total%></td>
														<td></td>
														<td></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="month" class="tab-pane fade recuadro">
						<ul class="nav nav-tabs navTipo">
						<li class="active"><a data-toggle="tab" href="#generalMonth">General</a></li>
						<li><a data-toggle="tab" href="#ingresosMonth">Ingresos</a></li>
						<li><a data-toggle="tab" href="#gastosMonth">Gastos</a></li>
					</ul>
					<div class="tab-content">
					
					<!--Evolucion año -->
										
						<div id="generalMonth" class="tab-pane fade in active">
							<div class="bordeAbajo">
								<div class="paddingAnalisis">
									<h3>Resultado del Mes</h3>
									<div class="paddingAnalisis">
										<p class="labelBalance">
											Ingresos = <span class="label label-pill label-success"><%=totalIngresosMonth%></span>
										</p>
										<p class="labelBalance">
											Gastos = <span class="label label-pill label-danger"><%=totalGastosMonth%>
										</p>
										<div class=totalBalance></div>
										<p class="labelBalance">
											TOTAL = <span class="label label-pill label-success"><%=totalIngresosMonth%></span>
											- <span class="label label-pill label-danger"><%=totalGastosMonth%></span>
											= <span class="label label-pill label-info"><%=beneficioMonth%></span>
										</p>
									</div>
								</div>
							</div>
							<div class="row bordeAbajo">
								<div class="col-sm-6 bordeDerecha">
									<div class="paddingAnalisis ">
										<h3>Ingresos</h3>
										<div id="chartIngresosMonth2"></div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="paddingAnalisis ">
										<h3>Gastos</h3>
										<div id="chartGastosMonth2"></div>
									</div>
								</div>
							</div>
								<div>
									<div class="paddingAnalisis">
									<h3>Listado de Movimientos del Mes</h3>
										<div class="tableContainer">
											<table class="table table-hover">
												<thead class="thead-propio">
													<tr class="totalCabecerasAnalisis">
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
														total = beneficioMonth;
														clase = null;
														for (int i = 0; i < listaMovimientosMonth.size(); i++) {
															movimiento = listaMovimientosMonth.get(i);
															if (movimiento.getTipo()==1) {
																clase = listaClaseIngreso.get(movimiento.getId_clase() - 1).getDescripcion();
															}
															if (movimiento.getTipo()==2) {
																clase = listaClaseGasto.get(movimiento.getId_clase() - 1).getDescripcion();
															}
													%>
													<tr>
														<td><%=movimiento.getId_movimiento()%></td>
														<td><%=listaTiposMovimiento.get(movimiento.getTipo()-1).getDescripcion()%></td>
														<%
															SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
																String string = dateFormat.format(movimiento.getFecha());
														%>
														<td><%=string%></td>
														<td><%=clase%></td>
														<td><%=movimiento.getUsername()%></td>
														<td><%=listaCuentas.get(movimiento.getId_cuenta() - 1).getDescripcion()%></td>
														<%
															if (movimiento.getTipo()==1) {
														%><td class="ingreso">+<%=movimiento.getImporte()%></td>
														<%
															} else {
														%>
														<td class="gasto">-<%=movimiento.getImporte()%></td>
														<%
															}
														%>
														<td class="tdDescripcion"><%=movimiento.getDescripcion()%></td>
														<td>
															<form method="POST" id="form"
																action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteMovimiento">
																<input type="hidden" name="id_movimiento"
																	value="<%=movimiento.getId_movimiento()%>">
																<button type="submit" class="btn btn-default"
																	name="update">
																	<span class="glyphicon glyphicon-pencil"
																		aria-hidden="true"></span>
																</button>
																<button type="submit" class="btn btn-default"
																	name="delete"
																	onClick="return confirm('¿Desea eliminar este movimiento?');">
																	<span class="glyphicon glyphicon-trash"
																		aria-hidden="true"></span>
																</button>
															</form>
														</td>
													</tr>
													<%
														}
													%>
													<tr class="total">
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td class=total>TOTAL</td>
														<%
															if(total>0){
																%><td class="total ingreso"><%=+total%></td><%
															}
															if(total<0){
																%><td class="total gasto"><%=total%></td><%
															}
															if(total == 0){
																%><td class="total cero"><%=+total%></td><%
															}														
														%>
														<td></td>
														<td></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
						</div>
												
						<!-- Mes Ingresos -->
						
						<div id="ingresosMonth" class="tab-pane fade">
							<div>
								<div class="bordeAbajo">
									<div class="row">
										<div class="col-sm-6 bordeDerecha">
											<div class="paddingAnalisis">
												<h3>Ingresos</h3>
												<div id="chartIngresosMonth"></div>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="paddingAnalisis">
												<h3>Resultado del Mes</h3>
												<div class="paddingAnalisis">
													<div class="bordeAbajo">
														<p class="labelBalance">
															Ingresos = <span class="label label-pill label-success"><%=totalIngresosMonth%></span>
														</p>
														<p class="labelBalance">
															Gastos = <span class="label label-pill label-danger"><%=totalGastosMonth%>
														</p>
														<div class=totalBalance></div>
														<p class="labelBalance">
															TOTAL = <span class="label label-pill label-success"><%=totalIngresosMonth%></span>
															- <span class="label label-pill label-danger"><%=totalGastosMonth%></span>
															= <span class="label label-pill label-info"><%=beneficioMonth%></span>
														</p>
														<div class="paddingTop"></div>
													</div>
													<div class="desglose">
														<div class="paddingTop"></div>
														<p class="labelBalance">Desglose:</p>

														<div class="paddingAnalisis">
															<ul style="list-style-type: disc">
																<%
																	for (int i = 0; i < listaClaseIngreso.size(); i++) {
																%>
																<li><p><span class="labelDesglose"><%=listaClaseIngreso.get(i).getDescripcion()%>:</span>
																		<%=listaClaseIngresoMonth.get(i)%></p></li>
																<%
																	}
																%>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div>
									<div class="paddingAnalisis">
										<h3>Listado de Ingresos del Mes</h3>
										<div class="tableContainer">
											<table class="table table-hover">
												<thead class="thead-propio">
													<tr class="totalCabecerasAnalisis">
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
														 total = totalIngresosMonth;						
														 clase = null;
														for (int i = 0; i < listaIngresosMonth.size(); i++) {
															movimiento = listaIngresosMonth.get(i);
															if (movimiento.getTipo()==1) {
																clase = listaClaseIngreso.get(movimiento.getId_clase() - 1).getDescripcion();
															}
															if (movimiento.getTipo()==2) {
																clase = listaClaseGasto.get(movimiento.getId_clase() - 1).getDescripcion();
															}
													%>
													<tr>
														<td><%=movimiento.getId_movimiento()%></td>
														<td><%=listaTiposMovimiento.get(movimiento.getTipo()-1).getDescripcion()%></td>
														<%
															SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
																String string = dateFormat.format(movimiento.getFecha());
														%>
														<td><%=string%></td>
														<td><%=clase%></td>
														<td><%=movimiento.getUsername()%></td>
														<td><%=listaCuentas.get(movimiento.getId_cuenta() - 1).getDescripcion()%></td>
														<%
															if (movimiento.getTipo()==1) {
														%><td class="ingreso">+<%=movimiento.getImporte()%></td>
														<%
															} else {
														%>
														<td class="gasto">-<%=movimiento.getImporte()%></td>
														<%
															}
														%>
														<td class="tdDescripcion"><%=movimiento.getDescripcion()%></td>
														<td>
															<form method="POST" id="form"
																action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteMovimiento">
																<input type="hidden" name="id_movimiento"
																	value="<%=movimiento.getId_movimiento()%>">
																<button type="submit" class="btn btn-default"
																	name="update">
																	<span class="glyphicon glyphicon-pencil"
																		aria-hidden="true"></span>
																</button>
																<button type="submit" class="btn btn-default"
																	name="delete"
																	onClick="return confirm('¿Desea eliminar este movimiento?');">
																	<span class="glyphicon glyphicon-trash"
																		aria-hidden="true"></span>
																</button>
															</form>
														</td>
													</tr>
													<%
														}
													%>
													<tr class="total">
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td class=total>TOTAL</td>
														<td class="total ingreso">+<%=total%></td>
														<td></td>
														<td></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Month Gastos -->
						
						<div id="gastosMonth" class="tab-pane fade">
							<div>
								<div class="bordeAbajo">
									<div class="row">
										<div class="col-sm-6 bordeDerecha">
											<div class="paddingAnalisis">
												<h3>Gastos</h3>
												<div id="chartGastosMonth"></div>
											</div>
										</div>
										<div class="col-sm-6">
											<div class="paddingAnalisis">
												<h3>Resultado del Mes</h3>
												<div class="paddingAnalisis">
													<div class="bordeAbajo">
														<p class="labelBalance">
															Ingresos = <span class="label label-pill label-success"><%=totalIngresosMonth%></span>
														</p>
														<p class="labelBalance">
															Gastos = <span class="label label-pill label-danger"><%=totalGastosMonth%>
														</p>
														<div class=totalBalance></div>
														<p class="labelBalance">
															TOTAL = <span class="label label-pill label-success"><%=totalIngresosMonth%></span>
															- <span class="label label-pill label-danger"><%=totalGastosMonth%></span>
															= <span class="label label-pill label-info"><%=beneficioMonth%></span>
														</p>
														<div class="paddingTop"></div>
													</div>
													<div class="desglose">
														<div class="paddingTop"></div>
														<p class="labelBalance">Desglose:</p>
														<div class="paddingAnalisis">
															<ul style="list-style-type: disc">
																<%
																	for (int i = 0; i < listaClaseGasto.size(); i++) {
																%>
																<li><p><span class="labelDesglose"><%=listaClaseGasto.get(i).getDescripcion()%>:</span>
																		<%=listaClaseGastoMonth.get(i)%></p></li>
																<%
																	}
																%>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div>
									<div class="paddingAnalisis">
									<h3>Listado de Gastos del Mes</h3>
										<div class="tableContainer">
											<table class="table table-hover">
												<thead class="thead-propio">
													<tr class="totalCabecerasAnalisis">
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
														total = totalGastosMonth;
														movimiento = null;
														clase = null;
														for (int i = 0; i < listaGastosMonth.size(); i++) {
															movimiento = listaGastosMonth.get(i);
															if (movimiento.getTipo()==1) {
																clase = listaClaseIngreso.get(movimiento.getId_clase() - 1).getDescripcion();
															}
															if (movimiento.getTipo()==2) {
																clase = listaClaseGasto.get(movimiento.getId_clase() - 1).getDescripcion();
															}
													%>
													<tr>
														<td><%=movimiento.getId_movimiento()%></td>
														<td><%=listaTiposMovimiento.get(movimiento.getTipo()-1).getDescripcion()%></td>
														<%
															SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
																String string = dateFormat.format(movimiento.getFecha());
														%>
														<td><%=string%></td>
														<td><%=clase%></td>
														<td><%=movimiento.getUsername()%></td>
														<td><%=listaCuentas.get(movimiento.getId_cuenta() - 1).getDescripcion()%></td>
														<%
															if (movimiento.getTipo()==1) {
														%><td class="ingreso">+<%=movimiento.getImporte()%></td>
														<%
															} else {
														%>
														<td class="gasto">-<%=movimiento.getImporte()%></td>
														<%
															}
														%>
														<td class="tdDescripcion"><%=movimiento.getDescripcion()%></td>
														<td>
															<form method="POST" id="form"
																action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteMovimiento">
																<input type="hidden" name="id_movimiento"
																	value="<%=movimiento.getId_movimiento()%>">
																<button type="submit" class="btn btn-default"
																	name="update">
																	<span class="glyphicon glyphicon-pencil"
																		aria-hidden="true"></span>
																</button>
																<button type="submit" class="btn btn-default"
																	name="delete"
																	onClick="return confirm('¿Desea eliminar este movimiento?');">
																	<span class="glyphicon glyphicon-trash"
																		aria-hidden="true"></span>
																</button>
															</form>
														</td>
													</tr>
													<%
														}
													%>
													<tr class="total">
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td class=total>TOTAL</td>
														<td class="total gasto">-<%=total%></td>
														<td></td>
														<td></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>	
				</div>
			</div>
		</div>
	</div>
</body>
</html>