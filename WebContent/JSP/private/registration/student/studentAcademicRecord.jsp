<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	
	<jsp:attribute name="title">
	Record Académico
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
			<div class="col-12">
				<h3>Mi Record Académico</h3>
				<span>Est. ${student.fullName}</span>
			</div>
		</div>
		<div class="row mt-4">
			<div class="col-12">
				<c:forEach var="enrollment" items="${enrollmentList}">
						<div class="row mt-4 page-header">
							<div class="col-12 ">
								<h4>Periodo Académico ${enrollment.academicPeriod}</h4>
							</div>
							<div class="col-12 table-responsive">
								<table class="table table-striped">
									<thead class="thead-dark">
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
							</div>
						</div>
				</c:forEach>
			</div>
		</div>
		
	</jsp:body>
	
</t:genericpage>