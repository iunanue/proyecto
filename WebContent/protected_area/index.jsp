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
<title>Index</title>
</head>
<body>
	<jsp:include page="/common/userHeader.jsp" />
	<div class="jumbotron jumbotronPersonalizado">
		<div class="container text-center">
			<h1>
				Bienvenido/a
				<%=request.getUserPrincipal().getName()%></h1>
			<p>Some text that represents "Me"...</p>
		</div>
	</div>
	<jsp:include page="/common/footer.jsp" />
</body>
</html>