/**
 * 
 */


function loadDataTable() {
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
}


			
function updateEnrollmentStatus(enrollmentId, status, dni){
	var url = "/SIA/EnrollmentStatusUpdate?option=update&enrollmentId=" +  enrollmentId + "&status=" + status + "&dni=" + dni;
	jQuery('#master-enrollment').load(`${url} #slave-enrollment`, function(data, status){
		if(status == 'success'){
			loadDataTable();
		}
	});
}
function searchStudent(){
	var form = $('#dni-form').serialize()
	var url = "/SIA/EnrollmentStatusUpdate?" + form;
	document.location.href = url;
}