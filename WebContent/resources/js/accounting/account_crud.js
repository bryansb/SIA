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

function createAccount() {
	var form = $('#account-form').serialize()
	var url = "/SIA/AccountController?" + form;
	console.log(url);
	jQuery('#master').load(`${url} #slave`);
}

function editAccount(id){
	var url = "/SIA/AccountController?option=read&accountId=" + id;
	jQuery('#master-form').load(`${url} #slave-form`);
}