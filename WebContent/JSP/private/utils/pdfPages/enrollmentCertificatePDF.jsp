<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	<jsp:body>
		<div class="row justify-content-center" id="pdf-content">
			<div class="col-12 text-center">
				<h2>Certificado de Matriculación</h2>
				<p>
					La institución educativa XXXXXXXXXX certifica que el estudiante ${enrollment.inscription.student.fullName}, <br/>
					con número de cédula ${enrollment.inscription.student.dni} se encuentra debidamente matriculado en el<br/>
					periodo lectivo ${enrollment.academicPeriod} actual.<br/>
				</p>
				<p>
					Certificado emitido el día ${currentDate.getDayOfMonth()} de ${currentDate.getMonthValue()} del ${currentDate.getYear()}, <br/>
					en secretaria por ${userLocal.fullName}.
				</p>
			</div>
		</div>
	</jsp:body>
</t:genericpage>
