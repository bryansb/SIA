<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	
	<jsp:attribute name="js">
		<script src="/SIA/resources/js/registration/student_enrollment.js"></script>
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
		<div id="master">
		<div id="slave">
			<c:choose>
				<c:when test="${level == 0}">
					<div class="row justify-content-center">
						<jsp:include page="enrollmentPages/subjectSelection.jsp">
							<jsp:param value="${inscription}" name="inscription"/>
							<jsp:param value="${subjectList}" name="subjectList"/>
						</jsp:include>
					</div>
					<div class="row justify-content-between">
						<button type="button" class="btn btn-primary disabled"> 
							<i class="fa fa-arrow-circle-left"></i> Atrás
						</button>
						<button type="button" class="btn btn-primary" onclick="subjectSelected();"> 
							Siguiente <i class="fa fa-arrow-circle-right"></i>
						</button>
					</div>
				</c:when>
				<c:when test="${level == 1}">
					<div class="row justify-content-center">
						<jsp:include page="enrollmentPages/groupSelection.jsp">
							<jsp:param value="${inscription}" name="inscription"/>
							<jsp:param value="${subjectList}" name="subjectList"/>
						</jsp:include>
					</div>
					<div class="row justify-content-between">
						<button type="button" class="btn btn-primary" onclick="previousLevel(${level});"> 
							<i class="fa fa-arrow-circle-left"></i> Atrás
						</button>
						<button type="button" class="btn btn-primary" onclick="subjectSelected();"> 
							Siguiente <i class="fa fa-arrow-circle-right"></i>
						</button>
					</div>
				</c:when>
			</c:choose>
		</div>
		</div>
	</jsp:body>
</t:genericpage>
