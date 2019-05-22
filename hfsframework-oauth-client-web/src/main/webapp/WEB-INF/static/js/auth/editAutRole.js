$('#btnCancel').click(function(event) {
	event.preventDefault();
	
	window.location.href='/private/roleView/list';	
});

$('#btnSave').click(function(event) {
	event.preventDefault();

	$.post(responsePage, getFields(), function(data, status) {
		window.location.href='list';
	})
	.fail(function(xhr, textStatus, msg){
		alert("An error occured: " + xhr.status + " " + xhr.statusText);
    });
});

function setFields(obj){
	$('#autRole_id').val(obj.id);
	$('#autRole_name').val(obj.name);
}

function getFields(){
	var sId = $('#autRole_id').val();
	var nId = sId.length == 0 ? null : parseInt(sId);
	
	var obj = {
		"id" : nId,
		"name" : $('#autRole_name').val()
	};
	return obj;
}

$(function() {  	
	var responsePage = getPersistedItem("responsePage");
	
	if (responsePage!=null){
		$.get(responsePage, function(data, status) {
			setFields(data);
			removePersistedItem("responsePage");
		})
		.fail(function(xhr, textStatus, msg){
			alert("An error occured: " + xhr.status + " " + xhr.statusText);
	    });
	}
 	 	
});
