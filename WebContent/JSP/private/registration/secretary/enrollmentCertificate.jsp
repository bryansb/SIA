<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
	
	<jsp:attribute name="js">
		<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.debug.js"></script>
	</jsp:attribute>
	
	<jsp:attribute name="title">
	Certificados
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
		<script>
			
			$(document).ready(function() {
			    var table = $('#enrollment-table').DataTable({
			        columnDefs: [{
			            orderable: false
			        }], 
					"language": {
			            "lengthMenu": "Mostrando _MENU_ por página",
			            "zeroRecords": "No se encontraron registros",
			            "info": "Página _PAGE_ de _PAGES_",
			            "infoEmpty": "No hay registros",
			            "infoFiltered": "(Filtrado de _MAX_ registros)",
						"search": "Buscar:",
						"paginate": {
					        "first":      "Primero",
					        "last":       "Último",
					        "next":       "Siguiente",
					        "previous":   "Anterior"
					    }
			        }
			    });
			});
			
			function downloadEnrollment(enrollmentId){
				var url = "/SIA/EnrollmentCertificate?option=download&enrollmentId=" +  enrollmentId;
				$.get(url, function(data, status){
					data = $(data).find('#pdf-content').html();
					var doc = new jsPDF('p', 'mm', 'a4');
					doc.fromHTML(data);
				    doc.save('CER-MATR-' + new Date() + '.pdf');
				});
			}
			function searchStudent(){
				var form = $('#dni-form').serialize()
				var url = "/SIA/EnrollmentCertificate?" + form;
				document.location.href = url;
			}
			
		</script>
		<div class="row">
			<div class="col-12 my-4">
				<h2>Certificado de Matriculación</h2>
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
		<div id="master-inscription">
			<div id="slave-inscription">
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
				<div class="row justify-content-center">
					<div class="col-12">
						<script></script>
						<table class="table display" id="enrollment-table">
							<thead>
								<tr>
									<th>Nombre de Carrera</th>
									<th>Periodo</th>
									<th>Descargar Certificado</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="e" items="${enrollmentList}">
									<tr>
										<td>${e.inscription.career.name}</td>
										<td>${e.academicPeriod}</td>
										<td>
											<button type="button" class="btn btn-info" onclick="downloadEnrollment(${e.id});">
												<i class="fas fa-file-pdf"></i>
											</button>
										</td>
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
