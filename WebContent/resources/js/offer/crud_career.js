function readCareer(careerId){
	var url = '/SIA/CareerController?option=read&careerId=' + careerId
	jQuery('#careerForm').load(`${url} #career-create-update`);
}

function deleteCareer(id){
	var url = '/SIA/CareerController?option=delete&id=' + id
	jQuery('#tableCareer').load(`${url} #table-career`, function(response, status){
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

