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
					chartArea:{left:0,top:0,width:"100%",height:"100%"},
					height: 400,
					width: 450
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
					chartArea:{left:0,top:0,width:"100%",height:"100%"},
					height: 400,
					width: 450
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
						chartArea:{left:0,top:0,width:"100%",height:"100%"},
						height: 400,
						width: 450
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
							chartArea:{left:0,top:0,width:"100%",height:"100%"},
							height: 400,
							width: 450
						};

						var chartGastosMonth = new google.visualization.PieChart(document
								.getElementById('chartGastosMonth'));

						chartGastosMonth.draw(dataGastosMonth,optionsGastosMonth);
			
		}
	</script>



	<jsp:include page="/common/userHeader.jsp" />

	<jsp:include page="/common/userHeader.jsp" />

	<div class="contentWrapper">
		<h2 class="titulo2">Análisis Estándar</h2>
		<h4 class="titulo4">Rango temporal</h4>

		<ul class="nav nav-tabs navTiempo">
			<li class="active"><a data-toggle="tab" href="#year">Año</a></li>
			<li><a data-toggle="tab" href="#month">Mes</a></li>
		</ul>

		<div>
			<div class="tab-content">


				<div id="year" class="tab-pane fade in active">
					<ul class="nav nav-tabs navTipo">
						<li class="active"><a data-toggle="tab" href="#generalYear">General</a></li>
						<li><a data-toggle="tab" href="#ingresosYear">Ingresos</a></li>
						<li><a data-toggle="tab" href="#gastosYear">Gastos</a></li>
					</ul>
					<div class="tab-content">
						<div id="generalYear" class="tab-pane fade in active">
							<div class="row">
								<div class="col-sm-6 bordeDerecha">
									<!-- 						<h4>Ingresos</h4> -->
									<!-- 									<div id="chartIngresosYear" class="grafico bordeAbajo"></div> -->
									<!-- 							<h4>Gastos</h4> -->
									<!-- 									<div id="chartGastosYear" class="grafico"></div> -->
								</div>
								<div class="col-sm-6">
									<div>
										<h3>Resultado del Año</h3>
										<span class="label label-pill label-success"><%=totalIngresosYear%></span>
										- <span class="label label-pill label-danger"><%=totalGastosYear%></span>
										= <span class="label label-pill label-info"><%=beneficioYear%></span>
									</div>
								</div>
							</div>
						</div>
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
														<p class="labelBalance totalBalance">
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
																<li><p><%=listaClaseIngreso.get(i).getDescripcion()%>:
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
										<div class="tableContainer">
											<table class="table table-hover">
												<thead>
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
													float total = totalIngresosYear;

													Movimiento movimiento;
													String clase = null;
													for (int i = 0; i < listaIngresosYear.size(); i++) {
														movimiento = listaIngresosYear.get(i);
														if (movimiento.getTipo().equals("Ingreso")) {
															clase = listaClaseIngreso.get(movimiento.getId_clase() - 1).getDescripcion();
														}
														if (movimiento.getTipo().equals("Gasto")) {
															clase = listaClaseGasto.get(movimiento.getId_clase() - 1).getDescripcion();
														}
												%>
													<tr>
														<td><%=movimiento.getId_movimiento()%></td>
														<td><%=movimiento.getTipo()%></td>
														<%
														SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
															String string = dateFormat.format(movimiento.getFecha());
													%>
														<td><%=string%></td>
														<td><%=clase%></td>
														<td><%=movimiento.getUsername()%></td>
														<td><%=listaCuentas.get(movimiento.getId_cuenta() - 1).getDescripcion()%></td>
														<%
														if (movimiento.getTipo().equals("Ingreso")) {
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

											<form method="POST"
												action="<%=Config.getInstance().getRoot()%>/protected_area/exportExcel">
												<%
												request.getSession().setAttribute("listaMovimientos", listaIngresosYear);
											%>
												<button type="submit" class="btn btn-default"
													name="exportar">
													<span class="glyphicon glyphicon-download-alt"
														aria-hidden="true"></span> Descargar Excel
												</button>
											</form>

										</div>
									</div>
								</div>
							</div>
						</div>
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
														<p class="labelBalance totalBalance">
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
																<li><p><%=listaClaseGasto.get(i).getDescripcion()%>:
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
										<div class="tableContainer">
											<table class="table table-hover">
												<thead>
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
														if (movimiento.getTipo().equals("Ingreso")) {
															clase = listaClaseIngreso.get(movimiento.getId_clase() - 1).getDescripcion();
														}
														if (movimiento.getTipo().equals("Gasto")) {
															clase = listaClaseGasto.get(movimiento.getId_clase() - 1).getDescripcion();
														}
												%>
													<tr>
														<td><%=movimiento.getId_movimiento()%></td>
														<td><%=movimiento.getTipo()%></td>
														<%
														SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
															String string = dateFormat.format(movimiento.getFecha());
													%>
														<td><%=string%></td>
														<td><%=clase%></td>
														<td><%=movimiento.getUsername()%></td>
														<td><%=listaCuentas.get(movimiento.getId_cuenta() - 1).getDescripcion()%></td>
														<%
														if (movimiento.getTipo().equals("Ingreso")) {
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

											<form method="POST"
												action="<%=Config.getInstance().getRoot()%>/protected_area/exportExcel">
												<%
												request.getSession().setAttribute("listaMovimientos", listaIngresosYear);
											%>
												<button type="submit" class="btn btn-default"
													name="exportar">
													<span class="glyphicon glyphicon-download-alt"
														aria-hidden="true"></span> Descargar Excel
												</button>
											</form>

										</div>
									</div>
								</div>
							</div>





















							<div class="row">
								<div class="col-sm-6 bordeDerecha">

									<div id="chartGastosYear" class="grafico bordeAbajo"></div>
								</div>
								<div class="col-sm-6">
									<div>
										<h3>Resultado del Mes</h3>
										<span class="label label-pill label-success"><%=totalIngresosMonth%></span>
										- <span class="label label-pill label-danger"><%=totalGastosMonth%></span>
										= <span class="label label-pill label-info"><%=beneficioMonth%></span>
									</div>
								</div>
							</div>
						</div>
					</div>


				</div>

				<div id="month" class="tab-pane fade">
					<h3>Mes</h3>

					<div class="row">
						<div class="col-sm-6 bordeDerecha">
							<div id="chartIngresosMonth" class="grafico bordeAbajo"></div>
							<div id="chartGastosMonth" class="grafico"></div>
						</div>
						<div class="col-sm-6">
							<div>
								<h3>Resultado del Mes</h3>
								<span class="label label-pill label-success"><%=totalIngresosMonth%></span>
								- <span class="label label-pill label-danger"><%=totalGastosMonth%></span>
								= <span class="label label-pill label-info"><%=beneficioMonth%></span>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>


	<!-- 	<div class="row"> -->
	<!-- 		<div class="col-sm-6"></div> -->
	<!-- 		<div class="col-sm-6"></div> -->
	<!-- 	</div> -->

	<!-- 	<div id="ingresosYear" class="tab-pane fade"> -->
	<!-- 		<div class="row"> -->
	<!-- 			<div class="col-sm-6 bordeDerecha"> -->
	<!-- 										<h4>Ingresos</h4> -->
	<!-- 				<div id="chartIngresosYear" class="grafico bordeAbajo"></div> -->
	<!-- 			</div> -->
	<!-- 			<div class="col-sm-6"> -->
	<!-- 				<div> -->
	<!-- 					<h3>Resultado del Año</h3> -->
	<%-- 					<span class="label label-pill label-success"><%=totalIngresosYear%></span> --%>
	<%-- 					- <span class="label label-pill label-danger"><%=totalGastosYear%></span> --%>
	<%-- 					= <span class="label label-pill label-info"><%=beneficioYear%></span> --%>
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</div> -->


</body>
</html>