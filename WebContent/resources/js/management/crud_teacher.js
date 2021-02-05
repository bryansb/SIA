function readTeacher(teacherId){
	var url = '/SIA/TeacherController?option=read&teacherId=' + teacherId
	jQuery('#teacherForm').load(`${url} #teacher-create-update`);
}

function deleteTeacher(id){
	var url = '/SIA/TeacherController?option=delete&teacherId=' + id
	jQuery('#tableTeacher').load(`${url} #table-teacher`, function(response, status){
		loadFunction();
	});
}

function loadFunction(){
    $('#table-content-teacher').DataTable({
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

function addDegree(teacherId){
	var url = '/SIA/TeacherController?option=selectDegree&teacherId=' + teacherId;
	document.location.href = url;
}

function deleteDegree(teacherId, degreeId){
	var url = '/SIA/TeacherController?option=deleteDegree&teacherId=' + teacherId +'&degreeId=' + degreeId
	jQuery('#tableDegree').load(`${url} #table-degree`, function(response, status){
		if (status == "success") {
			loadFunctionDegree();
		}
	});
}

function loadFunctionDegree(){
    $('#table-content-degree').DataTable({
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