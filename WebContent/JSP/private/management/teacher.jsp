<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	<jsp:attribute name="js">
		<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.js"></script>
		<script src="/SIA/resources/js/management/crud_teacher.js"></script>
	</jsp:attribute>
	
	<jsp:attribute name="css">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.css"/>
	</jsp:attribute>
	
	<jsp:attribute name="title">
		Profesor
	</jsp:attribute>
	
	<jsp:attribute name="header">
		<jsp:include page="/WEB-INF/templates/header.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="left">
		<jsp:include page="/WEB-INF/templates/admin_menu.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<jsp:include page="/WEB-INF/templates/footer.jsp"/>
	</jsp:attribute>
	
	
	<jsp:body>
		<div class="row justify-content-center">
			<div class="col-12" id="teacherForm">
				<div class="row justify-content-center" id="teacher-create-update">
					<jsp:include page="teacherPages/createTeacher.jsp"/>
				</div>
				
	   			<div class="row justify-content-center">
					<jsp:include page="teacherPages/listTeacher.jsp"/>
				</div>
			</div>
		</div>
	</jsp:body>
</t:genericpage>