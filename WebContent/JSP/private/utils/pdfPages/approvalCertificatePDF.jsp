<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	<jsp:body>
		<div class="row justify-content-center" id="pdf-content">
			<div class="col-12 text-center">
				<h2>Certificado de Aprobación de nivel</h2>
				<p>
					La institución educativa XXXXXXXXXX certifica que el estudiante ${enrollmentList[0].inscription.student.fullName},<br/> 
					con número de cédula ${enrollmentList[0].inscription.student.dni} ha aprobado de manera satisfactoria el/los niveles:<br/>
					<c:forEach var="enrollment" items="${enrollmentList}">
						<c:forEach var="g" items="${enrollment.gradeList}">
							- ${g.group.subject.name} - ${g.group.subject.level}<br/>
						</c:forEach>
					 </c:forEach>
					 de la carrera de ${enrollmentList[0].inscription.career.name}.<br/>
				</p>
				<p>
					Certificado emitido el día ${currentDate.getDayOfMonth()} de ${currentDate.getMonthValue()} del ${currentDate.getYear()}, <br/>
					en secretaria por ${userLocal.fullName}.
				</p>
			</div>
		</div>
	</jsp:body>
</t:genericpage>
