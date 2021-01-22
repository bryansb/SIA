<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="left" fragment="true" %>
<%@attribute name="css" fragment="true" %>
<%@attribute name="js" fragment="true" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/SIA/resources/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/SIA/resources/css/fontawesome/all.min.css">
    
    <jsp:invoke fragment="css"/>

    <script src="/SIA/resources/js/bootstrap/jquery-3.5.1.min.js"></script>
    <script src="/SIA/resources/js/bootstrap/popper.min.js"></script>
    <script src="/SIA/resources/js/bootstrap/bootstrap.min.js"></script>

	<jsp:invoke fragment="js"/>

    <title><jsp:invoke fragment="title"/></title>
</head>
<body>
	<jsp:invoke fragment="header"/>
	<div class="container"> 
		<div class="row" id="content">
			<div class="col-4">
				<jsp:invoke fragment="left"/>
			</div>
			<div class="col-8">
				<jsp:doBody/>
			</div>
		</div>
	</div>
	<jsp:invoke fragment="footer"/>
</body>
</html>

