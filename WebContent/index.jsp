<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<body>
<jsp:include page="/common/header.jsp" />
	<div class="jumbotron jumbotronPersonalizado">
		<div class="container text-center">
			<h1>SaveApp</h1>
			<p>Registrar, Analizar, Ahorrar</p>	
		</div>
	</div>
	<header class="major container 75%">
					<h2>Empieza a ahorrar en solo 3 pasos</h2>
				</header>

				<div class="box alt container">
					<section class="feature left">
						<a href="#" class="image icon fa-signal"><img src="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/img/registrar.jpg" alt="" /></a>
						<div class="content">
							<h3>Registrar</h3>
							<p>El primer paso en el proceso del ahorro es registrar los movimientos de tu d�a a d�a. Para ello haz click en la pesta�a 'Movimientos' de la barra superior y escoge la opci�n 'Registrar Movimiento'.</p>
							<p>��nimo! �Solo quedan 2 pasos para empezar a ahorrar!</p>
						</div>
					</section>
					<section class="feature right">
						<a href="#" class="image icon fa-code"><img src="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/img/analizar.png" alt="" /></a>
						<div class="content">
							<h3>Analizar</h3>
							<p>Una vez registrados los movimientos del d�a a d�a, es necesario analizarlos. Para ello, puedes hacer click en la pesta�a 'Movimientos' de la barra superior y escoger la opci�n 'Ver Movimientos', o si lo que prefieres es un an�lisis m�s profundo, haz click en la pesta�a 'An�lisis' de la barra superior y escoge la opci�n que desees.</p>
							<p>�Solo queda 1 paso!</p>
						</div>
					</section>
					<section class="feature left">
						<a href="#" class="image icon fa-mobile"><img src="${pageContext.request.contextPath}/bootstrap-3.3.6-dist/img/ahorrar.png" alt="" /></a>
						<div class="content">
							<h3>Ahorrar</h3>
							<p>Ahora que has obtenido informaci�n �til a trav�s de la herramienta de an�lisis ofrecida, es hora de decidir cu�les son l�mites de gastos que quieres establecer. Para ello haz click en la pesta�a 'Alertas' de la barra superior de herramientas, y escoge la opci�n 'Personalizar Alertas'.</p>
							<p>�Ahora te toca a t�! �A ahorrar!</p>
						</div>
					</section>
				</div>
<jsp:include page="/common/footer.jsp" />
</body>
</html>
