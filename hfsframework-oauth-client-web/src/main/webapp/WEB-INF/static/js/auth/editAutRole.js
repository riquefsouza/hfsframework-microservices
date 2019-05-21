$('#btnCancelar').click(function(event) {
	event.preventDefault();
	
	window.location.href='/admParametroCategoriaMB/listar';	
});

$('#btnSalvar').click(function(event) {
	event.preventDefault();

	$.post(responsePage, getFields(), function(data, status) {
		window.location.href='listar';
	})
	.fail(function(xhr, textStatus, msg){
		alert("An error occured: " + xhr.status + " " + xhr.statusText);
    });
});

function setFields(obj){
	$('#admParametroCategoria_id').val(obj.id);
	$('#admParametroCategoria_descricao').val(obj.descricao);
	$('#admParametroCategoria_ordem').val(obj.ordem);	
}

function getFields(){
	var sId = $('#admParametroCategoria_id').val();
	var nId = sId.length == 0 ? null : parseInt(sId);
	
	var obj = {
		"id" : nId,
		"descricao" : $('#admParametroCategoria_descricao').val(),
		"ordem" : 0
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
