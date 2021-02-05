<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	<jsp:attribute name="js">
		<script src="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.js"></script>
		<script src="/SIA/resources/js/registration/update_enrollment.js"></script>
	</jsp:attribute>
	<jsp:attribute name="css">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.css"/>
	</jsp:attribute>
	
	<jsp:attribute name="title">
	Actualizar Matriculaciones
	</jsp:attribute>
	
	<jsp:attribute name="header">
		<jsp:include page="/WEB-INF/templates/header.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="left">
		<jsp:include page="/WEB-INF/templates/secretary_menu.jsp"/>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<jsp:include page="/WEB-INF/templates/footer.jsp"/>
	</jsp:attribute>
	
	<jsp:body>
		<script type="text/javascript">loadDataTable();</script>
		<div class="row">
			<div class="col-12 my-4">
				<h2>Actualizar Matriculaciones</h2>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-6 my-2">
				<form onsubmit="searchStudent(); return false;" id="dni-form">
					<div class="input-group mb-3">
				  		<input type="text" class="form-control" placeholder="Cédula del Estudiante" 
				  		aria-label="Cédula del Estudiante" aria-describedby="basic-addon" name="dni">
				  	
					  	<div class="input-group-append">
					    	<button class="btn btn-primary" id="basic-addon">
					    		<i class="fas fa-search"></i>
					    	</button>
					 	</div>
				 	
					</div>
				</form>
			</div>
		</div>
		<div class="row my-4"> 
			<div class="col-12">
				<h5>Estudiante: ${enrollmentList[0].inscription.student.fullName}</h5>
			</div>
		</div>
		<div class="row my-4"> 
			<div class="col-12">
				<h3>Matrículas:</h3>
			</div>
		</div>
	<div id="master-enrollment">
	<div id="slave-enrollment">
		<div class="row justify-content-center">
			<div class="col-12">
				<table class="table display" id="enrollment-table">
					<thead>
						<tr>
							<th>Nombre de Carrera</th>
							<th>Periodo</th>
							<th>Estado</th>
							<th>Estado</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="e" items="${enrollmentList}">
							<tr>
								<td>${e.inscription.career.name}</td>
								<td>${e.academicPeriod}</td>
								<c:choose>
									<c:when test="${e.status eq 'C'.charAt(0)}">
										
										<td>
											<button type="button" class="btn btn-outline-info" disabled>
												Cursando
											</button>
										</td>
										<td>
											<button type="button" class="btn btn-success" 
											onclick="updateEnrollmentStatus(${e.id}, 'E', '${e.inscription.student.dni}');">
												<i class="fas fa-edit"></i> Terminar
											</button>
										</td>
										
									</c:when>
									<c:otherwise>
									
										<td>
											<button type="button" class="btn btn-info" 
											onclick="updateEnrollmentStatus(${e.id}, 'C', '${e.inscription.student.dni}');">
												<i class="fas fa-edit"></i> Cursar
											</button>
										</td>
										<td>
											<button type="button" class="btn btn-outline-success" disabled>
												Terminado
											</button>
										</td>
										
									</c:otherwise>
								</c:choose>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</div>
	</jsp:body>
	
</t:genericpage>
