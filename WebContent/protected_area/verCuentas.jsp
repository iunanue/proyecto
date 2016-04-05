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
	      // Define the chart to be drawn.
	    	var data = new google.visualization.DataTable();
	  		data.addColumn('string', 'cuenta');
	  		data.addColumn('number');
	  		data.addRows(<%=listaCuentas.size()%>);
	  		
	  		<%
		      for(int i=0;i<listaCuentas.size();i++){%>
		  		data.setCell(<%=i%>, 0, '<%=listaCuentas.get(i).getDescripcion()%>');
		      <%
				System.out.println(listaCuentas.get(i).getDescripcion());	
		      System.out.println(listaCuentas.get(i).getSaldo());	
		      }%>
	  		
	  		
		      
	  		<%
		      for(int i=0;i<listaCuentas.size();i++){%>
		      	data.setCell(<%=i%>, 1, <%=listaCuentas.get(i).getSaldo()%>);
		      <%}%>
	     

	      // Instantiate and draw the chart.
	      
	      var options = {
		    		  title:"", 
		    		  width:600,
		    		  height:500,
		    		  chartArea:{left:50,
		    			    top: 20,
		    			    width: '50%',
		    			    height: '60%',},
			            
			            isstacked:"true",
			            legend:"none"
					    };
	      
	      var chart = new google.visualization.ColumnChart(document.getElementById('visualization'));
	      chart.draw(data,options);
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
		
		<h4 class="titulo4">Saldo de las cuentas</h4>
		<div id="visualization"></div>
	</div>
	

	

</body>
</html>