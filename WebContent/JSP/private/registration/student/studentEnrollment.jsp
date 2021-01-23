<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	<jsp:attribute name="css">
		<script src="/SIA/resources/css/jquery.dataTables.min.css"></script>
	</jsp:attribute>
	<jsp:attribute name="js">
		<script src="/SIA/resources/js/student_enrollment.js"></script>
	</jsp:attribute>
	
	<jsp:attribute name="title">
	Matriculación
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
			<c:choose>
				<c:when test="${level == 0}">
					<jsp:include page="enrollmentPages/subjectSelection.jsp">
						<jsp:param value="${inscription}" name="inscription"/>
						<jsp:param value="${subjectList}" name="subjectList"/>
					</jsp:include>
				</c:when>
			</c:choose>
		</div>
	</jsp:body>
	
</t:genericpage>
