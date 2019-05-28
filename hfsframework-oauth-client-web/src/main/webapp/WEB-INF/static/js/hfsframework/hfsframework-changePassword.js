class HFSChangePassword extends HFSSystemUtil {
	constructor()
	{
		super();
		
		this._anchorHomePage = $('#anchorHomePage');
		
		this._formChangePassword = $('#formChangePassword');
		this._dlgChangePassword = $('#dlgChangePassword');
		
		
		this.buildDialogChangePassword(this._dlgChangePassword, this._formChangePassword);
	}
	
	btnSaveClick(event) {
		event.preventDefault();
		
		this._dlgChangePassword.puidialog('show');
	}

	buildDialogChangePassword(dlgChangePassword, formChangePassword){
		this._dlgChangePassword.puidialog({
		    minimizable: false,
		    maximizable: false,
		    responsive: true,
		    minWidth: 200,
		    modal: true,
		    buttons: [{
		            text: 'Yes',
		            icon: 'fa-check',
		            click: function() {
		            	formChangePassword.submit();
		            	dlgChangePassword.puidialog('hide');
		            }
		        },
		        {
		            text: 'No',
		            icon: 'fa-close',
		            click: function() {
		            	dlgChangePassword.puidialog('hide');
		            }
		        }
		    ]
		});	
	}
	
	
	btnBackClick(event) {
		event.preventDefault();
		this._anchorHomePage[0].click();
	}

}

$(function() {
	const hfsChangePassword = new HFSChangePassword();
	
	$('#btnSave').click(hfsChangePassword.btnSaveClick.bind(hfsChangePassword));
	$('#btnBack').click(hfsChangePassword.btnBackClick.bind(hfsChangePassword));
	
});
