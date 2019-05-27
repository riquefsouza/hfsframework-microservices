class ListAutUser extends HFSSystemUtil {
	constructor()
	{
		super();
		
		//this._url = window.location.href;
		//this._urlAuthServer = $("meta[name='URL-AUTH-SERVER']").attr("content");
		//this._authToken = $("meta[name='X-AUTH-TOKEN']").attr("content");
		//this._anchorHomePage = $('#anchorHomePage');
		//this._alertInfoMessage = $('#alert-info-message');
		//this._alertErrorMessage = $('#alert-error-message');
		
		this._urlApiServer = this._urlAuthServer + "/api/v1/user";
		
		this._cmbReportType = $('#cmbReportType');
		this._forceDownload = $('#forceDownload');
		this._tableList = $('#tableAutUser');
		this._dlgDeleteConfirmation = $('#dlgDeleteConfirmation');
		
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
    		this.buildDialogDelete(this._urlApiServer, this._authToken, this._tableList, this._dlgDeleteConfirmation);	        
		})
		.fail(function(xhr, textStatus, msg){
			alert("An error occured on ListAutUser: " + xhr.status + " " + xhr.statusText);
			/*
	    	this._alertMessages.show();
	    	setTimeout(function() {
	    		//this._alertMessages.toggle();
			}, 1500);
			*/
	    })
	    .always(function(){
	    	//$('#spinner').toggle();
	    });
		
	}
	
	btnExportClick(event) {
		event.preventDefault();
		
		var sUrl = this._url.replace("/list", "/export");
		//sUrl += "/" + this._cmbReportType.val() + "/" + this._forceDownload[0].checked;
		sUrl += "?reportType=" + this._cmbReportType.val() + "&forceDownload=" + this._forceDownload[0].checked + "&params=1,2,3";
		
		//window.location.href=sUrl;
		window.open(sUrl,'_blank');
		//console.log(sUrl);
	}
	
	btnAddClick(event) {
		event.preventDefault();
		
		HFSSystemUtil.persistItem("saveMethod", "POST");
		HFSSystemUtil.persistItem("urlApiServer", this._urlApiServer);
		window.location.href=this._url.replace("/list", "/add");
	}
	
	btnEditClick(event) {
		event.preventDefault();
		this._alertMessages.hide();
		
		var dataRowSelected = this._tableList.puidatatable('getSelection');		
		
		if (dataRowSelected.length > 0) {			
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
				HFSSystemUtil.persistItem("saveMethod", "PUT");
	        	HFSSystemUtil.persistItem("urlApiServer", this._urlApiServer + "/" + dataRowSelected[0].id);
				window.location.href=this._url.replace("/list", "/edit");
			})
			.fail(function() {
		    	this._alertMessages.show();
		    	setTimeout(function() {
		    		this._alertMessages.toggle();
				}, 1500);
	        });
		} else {
			this._alertMessages.show();
		}
	}
	
	buildDialogDelete(urlApiServer, authToken, tableList, dlgDeleteConfirmation){
		this._dlgDeleteConfirmation.puidialog({
		    minimizable: false,
		    maximizable: false,
		    responsive: true,
		    minWidth: 200,
		    modal: true,
		    buttons: [{
		            text: 'Yes',
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
		        				//alert(data);
		        				tableList.puidatatable('reload');
		        				dlgDeleteConfirmation.puidialog('hide');
			            	})
			    			.fail(function(xhr){
	        		            alert("An error occured DELETE: " + xhr.status + " " + xhr.statusText);
	        		        });

		            	}
		            	
		            }
		        },
		        {
		            text: 'No',
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
		this._alertMessages.hide();
		
		var dataRowSelected = this._tableList.puidatatable('getSelection');
		
		if (dataRowSelected.length > 0) {
			this._dlgDeleteConfirmation.puidialog('show');
		} else {
			this._alertMessages.show();
		}
	}

	btnBackClick(event) {
		event.preventDefault();
		this._anchorHomePage[0].click();
	}
	
	buildTable(urlApiServer, authToken, responsePage) {
		this._tableList.puidatatable({
			caption: 'Users',
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
		            alert("An error occured on buildTable: " + xhr.status + " " + xhr.statusText);
			    });
			},	           
			rowSelect: function(event, data) {
				//console.log(data);
			}
		});
		
	}
	
}

$(function() {
	const listAutUser = new ListAutUser();
	
	$('#btnExport').click(listAutUser.btnExportClick.bind(listAutUser));
	$('#btnAdd').click(listAutUser.btnAddClick.bind(listAutUser));
	$('#btnEdit').click(listAutUser.btnEditClick.bind(listAutUser));
	$('#btnDelete').click(listAutUser.btnDeleteClick.bind(listAutUser));
	$('#btnBack').click(listAutUser.btnBackClick.bind(listAutUser));
	
	
});
