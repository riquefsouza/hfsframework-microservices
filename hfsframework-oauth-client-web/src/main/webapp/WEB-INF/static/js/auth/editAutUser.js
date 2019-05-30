class EditAutUser extends HFSSystemUtil {
	constructor()
	{
		super();
		
		this.hideQueryString();
		
		this._saveMethod = this.getPersistedItem("saveMethod");

		this._autUserPickListRoles = $('#autUser_pickListRoles');
		this._editAutUserMessageSourceCaptionRoles = $('#editAutUser-message-sourceCaptionRoles').text();
		this._editAutUserMessageTargetCaptionRoles = $('#editAutUser-message-targetCaptionRoles').text();

		this.buildPickListRoles(this._editAutUserMessageSourceCaptionRoles, 
				this._editAutUserMessageTargetCaptionRoles);

		/*
		this._urlApiServer = this.getPersistedItem("urlApiServer");
		
		
		this._autUserId = $('#autUser_id');
		this._autUserName = $('#autUser_username');
		this._autUserPassword = $('#autUser_password');
		this._autUserEmail = $('#autUser_email');
		this._autUserUrlPhoto = $('#autUser_urlPhoto');
		this._autUserCurrentPassword = $('#autUser_currentPassword');
		this._autUserNewPassword = $('#autUser_newPassword');
		this._autUserConfirmNewPassword = $('#autUser_confirmNewPassword');
		
		this._autUserSourceRoles = $('#autUser_sourceRoles');
		
		
		if (this._saveMethod==="PUT"){
			$.get({
				url: this._urlApiServer,
				dataType: "json",
			    contentType: "application/json; charset=utf-8",								
		        context: this,
		        beforeSend: function (xhr) {
		            xhr.setRequestHeader("Authorization", "Bearer " + this._authToken);
		        },
		        success: function(data, status) {
		        	
		        	console.log(data);
		        	
					this.setFields(data);
		        }
			}).fail(function(xhr, textStatus, msg){
				this.dangerShow("An error occured on Edit: " + xhr.status + " " + xhr.statusText);
		    });
		} else {
			this.buildPickListRoles(this._editAutUserMessageSourceCaptionRoles, 
					this._editAutUserMessageTargetCaptionRoles);
		}
		 */
	}
	
	buildPickListRoles(messageSourceCaptionRoles, messageTargetCaptionRoles){
		this._autUserPickListRoles.puipicklist({
	        showSourceControls: false,
	        showTargetControls: false,
	        sourceCaption: messageSourceCaptionRoles,
	        targetCaption: messageTargetCaptionRoles,
	        filter: true,
	        filterMatchMode: "contains",
	        responsive: true
	        //sourceData: data
	    });
		
	}
	
	/*
	setFields(obj){
		this._autUserId.val(obj.id);
		this._autUserName.val(obj.name);
		this._autUserPassword.val(obj.password);
		this._autUserEmail.val(obj.email);
		this._autUserUrlPhoto.val(obj.urlPhoto);
		this._autUserCurrentPassword.val(obj.currentPassword);
		this._autUserNewPassword.val(obj.newPassword); 
		this._autUserConfirmNewPassword.val(obj.confirmNewPassword);
		
		
		//obj.roles.forEach(function(role){
			//console.log("role.id: " + role.id);
			//console.log("role.name: " + role.name);
		//});
		
	}


	getFields(){
		var sId = this._autUserId.val();
		var nId = sId.length == 0 ? null : parseInt(sId);
		
		var obj = {
			id: nId,
			username: this._autUserName.val(),
			password: this._autUserPassword.val(),
			email: this._autUserEmail.val(),
			urlPhoto: this._autUserUrlPhoto.val()
		}
		
		return JSON.stringify(obj);
	}
	 */
	
	btnCancelClick(event) {
		event.preventDefault();
		
		if (this._saveMethod==="PUT")
			window.location.href=this._url.replace("View/edit", "View");
		else
			window.location.href=this._url.replace("View/add", "View");
	}
	
	/*
	btnSaveClick(event) {
		event.preventDefault();
		
		$.ajax({
			method: this._saveMethod,
			url: this._urlApiServer,
			data: this.getFields(),
			dataType: "json",
		    contentType: "application/json; charset=utf-8",								
	        context: this,
	        beforeSend: function (xhr) {
	            xhr.setRequestHeader("Authorization", "Bearer " + this._authToken);
	        }
		})
		.done(function(data, status) {
			this.removePersistedItem("saveMethod");
			this.removePersistedItem("urlApiServer");
			if (this._saveMethod==="PUT")
				window.location.href=this._url.replace("View", "View/edit");
			else
				window.location.href=this._url.replace("View", "View/add");
		})
		.fail(function(xhr, textStatus, msg){
			this.dangerShow("An error occured on save: " + xhr.status + " " + xhr.statusText);
	    });
		 
	}
	*/
}

$(function() {
	const editAutUser = new EditAutUser();
	
	//$('#btnSave').click(editAutUser.btnSaveClick.bind(editAutUser));
	$('#btnCancel').click(editAutUser.btnCancelClick.bind(editAutUser));
	
});
