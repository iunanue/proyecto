<%@page import="controller.Config"%>
<%@page import="model.classes.Alerta"%>
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
<title>Personalizar Alertas</title>
</head>
<body>
	<script>
		<%
		Alerta alerta = (Alerta) request.getAttribute("alerta");
		%>
		$(document).ready(function() {
			
			<%
			if(alerta!=null){
				%>
				
				$('#alimentacionMonth').val("<%=alerta.getAlimentacionMonth()%>");
				$('#estudiosMonth').val("<%=alerta.getEstudiosMonth()%>");
				$('#suministrosMonth').val("<%=alerta.getSuministrosMonth()%>");
				$('#mantenimientoMonth').val("<%=alerta.getMantenimientoMonth()%>");
				$('#hipotecaAlquilerMonth').val("<%=alerta.getHipotecaAlquilerMonth()%>");
				$('#segurosMonth').val("<%=alerta.getSegurosMonth()%>");
				$('#ocioMonth').val("<%=alerta.getOcioMonth()%>");
				$('#otrosMonth').val("<%=alerta.getOtrosMonth()%>");
				
				$('#alimentacionYear').val("<%=alerta.getAlimentacionYear()%>");
				$('#estudiosYear').val("<%=alerta.getEstudiosYear()%>");
				$('#suministrosYear').val("<%=alerta.getSuministrosYear()%>");
				$('#mantenimientoYear').val("<%=alerta.getMantenimientoYear()%>");
				$('#hipotecaAlquilerYear').val("<%=alerta.getHipotecaAlquilerYear()%>");
				$('#segurosYear').val("<%=alerta.getSegurosYear()%>");
				$('#ocioYear').val("<%=alerta.getOcioYear()%>");
				$('#otrosYear').val("<%=alerta.getOtrosYear()%>");
			<%}
			else{
				System.out.println("llego fuera");
				%>$("#noAlertas").show();<%
			}
			%>
			
		});
																			
	</script>
		
	<jsp:include page="/common/userHeader.jsp" />
	<div class="contentWrapper">
				<div id="login-form" class="alertas-form">
					<h3>Personalizar alertas de Gasto</h3>
					<div id="noAlertas" style="display: none" class=" noAlertas paddingTop paddingLeft">
							<div class="alert alert-warning alertCustom" role="alert"><p>No ha definido aún ninguna alerta.</p></div>	
					</div>		
					<fieldset>
						<form method="POST" id="form" action="${pageContext.request.contextPath}/protected_area/personalizarAlertas">							
							<div class="row bordeAbajo">
								<div class="col-sm-6 bordeDerecha">
									<div class="paddingAnalisis ">
										<h2>Alertas del Año</h2>
										<label>Alimentación:<span class="required"> *</span></label> 
										<input id="alimentacionYear" type="number" name="alimentacionYear" step="0.01"> 
										<label>Estudios:<span class="required"> *</span></label> 
										<input id="estudiosYear" type="number" name="estudiosYear" step="0.01"> 
										<label>Suministros:<span class="required"> *</span></label> 
										<input id="suministrosYear" type="number" name="suministrosYear" step="0.01">
										<label>Mantenimiento:<span class="required"> *</span></label> 
										<input id="mantenimientoYear" type="number" name="mantenimientoYear" step="0.01">
										<label>Hipoteca/Alquiler:<span class="required"> *</span></label> 
										<input id="hipotecaAlquilerYear" type="number" name="hipotecaAlquilerYear" step="0.01">
										<label>Seguros:<span class="required"> *</span></label> 
										<input id="segurosYear" type="number" name="segurosYear" step="0.01">
										<label>Ocio:<span class="required"> *</span></label> 
										<input id="ocioYear" type="number" name="ocioYear" step="0.01">
										<label>Otros:<span class="required"> *</span></label> 
										<input id="otrosYear" type="number" name="otrosYear" step="0.01">
										
									</div>
								</div>
								<div class="col-sm-6">
									<div class="paddingAnalisis ">
										<h2>Alertas del Mes</h2>
										<label>Alimentación:<span class="required"> *</span></label> 
										<input id="alimentacionMonth" type="number" name="alimentacionMonth" step="0.01"> 
										<label>Estudios:<span class="required"> *</span></label> 
										<input id="estudiosMonth" type="number" name="estudiosMonth" step="0.01"> 
										<label>Suministros:<span class="required"> *</span></label> 
										<input id="suministrosMonth" type="number" name="suministrosMonth" step="0.01">
										<label>Mantenimiento:<span class="required"> *</span></label> 
										<input id="mantenimientoMonth" type="number" name="mantenimientoMonth" step="0.01">
										<label>Hipoteca/Alquiler:<span class="required"> *</span></label> 
										<input id="hipotecaAlquilerMonth" type="number" name="hipotecaAlquilerMonth" step="0.01">
										<label>Seguros:<span class="required"> *</span></label> 
										<input id="segurosMonth" type="number" name="segurosMonth" step="0.01">
										<label>Ocio:<span class="required"> *</span></label> 
										<input id="ocioMonth" type="number" name="ocioMonth" step="0.01">
										<label>Otros:<span class="required"> *</span></label> 
										<input id="otrosMonth" type="number" name="otrosMonth" step="0.01">
									</div>
								</div>
							</div>
							<input class="button blue" name="update" type="submit" value="Personalizar" onClick="return confirm('¿Desea personalizar las alertas?');">
						</form>
					</fieldset>


				<%
					if (request.getSession().getAttribute("mensaje") != null) {
				%>
				<div class="errormsg">
					<p class="errorfont"><%=request.getSession().getAttribute("mensaje")%></p>
				</div>
				<%
					}
					request.getSession().setAttribute("mensaje", null);
				%>
				</div>
	</div>
</body>
</html>>