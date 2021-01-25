/**
 * 
 */
function loadFunction(){
	$(document).ready(function() {
	    var table = $('#grade-list').DataTable({
	        columnDefs: [{
	            orderable: false
	        }], 
			"language": {
	            "lengthMenu": "Mostrando _MENU_ por página",
	            "zeroRecords": "No se encontraron registros",
	            "info": "Página _PAGE_ de _PAGES_",
	            "infoEmpty": "No hay registros",
	            "infoFiltered": "(Filtrado de _MAX_ registros)",
				"search": "Buscar:"
	        }
	    });
	 
	    $('#save-btn').click( function() {
	        var data = table.$('input').serialize();
			var url = "/SIA/GradeRegister?option=update&" + data;
			console.log(url);
			jQuery('#master').load(`${url} #slave`, function(data, status){
				if (status == 'success') {
					loadFunction();
				}
			});
	        return false;
	    } );
	} );
	
	$(".allow_decimal").on("input", function(evt) {
	   var self = $(this);
	   self.val(self.val().replace(/[^0-9\.]/g, ''));
	   if ((evt.which != 46 || self.val().indexOf('.') != -1) && (evt.which < 48 || evt.which > 57)) 
	   {
	     evt.preventDefault();
	   }
	});
}