class ListAutRole extends HFSSystemUtil {
	constructor()
	{
		super();
		
		this.hideQueryString();
		
		this._urlApiServer = this._urlAuthServer + "/api/v1/role";
		
		this._cmbReportType = $('#cmbReportType');
		this._forceDownload = $('#forceDownload');
		this._tableList = $('#tableAutRole');
		this._dlgDeleteConfirmation = $('#dlgDeleteConfirmation');
		
		this._formListAutRole = $('#formListAutRole');
		
		$.get({
			url: this._urlApiServer + "/pages",
			dataType: "json",
		    contentType: "application/json; charset=utf-8",								
	        context: this,
	        beforeSend: function (xhr) {
	            xhr.setRequestHeader("Authorization", "Bearer " + this._authToken);
	        }
		})
		.done(function(data) {
    		this.buildTable(this._urlApiServer + "/pages", this._authToken, data);
    		this.buildDialogDelete(this._urlApiServer, this._authToken, this._tableList, 
    				this._dlgDeleteConfirmation, this._messageButtonYes, this._messageButtonNo);	        
		})
		.fail(function(xhr, textStatus, msg){
			this.dangerShow("An error occured on List: " + xhr.status + " " + xhr.statusText);
	    	/*
	    	setTimeout(function() {
	    		//this.dangerHide();
			}, 1500);
			*/
	    })
	    .always(function(){
	    	//$('#spinner').toggle();
	    });
		
	}
	
	btnExportClick(event) {
		event.preventDefault();
		
		var sUrl = this._url + "/export";
		sUrl += "?reportType=" + this._cmbReportType.val() + 
				"&forceDownload=" + this._forceDownload[0].checked + 
				"&params=1,2,3";
		
		window.open(sUrl,'_blank');
	}
	
	btnAddClick(event) {
		event.preventDefault();
		
		this.persistItem("saveMethod", "POST");
		//this.persistItem("urlApiServer", this._urlApiServer);
		
		window.location.href=this._url.replace("View", "View/add");
	}
	
	btnEditClick(event) {
		event.preventDefault();		
		this.dangerHide();
		
		var dataRowSelected = this._tableList.puidatatable('getSelection');		
		
		if (dataRowSelected.length > 0) {
			this.persistItem("saveMethod", "PUT");
			//this.persistItem("urlApiServer", this._urlApiServer + "/" + dataRowSelected[0].id);
			this._formListAutRole.submit();	
			
			/*
			$.get({
				url: this._urlApiServer + "/" + dataRowSelected[0].id,
				dataType: "json",
			    contentType: "application/json; charset=utf-8",								
		        context: this,
		        beforeSend: function (xhr) {
		            xhr.setRequestHeader("Authorization", "Bearer " + this._authToken);
		        }
			})
			.done(function(responsePage) {
				this.persistItem("saveMethod", "PUT");
				this.persistItem("urlApiServer", this._urlApiServer + "/" + dataRowSelected[0].id);
				window.location.href=this._url.replace("View", "View/edit");
			})
			.fail(function() {
				this.dangerShow(this._messageSelectTable);
	        });
	        */
		} else {
			this.dangerShow(this._messageSelectTable);
		}
	}
	
	buildDialogDelete(urlApiServer, authToken, tableList, 
			dlgDeleteConfirmation, messageButtonYes, messageButtonNo){
		this._dlgDeleteConfirmation.puidialog({
		    minimizable: false,
		    maximizable: false,
		    resizable: false,
		    responsive: true,
		    minWidth: 200,
		    modal: true,
		    buttons: [{
		            text: messageButtonYes,
		            icon: 'fa-check',
		            click: function() {
		            	var dataRowSelected = tableList.puidatatable('getSelection');
		            	
		            	if (dataRowSelected.length > 0) {
		        			$.ajax({
		        				method: "DELETE",
		        				url: urlApiServer + "/" + dataRowSelected[0].id,
		        				dataType: "json",
		        			    contentType: "application/json; charset=utf-8",								
		        		        context: this,
		        		        beforeSend: function (xhr) {
		        		            xhr.setRequestHeader("Authorization", "Bearer " + authToken);
		        		        }
		        			})
		        			.done(function(data) {
		        				$('#autRole_jsonText').val("");
		        				
		        				tableList.puidatatable('reload');
		        				dlgDeleteConfirmation.puidialog('hide');
			            	})
			    			.fail(function(xhr){
			    				this.dangerShow("An error occured DELETE: " + xhr.status + " " + xhr.statusText);
	        		        });

		            	}
		            	
		            }
		        },
		        {
		            text: messageButtonNo,
		            icon: 'fa-close',
		            click: function() {
		                dlgDeleteConfirmation.puidialog('hide');
		            }
		        }
		    ]
		});	
	}

	btnDeleteClick(event) {
		event.preventDefault();
		this.dangerHide();
		
		var dataRowSelected = this._tableList.puidatatable('getSelection');
		
		if (dataRowSelected.length > 0) {
			this._dlgDeleteConfirmation.puidialog('show');
		} else {
			this.dangerShow(this._messageSelectTable);
		}
	}

	btnBackClick(event) {
		event.preventDefault();
		this._anchorHomePage[0].click();
	}
	
	buildTable(urlApiServer, authToken, responsePage) {
		this._tableList.puidatatable({
			caption: 'Roles',
			lazy: true,
			responsive: true,	           
			selectionMode: 'single',
			paginator: {
				rows: responsePage.size,
				totalRecords: (responsePage.totalElements-responsePage.size)
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
				//if (ui.filters){
				  // if (ui.filters.length > 0){
				   	//	console.log(ui.filters[0].field);
				   		//console.log(ui.filters[0].value);
				   //}	        	   		
				//}					           
				
				ui.sortField ? fieldSort = ui.sortField : fieldSort = 'id'; 	            
				ui.sortOrder == 1 ? ascDesc='asc' : ascDesc='desc';
				
				if (ui.first==0)
					uri = urlApiServer+'?page=0&size='+responsePage.size+'&sort='+fieldSort+','+ascDesc;
				else
					uri = urlApiServer+'?page='+((ui.first/ui.rows)+1)+'&size='+responsePage.size+'&sort='+fieldSort+','+ascDesc;
				
				$.ajax({
					method: "GET",
					url: uri,
					dataType: "json",
				    contentType: "application/json; charset=utf-8",								
			        context: this,
			        beforeSend: function (xhr) {
			            xhr.setRequestHeader("Authorization", "Bearer " + authToken);
			        }
				}).done(function(data) {
		        	callback.call(this, data.content);
				}).fail(function(xhr){
					this.dangerShow("An error occured on buildTable: " + xhr.status + " " + xhr.statusText);
			    });
			},	           
			rowSelect: function(event, data) {
				$('#autRole_jsonText').val(JSON.stringify(data));
			}
		});
		
	}
	
}

$(function() {
	const listAutRole = new ListAutRole();
	
	$('#btnExport').click(listAutRole.btnExportClick.bind(listAutRole));
	$('#btnAdd').click(listAutRole.btnAddClick.bind(listAutRole));
	$('#btnEdit').click(listAutRole.btnEditClick.bind(listAutRole));
	$('#btnDelete').click(listAutRole.btnDeleteClick.bind(listAutRole));
	$('#btnBack').click(listAutRole.btnBackClick.bind(listAutRole));
	
	
});
