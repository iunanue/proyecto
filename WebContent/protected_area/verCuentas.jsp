<%@page import="classes.Cuenta"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	      var chart = new google.visualization.ColumnChart(document.getElementById('visualization'));
	      chart.draw(data, {title:"Saldo de las cuentas existentes", 
	            width:600, height:400,
	            isStacked:"true",
	            legend:"none" });
	    }
	  
	</script>
	
	
	<jsp:include page="/common/userHeader.jsp" />
	
	<div class="contentWrapper">
		<h3>Cuentas</h3>
		<div id="visualization"></div>
		
		
	</div>
	


</body>
</html>