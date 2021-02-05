function createTeacher(f_id){
	var form = $("#" + f_id);
	if(valid(form)){
		$.post("/SIA/StudentController", form.serialize(), function(res, est, jqXHR){
			var msg = res.split("&", 2);
			showNotice(msg[0], msg[1]);
			jQuery('#table_product').load('/sgrc/ListProduct #table_content');
		});	
	} else {
		showNotice("Complete los campos resaltados en rojo", "e_notice_warning")
	}
}

function readStudent(studentId){
	var url = '/SIA/StudentController?option=read&studentId=' + studentId
	jQuery('#studentForm').load(`${url} #student-create-update`, function(response, status){
	});
}

function deleteStudent(id){
	var url = '/SIA/StudentController?option=delete&studentId=' + id
	jQuery('#tableStudent').load(`${url} #table-student`, function(response, status){
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
    
}