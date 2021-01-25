function createCareer(f_id){
	var form = $("#" + f_id);
//	$.post("/SIA/CareerController")
//		var msg = res.split("&", 2);
//		showNotice(msg[0], msg[1]);
//		jQuery('#table_product').load('/sgrc/ListProduct #table_content');

	if(valid(form)){
		$.post("/SIA/CareerController", form.serialize(), function(res, est, jqXHR){
			var msg = res.split("&", 2);
			showNotice(msg[0], msg[1]);
			jQuery('#table_product').load('/sgrc/ListProduct #table_content');
		});
	} else {
		showNotice("Complete los campos resaltados en rojo", "e_notice_warning")
	}
}