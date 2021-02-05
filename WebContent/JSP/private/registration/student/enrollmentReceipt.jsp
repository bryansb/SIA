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
	<jsp:attribute name="css">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.23/datatables.min.css"/>
	</jsp:attribute>
	
	<jsp:attribute name="title">
		Comprobantes de matrículas
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
				var url = "/SIA/EnrollmentReceipt?option=download&enrollmentId=" +  enrollmentId;
				$.get(url, function(data, status){
					data = $(data).find('#pdf-content').html();
					var doc = new jsPDF('p', 'mm', 'a4');
					doc.fromHTML(data);
				    doc.save('COM-' + new Date() + '.pdf');
				});
			}
		</script>
		<div class="row">
			<div class="col my-4">
				<h2>Emisión de Comprobantes</h2>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 my-4">
				<table class="table display p-2" id="enrollment-table">
					<thead>
						<tr>
							<th>Fecha</th>
							<th>Periodo Académico</th>
							<th>Descargar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="enrollment" items="${enrollmentList}">
							<tr>
								<td>${enrollment.date.time}</td>
								<td>${enrollment.academicPeriod}</td>
								<td><button type="button" class="btn btn-info" onclick="downloadEnrollment(${enrollment.id});"><i class="fas fa-file-pdf"></i></button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</jsp:body>
</t:genericpage>
