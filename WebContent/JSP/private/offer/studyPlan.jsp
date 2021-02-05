<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	
	<jsp:attribute name="title">
		Plan de estudio
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
		<div class="row ">
			<div class="col-12">
				<h2>Plan de Estudio</h2>
			</div>
		</div>
		<div class="row ">
			<div class="col-12">
				<h5>Seleccione Carrera</h5>
				<form action="/SIA/StudyPlan" method="post">
					<input type="hidden" name="option" value="find"/>
					<div class="from from-grouo">
						<select name="careerId" class="form-control" required>
				 			<option value="NaN">Seleccione</option>
					    	<c:forEach var="car" items="${careers}">
				    			<option value="${car.id}">${car.name}</option>
							</c:forEach>
						</select>
						<div class="from from-grouo">
							<input type="submit" value="Listar" class="btn btn-primary"/>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-12">
				<div>
					
					<table class="table display" id="table-content">
						<thead>
							<tr>
								<th scope="col">Asignatura</th>
								<th scope="col">Nivel</th>
							</tr>
						</thead>
						<tbody>
					    	<c:forEach var="sub" items="${subjects}">
								<tr>
									<td>${sub.name}</td>
									<td>${sub.level}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</jsp:body>
</t:genericpage>