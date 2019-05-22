$('#btnExport').click(function(event) {
	event.preventDefault();
	
	var sUrl = 'exportar/'+$('#cmbTipoRelatorio').val()+'/'+$('#forcaDownload')[0].checked;
	window.open(sUrl, '_blank');	
});

$('#btnAdd').click(function(event) {
	event.preventDefault();
	
	persistItem("responsePage", sURL);
	window.location.href='add';
});	
	
$('#btnEdit').click(function(event) {
	event.preventDefault();
	$('#alert-messages').hide();
	
	var dataRowSelected = $('#tableAutRole').puidatatable('getSelection');
	
	if (dataRowSelected.length > 0) {
		$.get(dataRowSelected[0]._links.self.href, function(responsePage) {			
			persistItem("responsePage", responsePage._links.self.href);
			window.location.href='edit';
		}).fail(function() {
	    	$('#alert-messages').show();
	    	setTimeout(function() {
	    		$('#alert-messages').toggle();
			}, 1500);
        });
	} else {
		$('#alert-messages').show();
	}
		
});	

function buildDialogExcluir(){
	$('#dlgDeleteConfirmation').puidialog({
	    minimizable: false,
	    maximizable: false,
	    responsive: true,
	    minWidth: 200,
	    modal: true,
	    buttons: [{
	            text: 'Yes',
	            icon: 'fa-check',
	            click: function() {
	            	var dataRowSelected = $('#tableAutRole').puidatatable('getSelection');
	            	
	            	if (dataRowSelected.length > 0) {
	        			$.ajax({
	        				type: "DELETE",
	        				url: dataRowSelected[0]._links.self.href,
	        				dataType: "json",
	        		        context: this,
	        		        success: function(data) {
	        		        	alert(data);	
	        		        },
	        		        error: function(xhr){
	        		            alert("An error occured: " + xhr.status + " " + xhr.statusText);
	        		        }			
	        		    });

	            	}
	            	
	                $('#dlgDeleteConfirmation').puidialog('hide');
	            }
	        },
	        {
	            text: 'No',
	            icon: 'fa-close',
	            click: function() {
	                $('#dlgDeleteConfirmation').puidialog('hide');
	            }
	        }
	    ]
	});	
}

$('#btn-show').puibutton({
    icon: 'fa-external-link-square',
    click: function() {
        $('#dlg').puidialog('show');
    }
});


$('#btnExcluir').click(function(event) {
	event.preventDefault();
	$('#alert-messages').hide();
	
	var dataRowSelected = $('#tableAutRole').puidatatable('getSelection');
	
	if (dataRowSelected.length > 0) {
		$('#dlgDeleteConfirmation').puidialog('show');
	} else {
		$('#alert-messages').show();
	}
	
});

$('#btnVoltar').click(function(event) {
	event.preventDefault();
	
	window.location.href='/';
});	


function buildTable(sURL, responsePage) {
	$('#tableAutRole').puidatatable({
		caption: 'Roles',
		lazy: true,
		responsive: true,	           
		selectionMode: 'single',
		paginator: {
			rows: responsePage.page.size,
			totalRecords: (responsePage.page.totalElements-responsePage.page.size)
		},
		columns: [
			{field: 'name', headerText: 'Name', sortable: true, filter: false}
		],
		datasource: function(callback, ui) {
			//ui.first = Index of the first record
			//ui.rows = Number of rows to load
			//ui.sortField = Field name of the sorted column
			//ui.sortOrder = Sort order of the sorted column
			//ui.sortMeta = Array of field names and sort orders of the sorted columns 
			//ui.filters = Filtering information with field-value pairs like ui.filters['fieldname'] = 'filtervalue'
			var uri = '';
			var ascDesc = 'asc';
			var fieldSort = 'id';
			
			//console.log(ui);
			/*
			if (ui.filters){
			   if (ui.filters.length > 0){
			   		console.log(ui.filters[0].field);
			   		console.log(ui.filters[0].value);
			   }	        	   		
			}
			*/	           
			
			ui.sortField ? fieldSort = ui.sortField : fieldSort = 'id'; 	            
			ui.sortOrder == 1 ? ascDesc='asc' : ascDesc='desc';
			
			if (ui.first==0)
				uri = sURL+'?page=0&size='+responsePage.page.size+'&sort='+fieldSort+','+ascDesc;
			else
				uri = sURL+'?page='+((ui.first/ui.rows)+1)+'&size='+responsePage.page.size+'&sort='+fieldSort+','+ascDesc;
			
			console.log(uri);
		
			$.ajax({
				type: "GET",
				url: uri,
				dataType: "json",
		        context: this,
		        success: function(data) {
		        	callback.call(this, data._embedded.admParametroCategorias);
		        },
		        error: function(xhr){
		            alert("An error occured: " + xhr.status + " " + xhr.statusText);
		        }			
		    });
		},	           
		rowSelect: function(event, data) {
			//console.log(data);
		}
	});
	
}

$(function() {
//var sURL = sURL_BACKEND + "/private/roleView";	
	
	//$('#spinner').toggle();
	
	$.get(sURL, function(data) {		
		buildTable(sURL, data);
		buildDialogExcluir();
	}).fail(function(xhr, textStatus, msg){
		alert("An error occured: " + xhr.status + " " + xhr.statusText);
		/*
    	$('#alert-messages').show();
    	setTimeout(function() {
    		//$('#alert-messages').toggle();
		}, 1500);
		*/
    }).always(function(){
    	//$('#spinner').toggle();
    });
 	 	
});
