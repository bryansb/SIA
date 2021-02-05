/**
 * 
 */
function loging(){
    var form = $("#logingForm");
	$.post("/SIA/Login", form.serialize(), function(res, est){
		if (est == 'success') {
			var msg = res.split("&", 2);
			if(msg[1] == "e_notice_sucess"){
				location.href = "/SIA/home";
			}else {
				showNotice(msg[0], msg[1]);
			}
		} else {
			showNotice("Error interno en el servidor", 
					"e_notice_error");
		}
	});

}

function hideNotice(){
	document.getElementById("main_notice").classList = '';
	document.getElementById("main_notice").classList.add("col-10");
    document.getElementById("main_notice").classList.add("e_hidden");
}

function showNotice(notice, notice_type){
	document.getElementById("main_notice").classList.remove("e_hidden");
    document.getElementById("main_notice").classList.add(notice_type);
    document.getElementById("notice").innerHTML =  notice;
}