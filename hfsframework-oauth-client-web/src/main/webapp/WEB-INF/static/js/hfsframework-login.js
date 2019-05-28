class HFSLogin extends HFSSystemUtil {
	constructor()
	{		
		super();
		
		this._anchorForgotPassword = $('#anchorForgotPassword');
		
		this._dlgForgotPassword = $('#dlgForgotPassword');
		this._dlgBecomeMember = $('#dlgBecomeMember');
		
		this.buildDlgForgotPassword(this._dlgForgotPassword);
		this.buildDlgBecomeMember(this._dlgBecomeMember);
	}
		
	anchorForgotPasswordClick(event) {
		event.preventDefault();
		
		this._dlgForgotPassword.puidialog('show');
	}

	dlgForgotPasswordBtnSendClick(event) {
		event.preventDefault();
		
		this._dlgForgotPassword.puidialog('hide');
	}

	buildDlgForgotPassword(dlgForgotPassword){
		this._dlgForgotPassword.puidialog({
		    minimizable: false,
		    maximizable: false,
		    resizable: false,
		    responsive: true,
		    minWidth: 200,
		    modal: true
		});			
	}
		
	btnBecomeMemberClick(event) {
		event.preventDefault();
		
		this._dlgBecomeMember.puidialog('show');
	}
	
	buildDlgBecomeMember(dlgBecomeMember){
		this._dlgBecomeMember.puidialog({
		    minimizable: false,
		    maximizable: false,
		    resizable: false,
		    responsive: true,
		    width: 400,
		    modal: true
		});	
	}
	
	dlgBecomeMemberBtnBecomeMemberClick(event) {
		event.preventDefault();
		
		this._dlgBecomeMember.puidialog('hide');
	}
	
}

$(function() {
	const hfsLogin = new HFSLogin();
	
	$('#anchorForgotPassword').click(hfsLogin.anchorForgotPasswordClick.bind(hfsLogin));
	$('#btnBecomeMember').click(hfsLogin.btnBecomeMemberClick.bind(hfsLogin));
	
	$('#dlgBecomeMember_btnBecomeMember').click(hfsLogin.dlgBecomeMemberBtnBecomeMemberClick.bind(hfsLogin));
	$('#dlgForgotPassword_btnSend').click(hfsLogin.dlgForgotPasswordBtnSendClick.bind(hfsLogin));
	
});
