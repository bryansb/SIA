<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	<jsp:body>
	<div class="row justify-content-center" id="pdf-content">
		<h3>Record Académico</h3>
		<span>Est. ${inscription.student.fullName}</span>
		<h4>Carrera</h4>
		<span>${inscription.career.name}</span>
		<c:forEach var="enrollment" items="${inscription.enrollmentList}">
			<h4>Periodo Académico ${enrollment.academicPeriod}</h4>
			<table style="width:100%">
				<thead>
					<tr>
						<th>Asignatura</th>
						<th>Calificación</th>
						<th>Estado</th>
						<th>Nivel</th>
						<th>Carrera</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="grade" items="${enrollment.gradeList}">
						<tr>
							<td>${grade.group.subject.name}</td>
							<td>${grade.gradeValue}</td>
							<td>
								<c:choose>
									<c:when test="${grade.status eq 'A'.charAt(0)}">
										Aprobado
									</c:when>
									<c:when test="${grade.status eq 'N'.charAt(0)}">
										Reprobado
									</c:when>
								</c:choose>
							</td>
							<td>${grade.group.subject.level}</td>
							<td>${grade.group.subject.career.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:forEach>
		<p>
		Emitido el día ${currentDate.getDayOfMonth()} de ${currentDate.getMonthValue()} del ${currentDate.getYear()}, <br/>
		en secretaria por ${userLocal.fullName}.
		</p>
	</div>
</jsp:body>

</t:genericpage>