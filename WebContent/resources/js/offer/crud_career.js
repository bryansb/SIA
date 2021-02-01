function createCareer(f_id){
	var form = $("#" + f_id);
//	$.post("/SIA/CareerController")
//		var msg = res.split("&", 2);
//		showNotice(msg[0], msg[1]);
//		jQuery('#table_product').load('/sgrc/ListProduct #table_content');

	if(valid(form)){
		$.post("/SIA/CareerController", form.serialize(), function(res, est, jqXHR){
			var msg = res.split("&", 2);
			showNotice(msg[0], msg[1]);
			jQuery('#table_product').load('/sgrc/ListProduct #table_content');
		});
	} else {
		showNotice("Complete los campos resaltados en rojo", "e_notice_warning")
	}
}

function readCareer(careerId){
	var url = '/SIA/CareerController?option=read&careerId=' + careerId
	jQuery('#careerForm').load(`${url} #career-create-update`, function(response, status){
//		location.reload(true);
	});
}

function updateCareer(){
	
}

function deleteCareer(id){
	var url = '/SIA/CareerController?option=delete&id=' + id
	jQuery('#tableCareer').load(`${url} #table-career`, function(response, status){
		loadFunction();
	});
}

function loadFunction(){
    var table = $('#table-content').DataTable({
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
	 
//	    $('#save-btn').click( function() {
//	        var data = table.$('input').serialize();
//			var url = "/SIA/GradeRegister?option=update&" + data;
//			console.log(url);
//			jQuery('#master').load(`${url} #slave`, function(data, status){
//				if (status == 'success') {
//					loadFunction();
//				}
//			});
//	        return false;
//	    } );
//	} );
}

