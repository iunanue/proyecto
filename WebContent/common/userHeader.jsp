<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/protected_area/index.jsp">SaveApp</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/protected_area/index.jsp">Home</a></li>
					
				<li><a class="dropdown-toggle" data-toggle="dropdown" href="#">Movimientos<span class="caret"></span></a>
        			<ul class="dropdown-menu">
          				<li><a href="${pageContext.request.contextPath}/protected_area/registrarMovimiento">Registrar</a></li>
			        	<li role="separator" class="divider"></li>
			         	<li><a href="${pageContext.request.contextPath}/protected_area/verMovimientos">Ver Movimientos</a></li>
			         	<li role="separator" class="divider"></li>
			         	<li><a href="${pageContext.request.contextPath}/protected_area/loadConsultaMovimientos">Generar consulta</a></li>
        			</ul>
      			</li>
      			
      			<li><a class="dropdown-toggle" data-toggle="dropdown" href="#">Cuentas<span class="caret"></span></a>
        			<ul class="dropdown-menu">
          				<li><a href="${pageContext.request.contextPath}/protected_area/nuevaCuenta.jsp">Nueva Cuenta</a></li>
			        	<li role="separator" class="divider"></li>
			         	<li><a href="${pageContext.request.contextPath}/protected_area/verCuentas">Ver Cuentas</a></li>
			         	<li role="separator" class="divider"></li>
			         	<li><a href="${pageContext.request.contextPath}/protected_area/loadConsultaMovimientos">Generar consulta</a></li>
        			</ul>
      			</li>
      			
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a
					href="${pageContext.request.contextPath}/protected_area/miCuenta"><span
						class="glyphicon glyphicon-user"></span> <%=request.getUserPrincipal().getName()%></a></li>

				<li><a href="${pageContext.request.contextPath}/Logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>

			</ul>
		</div>
	</div>
	</nav>
	<%System.out.println(request.getSession().getAttribute("username")); %>
</body>
</html>