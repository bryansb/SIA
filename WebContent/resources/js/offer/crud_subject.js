function readSubject(subjectId){
	var url = '/SIA/SubjectController?option=read&subjectId=' + subjectId
	jQuery('#subjectForm').load(`${url} #subject-create-update`, function(response, status){
//		location.reload(true);
	});
}

function updateSubject(){
	
}

function deleteSubject(id){
	var url = '/SIA/SubjectController?option=delete&id=' + id
	jQuery('#tableSubject').load(`${url} #table-subject`, function(response, status){
		loadFunction();
	});
}

function loadFunction(){
    $('#table-content').DataTable({
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

