<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	
	<jsp:attribute name="js">
		<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.js"></script>
		<script src="/SIA/resources/js/registration/grade_register.js"></script>
	</jsp:attribute>
	<jsp:attribute name="css">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.css"/>
	</jsp:attribute>
	
	<jsp:attribute name="title">
	Registro de Calificaciones
	</jsp:attribute>
	
	<jsp:attribute name="header">
		<jsp:include page="/WEB-INF/templates/header.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="left">
		<jsp:include page="/WEB-INF/templates/teacher_menu.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<jsp:include page="/WEB-INF/templates/footer.jsp"/>
	</jsp:attribute>
	
	<jsp:body>
		<div id="master">
		<div id="slave">
			<script type="text/javascript">loadFunction();</script>
			<c:if test="${noticeClass ne 'none'}">
				<div class="row p-4 justify-content-center" id="notice">
					<div class="col-6 rounded">
						<div class="row justify-content-end ${noticeClass}">
							<button type="button" onclick="hideNotice();" class="btn text-white">
								<i class="fa fa-window-close"></i> 
							</button>
						</div>
						<div class="row p-2 bg-light">
							<p class="col-12  text-center text-secondary">
								${output}
							</p>
						</div>
					</div>
				</div>
			</c:if>
			<div class="row my-4">
				<div class="col-12">
					<h2>Registro de Calificaciones</h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-12">
					<div class="row">
						<div class="col-12">
							<table class="table display" id="grade-list">
								<thead>
									<tr>
										<th>Estudiante</th>
										<th>Asignatura</th>
										<th>Calificación</th>
										<th>Descripción</th>
										<th>Estado</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="grade" items="${gradeList}">
										<tr>
											<td>${grade.enrollment.inscription.student.fullName}</td>
											<td>${grade.group.subject.name}</td>
											<td>
												<input type="text" id="grade-${grade.id}" name="grade-${grade.id}" value="${grade.gradeValue}"
												class="allow_decimal form-control" required>
											</td>
											<td>
												<input type="text" id="description-${grade.id}" name="description-${grade.id}" value="${grade.description}"
												class="form-control" required>
											</td>
											<td>
												<div class="form-check">
													<c:choose>
														<c:when test="${grade.status eq 'C'.charAt(0)}">
															<input class="form-check-input" type="radio" name="status-${grade.id}" 
															id="status-${grade.id}-C" value="C" checked>
														</c:when>
														<c:otherwise>
															<input class="form-check-input" type="radio" name="status-${grade.id}" 
															id="status-${grade.id}-C" value="C">
														</c:otherwise>
													</c:choose>
													<label class="form-check-label" for="status-${grade.id}-C">Cursando</label>
												</div>
												<div class="form-check">
													<c:choose>
														<c:when test="${grade.status eq 'A'.charAt(0)}">
															<input class="form-check-input" type="radio" name="status-${grade.id}" 
															id="status-${grade.id}-A" value="A" checked/>
														</c:when>
														<c:otherwise>
															<input class="form-check-input" type="radio" name="status-${grade.id}" 
															id="status-${grade.id}-A" value="A"/>
														</c:otherwise>
													</c:choose>
													<label class="form-check-label" for="status-${grade.id}-A">Aprobado</label>
												</div>
												<div class="form-check">
													<c:choose>
														<c:when test="${grade.status eq 'N'.charAt(0)}">
															<input class="form-check-input" type="radio" name="status-${grade.id}" 
															id="status-${grade.id}-N" value="N" checked/>
														</c:when>
														<c:otherwise>
															<input class="form-check-input" type="radio" name="status-${grade.id}" 
															id="status-${grade.id}-N" value="N"/>
														</c:otherwise>
													</c:choose>
													<label class="form-check-label" for="status-${grade.id}-N">Reprobado</label>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<br/>
					<div class="row justify-content-end">
						<button type="button" class="btn btn-primary" id="save-btn">Guardar</button>
					</div>
				</div>
			</div>
		</div>
		</div>
	</jsp:body>
	
</t:genericpage>
