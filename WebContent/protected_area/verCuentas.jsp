<%@page import="model.classes.Cuenta"%>
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
<title>Ver Cuentas</title>
</head>
<body>
	<%
		List <Cuenta> listaCuentas = (List) request.getAttribute("listaCuentas");
	%>

	<script type="text/javascript">
	  google.charts.load('current', {packages: ['corechart']});
	  google.charts.setOnLoadCallback(drawChart);
	  
	  function drawChart() {

		  
		  //Composicion
		  
		  var dataComposicion = new google.visualization.DataTable();
	      dataComposicion.addColumn('string', 'cuenta');
	      dataComposicion.addColumn('number');
	      dataComposicion.addRows(<%=listaCuentas.size()%>);
  		
  		<%
	      for(int i=0;i<listaCuentas.size();i++){
	      
		      if(listaCuentas.get(i).getSaldo()>=0){
		      %>
		     	 dataComposicion.setCell(<%=i%>, 0, '<%=listaCuentas.get(i).getDescripcion()%>');
		      	dataComposicion.setCell(<%=i%>, 1, <%=listaCuentas.get(i).getSaldo()%>);
		      <%
		      }
	      }
  		%>
  		
	      

		var optionsComposicion = {
				title : 'Composición',
				is3D: true,
// 				chartArea:{left:0,top:0,width:"100%",height:"100%"},
// 				height: 400,
// 				width: 450
		};

		var composicion = new google.visualization.PieChart(document
				.getElementById('composicion'));

	      composicion.draw(dataComposicion,optionsComposicion);

	
		  
		  //Saldo
		  
	    	var dataSaldo = new google.visualization.DataTable();
	    	dataSaldo.addColumn('string', 'cuenta');
	    	dataSaldo.addColumn('number');
	    	dataSaldo.addRows(<%=listaCuentas.size()%>);
	  		
	  		<%
		      for(int i=0;i<listaCuentas.size();i++){%>
		      dataSaldo.setCell(<%=i%>, 0, '<%=listaCuentas.get(i).getDescripcion()%>');
		      <%
				
		      }%>
	  		
	  		
		      
	  		<%
		      for(int i=0;i<listaCuentas.size();i++){%>
		     	 dataSaldo.setCell(<%=i%>, 1, <%=listaCuentas.get(i).getSaldo()%>);
		      <%}%>
	     

	      
	      var optionsSaldo = {
					is3D: true,
// 					chartArea:{left:50},
// 					width: 1100,
// 					min: 300,
// 					max: 1400,
			            legend:"none"
					    };
	      
	      
	      var saldo = new google.visualization.ColumnChart(document.getElementById('saldo'));
	      saldo.draw(dataSaldo,optionsSaldo);
	      
	      
	  }
	      
	     
	      
	</script>



	<jsp:include page="/common/userHeader.jsp" />

	<div class="contentWrapper">
		<h2 class="titulo2">Cuentas</h2>
		<div class="row">
			<div class="col-sm-6 bordeDerecha">
				<h4 class="titulo4">Listado de Cuentas</h4>
				<div class="tableContainer">
					<table class="table table-hover tableCuentas">
						<thead class="thead-propio">
							<tr class="total">
								<th>id</th>
								<th>Descripción</th>
								<th>Saldo</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<%
								for (int i = 0; i < listaCuentas.size(); i++) {
									Cuenta cuenta = listaCuentas.get(i);
							%>
							<tr>
								<td><%=cuenta.getId_cuenta()%></td>
								<td><%=cuenta.getDescripcion()%></td>
								<%
									if (cuenta.getSaldo() > 0) {
								%><td class="ingreso">+<%=cuenta.getSaldo()%></td>
								<%
									}
										if (cuenta.getSaldo() < 0) {
								%><td class="gasto"><%=cuenta.getSaldo()%></td>
								<%
									}
										if (cuenta.getSaldo() == 0) {
								%><td class="cero"><%=cuenta.getSaldo()%></td>
								<%
									}
								%>
								<td>
									<form method="POST" id="form"
										action="${pageContext.request.contextPath}/protected_area/selectUpdateDeleteCuenta">
										<input type="hidden" name="id_cuenta"
											value="<%=cuenta.getId_cuenta()%>">
										<button type="submit" class="btn btn-default" name="update">
											<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
										</button>
										<button type="submit" class="btn btn-default" name="delete"
											onClick="return confirm('¿Desea eliminar esta cuenta y todos los movimientos asociados a ella?');">
											<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
										</button>
									</form>
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-sm-6 bordeIzquierda">
				<div class="paddingAnalisis paddingIzquierda">
					<h4 class="titulo4">Composición de la cartera de Cuentas</h4>
					<div id="composicion" class="composicion"></div>
				</div>
			</div>
		</div>
		<div class="bordeAbajo paddingTop"></div>
		<div>
			<div class="paddingAnalisis">
				<h4 class="titulo4">Saldo de las Cuentas</h4>
				<div id="saldo" class="saldoCuentas"></div>
			</div>
		</div>



	</div>
</body>
</html>