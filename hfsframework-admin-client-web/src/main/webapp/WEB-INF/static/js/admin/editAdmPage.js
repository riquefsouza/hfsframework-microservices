class EditAdmPage extends HFSSystemUtil {
	constructor()
	{
		super();
		
		this.hideQueryString();
		
		this._saveMethod = this.getPersistedItem("saveMethod");
				
	}
	
	btnCancelClick(event) {
		event.preventDefault();
		
		if (this._saveMethod==="PUT")
			window.location.href=this._url.replace("View/edit", "View");
		else
			window.location.href=this._url.replace("View/add", "View");		
	}
	
}

$(function() {
	const editAdmPage = new EditAdmPage();
	
	$('#btnCancel').click(editAdmPage.btnCancelClick.bind(editAdmPage));
	
});
