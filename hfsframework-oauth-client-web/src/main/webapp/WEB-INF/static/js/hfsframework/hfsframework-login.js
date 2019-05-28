class HFSLogin extends HFSSystemUtil {
	constructor()
	{		
		super();
		
		this._anchorForgotPassword = $('#anchorForgotPassword');
		
		this._dlgForgotPassword = $('#dlgForgotPassword');
		this._dlgBecomeMember = $('#dlgBecomeMember');
		
		this._btnSignUp = $('#dlgBecomeMember_btnSignUp');
		this._btnSend = $('#dlgForgotPassword_btnSend');
		
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
	
	btnSignUpClick(event) {
		event.preventDefault();
		
		var sUrl = getSystemPage() + "/public/becomeMember";
		
		$.ajax({
			method: this._saveMethod,
			url: sUrl,
			data: this.getFields(),
			dataType: "json",
		    contentType: "application/json; charset=utf-8",								
	        context: this,
	        beforeSend: function (xhr) {
	            xhr.setRequestHeader("Authorization", "Bearer " + this._authToken);
	        }
		})
		.done(function(data, status) {
			this.infoShow("Welcome new member!");
		})
		.fail(function(xhr, textStatus, msg){
			this.errorShow("An error occured on save: " + xhr.status + " " + xhr.statusText);
	    });
		
	}
	
	btnSendClick(event) {
		event.preventDefault();
		
		var sUrl = getSystemPage() + "/public/forgotPassword";
		
		$.ajax({
			method: this._saveMethod,
			url: sUrl,
			data: this.getFields(),
			dataType: "json",
		    contentType: "application/json; charset=utf-8",								
	        context: this,
	        beforeSend: function (xhr) {
	            xhr.setRequestHeader("Authorization", "Bearer " + this._authToken);
	        }
		})
		.done(function(data, status) {
			this.infoShow("Email Sent!");
		})
		.fail(function(xhr, textStatus, msg){
			this.errorShow("An error occured on save: " + xhr.status + " " + xhr.statusText);
	    });
		
	}
	
}

$(function() {
	const hfsLogin = new HFSLogin();
	
	$('#anchorForgotPassword').click(hfsLogin.anchorForgotPasswordClick.bind(hfsLogin));
	$('#btnBecomeMember').click(hfsLogin.btnBecomeMemberClick.bind(hfsLogin));
	
	$('#dlgBecomeMember_btnBecomeMember').click(hfsLogin.dlgBecomeMemberBtnBecomeMemberClick.bind(hfsLogin));
	$('#dlgForgotPassword_btnSend').click(hfsLogin.dlgForgotPasswordBtnSendClick.bind(hfsLogin));

	$('#dlgBecomeMember_btnSignUp').click(hfsLogin.btnSignUpClick.bind(hfsLogin));
	$('#dlgForgotPassword_btnSend').click(hfsLogin.btnSendClick.bind(hfsLogin));

});
