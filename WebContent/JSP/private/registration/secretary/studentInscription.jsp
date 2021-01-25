<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	
	<jsp:attribute name="js">
		<script src="/SIA/resources/js/registration/student_inscription.js"></script>
	</jsp:attribute>
	
	<jsp:attribute name="title">
	Inscripción
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
			<c:if test="${noticeClass ne 'none'}">
				<div class="row p-4" id="notice">
					<div class="col-12 rounded">
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
			<c:choose>
				<c:when test="${option eq 'createStudentProcess'}">
					<jsp:include page="inscriptionPages/createStudent.jsp"/>
				</c:when>
				<c:otherwise>
					<div class="row my-4">
						<div class="col-12">
							<h2>Inscripción</h2>
						</div>
					</div>
					<div class="row justify-content-center">
						<div class="col-6">
							<form action="/SIA/InscriptionController" method="GET">
								<div class="input-group mb-3">
									<input type="hidden" name="option" value="inscribe"/>
							  		<input type="text" class="form-control" placeholder="Cédula del Estudiante" 
							  		aria-label="Cédula del Estudiante" aria-describedby="basic-addon" name="dni">
							  	
								  	<div class="input-group-append">
								    	<button class="input-group-text btn btn-primary" id="basic-addon"><i class="fas fa-search"></i></button>
								 	</div>
							 	
								</div>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<input type="hidden" name="studentId" id="studentId" value="${student.id}"/>
							<table class="table">
								<tbody>
									<tr >
										<th scope="row" class="text-right">Cédula: </th>
										<td>${student.dni}</td>
									</tr>
									<tr>
										<th scope="row" class="text-right">Nombre completo: </th>
										<td>${student.fullName}</td>
									</tr>
									<tr >
										<th scope="row" class="text-right">Correo: </th>
										<td>${student.email}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<div class="form-group">
								<label for="careerId">Carrera:</label>
								<select class="custom-select custom-select-md" size="10" id="careerId" name="careerId" required>
									<option value="" selected>Seleccione una carrera</option>
									<c:forEach items="${careerList}" var="career">
										<option value="${career.id}">${career.name}</option>
									</c:forEach>
								</select>
								<div class="invalid-feedback">Seleccione una carrera</div>
							</div>
						</div>
					</div>
					<br/>
					<div class="row justify-content-between">
						<button type="button" class="btn btn-danger"> 
							<i class="fas fa-window-close"></i> Cancelar
						</button>
						<button type="button" class="btn btn-success" onclick="createInscription();"> 
							<i class="fas fa-check-square"></i> Crear
						</button>
					</div>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</jsp:body>
</t:genericpage>
