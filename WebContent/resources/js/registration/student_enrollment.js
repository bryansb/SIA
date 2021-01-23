/**
 * 
 */
function createEnrollment() {
	
}

/**
$(document).ready(function() {
    var table = $('#subject-list').DataTable();
 
    $('#subject-list tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );
 
    $('#button').click( function () {
        alert( table.rows('.selected').data()[0] +' row(s) selected' );
    } );
} );
**/

function subjectSelected() {
	var subjectId = $('input[name="subjectId"]:checked');
	var url = "/SIA/EnrollmentController?option=enrollment" + "&level=1&" +
		$('input[name="subjectId"]:checked').serialize();
	//console.log(url);
	//$.get(url, function(data, status){
		//alert("Data: " + data + "\nStatus: " + status);
	//});
	
	jQuery('#master').load(`${url} #slave`);
}

function previousLevel(level) {
	var url = "/SIA/EnrollmentController?option=enrollment&level=" + (level - 1);
	jQuery('#master').load(`${url} #slave`);
}
