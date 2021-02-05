<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	
	<jsp:attribute name="title">
		Horario de Estudiante
	</jsp:attribute>
	
	<jsp:attribute name="header">
		<jsp:include page="/WEB-INF/templates/header.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="left">
		<jsp:include page="/WEB-INF/templates/student_menu.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<jsp:include page="/WEB-INF/templates/footer.jsp"/>
	</jsp:attribute>
	
	
	<jsp:body>
		<div class="row justify-content-center">
			<div class="col-12">
				<div>
					<h2>Mi Horario </h2>
					<span>
						Carrera: ${enrollment.inscription.career.name};
					</span>
					<table class="table display" id="table-content">
						<thead>
							<tr>
								<th scope="col">Asignatura</th>
								<th scope="col">Horario</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="tableCareer" scope="request" value="${careers}" />
					    	<c:forEach var="gr" items="${enrollment.gradeList}">
								<tr>
									<td>${gr.group.subject.name}</td>
									<td>${gr.group.getScheduleToString()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</jsp:body>
</t:genericpage>