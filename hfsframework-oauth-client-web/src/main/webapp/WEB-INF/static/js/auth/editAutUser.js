class EditAutUser extends HFSSystemUtil {
	constructor()
	{
		super();
		
		this._urlApiServer = this.getPersistedItem("urlApiServer");
		this._saveMethod = this.getPersistedItem("saveMethod");
		
		this._autUserId = $('#autUser_id');
		this._autUserName = $('#autUser_username');
		this._autUserEmail = $('#autUser_email');
		
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
					this.setFields(data);
		        }
			}).fail(function(xhr, textStatus, msg){
				this.dangerShow("An error occured on Edit: " + xhr.status + " " + xhr.statusText);
		    });
		}

	}
	
	setFields(obj){
		this._autUserId.val(obj.id);
		this._autUserName.val(obj.name);
		this._autUserEmail.val(obj.email);
	}

	getFields(){
		var sId = this._autUserId.val();
		var nId = sId.length == 0 ? null : parseInt(sId);
		
		var obj = {
			id: nId,
			name: this._autUserName.val(),
			email: this._autUserEmail.val()
		}
		
		return JSON.stringify(obj);
	}

	btnCancelClick(event) {
		event.preventDefault();
		
		if (this._saveMethod==="PUT")
			window.location.href=this._url.replace("/edit", "/list");
		else
			window.location.href=this._url.replace("/add", "/list");
	}
	
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
    			window.location.href=this._url.replace("/edit", "/list");
    		else
    			window.location.href=this._url.replace("/add", "/list");
		})
		.fail(function(xhr, textStatus, msg){
			this.dangerShow("An error occured on save: " + xhr.status + " " + xhr.statusText);
	    });

	}
	
}

$(function() {
	const editAutUser = new EditAutUser();
	
	$('#btnSave').click(editAutUser.btnSaveClick.bind(editAutUser));
	$('#btnCancel').click(editAutUser.btnCancelClick.bind(editAutUser));
	
});
