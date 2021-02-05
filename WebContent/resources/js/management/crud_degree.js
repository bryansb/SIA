function readDegree(degreeId) {
	var url = '/SIA/DegreeController?option=read&degreeId=' + degreeId
	jQuery('#degreeForm').load(`${url} #degree-create-update`);
}

function deleteDegree(id){
	var url = '/SIA/DegreeController?option=delete&degreeId=' + id
	jQuery('#tableDegree').load(`${url} #table-degree`, function(response, status){
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