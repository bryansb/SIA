function readGroup(groupId){
	var url = '/SIA/GroupController?option=read&groupId=' + groupId
	jQuery('#groupForm').load(`${url} #group-create-update`);
}

function deleteGroup(id){
	var url = '/SIA/GroupController?option=delete&id=' + id
	jQuery('#tableGroup').load(`${url} #table-group`, function(response, status){
		loadFunction();
	});
}

function loadFunction(){
    var table = $('#table-content-group').DataTable({
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

function addSchedule(groupId){
	var url = '/SIA/ScheduleController?groupId=' + groupId;
	jQuery('#groupForm').load(`${url} #scheduleForm`);
	
//	$.post("/SIA/ScheduleController?groupId=" + groupId);
//	jQuery('#groupForm').load('/SIA/ScheduleController #scheduleForm');
}

function readSchedule(scheduleId, groupId){
	var url = '/SIA/ScheduleController?option=read&groupId=' + groupId +'&scheduleId=' + scheduleId
	jQuery('#scheduleForm').load(`${url} #schedule-create-update`);
}

function deleteSchedule(scheduleId, groupId){
	var url = '/SIA/ScheduleController?option=delete&groupId=' + groupId +'&scheduleId=' + scheduleId
	jQuery('#tableSchedule').load(`${url} #table-schedule`, function(response, status){
		loadFunction();
		loadFunctionSchedule();
	});
}

function loadFunctionSchedule(){
    var table = $('#table-content-schedule').DataTable({
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


