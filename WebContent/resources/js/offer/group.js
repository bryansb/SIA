var l = [];

function addSchedule(){
//	var day = document.getElementById("day").value;
//	var startTime = document.getElementById("startTime").value;
//	var endTime = document.getElementById("endTime").value;
	
	$('#btn_createList').click(function(){
	    $('.ul_current').append($(l.push, {
	         text: $('#day').val()
	    }));
	    $('.ul_current').append($('<p>', {
	         text: $('#startTime').val()
	    }));
	    $('.ul_current').append($('<p>', {
	         text: $('#endTime').val()
	    }));
	});
	
	console.log(l)
}

