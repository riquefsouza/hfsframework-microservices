class HFSLogin extends HFSSystemUtil {
	constructor()
	{		
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
	
	buildDlgForgotPassword(dlgForgotPassword){
		this._dlgForgotPassword.puidialog({
		    minimizable: false,
		    maximizable: false,
		    responsive: true,
		    minWidth: 200,
		    modal: true,
		    buttons: [{
		            text: 'Yes',
		            icon: 'fa-check',
		            click: function() {
		            	dlgForgotPassword.puidialog('hide');
		            }
		        },
		        {
		            text: 'No',
		            icon: 'fa-close',
		            click: function() {
		            	dlgForgotPassword.puidialog('hide');
		            }
		        }
		    ]
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
		    responsive: true,
		    minWidth: 200,
		    modal: true,
		    buttons: [{
		            text: 'Yes',
		            icon: 'fa-check',
		            click: function() {
		            	dlgBecomeMember.puidialog('hide');
		            }
		        },
		        {
		            text: 'No',
		            icon: 'fa-close',
		            click: function() {
		            	dlgBecomeMember.puidialog('hide');
		            }
		        }
		    ]
		});	
	}

}

$(function() {
	const hfsLogin = new HFSLogin();
	
	$('#anchorForgotPassword').click(hfsLogin.anchorForgotPasswordClick.bind(hfsLogin));
	$('#btnBecomeMember').click(hfsLogin.btnBecomeMemberClick.bind(hfsLogin));
	
});
