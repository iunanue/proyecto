<%@page import="controller.Config"%>
<%@page import="model.classes.Alerta"%>
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
<title>Ver Alertas</title>
</head>
<body>
	<%
		Alerta alerta = (Alerta) request.getAttribute("alerta");
		List<Float> listaAlertasYear = (List) request.getAttribute("listaAlertasYear");
		List<Float> listaAlertasMonth = (List) request.getAttribute("listaAlertasMonth");
		
	%>


	<%if(alerta != null){
	%>
	
		<script type="text/javascript">
	 google.charts.load('current', {'packages':['bar']});
	  google.charts.setOnLoadCallback(drawChart);
	  
	  function drawChart() {

		  var options = {
					width: 400,
			        height: 150,
			        bar: {groupWidth: "95%"},
			        bars: 'horizontal', // Required for Material Bar Charts.
			        };
	      
		  //YEAR
		  
				var dataAlimentacionYear = new google.visualization.arrayToDataTable(
				  [['', 'Tope', 'Gastado'],
				   ['Alimentación', <%=alerta.getAlimentacionYear()%>, <%=listaAlertasYear.get(0)%>]]);
				 		
				
				var chartAlimentacionYear = new google.charts.Bar(document
							.getElementById('chartAlimentacionYear'));
				
				chartAlimentacionYear.draw(dataAlimentacionYear,options);
					
			
				//	
					
				var dataEstudiosYear = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Estudios', <%=alerta.getEstudiosYear()%>, <%=listaAlertasYear.get(1)%>]]);
						
				
				var chartEstudiosYear = new google.charts.Bar(document
						.getElementById('chartEstudiosYear'));
				
				chartEstudiosYear.draw(dataEstudiosYear,options);

				//	

				var dataSuministrosYear = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Suministros', <%=alerta.getSuministrosYear()%>, <%=listaAlertasYear.get(2)%>]]);
						
				
				var chartSuministrosYear = new google.charts.Bar(document
						.getElementById('chartSuministrosYear'));
				
				chartSuministrosYear.draw(dataSuministrosYear,options);
				
				//	

				var dataMantenimientoYear = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Mantenimiento', <%=alerta.getMantenimientoYear()%>, <%=listaAlertasYear.get(3)%>]]);
						
				
				var chartMantenimientoYear = new google.charts.Bar(document
						.getElementById('chartMantenimientoYear'));
				
				chartMantenimientoYear.draw(dataMantenimientoYear,options);
				
				//	

				var dataHipotecaAlquilerYear = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Hipoteca/Alquiler', <%=alerta.getHipotecaAlquilerYear()%>, <%=listaAlertasYear.get(4)%>]]);
						
				
				var chartHipotecaAlquilerYear = new google.charts.Bar(document
						.getElementById('chartHipotecaAlquilerYear'));
				
				chartHipotecaAlquilerYear.draw(dataHipotecaAlquilerYear,options);
				
				//	

				var dataSegurosYear = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Seguros', <%=alerta.getSegurosYear()%>, <%=listaAlertasYear.get(5)%>]]);
						
				
				var chartSegurosYear = new google.charts.Bar(document
						.getElementById('chartSegurosYear'));
				
				chartSegurosYear.draw(dataSegurosYear,options);
				
				//	

				var dataOcioYear = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Ocio', <%=alerta.getOcioYear()%>, <%=listaAlertasYear.get(6)%>]]);
						
				
				var chartOcioYear = new google.charts.Bar(document
						.getElementById('chartOcioYear'));
				
				chartOcioYear.draw(dataOcioYear,options);
				
				//	

				var dataOtrosYear = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Otros', <%=alerta.getOtrosYear()%>, <%=listaAlertasYear.get(7)%>]]);
						
				
				var chartOtrosYear = new google.charts.Bar(document
						.getElementById('chartOtrosYear'));
				
				chartOtrosYear.draw(dataOtrosYear,options);
	
	
 			//MONTH

				var dataAlimentacionMonth = new google.visualization.arrayToDataTable(
						 [['', 'Tope', 'Gastado'],
				   		   ['Alimentación', <%=alerta.getAlimentacionMonth()%>, <%=listaAlertasMonth.get(0)%>]]);
						
				
				var chartAlimentacionMonth = new google.charts.Bar(document
						.getElementById('chartAlimentacionMonth'));
				
				chartAlimentacionMonth.draw(dataAlimentacionMonth,options);
				
				//
				
				
				var dataEstudiosMonth = new google.visualization.arrayToDataTable(
				   		  [['', 'Tope', 'Gastado'],
				   		   ['Estudios', <%=alerta.getEstudiosMonth()%>, <%=listaAlertasMonth.get(1)%>]]);
						
				
				var chartEstudiosMonth = new google.charts.Bar(document
						.getElementById('chartEstudiosMonth'));
				
				chartEstudiosMonth.draw(dataEstudiosMonth,options);
				
				//	
					

				var dataSuministrosMonth = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Suministros', <%=alerta.getSuministrosMonth()%>, <%=listaAlertasYear.get(2)%>]]);
						
				
				var chartSuministrosMonth = new google.charts.Bar(document
						.getElementById('chartSuministrosMonth'));
				
				chartSuministrosMonth.draw(dataSuministrosMonth,options);
				
				//

				var dataMantenimientoMonth = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Mantenimiento', <%=alerta.getMantenimientoMonth()%>, <%=listaAlertasMonth.get(3)%>]]);
						
				
				var chartMantenimientoMonth = new google.charts.Bar(document
						.getElementById('chartMantenimientoMonth'));
				
				chartMantenimientoMonth.draw(dataMantenimientoMonth,options);
				
				//	

				var dataHipotecaAlquilerMonth = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Hipoteca/Alquiler', <%=alerta.getHipotecaAlquilerMonth()%>, <%=listaAlertasMonth.get(4)%>]]);
						
				
				var chartHipotecaAlquilerMonth = new google.charts.Bar(document
						.getElementById('chartHipotecaAlquilerMonth'));
				
				chartHipotecaAlquilerMonth.draw(dataHipotecaAlquilerMonth,options);
				
				//	

				var dataSegurosMonth = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Seguros', <%=alerta.getSegurosMonth()%>, <%=listaAlertasMonth.get(5)%>]]);
						
				
				var chartSegurosMonth = new google.charts.Bar(document
						.getElementById('chartSegurosMonth'));
				
				chartSegurosMonth.draw(dataSegurosMonth,options);
				
				//	

				var dataOcioMonth = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Ocio', <%=alerta.getOcioMonth()%>, <%=listaAlertasMonth.get(6)%>]]);
						
				
				var chartOcioMonth = new google.charts.Bar(document
						.getElementById('chartOcioMonth'));
				
				chartOcioMonth.draw(dataOcioMonth,options);
				
				//	

				var dataOtrosMonth = new google.visualization.arrayToDataTable(
				    		  [['', 'Tope', 'Gastado'],
				    		   ['Otros', <%=alerta.getOtrosMonth()%>, <%=listaAlertasMonth.get(7)%>]]);
						
				
				var chartOtrosMonth = new google.charts.Bar(document
						.getElementById('chartOtrosMonth'));
				
				chartOtrosMonth.draw(dataOtrosMonth,options);
		}
	</script>
	<%
	}
	%>
	<jsp:include page="/common/userHeader.jsp" />

	<jsp:include page="/common/userHeader.jsp" />

	<div class="contentWrapperAnalisis">
		<h1 class="titulo1Alertas">Alertas</h1>
			<%if(alerta != null){
			%>
							<div class="row bordeAbajo">
							<div class="col-sm-6 bordeDerecha">
									<div class="paddingAnalisis">
											<h3>Alertas del Año</h3>
											<div class="paddingAnalisis bordeAbajo">
												<label>Alimentación</label> 
												<div id="chartAlimentacionYear" class="paddingLeft"></div> 
											</div>
							
											<div class="paddingAnalisis bordeAbajo">
												<label>Estudios</label> 
												<div id="chartEstudiosYear" class="paddingLeft"></div> 
											</div>
							
											<div class="paddingAnalisis bordeAbajo">
												<label>Suministros</label> 
												<div id="chartSuministrosYear" class="paddingLeft"></div> 
											</div> 
											
											<div class="paddingAnalisis bordeAbajo">
												<label>Mantenimiento</label> 
												<div id="chartMantenimientoYear" class="paddingLeft"></div> 
											</div> 
							
											<div class="paddingAnalisis bordeAbajo">
												<label>Hipoteca/Alquiler</label> 
												<div id="chartHipotecaAlquilerYear" class="paddingLeft"></div> 
											</div> 
											
											<div class="paddingAnalisis bordeAbajo">
												<label>Seguros</label> 
												<div id="chartSegurosYear" class="paddingLeft"></div> 
											</div> 
											
											<div class="paddingAnalisis bordeAbajo">
												<label>Ocio</label> 
												<div id="chartOcioYear" class="paddingLeft"></div> 
											</div> 
											
											<div class="paddingAnalisis">
												<label>Otros</label> 
												<div id="chartOtrosYear" class="paddingLeft"></div> 
											</div>  
									</div>
							</div>
						<div class="col-sm-6">
							<div class="paddingAnalisis">
								<h3>Alertas del Mes</h3>
								<div class="paddingAnalisis bordeAbajo">
									<label>Alimentación</label> 
									<div id="chartAlimentacionMonth" class="paddingLeft"></div> 
								</div>
					
								<div class="paddingAnalisis bordeAbajo">
									<label>Estudios</label> 
									<div id="chartEstudiosMonth" class="paddingLeft"></div> 
								</div>
					
								<div class="paddingAnalisis bordeAbajo">
									<label>Suministros</label> 
									<div id="chartSuministrosMonth" class="paddingLeft"></div> 
								</div> 
											
								<div class="paddingAnalisis bordeAbajo">
									<label>Mantenimiento</label> 
									<div id="chartMantenimientoMonth" class="paddingLeft"></div> 
								</div> 
				
								<div class="paddingAnalisis bordeAbajo">
									<label>Hipoteca/Alquiler</label> 
									<div id="chartHipotecaAlquilerMonth" class="paddingLeft"></div> 
								</div> 
								
								<div class="paddingAnalisis bordeAbajo">
									<label>Seguros</label> 
									<div id="chartSegurosMonth" class="paddingLeft"></div> 
								</div> 
								
								<div class="paddingAnalisis bordeAbajo">
									<label>Ocio</label> 
									<div id="chartOcioMonth" class="paddingLeft"></div> 
								</div> 
								
								<div class="paddingAnalisis">
									<label>Otros</label> 
									<div id="chartOtrosMonth" class="paddingLeft"></div> 
								</div>  
							</div>	
					</div>
			</div>	
			<%
			}
			else{
			%>
			<h3>No ha establecido ninguna alerta.</h3>
			<p>Si lo desea, pude dirigirse a la opción <a href="${pageContext.request.contextPath}/protected_area/loadPersonalizarAlertas">Personalizar Alertas</a> para definir las alertas que desee.</p>
			<%
			}
			%>		
		</div>
</body>
</html>