<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="left" fragment="true" %>
<%@attribute name="css" fragment="true" %>
<%@attribute name="js" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
   	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/SIA/resources/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/SIA/resources/css/fontawesome/all.min.css">
    <link rel="stylesheet" href="/SIA/resources/css/main.css">
    
    <jsp:invoke fragment="css"/>

    <script src="/SIA/resources/js/bootstrap/jquery-3.5.1.min.js"></script>
    <script src="/SIA/resources/js/bootstrap/popper.min.js"></script>
    <script src="/SIA/resources/js/bootstrap/bootstrap.min.js"></script>
	<script src="/SIA/resources/js/main.js"></script>
	
	<jsp:invoke fragment="js"/>

    <title><jsp:invoke fragment="title"/></title>
</head>
<body>
	<jsp:invoke fragment="header"/>
	<div class="container-fluid">
		<div class="row justify-content-center my-4" id="content">
			<c:if test="${isLogged and not isOnIndex}">
				<div class="col-3 m-2">
					<jsp:invoke fragment="left"/>
				</div>
			</c:if>
			<div class="col-8 m-2">
				<jsp:doBody/>
			</div>
		</div>
	</div>
	<jsp:invoke fragment="footer"/>
</body>
</html>

