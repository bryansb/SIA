<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	
	<jsp:attribute name="js">
		<script src="/SIA/resources/js/offer/group.js"></script>
	</jsp:attribute>
	
	<jsp:attribute name="title">
		Grupo
	</jsp:attribute>
	
	<jsp:attribute name="header">
		<jsp:include page="/WEB-INF/templates/header.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="left">
		<!-- Editar al menú que corresponda -->
		<jsp:include page="/WEB-INF/templates/student_menu.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<jsp:include page="/WEB-INF/templates/footer.jsp"/>
	</jsp:attribute>
	
	
	<jsp:body>
		<div class="row justify-content-center">
			<div class="col-6">
				<div class="row justify-content-center">
					<jsp:include page="groupPages/createGroup.jsp"/>
				</div>
				
	   			<div class="row justify-content-center">
					<jsp:include page="groupPages/listGroup.jsp"/>
				</div>
			</div>
		</div>
	</jsp:body>
	
</t:genericpage>