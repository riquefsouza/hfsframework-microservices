class ListVwAdmLogValue extends HFSSystemUtil {
	constructor()
	{
		super();
		
		this.hideQueryString();
		
		this._urlApiServer = this._urlAuthServer + "/api/v1/vwAdmLogValue";
		
		this._cmbReportType = $('#cmbReportType');
		this._forceDownload = $('#forceDownload');
		this._tableList = $('#tableVwAdmLogValue');
		this._dlgDeleteConfirmation = $('#dlgDeleteConfirmation');
		this._formTitle = $('#formTitle');
		this._formListVwAdmLogValue = $('#formListVwAdmLogValue');
		
		this.buildGetPages(this._urlApiServer, this._authToken, this._formTitle, this._tableList, 
				this._dlgDeleteConfirmation, this._messageButtonYes, this._messageButtonNo, 
				$('#vwAdmLogValue_jsonText'),
				[
					{field: 'name', headerText: 'Name', sortable: true, filter: false}
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
		window.location.href=this._url.replace("View", "View/add");
	}
	
	btnEditClick(event) {
		event.preventDefault();		
		this.dangerHide();
		
		var dataRowSelected = this._tableList.puidatatable('getSelection');		
		
		if (dataRowSelected.length > 0) {
			this.persistItem("saveMethod", "PUT");
			this._formListVwAdmLogValue.submit();	
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
	const listVwAdmLogValue = new ListVwAdmLogValue();
	
	$('#btnExport').click(listVwAdmLogValue.btnExportClick.bind(listVwAdmLogValue));
	$('#btnAdd').click(listVwAdmLogValue.btnAddClick.bind(listVwAdmLogValue));
	$('#btnEdit').click(listVwAdmLogValue.btnEditClick.bind(listVwAdmLogValue));
	$('#btnDelete').click(listVwAdmLogValue.btnDeleteClick.bind(listVwAdmLogValue));
	$('#btnBack').click(listVwAdmLogValue.btnBackClick.bind(listVwAdmLogValue));
	
	
});