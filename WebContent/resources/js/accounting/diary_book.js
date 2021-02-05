/**
 * 
 */

function loadDataTable(){
	var table = $('#amount-list').DataTable({
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
}

function getDiaryBook() {
	var accountId = $('#accountId').val();
	var start = $('#start').val();
	var end = $('#end').val();
	var url = "/SIA/DiaryBook?start=" + start + "&end=" + end + "&accountId=" + accountId;
	jQuery('#master').load(`${url} #slave`);
}