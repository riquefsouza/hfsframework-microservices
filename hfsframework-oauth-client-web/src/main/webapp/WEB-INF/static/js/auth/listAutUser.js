class ListAutUser extends HFSSystemUtil {
	constructor()
	{
		super();
		
		this.hideQueryString();
		
		this._urlApiServer = this._urlAuthServer + "/api/v1/user";
		
		this._cmbReportType = $('#cmbReportType');
		this._forceDownload = $('#forceDownload');
		this._tableList = $('#tableAutUser');
		this._dlgDeleteConfirmation = $('#dlgDeleteConfirmation');
		this._formTitle = $('#formTitle');
		this._formListAutUser = $('#formListAutUser');
				
		this.buildGetPages(this._urlApiServer, this._authToken, this._formTitle, this._tableList, 
				this._dlgDeleteConfirmation, this._messageButtonYes, this._messageButtonNo, 
				$('#autUser_jsonText'),
				[
					{field: 'username', headerText: 'UserName', sortable: true, filter: false},
					{field: 'email', headerText: 'Email', sortable: true, filter: false},
					{field: 'urlPhoto', headerText: 'Url Photo', sortable: true, filter: false}
				]);
		
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
			this._formListAutUser.submit();	
			
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
	
}

$(function() {
	const listAutUser = new ListAutUser();
	
	$('#btnExport').click(listAutUser.btnExportClick.bind(listAutUser));
	$('#btnAdd').click(listAutUser.btnAddClick.bind(listAutUser));
	$('#btnEdit').click(listAutUser.btnEditClick.bind(listAutUser));
	$('#btnDelete').click(listAutUser.btnDeleteClick.bind(listAutUser));
	$('#btnBack').click(listAutUser.btnBackClick.bind(listAutUser));
	
	
});
